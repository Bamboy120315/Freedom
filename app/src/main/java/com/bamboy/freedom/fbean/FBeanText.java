package com.bamboy.freedom.fbean;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;

import java.util.List;

/**
 * 文本的条目的Bean
 * <p>
 * Created by Bamboy on 2018/3/25.
 */
public class FBeanText extends FreedomBean {

    private String text;

    /**
     * 构造函数
     */
    public FBeanText() {
    }

    /**
     * 构造函数
     *
     * @param text 标题
     */
    public FBeanText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    /**
     * 设置本Bean所对应的条目类型
     */
    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_TEXT);
    }

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    public void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(Context context, ViewHolderManager.ViewHolder viewHolder, int position) {
                TextViewHolder vh = (TextViewHolder) viewHolder;

                vh.tv_title.setText(getText());
            }
        });
    }

    /**
     * ViewHolder --> 文本
     */
    public static class TextViewHolder extends ViewHolderManager.ViewHolder {
        public TextView tv_title;

        public TextViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_title);

            tv_title = itemView.findViewById(R.id.tv_title);
        }

    }
}
