package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToWeiBoJSON;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.page.fitem.FitemWeibo;
import com.bamboy.freedom.page.modle.WeiboNetworkModle;

import java.util.ArrayList;
import java.util.List;

public class WeiboJsonActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<FitemWeibo> mList;
    FreedomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("仿微博（单列）");

        recycler = findViewById(R.id.recycler);

        // 获取json数据
        String json = getDataToWeiBoJSON();

        // 解析json
        WeiboNetworkModle modle = JSON.parseObject(json, WeiboNetworkModle.class);
        // 解析json
        mList = modle.data;

        // 初始化列表
        notifyList();
    }

    /**
     * 初始化数据
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