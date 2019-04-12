package com.bamboy.freedom.ui.smartrefresh.impl;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.Space;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.bamboy.freedom.ui.smartrefresh.api.RefreshContent;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshKernel;
import com.bamboy.freedom.ui.smartrefresh.api.ScrollBoundaryDecider;
import com.bamboy.freedom.ui.smartrefresh.listener.CoordinatorLayoutListener;
import com.bamboy.freedom.ui.smartrefresh.util.DesignUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.bamboy.freedom.ui.smartrefresh.util.ScrollBoundaryUtil.canScrollDown;
import static com.bamboy.freedom.ui.smartrefresh.util.ScrollBoundaryUtil.canScrollUp;
import static com.bamboy.freedom.ui.smartrefresh.util.ScrollBoundaryUtil.isTransformedTouchPointInView;
import static com.bamboy.freedom.ui.smartrefresh.util.SmartUtil.isContentView;
import static com.bamboy.freedom.ui.smartrefresh.util.SmartUtil.measureViewHeight;
import static com.bamboy.freedom.ui.smartrefresh.util.SmartUtil.scrollListBy;

/**
 * 刷新内容包装
 * Created by SCWANG on 2017/5/26.
 */
@SuppressWarnings("WeakerAccess")
public class RefreshContentWrapper implements RefreshContent , CoordinatorLayoutListener, AnimatorUpdateListener {

//    protected int mHeaderHeight = Integer.MAX_VALUE;
//    protected int mFooterHeight = mHeaderHeight - 1;
    protected View mContentView;//直接内容视图
    protected View mOriginalContentView;//被包裹的原真实视图
    protected View mScrollableView;
    protected View mFixedHeader;
    protected View mFixedFooter;
    protected int mLastSpinner = 0;
    protected boolean mEnableRefresh = true;
    protected boolean mEnableLoadMore = true;
//    protected MotionEvent mMotionEvent;
    protected ScrollBoundaryDeciderAdapter mBoundaryAdapter = new ScrollBoundaryDeciderAdapter();

    public RefreshContentWrapper(@NonNull View view) {
        this.mContentView = mOriginalContentView = mScrollableView = view;
    }

    //<editor-fold desc="findScrollableView">
    protected void findScrollableView(View content, RefreshKernel kernel) {
        View scrollableView = null;
        boolean isInEditMode = mContentView.isInEditMode();
        while (scrollableView == null || (scrollableView instanceof NestedScrollingParent
                && !(scrollableView instanceof NestedScrollingChild))) {
            content = findScrollableViewInternal(content, scrollableView == null);
            if (content == scrollableView) {
                break;
            }
            if (!isInEditMode) {
                DesignUtil.checkCoordinatorLayout(content, kernel, this);
            }
            scrollableView = content;
        }
        if (scrollableView != null) {
            mScrollableView = scrollableView;
        }
    }

    @Override
    public void onCoordinatorUpdate(boolean enableRefresh, boolean enableLoadMore) {
        mEnableRefresh = enableRefresh;
        mEnableLoadMore = enableLoadMore;
    }

    protected View findScrollableViewInternal(View content, boolean selfable) {
        View scrollableView = null;
        Queue<View> views = new LinkedList<>(Collections.singletonList(content));
        while (!views.isEmpty() && scrollableView == null) {
            View view = views.poll();
            if (view != null) {
                if ((selfable || view != content) && isContentView(view)) {
                    scrollableView = view;
                } else if (view instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) view;
                    for (int j = 0; j < group.getChildCount(); j++) {
                        views.add(group.getChildAt(j));
                    }
                }
            }
        }
        return scrollableView == null ? content : scrollableView;
    }

    protected View findScrollableViewByPoint(View content, PointF event, View orgScrollableView) {
        if (content instanceof ViewGroup && event != null) {
            ViewGroup viewGroup = (ViewGroup) content;
            final int childCount = viewGroup.getChildCount();
            PointF point = new PointF();
            for (int i = childCount; i > 0; i--) {
                View child = viewGroup.getChildAt(i - 1);
                if (isTransformedTouchPointInView(viewGroup, child, event.x, event.y, point)) {
                    if (child instanceof ViewPager || !isContentView(child)) {
                        event.offset(point.x, point.y);
                        child = findScrollableViewByPoint(child, event, orgScrollableView);
                        event.offset(-point.x, -point.y);
                    }
                    return child;
                }
            }
        }
        return orgScrollableView;
    }
    //</editor-fold>

    //<editor-fold desc="implements">
    @NonNull
    public View getView() {
        return mContentView;
    }

    @Override
    @NonNull
    public View getScrollableView() {
        return mScrollableView;
    }

    @Override
    public void moveSpinner(int spinner, int headerTranslationViewId, int footerTranslationViewId) {
        boolean translated = false;
        if (headerTranslationViewId != View.NO_ID) {
            View headerTranslationView = mOriginalContentView.findViewById(headerTranslationViewId);
            if (headerTranslationView != null) {
                if (spinner > 0) {
                    translated = true;
                    headerTranslationView.setTranslationY(spinner);
                } else if (headerTranslationView.getTranslationY() > 0) {
                    headerTranslationView.setTranslationY(0);
                }
            }
        }
        if (footerTranslationViewId != View.NO_ID) {
            View footerTranslationView = mOriginalContentView.findViewById(footerTranslationViewId);
            if (footerTranslationView != null) {
                if (spinner < 0) {
                    translated = true;
                    footerTranslationView.setTranslationY(spinner);
                } else if (footerTranslationView.getTranslationY() < 0) {
                    footerTranslationView.setTranslationY(0);
                }
            }
        }
        if (!translated) {
            mOriginalContentView.setTranslationY(spinner);
        } else {
            mOriginalContentView.setTranslationY(0);
        }
        if (mFixedHeader != null) {
            mFixedHeader.setTranslationY(Math.max(0, spinner));
        }
        if (mFixedFooter != null) {
            mFixedFooter.setTranslationY(Math.min(0, spinner));
        }
    }

    @Override
    public boolean canRefresh() {
        return mEnableRefresh && mBoundaryAdapter.canRefresh(mContentView);
    }

    @Override
    public boolean canLoadMore() {
        return mEnableLoadMore && mBoundaryAdapter.canLoadMore(mContentView);
    }

    @Override
    public void onActionDown(MotionEvent e) {
//        mMotionEvent = MotionEvent.obtain(e);
//        mMotionEvent.offsetLocation(-mContentView.getLeft(), -mContentView.getTop());
        PointF point = new PointF(e.getX(), e.getY());
        point.offset(-mContentView.getLeft(), -mContentView.getTop());
        if (mScrollableView != mContentView) {
            //如果内容视图不是 ScrollableView 说明使用了Layout嵌套内容，需要动态搜索 ScrollableView
            mScrollableView = findScrollableViewByPoint(mContentView, point, mScrollableView);
        }
        if (mScrollableView == mContentView) {
            //如果内容视图就是 ScrollableView 就不需要使用事件来动态搜索 而浪费CPU时间和性能了
//            mBoundaryAdapter.setActionEvent(null);
            mBoundaryAdapter.mActionEvent = null;
        } else {
            mBoundaryAdapter.mActionEvent = point;
//            mBoundaryAdapter.setActionEvent(mMotionEvent);
        }
    }

//    @Override
//    public void onActionUpOrCancel() {
//        mMotionEvent = null;
//    }

    @Override
    public void setUpComponent(RefreshKernel kernel, View fixedHeader, View fixedFooter) {
        findScrollableView(mContentView, kernel);

        if (fixedHeader != null || fixedFooter != null) {
            mFixedHeader = fixedHeader;
            mFixedFooter = fixedFooter;
            ViewGroup frameLayout = new FrameLayout(mContentView.getContext());
            kernel.getRefreshLayout().getLayout().removeView(mContentView);
            ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
            frameLayout.addView(mContentView, MATCH_PARENT, MATCH_PARENT);
            kernel.getRefreshLayout().getLayout().addView(frameLayout, layoutParams);
            mContentView = frameLayout;
            if (fixedHeader != null) {
                fixedHeader.setClickable(true);
                ViewGroup.LayoutParams lp = fixedHeader.getLayoutParams();
                ViewGroup parent = (ViewGroup) fixedHeader.getParent();
                int index = parent.indexOfChild(fixedHeader);
                parent.removeView(fixedHeader);
                lp.height = measureViewHeight(fixedHeader);
                parent.addView(new Space(mContentView.getContext()), index, lp);
                frameLayout.addView(fixedHeader);
            }
            if (fixedFooter != null) {
                fixedFooter.setClickable(true);
                ViewGroup.LayoutParams lp = fixedFooter.getLayoutParams();
                ViewGroup parent = (ViewGroup) fixedFooter.getParent();
                int index = parent.indexOfChild(fixedFooter);
                parent.removeView(fixedFooter);
                FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(lp);
                lp.height = measureViewHeight(fixedFooter);
                parent.addView(new Space(mContentView.getContext()), index, lp);
                flp.gravity = Gravity.BOTTOM;
                frameLayout.addView(fixedFooter, flp);
            }
        }
    }

//    @Override
//    public void onInitialHeaderAndFooter(int headerHeight, int footerHeight) {
//        mHeaderHeight = headerHeight;
//        mFooterHeight = footerHeight;
//    }

    @Override
    public void setScrollBoundaryDecider(ScrollBoundaryDecider boundary) {
        if (boundary instanceof ScrollBoundaryDeciderAdapter) {
            mBoundaryAdapter = ((ScrollBoundaryDeciderAdapter) boundary);
        } else {
            mBoundaryAdapter.boundary = (boundary);
        }
    }

    @Override
    public void setEnableLoadMoreWhenContentNotFull(boolean enable) {
//        mBoundaryAdapter.setEnableLoadMoreWhenContentNotFull(enable);
        mBoundaryAdapter.mEnableLoadMoreWhenContentNotFull = enable;
    }

    @Override
    public AnimatorUpdateListener scrollContentWhenFinished(final int spinner) {
        if (mScrollableView != null && spinner != 0) {
            if ((spinner < 0 && canScrollDown(mScrollableView)) || (spinner > 0 && canScrollUp(mScrollableView))) {
                mLastSpinner = spinner;
                return this;
            }
        }
        return null;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        try {
            if (mScrollableView instanceof AbsListView) {
                scrollListBy((AbsListView) mScrollableView, value - mLastSpinner);
            } else {
                mScrollableView.scrollBy(0, value - mLastSpinner);
            }
        } catch (Throwable ignored) {
            //根据用户反馈，此处可能会有BUG
        }
        mLastSpinner = value;
    }
    //</editor-fold>

}
