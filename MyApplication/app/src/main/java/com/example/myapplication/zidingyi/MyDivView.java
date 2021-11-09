package com.example.myapplication.zidingyi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyDivView extends View {
    private Paint paint;
    public MyDivView(Context context) {
        super(context);
        paint = new Paint();
    }

    public MyDivView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public MyDivView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        canvas.drawLine(30,30,120,70,paint);
        super.onDraw(canvas);
    }
}
