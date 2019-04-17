package com.bamboy.freedom.fbean;

import android.content.Context;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.ViewHolderBindListener;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;

import java.util.List;

/**
 * 个人资产条目Bean
 * <p>
 * Created by Bamboy on 2019/4/11.
 */
public class FBeanPersonalAssets extends FreedomBean {

    /**
     * 余额
     */
    private String balance;
    /**
     * 券
     */
    private String coupons;
    /**
     * 卡
     */
    private String card;
    /**
     * 积分
     */
    private String points;
    /**
     * 条目容器
     */
    public ViewGroup itemRoot;

    public FBeanPersonalAssets() {
    }

    /**
     * 构造
     *
     * @param balance 余额
     * @param coupons 券
     * @param card    卡
     * @param points  积分
     */
    public FBeanPersonalAssets(String balance, String coupons, String card, String points) {
        this.balance = balance;
        this.coupons = coupons;
        this.card = card;
        this.points = points;
    }

    /**
     * 余额
     */
    public String getBalance() {
        return balance;
    }

    /**
     * 余额
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * 券
     */
    public String getCoupons() {
        return coupons;
    }

    /**
     * 券
     */
    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    /**
     * 卡
     */
    public String getCard() {
        return card;
    }

    /**
     * 卡
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * 积分
     */
    public String getPoints() {
        return points;
    }

    /**
     * 积分
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     * 条目容器
     */
    public ViewGroup getItemRoot() {
        return itemRoot;
    }

    /**
     * 条目容器
     */
    public void setItemRoot(ViewGroup itemRoot) {
        this.itemRoot = itemRoot;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_PERSONAL_ASSETS);
    }

    @Override
    protected void initBindView(final List list) {

        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                final PersonalAssetsViewHolder vh = (PersonalAssetsViewHolder) viewHolder;

                // 存储条目容器
                setItemRoot(vh.ll_root);

                vh.tv_balance.setText(Html.fromHtml("<font><small>￥</small>"+getBalance() +"</font>"));
                vh.tv_coupons.setText(getCoupons());
                vh.tv_card.setText(Html.fromHtml("<font><small>￥</small>"+getCard() +"</font>"));
                vh.tv_points.setText(getPoints());
            }
        });
    }

    /**
     * ViewHolder --> 个人资产条目
     */
    public static class PersonalAssetsViewHolder extends ViewHolderManager.ViewHolder {

        /**
         * 条目容器
         */
        public LinearLayout ll_root;
        /**
         * 余额容器
         */
        public RelativeLayout rl_balance;
        /**
         * 余额
         */
        public TextView tv_balance;

        /**
         * 券容器
         */
        public RelativeLayout rl_coupons;
        /**
         * 券
         */
        public TextView tv_coupons;

        /**
         * 卡容器
         */
        public RelativeLayout rl_card;
        /**
         * 卡
         */
        public TextView tv_card;

        /**
         * 积分容器
         */
        public RelativeLayout rl_points;
        /**
         * 积分
         */
        public TextView tv_points;

        public PersonalAssetsViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_personal_assets);

            ll_root = itemView.findViewById(R.id.ll_root);
            rl_balance = itemView.findViewById(R.id.rl_balance);
            tv_balance = itemView.findViewById(R.id.tv_balance);
            rl_coupons = itemView.findViewById(R.id.rl_coupons);
            tv_coupons = itemView.findViewById(R.id.tv_coupons);
            rl_card = itemView.findViewById(R.id.rl_card);
            tv_card = itemView.findViewById(R.id.tv_card);
            rl_points = itemView.findViewById(R.id.rl_points);
            tv_points = itemView.findViewById(R.id.tv_points);
        }

    }
}
