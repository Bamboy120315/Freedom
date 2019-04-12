package com.bamboy.freedom.page.fbean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerB;

import java.util.List;

/**
 * 音乐条目Bean
 * <p/>
 * Created by Bamboy on 2017/5/10.
 */
public class FBeanMusic extends FreedomBean {

    /**
     * 新闻标题
     */
    private String song;
    /**
     * 新闻内容
     */
    private String singer;

    public FBeanMusic() {
    }

    /**
     * 构造
     *
     * @param song   歌曲
     * @param singer 歌手
     */
    public FBeanMusic(String song, String singer) {
        this.song = song;
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    /**
     * 当前条目占屏幕的二分之一
     *
     * @param spanCount 分割总数
     * @return
     */
    @Override
    public int getSpanSize(int spanCount) {
        return spanCount / 2;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerB.ITEM_TYPE_MUSIC);
    }

    @Override
    protected void initBindView(final List list) {

        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                final MusicViewHolder vh = (MusicViewHolder) viewHolder;

                vh.tv_song.setText(getSong());
                vh.tv_singer.setText(getSinger());

                vh.rl_music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 点击事件
                        // 如果不需要和Activity进行交互，
                        // 那么直接在这里写点击事件即可
                        //
                        // 如果需要和Activity进行交互，
                        // 那么Activity实现FreedomCallback接口，
                        // 并在onClickCallback里编写代码，
                        // 即可触发回调，
                        // 以和Activity进行交互。
                        //
                        // 注意：
                        // 该Activity必须实现FreedomCallback接口才能触发回调，
                        // 否则会报错
                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 主页的按钮
     */
    public static class MusicViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_music;
        public TextView tv_song;
        public TextView tv_singer;

        public MusicViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_music);

            rl_music = (RelativeLayout) itemView.findViewById(R.id.rl_music);
            tv_song = (TextView) itemView.findViewById(R.id.tv_song);
            tv_singer = (TextView) itemView.findViewById(R.id.tv_singer);
        }
    }
}
