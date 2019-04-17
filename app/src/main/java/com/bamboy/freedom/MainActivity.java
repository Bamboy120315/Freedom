package com.bamboy.freedom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bamboy.freedom.fbean.FBeanStartActivityBtn;
import com.bamboy.freedom.fbean.FBeanText;
import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FreedomCallback {

    private List<FreedomBean> mList;
    private FreedomAdapter mAdapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_freedom);

        recycler = findViewById(R.id.recycler);

        init();

    }

    public void init() {

        mList = new ArrayList<>();

        mList.add(new FBeanText("万能适配器："));
        mList.add(new FBeanStartActivityBtn("打开新闻列表(单列)", ActNews.class));
        mList.add(new FBeanStartActivityBtn("打开音乐列表(多列)", ActMusic.class));
        mList.add(new FBeanStartActivityBtn("打开混合列表(都有)", ActMix.class));

        mList.add(new FBeanText("下拉刷新："));
        mList.add(new FBeanStartActivityBtn("默认风格", ActSmartRefresh.class));
        mList.add(new FBeanStartActivityBtn("经典风格", ActSmartRefreshClassice.class));
        mList.add(new FBeanStartActivityBtn("个人中心", ActSmartRefreshPersonalCenter.class));
        mList.add(new FBeanStartActivityBtn("头部固定", ActSmartRefreshTopFixed.class));

        mList.add(new FBeanText("分页加载："));
        mList.add(new FBeanStartActivityBtn("上拉加载", ActLoadFooter.class));
        mList.add(new FBeanStartActivityBtn("自动、无感加载", ActLoadAuto.class));

        mAdapter = new FreedomAdapter(this, mList);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(mAdapter);

    }

    /**
     * 条目里的View的点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        switch (view.getId()) {
            case R.id.btn:
                if (false == mList.get(position) instanceof FBeanStartActivityBtn) {
                    break;
                }
                FBeanStartActivityBtn bean = (FBeanStartActivityBtn) mList.get(position);
                if (bean.getStartClass() != null) {
                    Intent intent = new Intent(MainActivity.this, bean.getStartClass());
                    MainActivity.this.startActivity(intent);
                }
                break;
        }
    }

}
