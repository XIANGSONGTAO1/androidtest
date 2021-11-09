package com.example.myapplication.zidingyi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.example.myapplication.R;

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;
    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable ==null){
            mClearDrawable = getResources().getDrawable(R.drawable.ic_baseline_clear_24);
        }
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.getIntrinsicHeight());
        setmClearDrawable(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP){
            if (getCompoundDrawables()[2]!=null){
                boolean touchable = event.getX()>(getWidth()-getTotalPaddingRight())&&(event.getX()<(getWidth()-getPaddingRight()));
                if (touchable){
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (hasFoucs){
            setClearIconVisible(charSequence.length()>0);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        this.hasFoucs = hasFoucs;
        if (hasFoucs){
            setmClearDrawable(getText().length()>0);
        }else {
            setmClearDrawable(false);
        }
    }
    protected void setmClearDrawable(boolean visible){
        Drawable right = visible?mClearDrawable:null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],right,getCompoundDrawables()[3]);
    }
    protected void setClearIconVisible(boolean visible){
        Drawable right = visible?mClearDrawable:null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],right,getCompoundDrawables()[3]);

    }
    public static Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0,10,0,0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }
}
