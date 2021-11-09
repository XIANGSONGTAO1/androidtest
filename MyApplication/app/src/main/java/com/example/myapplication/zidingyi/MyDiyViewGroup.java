package com.example.myapplication.zidingyi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyDiyViewGroup extends ViewGroup {
    private View myDivView;
    private View mViewText;
    public MyDiyViewGroup(Context context) {
        super(context);
    }

    public MyDiyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int mViewTextWidth = mViewText.getMeasuredWidth();
        int mViewTextHeight = mViewText.getMeasuredHeight();
        mViewText.layout(0,0,mViewTextWidth,mViewTextHeight);
        int mViewDivWidth = myDivView.getMeasuredWidth();
        int mViewDivHeight = myDivView.getMeasuredHeight();
        myDivView.layout(mViewTextWidth,0,mViewTextWidth+mViewDivWidth,mViewDivHeight);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewText = getChildAt(0);
        myDivView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LayoutParams layoutParams = mViewText.getLayoutParams();
        int mViewTextWidth = MeasureSpec.makeMeasureSpec(layoutParams.width,MeasureSpec.EXACTLY);
        int mViewTextHeight = MeasureSpec.makeMeasureSpec(layoutParams.height,MeasureSpec.EXACTLY);
        mViewText.measure(mViewTextWidth,mViewTextHeight);
        LayoutParams layoutParams1 = myDivView.getLayoutParams();
        int myDivViewWidth = MeasureSpec.makeMeasureSpec(layoutParams1.width,MeasureSpec.EXACTLY);
        int myDivViewHeight = MeasureSpec.makeMeasureSpec(layoutParams1.height,MeasureSpec.EXACTLY);
        myDivView.measure(myDivViewWidth,myDivViewHeight);

    }
}
