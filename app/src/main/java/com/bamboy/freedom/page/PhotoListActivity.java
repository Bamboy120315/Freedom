package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToPhoto;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.fitem.FitemPhoto;
import com.bamboy.freedom.page.fitem.FitemPhoto;
import com.bamboy.freedom.page.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class PhotoListActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<FreedomItem> mList;
    FreedomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("图库列表（多列）");
        recycler = findViewById(R.id.recycler);
        int dp3 = DensityUtils.dip2px(this, 3);
        recycler.setPadding(dp3, dp3, dp3, dp3);

        // 获取数据
        mList = getDataToPhoto();

        notifyList();
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            // 把每行平分成3份
            GridLayoutManager manager = new GridLayoutManager(this, 3);
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