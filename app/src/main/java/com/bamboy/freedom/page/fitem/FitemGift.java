package com.bamboy.freedom.page.fitem;

import android.content.Context;
import android.view.View;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.model.GiftModel;

import java.util.List;

public class FitemGift extends FreedomItem {

    /**
     * 介绍
     */
    public GiftModel modle;
    /**
     * 是否被选中
     */
    public boolean isCheck = false;
    /**
     * 边框View
     */
    public View view_border;

    /**
     * 构造
     *
     * @param modle 介绍
     */
    public FitemGift(GiftModel modle) {
        this.modle = modle;
    }

    /**
     * 获取选中状态
     *
     * @return
     */
    public boolean isCheck() {
        return isCheck;
    }

    /**
     * 设置选中状态
     *
     * @param check
     */
    public void setCheck(boolean check) {
        isCheck = check;

        if (view_border == null) {
            return;
        }
        if (check && view_border.getAlpha() != 1) {
            view_border.animate()
                    .alpha(1)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start();
        } else if (!check && view_border.getAlpha() != 0) {
            view_border.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(100)
                    .start();
        }
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_gift;
    }

    @Override
    protected float getSpanRatio() {
        return 1f / 4f;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        vh

                // 礼物图片
                .setImageResource(R.id.iv_gift, modle.images_static)

                // 礼物名称
                .setText(R.id.tv_gift_name, modle.name)

                // 礼物价格
                .setText(R.id.tv_price, String.valueOf(modle.beans));


        // 是否被选中状态
        view_border = vh.getView(R.id.view_border);

        // 如果自身带有动画，则取消动画，避免条目复用带来的显示异常
        if (view_border.animate() != null) {
            view_border.animate().cancel();
        }

        if (isCheck) {
            view_border.setScaleX(1);
            view_border.setScaleY(1);
            view_border.setAlpha(1);
        } else {
            view_border.setScaleX(0.8f);
            view_border.setScaleY(0.8f);
            view_border.setAlpha(0);
        }
    }

}
