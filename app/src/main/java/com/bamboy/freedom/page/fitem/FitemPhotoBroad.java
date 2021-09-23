package com.bamboy.freedom.page.fitem;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.annotation.IdRes;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.PhotoInfoActivity;

import java.util.List;

public class FitemPhotoBroad extends FreedomItem {

    /**
     * 预览图
     */
    public int resID;

    /**
     * 构造
     */
    public FitemPhotoBroad() {
    }

    /**
     * 构造
     *
     * @param resId 图片资源
     */
    public FitemPhotoBroad(@IdRes int resId) {
        this.resID = resId;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_photo_broad;
    }

    @Override
    protected float getSpanRatio() {
        return 2f / 3f;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        ImageView iv_preview = vh.getView(R.id.iv_preview);

        // 显示 预览图片
        iv_preview.setImageResource(resID);

        iv_preview.setOnClickListener(v -> {
            Intent intent = new Intent(context, PhotoInfoActivity.class);
            intent.putExtra("resID", resID);

            context.startActivity(
                    intent, ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context,
                            iv_preview, "photo"
                    ).toBundle());
        });
    }
}
