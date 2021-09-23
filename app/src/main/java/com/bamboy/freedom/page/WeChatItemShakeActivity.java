package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToWeichat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.fitem.FitemWeChat;
import com.bamboy.freedom.page.util.DensityUtils;

import java.util.List;

public class WeChatItemShakeActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<FreedomItem> mList;
    LinearLayoutManager mLinearLayoutManager;
    FreedomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("仿微信炸弹条目摇晃");
        recycler = findViewById(R.id.recycler);

        // 获取数据
        mList = getDataToWeichat();

        notifyList();

        // 添加爆炸按钮
        addBombBtn();
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            mLinearLayoutManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(mLinearLayoutManager);
            mAdapter = new FreedomAdapter(this, mList);
            recycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 添加爆炸按钮
     */
    @SuppressLint("Range")
    private void addBombBtn() {
        ImageView bombBtn = new ImageView(this);
        int dp300 = DensityUtils.dip2px(this, 300);
        int dp60 = DensityUtils.dip2px(this, 60);
        int dp10 = DensityUtils.dip2px(this, 10);
        int dp6 = DensityUtils.dip2px(this, 6);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dp60, dp60);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.bottomMargin = dp60;
        bombBtn.setLayoutParams(params);
        bombBtn.setElevation(dp6);
        bombBtn.setPadding(dp10, dp10, dp10, dp10);
        bombBtn.setBackgroundResource(R.drawable.bg_circle_bomb);
        bombBtn.setImageResource(R.drawable.iv_bomb);
        bombBtn.setAlpha(-0.3f);
        bombBtn.setTranslationY(-dp300);
        bombBtn.setRotation(-180);
        bombBtn.setScaleX(10f);
        bombBtn.setScaleY(10f);
        ((ViewGroup) recycler.getParent()).addView(bombBtn);

        bombBtn.post(() ->
                bombBtn.animate()
                        .alpha(1)
                        .translationY(0)
                        .rotation(0)
                        .scaleX(1)
                        .scaleY(1)
                        .setInterpolator(new OvershootInterpolator(1f))
                        .setDuration(900));


        bombBtn.setOnClickListener(v -> {
            int[] location = new int[2];
            bombBtn.getLocationOnScreen(location);
            location[0] += bombBtn.getWidth() / 2;
            location[1] += bombBtn.getHeight() / 2;
            shock(location[0], location[1]);
        });
    }

    /**
     * 震荡
     *
     * @param locationX 震荡中心点X坐标
     * @param locationY 震荡中心点Y坐标
     */
    private void shock(int locationX, int locationY) {
        // 屏幕内可见的第一个条目的索引
        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        // 屏幕内可见的最后一个条目的索引
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        // 遍历条目，震荡屏幕内可见条目
        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            if (i < 0 || i >= mList.size()) {
                continue;
            }

            FreedomItem fItem = mList.get(i);
            if (fItem instanceof FitemWeChat == false) {
                continue;
            }

            // 震荡条目
            ((FitemWeChat) fItem).shock(this, locationX, locationY);
        }
    }
}