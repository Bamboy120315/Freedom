package com.bamboy.freedom.ui.smartrefresh.header;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bamboy.freedom.R;;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshHeader;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.constant.RefreshState;
import com.bamboy.freedom.ui.smartrefresh.constant.SpinnerStyle;
import com.bamboy.freedom.ui.smartrefresh.internal.ArrowDrawable;
import com.bamboy.freedom.ui.smartrefresh.internal.InternalClassics;
import com.bamboy.freedom.ui.smartrefresh.internal.ProgressDrawable;
import com.bamboy.freedom.ui.smartrefresh.util.DensityUtil;

import java.util.List;

/**
 * 经典下拉头部
 * Created by SCWANG on 2017/5/28.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ClassicsHeader extends InternalClassics<ClassicsHeader> implements RefreshHeader {

    public static final byte ID_TEXT_UPDATE = 4;

    public static String REFRESH_HEADER_PULLING = null;//"下拉可以刷新";
    public static String REFRESH_HEADER_REFRESHING = null;//"正在刷新...";
    public static String REFRESH_HEADER_LOADING = null;//"正在加载...";
    public static String REFRESH_HEADER_RELEASE = null;//"释放立即刷新";
    public static String REFRESH_HEADER_FINISH = null;//"刷新完成";
    public static String REFRESH_HEADER_FAILED = null;//"刷新失败";
    public static String REFRESH_HEADER_UPDATE = null;//"上次更新 M-d HH:mm";
    public static String REFRESH_HEADER_SECONDARY = null;//"释放进入二楼";
//    public static String REFRESH_HEADER_UPDATE = "'Last update' M-d HH:mm";

    protected SharedPreferences mShared;

    protected String mTextPulling = null;//"下拉可以刷新";
    protected String mTextRefreshing = null;//"正在刷新...";
    protected String mTextLoading = null;//"正在加载...";
    protected String mTextRelease = null;//"释放立即刷新";
    protected String mTextFinish = null;//"刷新完成";
    protected String mTextFailed = null;//"刷新失败";
    protected String mTextUpdate = null;//"上次更新 M-d HH:mm";
    protected String mTextSecondary = null;//"释放进入二楼";

    //<editor-fold desc="RelativeLayout">
    public ClassicsHeader(Context context) {
        this(context, null);
    }

    public ClassicsHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final View thisView = this;
        final View arrowView = mArrowView;
        final View progressView = mProgressView;
        final ViewGroup centerLayout = mCenterLayout;
        final DensityUtil density = new DensityUtil();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClassicsHeader);

        RelativeLayout.LayoutParams lpArrow = (RelativeLayout.LayoutParams) arrowView.getLayoutParams();
        RelativeLayout.LayoutParams lpProgress = (RelativeLayout.LayoutParams) progressView.getLayoutParams();
        lpProgress.rightMargin = ta.getDimensionPixelSize(R.styleable.ClassicsFooter_srlDrawableMarginRight, density.dip2px(20));
        lpArrow.rightMargin = lpProgress.rightMargin;

        lpArrow.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.height);

        lpArrow.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.height);

        mFinishDuration = ta.getInt(R.styleable.ClassicsHeader_srlFinishDuration, mFinishDuration);
        mSpinnerStyle = SpinnerStyle.values()[ta.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle,mSpinnerStyle.ordinal())];

        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableArrow)) {
            mArrowView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableArrow));
        } else {
            mArrowDrawable = new ArrowDrawable();
            mArrowDrawable.setColor(0xff666666);
            mArrowView.setImageDrawable(mArrowDrawable);
        }

        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableProgress)) {
            mProgressView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableProgress));
        } else {
            mProgressDrawable = new ProgressDrawable();
            mProgressDrawable.setColor(0xff666666);
            mProgressView.setImageDrawable(mProgressDrawable);
        }

        if (ta.hasValue(R.styleable.ClassicsHeader_srlTextSizeTitle)) {
            mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextSizeTitle, DensityUtil.dp2px(16)));
        } else {
            mTitleText.setTextSize(16);
        }

        if (ta.hasValue(R.styleable.ClassicsHeader_srlPrimaryColor)) {
            super.setPrimaryColor(ta.getColor(R.styleable.ClassicsHeader_srlPrimaryColor, 0));
        }
        if (ta.hasValue(R.styleable.ClassicsHeader_srlAccentColor)) {
            setAccentColor(ta.getColor(R.styleable.ClassicsHeader_srlAccentColor, 0));
        }

        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextPulling)){
            mTextPulling = ta.getString(R.styleable.ClassicsHeader_srlTextPulling);
        } else if(REFRESH_HEADER_PULLING != null) {
            mTextPulling = REFRESH_HEADER_PULLING;
        } else {
            mTextPulling = context.getString(R.string.srl_header_pulling);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextLoading)){
            mTextLoading = ta.getString(R.styleable.ClassicsHeader_srlTextLoading);
        } else if(REFRESH_HEADER_LOADING != null) {
            mTextLoading = REFRESH_HEADER_LOADING;
        } else {
            mTextLoading = context.getString(R.string.srl_header_loading);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextRelease)){
            mTextRelease = ta.getString(R.styleable.ClassicsHeader_srlTextRelease);
        } else if(REFRESH_HEADER_RELEASE != null) {
            mTextRelease = REFRESH_HEADER_RELEASE;
        } else {
            mTextRelease = context.getString(R.string.srl_header_release);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextFinish)){
            mTextFinish = ta.getString(R.styleable.ClassicsHeader_srlTextFinish);
        } else if(REFRESH_HEADER_FINISH != null) {
            mTextFinish = REFRESH_HEADER_FINISH;
        } else {
            mTextFinish = context.getString(R.string.srl_header_finish);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextFailed)){
            mTextFailed = ta.getString(R.styleable.ClassicsHeader_srlTextFailed);
        } else if(REFRESH_HEADER_FAILED != null) {
            mTextFailed = REFRESH_HEADER_FAILED;
        } else {
            mTextFailed = context.getString(R.string.srl_header_failed);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextSecondary)){
            mTextSecondary = ta.getString(R.styleable.ClassicsHeader_srlTextSecondary);
        } else if(REFRESH_HEADER_SECONDARY != null) {
            mTextSecondary = REFRESH_HEADER_SECONDARY;
        } else {
            mTextSecondary = context.getString(R.string.srl_header_secondary);
        }
        if(ta.hasValue(R.styleable.ClassicsHeader_srlTextRefreshing)){
            mTextRefreshing = ta.getString(R.styleable.ClassicsHeader_srlTextRefreshing);
        } else if(REFRESH_HEADER_REFRESHING != null) {
            mTextRefreshing = REFRESH_HEADER_REFRESHING;
        } else {
            mTextRefreshing = context.getString(R.string.srl_header_refreshing);
        }

        ta.recycle();

        mTitleText.setText(thisView.isInEditMode() ? mTextRefreshing : mTextPulling);

        try {//try 不能删除-否则会出现兼容性问题
            if (context instanceof FragmentActivity) {
                FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
                if (manager != null) {
                    @SuppressLint("RestrictedApi")
                    List<Fragment> fragments = manager.getFragments();
                    if (fragments != null && fragments.size() > 0) {
                        return;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        mShared = context.getSharedPreferences("ClassicsHeader", Context.MODE_PRIVATE);

    }

//    @Override
//    protected ClassicsHeader self() {
//        return this;
//    }

    //</editor-fold>

    //<editor-fold desc="RefreshHeader">

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (success) {
            mTitleText.setText(mTextFinish);
        } else {
            mTitleText.setText(mTextFailed);
        }
        return super.onFinish(layout, success);//延迟500毫秒之后再弹回
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        final View arrowView = mArrowView;
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTitleText.setText(mTextPulling);
                arrowView.setVisibility(VISIBLE);
                arrowView.animate().rotation(0);
                break;
            case Refreshing:
            case RefreshReleased:
                mTitleText.setText(mTextRefreshing);
                arrowView.setVisibility(GONE);
                break;
            case ReleaseToRefresh:
                mTitleText.setText(mTextRelease);
                arrowView.animate().rotation(180);
                break;
            case ReleaseToTwoLevel:
                mTitleText.setText(mTextSecondary);
                arrowView.animate().rotation(0);
                break;
            case Loading:
                arrowView.setVisibility(GONE);
                mTitleText.setText(mTextLoading);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="API">

    public ClassicsHeader setAccentColor(@ColorInt int accentColor) {
        return super.setAccentColor(accentColor);
    }


//    public ClassicsHeader setTextTimeMarginTopPx(int px) {
//        MarginLayoutParams lp = (MarginLayoutParams)mLastUpdateText.getLayoutParams();
//        lp.topMargin = px;
//        mLastUpdateText.setLayoutParams(lp);
//        return this;
//    }

//    /**
//     * @deprecated 使用 findViewById(ID_TEXT_UPDATE) 代替
//     */
//    @Deprecated
//    public TextView getLastUpdateText() {
//        return mLastUpdateText;
//    }

    //</editor-fold>

}
