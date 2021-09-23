package com.bamboy.freedom.page.fitem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.util.DensityUtils;

import java.util.List;

public class FitemWeChat extends FreedomItem {

    /**
     * 是否是自己
     */
    public boolean isOneself;
    /**
     * 头像
     */
    public int headResID;
    /**
     * 昵称
     */
    public String nickname;
    /**
     * 内容
     */
    public String content;
    /**
     * 条目容器
     */
    public View itemView;
    /**
     * 头像View
     */
    public ImageView iv_head;
    /**
     * 昵称View
     */
    public TextView tv_nickname;
    /**
     * 内容View
     */
    public TextView tv_content;

    /**
     * 构造
     */
    public FitemWeChat() {
    }

    /**
     * 构造
     *
     * @param headResID 头像
     * @param nickname  昵称
     * @param content   内容
     * @param isOneself 是否是自己
     */
    public FitemWeChat(@IdRes int headResID, String nickname, String content, boolean isOneself) {
        this.headResID = headResID;
        this.nickname = nickname;
        this.content = content;
        this.isOneself = isOneself;
    }

    @Override
    protected int bindLayoutId() {
        return isOneself ? R.layout.fitem_wechat_right : R.layout.fitem_wechat_left;
    }

    @Override
    public void initBindView(Context context, BaseViewHolder vh, List<FreedomItem> list, int position) {
        // 记录条目View，摇晃时使用
        itemView = vh.itemView;
        iv_head = vh.getView(R.id.iv_head);
        tv_nickname = vh.getView(R.id.tv_nickname);
        tv_content = vh.getView(R.id.tv_content);

        vh
                // 显示头像
                .setImageResource(iv_head, headResID)
                // 显示昵称
                .setText(tv_nickname, nickname)
                // 显示内容
                .setText(tv_content, content);

    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于 震荡动画  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 中心点
     */
    private int centralPointX, centralPointY;
    /**
     * 内容中心点
     */
    private int contentViewPointX, contentViewPointY;
    /**
     * 条目在中心点的位置
     */
    boolean isLeft, isTop;
    /**
     * 屏幕宽高
     */
    private int windowWidth, windowHeight;
    /**
     * 震荡比率
     */
    private float scaleX, scaleY;
    /**
     * 动画时长
     */
    private float animDuration = 300;
    /**
     * 插值器
     */
    private BounceInterpolator bounceInterpolator = new BounceInterpolator();

    /**
     * 震荡
     *
     * @param locationX
     * @param locationY
     */
    public void shock(Activity activity, int locationX, int locationY) {
        if (tv_content == null) {
            return;
        }

        centralPointX = locationX;
        centralPointY = locationY;

        // 内容坐标
        int[] location = new int[2];
        tv_content.getLocationOnScreen(location);
        contentViewPointX = location[0] + tv_content.getWidth() / 2;
        contentViewPointY = location[1] + tv_content.getHeight() / 2;

        // 获取屏幕高度
        if (windowHeight == 0) {
            Point outSize = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(outSize);
            windowWidth = outSize.x;
            windowHeight = outSize.y;
        }

        // 计算比率
        scaleX = (float) tv_content.getWidth() / (float) windowWidth;
        scaleY = (float) Math.abs(centralPointY - contentViewPointY) / (float) windowHeight;

        // 开始收拢动画
        frownAnim(activity);
    }

    /**
     * 收拢动画
     */
    private void frownAnim(Context context) {
        isLeft = contentViewPointX < centralPointX;
        isTop = contentViewPointY < centralPointY;

        int dp10 = DensityUtils.dip2px(context, 10);
        int dp3 = DensityUtils.dip2px(context, 3);

        // 头像收拢
        iv_head
                .animate()
                .translationX(isLeft ? (dp10 + dp3 * 2) : -(dp10 + dp3 * 2))
                .translationY(isTop ? dp3 : -dp3)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // 动画结束后散开
                        disperse(context);
                    }
                })
                .setDuration((long) (animDuration * (scaleY + 0.5)));

        // 昵称收拢
        if (tv_nickname != null) {
            tv_nickname
                    .animate()
                    .translationX(isLeft ? (dp10 + dp3) : -(dp10 + dp3))
                    .translationY(isTop ? dp3 * 2 : -dp3 * 2)
                    .setDuration((long) (animDuration * (scaleY + 0.5)));
        }

        // 内容收拢
        tv_content
                .animate()
                .translationX(isLeft ? dp10 : -dp10)
                .translationY(isTop ? dp3 : -dp3)
                .setDuration((long) (animDuration * (scaleY + 0.5)));

    }

    /**
     * 散开
     */
    private void disperse(Context context) {
        int dp20 = DensityUtils.dip2px(context, 20);
        int dp10 = DensityUtils.dip2px(context, 10);
        int dp3 = DensityUtils.dip2px(context, 3);

        // 头像横向移动
        ObjectAnimator.ofFloat(iv_head, "translationX",
                iv_head.getTranslationX(),
                (isLeft ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                0)
                .setDuration((long) (animDuration * (scaleY + 0.5)))
                .start();

        // 头像纵向移动
        ObjectAnimator.ofFloat(iv_head, "translationY",
                iv_head.getTranslationY(),
                (isTop ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                0)
                .setDuration((long) (animDuration * (scaleY + 0.5)))
                .start();

        // 头像旋转
        ObjectAnimator iv_headRotation = ObjectAnimator.ofFloat(iv_head, "Rotation",
                iv_head.getRotation(),
                (isLeft ? -40 : 40) * (scaleY + 0.8f),
                -(isLeft ? -30 : 30) * (scaleY + 0.8f),
                (isLeft ? -20 : 20) * (scaleY + 0.8f),
                -(isLeft ? -10 : 10) * (scaleY + 0.8f),
                0);
        iv_headRotation.setDuration((long) (animDuration * (scaleY * 4 + 1.5)));
        iv_headRotation.start();


        if (tv_nickname != null) {
            // 昵称横向移动
            ObjectAnimator.ofFloat(tv_nickname, "translationX",
                    tv_nickname.getTranslationX(),
                    (isLeft ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                    0)
                    .setDuration((long) (animDuration * (scaleY + 0.5)))
                    .start();

            // 昵称纵向移动
            ObjectAnimator.ofFloat(tv_nickname, "translationY",
                    tv_nickname.getTranslationY(),
                    (isTop ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                    0)
                    .setDuration((long) (animDuration * (scaleY + 0.5)))
                    .start();

            tv_nickname.setPivotX(0 - dp10 + tv_nickname.getWidth() / 4f);
            // 昵称旋转
            ObjectAnimator tv_nicknameRotation = ObjectAnimator.ofFloat(tv_nickname, "Rotation",
                    tv_nickname.getRotation(),
                    (isLeft ? -12 : 12) * (scaleY + 0.8f),
                    -(isLeft ? -8 : 8) * (scaleY + 0.8f),
                    (isLeft ? -4 : 4) * (scaleY + 0.8f),
                    0);
            tv_nicknameRotation.setDuration((long) (animDuration * (scaleY * 6 + 1.5)));
            tv_nicknameRotation.start();
        }


        // 内容横向移动
        ObjectAnimator.ofFloat(tv_content, "translationX",
                tv_content.getTranslationX(),
                (isLeft ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                0)
                .setDuration((long) (animDuration * (scaleY + 0.5)))
                .start();

        // 内容纵向移动
        ObjectAnimator.ofFloat(tv_content, "translationY",
                tv_content.getTranslationY(),
                (isTop ? -(dp10 + dp3) : (dp10 + dp3)) * (scaleY + 0.3f),
                0)
                .setDuration((long) (animDuration * (scaleY + 0.5)))
                .start();

        tv_content.setPivotX(isLeft ? dp20 : tv_content.getWidth() - dp20);
        tv_content.setPivotY(isTop ? dp20 : tv_content.getHeight() - dp20);

        // 内容横向翻转
        ObjectAnimator tv_contentRotationX = ObjectAnimator.ofFloat(tv_content, "RotationX",
                tv_content.getRotationX(),
                (isTop ? 25 : -25) * ((1 - scaleY) * 0.4f + 0.6f) * (1 - scaleX) * 1.3f,
                0);
        tv_contentRotationX.setDuration((long) (animDuration * (scaleY * 5.3f + 2.8)));
        tv_contentRotationX.setInterpolator(bounceInterpolator);
        tv_contentRotationX.start();

        // 内容纵向翻转
        ObjectAnimator tv_contentRotationY = ObjectAnimator.ofFloat(tv_content, "RotationY",
                tv_content.getRotationY(),
                (isLeft ? -25 : 25) * ((1 - scaleY) * 0.4f + 0.6f) * (1 - scaleX) * 1.3f,
                0);
        tv_contentRotationY.setDuration((long) (animDuration * (scaleY * 5.3f + 2.8)));
        tv_contentRotationY.setInterpolator(bounceInterpolator);
        tv_contentRotationY.start();

    }


}
