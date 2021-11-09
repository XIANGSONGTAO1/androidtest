package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.downloadtest.FileDownloadThread;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThreadDownloadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThreadDownloadActivity.class.getSimpleName();
    ProgressBar progressBar;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_download);
        progressBar = findViewById(R.id.my_processbar);
        textView = findViewById(R.id.my_process_text);
        button = findViewById(R.id.thread_download);
        button.setOnClickListener(this);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            progressBar.setProgress(msg.getData().getInt("size"));
            float temp = (float) progressBar.getProgress() / (float) progressBar.getMax();
            int progress = (int) (temp * 100);
            if (progress == 100) {
                Toast.makeText(ThreadDownloadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }
            textView.setText("下载进度" + progress + "%");
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.thread_download:
                if (ContextCompat.checkSelfPermission(ThreadDownloadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(ThreadDownloadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    doDownload();
                } else {
                    ActivityCompat.requestPermissions(ThreadDownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doDownload();
                } else {
                    Toast.makeText(ThreadDownloadActivity.this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void doDownload() {

        String path = Environment.getExternalStorageDirectory() + "/amosdownload/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        progressBar.setProgress(0);
        String downloadUrl = "http://p.gdown.baidu.com/9cc36b4f58d99df714814c535879374aa34fb1a8f3310e2e4ddaf3d061ebe5e1e7fa89f06991ea34d786f5e3f294f4ad7e7b95bb1c84b971c08e1127303ab1310d6b4467175b54468dc1c6d15081d1700e5ac074dc2c6da02093102d7e6c0d3e8caf3958ff6901f45ba4be2de4471ad8d91e88dc498ed486cf2def67dcb7aba9be28020f04c85963df66314e844d6ddde579760173d0853b9b0b61bc0a712d96fe64eb2455c41abe235e29c2fb6dab2a1dc64ce5360f26578360ee2b09c8a993";
        String filename = "wangzherongyao.apk";
        int threadNum = 5;
        String filePath = path + filename;
        Log.d(TAG, "Download file path:" + filePath);
        DownloadTask downloadTask = new DownloadTask(downloadUrl, threadNum, filePath);
        downloadTask.start();
    }

    public class DownloadTask extends Thread {
        private String downloadUrl;
        private int threadNum;
        private String filePath;
        private int blockSize;

        public DownloadTask(String downloadUrl, int threadNum, String filePath) {
            this.downloadUrl = downloadUrl;
            this.threadNum = threadNum;
            this.filePath = filePath;
        }


        @Override
        public void run() {
            FileDownloadThread[] threads = new FileDownloadThread[threadNum];
            try {
                URL url = new URL(downloadUrl);
                Log.d(TAG, "download file http path" + downloadUrl);
                URLConnection connection = url.openConnection();
                int fileSize = connection.getContentLength();
                if (fileSize <= 0) {
                    System.out.println("读取文件失败");
                    return;
                }
                progressBar.setMax(fileSize);
                blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum : fileSize / threadNum + 1;
                Log.d(TAG, "filesize:" + fileSize + " blockSize:");
                File file = new File(filePath);
                for (int i = 0; i < threads.length; i++) {
                    threads[i] = new FileDownloadThread(url, file, blockSize, (i + 1));
                    threads[i].setName("Thread:" + i);
                    threads[i].start();
                }
                boolean isFinished = false;
                int downloadedAllSize = 0;
                while (!isFinished) {
                    isFinished = true;
                    downloadedAllSize = 0;
                    for (int i = 0; i < threads.length; i++) {
                        downloadedAllSize += threads[i].getDownloadLength();
                        if (!threads[i].isCompleted()) {
                            isFinished = false;
                        }
                    }
                    Message msg = new Message();
                    msg.getData().putInt("size", downloadedAllSize);
                    handler.sendMessage(msg);
                    Thread.sleep(1000);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}