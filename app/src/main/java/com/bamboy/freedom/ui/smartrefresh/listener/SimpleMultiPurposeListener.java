package com.bamboy.freedom.ui.smartrefresh.listener;

import android.support.annotation.NonNull;

import com.bamboy.freedom.ui.smartrefresh.api.RefreshFooter;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshHeader;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.constant.RefreshState;

/**
 * 多功能监听器
 * Created by SCWANG on 2017/5/26.
 */

public class SimpleMultiPurposeListener implements OnMultiPurposeListener {

    /**
     * 下拉监听
     *
     * @param header        header对象
     * @param isDragging    触点还在屏幕上
     * @param percent       当前下拉位置 与 header高度比例
     * @param offset        下拉长度
     * @param headerHeight  header高度
     * @param maxDragHeight 最大下拉高度
     */
    @Override
    public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int footerHeight, int maxDragHeight) {

    }

    /**
     * 刷新完成
     *
     * @param header header对象
     * @param success 是否成功
     */
    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {

    }

    /**
     * 开始刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

}
