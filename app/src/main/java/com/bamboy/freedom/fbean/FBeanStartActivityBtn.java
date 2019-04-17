package com.bamboy.freedom.fbean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;

import java.util.List;

/**
 * 单个按钮的条目的Bean
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class FBeanStartActivityBtn extends FreedomBean {

    private String text;
    private Class startClass;

    /**
     * 构造函数
     */
    public FBeanStartActivityBtn() {
    }

    /**
     * 构造函数
     *
     * @param text       按钮上的文字
     * @param startClass 要打开的Activity的类名
     */
    public FBeanStartActivityBtn(String text, Class startClass) {
        this.text = text;
        this.startClass = startClass;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getStartClass() {
        return startClass;
    }

    public void setStartClass(Class startClass) {
        this.startClass = startClass;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    /**
     * 设置本Bean所对应的条目类型
     */
    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_BUTTON);
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
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                BtnViewHolder vh = (BtnViewHolder) viewHolder;
                vh.btn.setText(getText());

                vh.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, viewHolder);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 按钮
     */
    public static class BtnViewHolder extends ViewHolderManager.ViewHolder {
        public Button btn;

        public BtnViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_button);

            btn = itemView.findViewById(R.id.btn);
        }

    }
}
