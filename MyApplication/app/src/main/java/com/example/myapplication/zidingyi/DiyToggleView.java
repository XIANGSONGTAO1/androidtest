package com.example.myapplication.zidingyi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DiyToggleView extends View {
    private Bitmap toggleBall;
    private Bitmap toggleOnBg;
    private Bitmap toggleOffBg;
    private int ballCurrentX;
    private boolean isOpen = true;
    private boolean isTouch;

    public DiyToggleView(Context context) {
        super(context);
    }

    public DiyToggleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiyToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setToggleBall(int resId) {
        toggleBall = BitmapFactory.decodeResource(getResources(), resId);
    }

    public void setToggleOnBg(int resId) {
        toggleOnBg = BitmapFactory.decodeResource(getResources(), resId);
    }

    public void setToggleOffBg(int resId) {
        toggleOffBg = BitmapFactory.decodeResource(getResources(), resId);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (toggleOnBg!=null){
//            canvas.drawBitmap(toggleOnBg,0,0,null);
//        }
//        if (toggleBall!=null){
//            canvas.drawBitmap(toggleBall,ballCurrentX,0,null);
//        }
        if (toggleBall == null || toggleOnBg == null || toggleOffBg == null) {
            return;
        }
        int ballWidth = toggleBall.getWidth();
        int ballToRightX = toggleOnBg.getWidth()-ballWidth;
        if (isTouch){
            if (isOpen){
                canvas.drawBitmap(toggleOnBg,0,0,null);
                if(ballCurrentX>(ballWidth)&&ballCurrentX<ballToRightX){
                    canvas.drawBitmap(toggleBall,ballCurrentX, 0, null);
                }
                if (ballCurrentX>ballToRightX){
                    canvas.drawBitmap(toggleBall,ballToRightX,0,null);
                }
                if (ballCurrentX<ballWidth){
                    canvas.drawBitmap(toggleBall,0,0,null);
                }
            }else{
                canvas.drawBitmap(toggleOffBg,0,0,null);
                if(ballCurrentX>ballWidth&&ballCurrentX<ballToRightX){
                    canvas.drawBitmap(toggleBall,ballCurrentX, 0, null);
                }
                if (ballCurrentX>ballToRightX){
                    canvas.drawBitmap(toggleBall,ballToRightX,0,null);
                }
                if (ballCurrentX<ballWidth){
                    canvas.drawBitmap(toggleBall,0,0,null);
                }
            }
        }else{
            if (isOpen){
                canvas.drawBitmap(toggleOnBg,0,0,null);
                canvas.drawBitmap(toggleBall,0,0,null);
            }else{
                canvas.drawBitmap(toggleOffBg,0,0,null);
                canvas.drawBitmap(toggleBall,ballToRightX,0,null);
            }

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = toggleOnBg.getWidth();
        int measuredHeight = toggleOnBg.getHeight();
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                ballCurrentX = (int) (event.getX() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                isTouch = true;
                ballCurrentX = (int) (event.getX() + 0.5f);
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                ballCurrentX = (int) (event.getX() + 0.5f);
                if (ballCurrentX<toggleOnBg.getWidth()/2){
                    isOpen = true;
                }
                if (ballCurrentX>toggleOnBg.getWidth()/2){
                    isOpen = false;
                }
                break;
        }
        invalidate();
        return true;

    }
}
