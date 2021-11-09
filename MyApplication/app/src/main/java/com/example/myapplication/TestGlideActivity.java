package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.PathUtils;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class TestGlideActivity extends AppCompatActivity {
    ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);
        imageView = findViewById(R.id.my_image);
        String url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.2008php.com%2F09_Website_appreciate%2F10-07-11%2F1278861720_g.jpg&refer=http%3A%2F%2Fwww.2008php.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638597765&t=f837ff74cb4ab53e525d265f563ba368";
        Glide.with(this)
                .load(url)
                .into(imageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}