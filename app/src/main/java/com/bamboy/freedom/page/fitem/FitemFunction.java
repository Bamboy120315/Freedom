package com.bamboy.freedom.page.fitem;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.IdRes;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;

import java.util.List;

public class FitemFunction extends FreedomItem {

    /**
     * 功能名称
     */
    public String title;
    /**
     * 功能介绍
     */
    public String content;
    /**
     * 预览图
     */
    public int resID;
    /**
     * 跳转的类
     */
    public Class jumpClass;

    /**
     * 构造
     */
    public FitemFunction() {
    }

    /**
     * 构造
     *
     * @param title     标题
     * @param content   内容
     * @param resId     图片资源
     * @param jumpClass 要跳转的类
     */
    public FitemFunction(String title, String content, @IdRes int resId, Class jumpClass) {
        this.title = title;
        this.content = content;
        this.resID = resId;
        this.jumpClass = jumpClass;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_function;
    }

    @Override
    protected float getSpanRatio() {
        return 1;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        // 显示 功能名称
        vh.setText(R.id.tv_title, title)

                // 显示 功能介绍
                .setText(R.id.tv_content, content)

                // 显示 预览图片
                .setImageResource(R.id.iv_preview, resID)

                // 跳转事件
                .setItemClick(v -> context.startActivity(new Intent(context, jumpClass)));
    }
}
