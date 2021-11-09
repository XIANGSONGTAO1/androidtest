package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        button = findViewById(R.id.sendHttp);
        button.setOnClickListener(this);
        textView = findViewById(R.id.response_text);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendHttp:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = HttpUtils.sendOkHttp();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(res);
                            }
                        });
                    }
                }).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}