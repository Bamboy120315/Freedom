package com.bamboy.freedom.ui.smartrefresh.header;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.smartrefresh.SmartRefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshHeader;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshInternal;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshKernel;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.constant.RefreshState;
import com.bamboy.freedom.ui.smartrefresh.constant.SpinnerStyle;
import com.bamboy.freedom.ui.smartrefresh.util.BoundingUtil;
import com.bamboy.freedom.ui.smartrefresh.util.DensityUtil;
import com.bamboy.freedom.ui.smartrefresh.view.SlopeProgress;

/**
 * 旋转进度风格的 Header
 * <p>
 * Created by Bamboy on 2019/4/10.
 */
public class SlopeHeader extends LinearLayout implements RefreshInternal, RefreshHeader {

    /**
     * 圆环对象
     */
    private SlopeProgress sp;
    /**
     * 动画对象
     */
    private AnimatorSet spAnim;
    /**
     * 线的宽度
     */
    private float lineWidth;
    /**
     * 圆环颜色
     */
    private int ringColor;
    /**
     * 圆环当前旋转角度
     */
    private float rotation;
    /**
     * View高度
     */
    private int height;

    public SlopeHeader(Context context) {
        super(context);
        init(context);
    }

    public SlopeHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // -------------------- 获取自定义属性 --------------------
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlopeHeader);
        lineWidth = typedArray.getDimension(R.styleable.SlopeHeader_lineWidth, -1);
        ringColor = typedArray.getColor(R.styleable.SlopeHeader_color, ContextCompat.getColor(context, R.color.colorPrimary));

        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        setGravity(Gravity.CENTER);

        height = DensityUtil.dp2px(50);

        sp = new SlopeProgress(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(height / 3 * 2, height / 3 * 2);
        sp.setLayoutParams(params);
        sp.setRingColor(ringColor);
        sp.setMaxProgress(100);
        sp.setProgress(0);
        if (lineWidth > 0) {
            sp.setLine(lineWidth);
        }
        addView(sp);

        setMinimumHeight(height);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    /**
     * 添加顶部间距 避免与刘海重叠 同时避免被刘海遮挡
     */
    public void setMarginTop(Context context, SmartRefreshLayout refreshLayout) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        // 计算背景图片高度
        float imgHeight = refreshLayout.getHeaderMaxDragRate() * height;

        // 获取状态栏高度
        int marginTop = BoundingUtil.getBarHeight(context);
        height += marginTop;

        // 设置加上状态栏后的新高度
        SmartRefreshLayout.LayoutParams params = (SmartRefreshLayout.LayoutParams) getLayoutParams();
        params.height = height;

        // 进度条下移，避开状态栏区域
        sp.setTranslationY(marginTop / 2);

        // 设置最小高度
        setMinimumHeight(height);

        // 根据背景图片高度，设置下拉最大值，避免图片拉完后顶部出现白条
        refreshLayout.setHeaderMaxDragRate(imgHeight / (float) height);

    }

    /**
     * 设置进度条圆环颜色
     *
     * @param color
     */
    public void setColors(int color) {
        ringColor = color;
        if (sp == null) {
            return;
        }
        sp.setRingColor(color);
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    /**
     * Header移动监听
     *
     * @param isDragging    true 手指正在拖动 false 回弹动画
     * @param percent       下拉的百分比 值 = offset/footerHeight (0 - percent - (footerHeight+maxDragHeight) / footerHeight )
     * @param offset        下拉的像素偏移量  0 - offset - (footerHeight+maxDragHeight)
     * @param height        高度 HeaderHeight or FooterHeight
     * @param maxDragHeight 最大拖动高度
     */
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        if (spAnim != null && spAnim.isRunning()) {
            return;
        }

        // 计算进度条进度
        int progress = (int) (percent * 90);
        if (progress > 90)
            progress = 90;

        // 设置进度
        sp.setProgress(progress);

        // 计算旋转角度
        if (percent < 1) {
            rotation = 90 + (450 * percent);
        } else {
            rotation = (90 + 450) + (percent - 1) * 180;
        }

        // 设置旋转
        sp.setRotation(rotation);

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        onStartAnimator(refreshLayout, height, maxDragHeight);
    }

    /**
     * 动画开始
     *
     * @param refreshLayout RefreshLayout
     * @param height        HeaderHeight or FooterHeight
     * @param maxDragHeight 最大拖动高度
     */
    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        if (spAnim == null) {
            spAnim = new AnimatorSet();
        }

        if (spAnim.isRunning()) {
            return;
        }

        // 旋转动画
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(sp, "rotation", rotation, rotation + 360);
        rotationAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotationAnim.setRepeatMode(ValueAnimator.RESTART);
        rotationAnim.setDuration(780);
        rotationAnim.setInterpolator(new LinearInterpolator());

        // 改变进度动画
        ObjectAnimator progressAnim = ObjectAnimator.ofInt(sp, "progress", 90, 1);
        progressAnim.setRepeatCount(ValueAnimator.INFINITE);
        progressAnim.setRepeatMode(ValueAnimator.REVERSE);
        progressAnim.setDuration(920);

        spAnim.playTogether(rotationAnim, progressAnim);
        spAnim.start();

    }

    /**
     * 加载结束
     *
     * @param refreshLayout RefreshLayout
     * @param success       加载是否成功
     * @return
     */
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (success) {
            // 刷新成功
        } else {
            // 刷新失败
        }

        // 进度条收缩隐藏动画
        sp.animate()
                .alpha(0)
                .scaleX(0.4f)
                .scaleY(0.4f)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (spAnim != null) {
                            spAnim.cancel();
                            spAnim = null;
                        }
                    }
                })
                .start();

        // header延迟250毫秒之后再弹回
        return 250;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    /**
     * 下拉状态
     *
     * @param refreshLayout RefreshLayout
     * @param oldState      改变之前的状态
     * @param newState      改变之后的状态
     */
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                // 下拉开始刷新
                sp.setAlpha(1);
                sp.setScaleX(1);
                sp.setScaleY(1);
                sp.setProgress(0);
                sp.setRotation(0);
                break;
            case Refreshing:
                // 开始刷新
                break;
            case ReleaseToRefresh:
                // 到达刷新阈值
                break;
        }
    }
}
