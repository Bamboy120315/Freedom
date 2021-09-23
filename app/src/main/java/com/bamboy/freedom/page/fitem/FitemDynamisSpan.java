package com.bamboy.freedom.page.fitem;

import android.content.Context;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;

import java.util.List;

public class FitemDynamisSpan extends FreedomItem {

    /**
     * 跨的列数
     */
    public int span;

    /**
     * 构造
     */
    public FitemDynamisSpan() {
    }

    /**
     * 构造
     */
    public FitemDynamisSpan(int span) {
        this.span = span;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_dunamic_span;
    }

    @Override
    protected int getSpanLength() {
        return span;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        vh.setText(R.id.tv_text, "这个条目跨" + span + "列");
    }
}
