package com.bamboy.freedom.ui.smartrefresh.footer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshFooter;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.constant.SpinnerStyle;
import com.bamboy.freedom.ui.smartrefresh.internal.InternalAbstract;
import com.bamboy.freedom.ui.smartrefresh.util.DensityUtil;
import com.bamboy.freedom.ui.smartrefresh.view.SlopeProgress;

import java.util.HashMap;
import java.util.Map;

;

/**
 * 圆环底部加载组件
 * Created by SCWANG on 2017/5/30.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SlopeFooter extends InternalAbstract implements RefreshFooter {

    /**
     * 圆环对象
     */
    private SlopeProgress sp;
    /**
     * View高度
     */
    private int height;
    /**
     * 线的宽度
     */
    private float lineWidth;
    /**
     * 动画对象
     */
    private AnimatorSet spAnim;

    //<editor-fold desc="属性变量">
    public static final int DEFAULT_SIZE = 50; //dp

    protected int ringColor = 0xffeeeeee;

    protected float mCircleSpacing;
    protected float[] mScaleFloats = new float[]{1f, 1f, 1f};


    protected boolean mIsStarted = false;
    protected Map<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new HashMap<>();
    ;
    //</editor-fold>

    //<editor-fold desc="构造方法">
    public SlopeFooter(@NonNull Context context) {
        this(context, null);
    }

    public SlopeFooter(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlopeFooter(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final View thisView = this;
        height = DensityUtil.dp2px(50);
        thisView.setMinimumHeight(height);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlopeFooter);

        mSpinnerStyle = SpinnerStyle.Translate;
        mSpinnerStyle = SpinnerStyle.values()[ta.getInt(R.styleable.SlopeFooter_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal())];

        if (ta.hasValue(R.styleable.SlopeFooter_color)) {
            setRingColor(ta.getColor(R.styleable.SlopeFooter_color, ContextCompat.getColor(context, R.color.colorPrimary)));
        }
        if (ta.hasValue(R.styleable.SlopeFooter_color)) {
            lineWidth = ta.getDimension(R.styleable.SlopeFooter_lineWidth, -1);
        }

        ta.recycle();

        mCircleSpacing = DensityUtil.dp2px(4);

        sp = new SlopeProgress(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(height / 3 * 2, height / 3 * 2);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        sp.setLayoutParams(params);
        sp.setRingColor(ringColor);
        sp.setMaxProgress(100);
        sp.setProgress(0);
        if (lineWidth > 0) {
            sp.setLine(lineWidth);
        }
        addView(sp);

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        if (mIsStarted) return;

        if (spAnim == null) {
            spAnim = new AnimatorSet();
        }

        if (spAnim.isRunning()) {
            return;
        }

        sp.setScaleX(1);
        sp.setScaleY(1);
        sp.setAlpha(1);

        // 旋转动画
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(sp, "rotation", 90, 90 + 360);
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

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
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
        return 0;
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }

    @Override
    @Deprecated
    public void setPrimaryColors(@ColorInt int... colors) {
        if (colors.length > 1) {
            setRingColor(colors[1]);
        } else if (colors.length > 0) {
            setRingColor(ColorUtils.compositeColors(0x99ffffff, colors[0]));
        }
    }

//    @NonNull
//    @Override
//    public SpinnerStyle getSpinnerStyle() {
//        return mSpinnerStyle;
//    }

    //</editor-fold>

    //<editor-fold desc="开放接口 - API">

    public SlopeFooter setSpinnerStyle(SpinnerStyle mSpinnerStyle) {
        this.mSpinnerStyle = mSpinnerStyle;
        return this;
    }

    public SlopeFooter setRingColor(@ColorInt int color) {
        ringColor = color;
        if (!mIsStarted && sp != null) {
            sp.setRingColor(color);
        }
        return this;
    }
}
