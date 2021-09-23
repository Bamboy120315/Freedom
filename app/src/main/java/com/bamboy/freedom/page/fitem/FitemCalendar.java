package com.bamboy.freedom.page.fitem;

import android.content.Context;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;

import java.util.List;

public class FitemCalendar extends FreedomItem {

    private static OvershootInterpolator interpolatorShow = new OvershootInterpolator(1.5f);
    private static AnticipateInterpolator interpolatorHide = new AnticipateInterpolator(1.5f);

    /**
     * 日期 --> 日
     */
    public int day;
    /**
     * 条目高度
     */
    public int height;
    /**
     * 条目容器View
     */
    public View rl_item_root;
    /**
     * 日期View
     */
    public TextView tv_date_day;

    /**
     * 构造
     *
     * @param day    日期 --> 天
     * @param height 条目高度
     */
    public FitemCalendar(int day, int height) {
        this.day = day;
        this.height = height;
    }

    /**
     * 获取中心点坐标
     *
     * @return
     */
    public int[] getCentralPointLocation() {
        if (tv_date_day == null) {
            return new int[]{0, 0};
        }

        // 内容坐标
        int[] location = new int[2];
        tv_date_day.getLocationOnScreen(location);
        location[0] += tv_date_day.getWidth() / 2;
        location[1] += tv_date_day.getHeight() / 2;

        return location;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_calendar;
    }

    /**
     * 当前条目所占总列数的比例
     *
     * @return
     */
    @Override
    protected float getSpanRatio() {
        return 1f / 7f;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        rl_item_root = vh.getView(R.id.rl_item_root);
        tv_date_day = vh.getView(R.id.tv_date_day);

        // 显示 日期
        vh.setText(tv_date_day, day == 0 ? "" : String.valueOf(day));

        // 设置条目高度
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, height);
        rl_item_root.setLayoutParams(layoutParams);
        rl_item_root.invalidate();
    }
}
