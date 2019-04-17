package com.bamboy.freedom.fbean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerC;

import java.util.List;

/**
 * Created by 程序猿C on 2017/5/19.
 */
public class FBeanDialogueRight extends FreedomBean {

    /**
     * 名字
     */
    private String name;
    /**
     * 内容
     */
    private String content;

    /**
     * 构造
     */
    public FBeanDialogueRight() {
    }

    /**
     * 构造
     *
     * @param name    名字
     * @param content 内容
     */
    public FBeanDialogueRight(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, ViewHolderManager.ViewHolder viewHolder, final int position) {
                final DialogueViewHolder vh = (DialogueViewHolder) viewHolder;

                vh.tv_name.setText(getName());
                vh.tv_content.setText(getContent());
                vh.tv_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });

            }
        });
    }

    @Override
    protected void initItemType() {
        setItemType(ManagerC.ITEM_TYPE_DIALOGUE_RIGHT);
    }

    public static class DialogueViewHolder extends ViewHolderManager.ViewHolder {

        TextView tv_name;
        TextView tv_content;

        public DialogueViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.fitem_dialogue_r);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);

        }
    }
}
