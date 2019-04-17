package com.bamboy.freedom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bamboy.freedom.fbean.FBeanDialogueLeft;
import com.bamboy.freedom.fbean.FBeanDialogueRight;
import com.bamboy.freedom.fbean.FBeanFootPrompt;
import com.bamboy.freedom.fbean.FBeanMusic;
import com.bamboy.freedom.fbean.FBeanNewsImg;
import com.bamboy.freedom.fbean.FBeanNewsText;
import com.bamboy.freedom.fbean.FBeanPagingItem;
import com.bamboy.freedom.fbean.FBeanText;
import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;
import com.bamboy.freedom.ui.freedom.manager.ManagerB;
import com.bamboy.freedom.ui.smartrefresh.SmartRefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshInternal;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.footer.SlopeFooter;
import com.bamboy.freedom.ui.smartrefresh.listener.OnLoadMoreListener;
import com.bamboy.freedom.ui.smartrefresh.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ActLoadFooter extends AppCompatActivity implements FreedomCallback {

    /**
     * 下拉刷新容器
     */
    private SmartRefreshLayout refreshLayout;

    /**
     * 列表对象
     */
    private RecyclerView recycler;

    /**
     * 数据源
     */
    private List<FreedomBean> mList;

    /**
     * 适配器
     */
    private FreedomAdapter mAdapter;
    /**
     * 每页条数
     */
    private final int PAGESIZE = 10;
    /**
     * 当前页数
     */
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_load_footer);
        initBack();

        setTitle("上拉加载");

        refreshLayout = findViewById(R.id.refreshLayout);
        recycler = findViewById(R.id.recycler);

        // 初始化列表数据
        initRecycler();

        // 初始化下拉刷新
        initRefreshLayout();
    }

    /**
     * 初始化下拉刷新
     */
    private void initRefreshLayout() {
        // 下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                if (mList == null){
                    mList = new ArrayList<>();
                } else {
                    mList.clear();
                }
                page = 1;

                // 加载数据
                loadData();
            }
        });

        // 上拉加载监听
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 加载数据
                loadData();
            }
        });
    }

    // =============================================================================================
    // ======================== 以 下 是 RecyclerView 相 关 =========================================
    // =============================================================================================

    /**
     * 初始化列表数据
     */
    private void initRecycler() {
        // 初始化数据
        loadData();

        // 实例化RecyclerView
        // 把每行平分成2份
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                FreedomBean bean = mList.get(position);
                // 获取当前这个条目占几份
                return bean.getSpanSize(2);
            }
        });
        recycler.setLayoutManager(manager);

        recycler.setItemAnimator(null);
        mAdapter = new FreedomAdapter(this, mList);
        recycler.setAdapter(mAdapter);
    }

    /**
     * 点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        switch (view.getId()) {
            case R.id.rl_root:
                FBeanPagingItem bean = (FBeanPagingItem) mList.get(position);
                Toast.makeText(
                        this,
                        "点击了第" + bean.getPagination() + "页的第" + bean.getSerialNumber() + "个",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void loadData() {

        // 模拟加载数据
        List<FBeanPagingItem> list = new ArrayList();
        for (int i = 0; i < PAGESIZE; i++) {
            list.add(new FBeanPagingItem(page, i + 1));

            // 模拟数据一共只有53条
            if (page == 5 && i == 2) {
                break;
            }
        }

        // 是否是最后一页判断
        if (list.size() < PAGESIZE) {
            if (refreshLayout != null) {
                refreshLayout.setNoMoreData(true);
            }
        }

        // 如果mList为空，则进行初始化
        if (mList == null) {
            mList = new ArrayList();
        }

        // 将数据放到数据源中
        mList.addAll(list);
        page++;

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        if (refreshLayout != null){
            refreshLayout.finishLoadMore();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * TitleBar添加返回按钮
     */
    private void initBack() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * TitleBar返回按钮点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
