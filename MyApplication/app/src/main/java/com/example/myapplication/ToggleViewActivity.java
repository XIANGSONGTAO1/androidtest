package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.zidingyi.DiyToggleView;

public class ToggleViewActivity extends AppCompatActivity {
    private DiyToggleView diyToggleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_view);
        diyToggleView = findViewById(R.id.mDtv);
        diyToggleView.setToggleOnBg(R.mipmap.switch_btn_on);
        diyToggleView.setToggleBall(R.mipmap.switch_btn_ball);
        diyToggleView.setToggleOffBg(R.mipmap.switch_btn_off);

    }
}