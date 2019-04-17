package com.bamboy.freedom.fbean;

import android.content.Context;
import android.text.Html;
import android.view.View;
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
 * 分页加载Activity的Bean
 * <p>
 * 用于在分页加载页展示
 * <p>
 * Created by Bamboy on 2017/4/11.
 */
public class FBeanPagingItem extends FreedomBean {

    private int Pagination;
    private int serialNumber;

    public FBeanPagingItem() {
    }

    public FBeanPagingItem(int pagination, int serialNumber) {
        Pagination = pagination;
        this.serialNumber = serialNumber;
    }

    public int getPagination() {
        return Pagination;
    }

    public void setPagination(int pagination) {
        Pagination = pagination;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_PAGING);
    }

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    protected void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, ViewHolderManager.ViewHolder viewHolder, final int position) {
                final PagingItemViewHolder vh = (PagingItemViewHolder) viewHolder;

                // 第N页
                vh.tv_pagination.setText(Html.fromHtml("<small><small><small>" + "第" + "</small></small></small>" +
                        getPagination() +
                        "<small><small><small>" + "页" + "</small></small></small>"));

                // 由于本Demo最大的优点就是是无感知
                // 所以为了方便同学们明确感知到分批加载
                // 每一页以不同颜色来进行区分
                switch (getPagination() % 5) {
                    case 1:
                    default:
                        vh.tv_pagination.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        break;
                    case 2:
                        vh.tv_pagination.setBackgroundColor(0xFF993333);
                        break;
                    case 3:
                        vh.tv_pagination.setBackgroundColor(0xFFFF9900);
                        break;
                    case 4:
                        vh.tv_pagination.setBackgroundColor(0xFF999933);
                        break;
                    case 0:
                        vh.tv_pagination.setBackgroundColor(0xFFFF0033);
                        break;
                }

                //第N个条目
                vh.tv_serial_number.setText("第" + getSerialNumber() + "个");

                vh.rl_root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 分页加载Item
     */
    public static class PagingItemViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_root;
        public TextView tv_pagination;
        public TextView tv_serial_number;

        public PagingItemViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_paging_load);

            rl_root = itemView.findViewById(R.id.rl_root);
            tv_pagination = itemView.findViewById(R.id.tv_pagination);
            tv_serial_number = itemView.findViewById(R.id.tv_serial_number);
        }

    }
}
