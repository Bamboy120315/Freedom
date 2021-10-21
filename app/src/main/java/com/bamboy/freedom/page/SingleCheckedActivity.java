package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToGiftList;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.fitem.FitemGift;
import com.bamboy.freedom.page.util.DensityUtils;

import java.util.List;

public class SingleCheckedActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<FitemGift> mList;
    FreedomAdapter mAdapter;

    private int lastCheckGiftId = -1;
    private FitemGift lastCheckItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("条目单选");
        recycler = findViewById(R.id.recycler);
        int dp3 = DensityUtils.dip2px(this, 3);
        recycler.setPadding(dp3, dp3, dp3, dp3);

        // 获取数据
        mList = getDataToGiftList();

        // 初始化列表
        notifyList();

        // 条目点击事件
        mAdapter.setItemClickCallback((View view, int position, BaseViewHolder holder) -> {
            if (mList == null || position >= mList.size()) {
                return;
            }

            // 获取当前选择的条目
            FitemGift item = mList.get(position);

            // 如果当前选择的就是这个条目，则不处理
            if (lastCheckGiftId == item.modle.goods_id) {
                return;
            }

            // 取消上一个条目的选中状态
            if (lastCheckItem != null) {
                lastCheckItem.setCheck(false);
            }

            // 设置当前条目的选中状态
            item.setCheck(true);
            lastCheckGiftId = item.modle.goods_id;
            lastCheckItem = item;
        });
    }

    /**
     * 初始化数据
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            // 把每行平分成4份
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    FreedomItem bean = mList.get(position);
                    // 获取当前这个条目的步长
                    return bean.getSpanSize(manager.getSpanCount());
                }
            });
            recycler.setLayoutManager(manager);
            mAdapter = new FreedomAdapter(this, mList);
            recycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}