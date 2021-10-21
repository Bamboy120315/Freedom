package com.bamboy.freedom.page;

import static com.bamboy.freedom.page.util.DataUtil.getDataToWeiBo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.clickcallback.OnClickCallback;
import com.bamboy.freedom.page.model.WeiboModel;
import com.bamboy.freedom.page.fitem.FitemWeibo;

import java.util.ArrayList;
import java.util.List;

public class WeiboActivity extends AppCompatActivity implements OnClickCallback {

    RecyclerView recycler;
    List<FitemWeibo> mList;
    FreedomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setTitle("仿微博（单列）");

        recycler = findViewById(R.id.recycler);

        // 获取数据
        mList = new ArrayList<>();
        List<WeiboModel> list = getDataToWeiBo();
        for (WeiboModel bean : list) {
            mList.add(new FitemWeibo(bean));
        }

        // 初始化列表
        notifyList();

        // 设置条目里的View的点击事件
        mAdapter.setViewClickCallback(this);
    }

    /**
     * 初始化数据
     */
    @SuppressLint("NotifyDataSetChanged")
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

    @Override
    public void onClick(View view, int position, BaseViewHolder holder) {
        String action = "";
        switch (view.getId()) {
            case R.id.rl_share:
                action = "转发";
                break;
            case R.id.rl_speech:
                action = "评论";
                break;
            case R.id.rl_like:
                action = "点赞";
                break;
        }
        Toast.makeText(this, "点击了第" + (position + 1) + "个条目的“" + action + "”", Toast.LENGTH_SHORT).show();
    }

}