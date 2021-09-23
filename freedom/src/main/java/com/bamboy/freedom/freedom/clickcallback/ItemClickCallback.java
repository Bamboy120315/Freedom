package com.bamboy.freedom.freedom.clickcallback;

import android.view.View;

import com.bamboy.freedom.freedom.BaseViewHolder;

/**
 * RecylerView回调
 * 用于item的点击事件交互
 * <p/>
 * Created by Bamboy on 2021/7/28.
 */
public interface ItemClickCallback {
    /**
     * 点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    void onClick(View view, int position, BaseViewHolder holder);
}
