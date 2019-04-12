package com.bamboy.freedom.ui.smartrefresh.api;

import android.support.annotation.RestrictTo;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;
import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * 刷新底部
 * Created by SCWANG on 2017/5/26.
 */
public interface RefreshFooter extends RefreshInternal {

    /**
     * 【仅限框架内调用】设置数据全部加载完成，将不能再次触发加载功能
     * @param noMoreData 是否有更多数据
     * @return true 支持全部加载完成的状态显示 false 不支持
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    boolean setNoMoreData(boolean noMoreData);
}
