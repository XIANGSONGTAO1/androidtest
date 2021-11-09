package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopView extends RelativeLayout {
    private ImageView top_left;
    private TextView top_title;
    private TextView top_end;

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TopView);

        LayoutInflater.from(context).inflate(R.layout.title, this);
        top_left = findViewById(R.id.top_left);
        top_title = findViewById(R.id.top_title);
        top_end = findViewById(R.id.top_right);
        String titleString = ta.getString(R.styleable.TopView_titleText);
        String endString = ta.getString(R.styleable.TopView_endText);
        top_title.setText(titleString);
        top_end.setText(endString);
    }

    public void setOnclickLeft(OnClickListener onclickLeft) {
        top_left.setOnClickListener(onclickLeft);
    }

    public void setTitle(String title) {
        top_title.setText(title);
    }

    public void setEnd(String end) {
        top_end.setText(end);
    }
}
