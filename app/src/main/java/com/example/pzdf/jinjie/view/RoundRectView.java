package com.example.pzdf.jinjie.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundRectView extends View {

    Paint paint;
    float circleAngle;
    int height;
    ValueAnimator animator_rect_to_angle;

    public RoundRectView(Context context) {
        super(context);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        height = h;
        set_rect_to_angle_animation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(100,10,getWidth()-100,100);
        canvas.drawRoundRect(rectF,circleAngle,circleAngle,paint);
    }

    private void set_rect_to_angle_animation() {
        animator_rect_to_angle = ValueAnimator.ofInt(0, height / 2);
        animator_rect_to_angle.setDuration(3000);
        animator_rect_to_angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator_rect_to_angle.start();
    }

}
