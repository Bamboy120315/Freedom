package com.bamboy.freedom.page.fitem;

import android.content.Context;
import android.text.TextUtils;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;

import java.util.List;

public class FitemWeibo extends FreedomItem {

    /**
     * 头像
     */
    public int headId;
    /**
     * 昵称
     */
    public String nickname;
    /**
     * 时间
     */
    public String time;
    /**
     * 内容
     */
    public String content;
    /**
     * 图片
     */
    public int contentImg;
    /**
     * 定位
     */
    public String location;
    /**
     * 转发数
     */
    public int shareCount;
    /**
     * 评论数
     */
    public int speechCount;
    /**
     * 点赞数
     */
    public int likeCount;

    /**
     * 构造
     */
    public FitemWeibo() {
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fitem_weibo;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        vh

                // 显示 头像
                .setImageResource(R.id.iv_head, headId)

                // 显示 昵称
                .setText(R.id.tv_nickname, nickname)

                // 显示 时间
                .setText(R.id.tv_time, time)

                // 显示 内容
                .setText(R.id.tv_content, content)

                // 显示 位置
                .setText(R.id.tv_location, location == null ? "" : location)

                // 显示 位置容器是否显示
                .setVisible(R.id.rl_location, !TextUtils.isEmpty(location))

                // 显示 转发数
                .setText(R.id.tv_share, shareCount <= 0 ? "转发" : String.valueOf(shareCount))

                // 显示 评论数
                .setText(R.id.tv_speech, speechCount <= 0 ? "评论" : String.valueOf(speechCount))

                // 显示 点赞数
                .setText(R.id.tv_like, likeCount <= 0 ? "点赞" : String.valueOf(likeCount));

        // 处理内容里面的图片
        if (contentImg <= 0) {
            vh.setGone(R.id.iv_content);
        } else {
            vh.setVisible(R.id.iv_content);
            vh.setImageResource(R.id.iv_content, contentImg);
        }
    }
}
