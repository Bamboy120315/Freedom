package com.bamboy.freedom.page.fitem;

import android.content.Context;
import android.text.TextUtils;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.model.WeiboModel;

import java.util.List;

public class FitemWeibo extends FreedomItem {

    public WeiboModel bean;

    /**
     * 构造
     * @param bean
     */
    public FitemWeibo(WeiboModel bean) {
        this.bean = bean;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_weibo;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        vh
                // 显示 头像
                .setImageResource(R.id.iv_head, bean.headId)
                // 显示 昵称
                .setText(R.id.tv_nickname, bean.nickname)
                // 显示 时间
                .setText(R.id.tv_time, bean.time)
                // 显示 内容
                .setText(R.id.tv_content, bean.content)
                // 显示 位置
                .setText(R.id.tv_location, bean.location == null ? "" : bean.location)
                // 显示 位置容器是否显示
                .setVisible(R.id.rl_location, !TextUtils.isEmpty(bean.location))
                // 显示 转发数
                .setText(R.id.tv_share, bean.shareCount <= 0 ? "转发" : String.valueOf(bean.shareCount))
                // 显示 评论数
                .setText(R.id.tv_speech, bean.speechCount <= 0 ? "评论" : String.valueOf(bean.speechCount))
                // 显示 点赞数
                .setText(R.id.tv_like, bean.likeCount <= 0 ? "点赞" : String.valueOf(bean.likeCount))

                // 设置点击事件 --> 转发
                .setOnClickListener(R.id.rl_share)
                // 设置点击事件 --> 评论
                .setOnClickListener(R.id.rl_speech)
                // 设置点击事件 --> 点赞
                .setOnClickListener(R.id.rl_like);

        // 处理内容里面的图片
        if (bean.contentImg <= 0) {
            vh.setGone(R.id.iv_content);
        } else {
            vh.setVisible(R.id.iv_content);
            vh.setImageResource(R.id.iv_content, bean.contentImg);
        }
    }
}