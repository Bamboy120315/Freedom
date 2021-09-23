package com.bamboy.freedom.freedom;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

/**
 * 列表Bean的基类
 * <p/>
 * 所有用于Item的Bean均要继承本类，
 * 以保证对插件式列表的支持
 * <p/>
 * Created by Bamboy on 2021/7/28.
 */
public abstract class FreedomItem implements Serializable {

    /**
     * viewHolder
     */
    public BaseViewHolder viewHolder;

    /**
     * 保存viewHolder
     *
     * @param viewHolder
     */
    public void setViewHolder(BaseViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    /**
     * 执行BindView
     *
     * @param list
     */
    public abstract void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position);

    /**
     * 绑定布局ID
     *
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 获取条目类型
     *
     * @return
     */
    public int getItemType() {
        return bindLayoutId();
    }

    /**
     * 当前条目所占总列数的比例
     *
     * @return
     */
    protected float getSpanRatio() {
        return 0;
    }

    /**
     * 当前条目所占步长
     *
     * @return
     */
    protected int getSpanLength() {
        return 0;
    }

    /**
     * 算出最终步长
     *
     * @param spanCount 总列数
     * @return
     */
    public int getSpanSize(int spanCount) {
        // 获取设置的步长
        int spanSize = getSpanLength();

        // 如果步长为0，则以总列数按照比例算出步长
        if (spanSize <= 0) {
            spanSize = (int) (spanCount * getSpanRatio());
        }

        // 步长最小值为1，即最小占用一列
        if (spanSize <= 0) {
            spanSize = 1;
        }
        return spanSize;
    }

}
