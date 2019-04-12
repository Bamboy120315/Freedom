package com.bamboy.freedom.page.fbean;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;

import java.util.List;

/**
 * 个人信息条目Bean
 * <p>
 * Created by Bamboy on 2019/4/11.
 */
public class FBeanPersonalInfo extends FreedomBean {

    private String name;

    public FBeanPersonalInfo() {
    }

    /**
     * 构造函数
     *
     * @param name 标题
     */
    public FBeanPersonalInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_PERSONAL_INFO);
    }

    @Override
    protected void initBindView(final List list) {

        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                final PersonalInfoViewHolder vh = (PersonalInfoViewHolder) viewHolder;

                vh.tv_name.setText(getName());
            }
        });
    }

    /**
     * ViewHolder --> 个人信息条目
     */
    public static class PersonalInfoViewHolder extends ViewHolderManager.ViewHolder {
        public TextView tv_name;

        public PersonalInfoViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_personal_info);

            tv_name = itemView.findViewById(R.id.tv_name);
        }

    }
}
