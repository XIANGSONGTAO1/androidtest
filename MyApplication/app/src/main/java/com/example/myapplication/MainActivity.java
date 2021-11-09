package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TopView topView;
    private ImgEditText mEt_login_password;
    private boolean isHidden = false;
    private Button sendNotify;
    private WebView webView;
    private Button sendHttp;
    private Button testFragment;
    private Button testDian9;
    private Button testFragment2;
    private Button testGlide;
    private Button downloadButton;
    private Button editTextButton;
    private Button test_zidingyi;
    private Button test_toggle;
    private Button qqSlideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topView = findViewById(R.id.top_view);
        sendNotify = findViewById(R.id.sendNotify);
        webView = findViewById(R.id.web_view);
        sendHttp = findViewById(R.id.sendOkhttp);
        testFragment = findViewById(R.id.test_fragment);
        testDian9 = findViewById(R.id.dian9ceshi);
        testFragment2 = findViewById(R.id.test_fragment_2);
        testGlide = findViewById(R.id.test_glide);
        downloadButton = findViewById(R.id.thread_download);
        editTextButton = findViewById(R.id.testEditText);
        test_zidingyi = findViewById(R.id.test_zidingyi);
        test_toggle = findViewById(R.id.test_toggle);
        qqSlideButton = findViewById(R.id.test_my_qq_slide);
        qqSlideButton.setOnClickListener(this);
        test_toggle.setOnClickListener(this);
        test_zidingyi.setOnClickListener(this);
        editTextButton.setOnClickListener(this);
        downloadButton.setOnClickListener(this);
        testGlide.setOnClickListener(this);
        testFragment2.setOnClickListener(this);
        testDian9.setOnClickListener(this);
        testFragment.setOnClickListener(this);
        sendHttp.setOnClickListener(this);
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        webView.setWebViewClient(new WebViewClient() {

            /**
             * 防止加载网页时调起系统浏览器
             */
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedHttpAuthRequest(WebView webview,
                                                  HttpAuthHandler httpAuthHandlerhost, String host,
                                                  String realm) {
                boolean flag = httpAuthHandlerhost.useHttpAuthUsernamePassword();
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                System.out.println("***********onReceivedError ************");
                super.onReceivedError(webView, i, s, s1);
            }

            @Override
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                System.out.println("***********onReceivedHttpError ************");
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        });
        webView.loadUrl("https://www.hao123.com/?tn=88093251_73_hao_pg");
        sendNotify.setOnClickListener(this);
        mEt_login_password = findViewById(R.id.et_login_password);
        mEt_login_password.setDrawableClick(new ImgEditText.IMyRightDrawableClick() {
            @Override
            public void rightDrawableClick() {

                if (isHidden) {
                    //设置EditText文本为可见的
                    mEt_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEt_login_password.setRightDrawable(getResources().getDrawable(R.drawable.ic_baseline_clear_24));
                } else {
                    //设置EditText文本为隐藏的
                    mEt_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEt_login_password.setRightDrawable(getResources().getDrawable(R.drawable.ic_baseline_panorama_fish_eye_24));
                }
                isHidden = !isHidden;
                mEt_login_password.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mEt_login_password.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
        topView.setOnclickLeft(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendNotify:
                Intent intent = new Intent(this, NotifycationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")

                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_clear_24))
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1, notification);
                break;
            case R.id.sendOkhttp:
                Intent intent1 = new Intent(MainActivity.this, OkHttpActivity.class);
                startActivity(intent1);
                break;
            case R.id.test_fragment:
                Intent intent2 = new Intent(MainActivity.this, MyFragmentActivity.class);
                startActivity(intent2);
                break;
            case R.id.dian9ceshi:
                Intent intent3 = new Intent(MainActivity.this,Dian9TestActivity.class);
                startActivity(intent3);
                break;
            case R.id.test_fragment_2:
                Intent intent4 = new Intent(MainActivity.this,TestFragmentSimpleActivity.class);
                startActivity(intent4);
                break;
            case R.id.test_glide:
                Intent intent5 = new Intent(this,TestGlideActivity.class);
                startActivity(intent5);
                break;
            case R.id.thread_download:
                Intent intent6 = new Intent(this,ThreadDownloadActivity.class);
                startActivity(intent6);
                break;
            case R.id.testEditText:
                Intent intent7 = new Intent(this,ZidingyiEditTextActivity.class);
                startActivity(intent7);
                break;
            case R.id.test_zidingyi:
                Intent intent8 = new Intent(this,TestZiDingYiViewActivity.class);
                startActivity(intent8);
                break;
            case R.id.test_toggle:
                Intent intent9 = new Intent(this,ToggleViewActivity.class);
                startActivity(intent9);
                break;
            case R.id.test_my_qq_slide:
                Intent intent10 = new Intent(this,QQSlideMenuActivity.class);
                startActivity(intent10);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}