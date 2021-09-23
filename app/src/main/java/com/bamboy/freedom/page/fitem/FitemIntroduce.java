package com.bamboy.freedom.page.fitem;

import android.content.Context;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;

import java.util.List;

public class FitemIntroduce extends FreedomItem {

    /**
     * 介绍
     */
    public String introduce;

    /**
     * 构造
     *
     * @param introduce     介绍
     */
    public FitemIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_introduce;
    }

    @Override
    protected float getSpanRatio() {
        return 1;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        // 显示 介绍
        vh.setText(R.id.tv_introduce, introduce);
    }
}
