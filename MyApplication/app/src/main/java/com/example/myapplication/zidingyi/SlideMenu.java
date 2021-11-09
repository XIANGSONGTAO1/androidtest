package com.example.myapplication.zidingyi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideMenu extends ViewGroup {
    private View mLeftMenu;
    private View mMainPage;
    int downx;
    Scroller scroller;

    public SlideMenu(Context context) {
        super(context);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int leftViewWidth = mLeftMenu.getMeasuredWidth();
        int leftViewHeight = mLeftMenu.getMeasuredHeight();
        mLeftMenu.layout(-leftViewWidth, 0, 0, leftViewHeight);
        int mainViewWidth = mMainPage.getMeasuredWidth();
        int mainViewHeight = mMainPage.getMeasuredHeight();
        mMainPage.layout(0, 0, mainViewWidth, mainViewHeight);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftMenu = getChildAt(0);
        mMainPage = getChildAt(1);
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int leftViewMeasureSpecWidth = MeasureSpec.makeMeasureSpec(mLeftMenu.getLayoutParams().width, MeasureSpec.EXACTLY);
        mLeftMenu.measure(leftViewMeasureSpecWidth, heightMeasureSpec);
        mMainPage.measure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = (int) (event.getX() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                int movex = (int) (event.getX() + 0.5f);
                int distance = downx - movex;
                int scrollX = getScrollX();
                if (scrollX + distance < -mLeftMenu.getMeasuredWidth()) {
                    scrollTo(-mLeftMenu.getMeasuredWidth(), 0);
                } else if (scrollX + distance > 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(distance, 0);
                }
                downx = movex;
                break;
            case MotionEvent.ACTION_UP:
                int upScrollX = getScrollX();
//                if (upScrollX < -(mLeftMenu.getMeasuredWidth() / 2)) {
//                    scrollTo(-mLeftMenu.getMeasuredWidth(), 0);
//                } else {
//                    scrollTo(0, 0);
//                }
                choosePage(upScrollX<-(mLeftMenu.getMeasuredWidth()/2));
                break;
        }
        return true;
    }
    private void choosePage(boolean isMainPage){
        if (isMainPage){
            stratScrollNow(-mLeftMenu.getMeasuredWidth());
        }else{
            stratScrollNow(0);
        }
    }
    private void stratScrollNow(int endX){
        int startx = getScrollX();
        int starty = 0;
        int dx = endX-startx;
        int dy = 0;
        int time = Math.abs(dx)*10;
        int duration = time>600?600:time;
        scroller.startScroll(startx,starty,dx,dy,duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }
}
