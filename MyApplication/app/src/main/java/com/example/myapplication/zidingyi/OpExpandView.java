package com.example.myapplication.zidingyi;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

public class OpExpandView extends RelativeLayout implements View.OnClickListener {
    RelativeLayout relativeLayout;
    ImageView imageView;
    boolean isHide = false;
    public OpExpandView(Context context) {
        super(context);
        initUi(context);
    }

    public OpExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi(context);
    }

    public OpExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context);
    }
    private void initUi(Context context){
        View.inflate(context, R.layout.diy_op_view,this);
        relativeLayout = findViewById(R.id.mRlOut);
        imageView = findViewById(R.id.center);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.center:
                if (!isHide){
                    scaleGeneral(relativeLayout, 2000, "scaleX", 1.0f, 0.0f);
                    scaleGeneral(relativeLayout, 2000, "scaleY", 1.0f, 0.0f);
                    rotateGeneral(relativeLayout, 2000, "rotationX", 1.0f, 180f);
                    rotateGeneral(relativeLayout, 2000, "rotationY", 1.0f, 180f);
                    alphaGeneral(relativeLayout, 2000, "alpha", 1.0f, 0.0f);
                }else {
                    scaleGeneral(relativeLayout, 2000, "scaleX", 0.0f, 1.0f);
                    scaleGeneral(relativeLayout, 2000, "scaleY", 0.0f, 1.0f);
                    rotateGeneral(relativeLayout, 2000, "rotationX", 180f, 1.0f);
                    rotateGeneral(relativeLayout, 2000, "rotationY", 180f, 1.0f);
                    alphaGeneral(relativeLayout, 2000, "alpha", 0.0f, 1.0f);
                }
                isHide=!isHide;
                break;
        }
    }
    private void scaleGeneral(View view,int time,String objectName,float start,float end){
        ObjectAnimator scale = ObjectAnimator.ofFloat(view,objectName,start,end);
        scale.setDuration(time);
        scale.start();
    }
    private void rotateGeneral(View view,int time,String objectName,float start,float end){
        ObjectAnimator scale = ObjectAnimator.ofFloat(view,objectName,start,end);
        scale.setDuration(time);
        scale.start();
    }
    private void alphaGeneral(View view,int time,String objectName,float start,float end){
        ObjectAnimator scale = ObjectAnimator.ofFloat(view,objectName,start,end);
        scale.setDuration(time);
        scale.start();
    }
}
