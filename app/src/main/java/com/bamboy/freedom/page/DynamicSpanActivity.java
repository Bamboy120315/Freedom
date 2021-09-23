package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToDynamicSpan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.fitem.FitemDynamisSpan;
import com.bamboy.freedom.page.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class DynamicSpanActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<FreedomItem> mList;
    FreedomAdapter mAdapter;
    int mSpanCount = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("动态跨列");
        recycler = findViewById(R.id.recycler);
        int dp3 = DensityUtils.dip2px(this, 3);
        recycler.setPadding(dp3, dp3, dp3, dp3);

        // 获取数据
        mList = getDataToDynamicSpan();

        notifyList();

        itemClick();
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            // 把每行平分成4份
            GridLayoutManager manager = new GridLayoutManager(this, mSpanCount);
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

    /**
     * 条目点击事件
     */
    private void itemClick(){
        mAdapter.setItemClickCallback(
                (View view, int position, BaseViewHolder holder) -> {
                    if (position >= mList.size() || !(mList.get(position) instanceof FitemDynamisSpan)) {
                        return;
                    }
                    FitemDynamisSpan item = (FitemDynamisSpan) mList.get(position);
                    if (item.span == mSpanCount) {
                        item.span = 1;
                    } else {
                        item.span += 1;
                    }
                    mAdapter.notifyItemChanged(position);
                });
    }
}