package com.bamboy.freedom.ui.smartrefresh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bamboy.freedom.R;

/**
 * 圆形进度条
 * <p>
 * Created by Bamboy on 2018/10/18.
 */
public class SlopeProgress extends View {
    /**
     * 当前进度
     */
    private int progress = 0;
    /**
     * 总进度
     */
    private int maxProgress = 100;
    /**
     * 圆环颜色
     */
    private int ringColor;
    /**
     * 直径
     */
    private float diam = 100;
    /**
     * 线条
     */
    private float line = 0;

    public SlopeProgress(Context context) {
        super(context);

        ringColor = ContextCompat.getColor(context, R.color.colorPrimary);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int diameter = Math.min(width, height);

        if (line == 0) {
            line = diameter * 0.13f;
        }
        diam = diameter - line * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float length = Math.min(width, height);

        // =============== 声明画笔 ===============
        // 初始化画笔
        Paint ringPaint = new Paint();
        // 设置消除锯齿
        ringPaint.setAntiAlias(true);
        // 设置防抖，即边缘柔化
        ringPaint.setDither(true);
        // 设置颜色
        ringPaint.setColor(ringColor);
        // 设置实心
        ringPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔的宽度
        ringPaint.setStrokeWidth(line);
        // 设置线条圆角
        ringPaint.setStrokeCap(Paint.Cap.ROUND);

        // =============== 画圆环 ===============
        // 进度
        float pro = (float) progress / (float) maxProgress;
        // 旋转角度
        float startAngle = -90 - (pro * 180);
        // 圆环进度
        float sweepAngle = pro * 360;
        // 计算内边距
        int padding = (int) ((length - diam) / 2);
        RectF oval = new RectF(padding, padding, padding + diam, padding + diam);

        // 绘制圆圈
        canvas.drawArc(oval, startAngle, sweepAngle, false, ringPaint);

    }

    /**
     * 总进度
     */
    public int getMaxProgress() {
        return maxProgress;
    }

    /**
     * 总进度
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    /**
     * 设置颜色
     */
    public void setRingColor(int ringColor) {
        if (ringColor != 0)
            this.ringColor = ringColor;
    }

    /**
     * 线条
     */
    public float getLine() {
        return line;
    }

    /**
     * 线条
     */
    public void setLine(float line) {
        this.line = line;
    }

    /**
     * 获取当前进度
     *
     * @return
     */
    public int getProgress() {
        return progress;
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }

        this.progress = progress;
        invalidate();
    }
}
