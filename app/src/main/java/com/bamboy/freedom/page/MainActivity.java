package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToMain;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.fitem.FitemFunction;
import com.bamboy.freedom.page.fitem.FitemIntroduce;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<FreedomItem> mList;
    FreedomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        recycler = findViewById(R.id.recycler);

        // 获取数据
        mList = getDataToMain();

        notifyList();
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recycler.setLayoutManager(linearLayoutManager);
            mAdapter = new FreedomAdapter(this, mList);
            recycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}