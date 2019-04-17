package com.bamboy.freedom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bamboy.freedom.fbean.FBeanFootPrompt;
import com.bamboy.freedom.fbean.FBeanPagingItem;
import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;

import java.util.ArrayList;
import java.util.List;

public class ActLoadAuto extends AppCompatActivity implements FreedomCallback {

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
     * 页脚的提示文字
     */
    private FBeanFootPrompt tvBean;

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
        setContentView(R.layout.act_load_auto);
        initBack();

        setTitle("自动、无感加载");

        recycler = findViewById(R.id.recycler);

        // 设置滚动监听
        setListener();

        // 初始化页脚提示文字
        tvBean = new FBeanFootPrompt();
        tvBean.setType(FBeanFootPrompt.TYPE_PAGE_SUCCEED);

        // 加载数据
        loadData();
    }

    /**
     * 设置滚动监听
     */
    protected void setListener() {
        // 为RecyclerView添加滑动监听
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {

                    //获取最后一个可见view的位置
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int lastPosition = linearManager.findLastVisibleItemPosition();

                    // 如果滑动到倒数第三条数据，就自动加载下一页数据
                    if (lastPosition >= layoutManager.getItemCount() - 4) {
                        loadData();
                    }

                }
            }
        });
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
            case R.id.tv_foot_prompt:                   // 底部提示文字点击事件
                if (tvBean.getType() != 2 && tvBean.getType() != 3) {
                    // 只要不是正在读取下一页 和 已经读取全部数据，点击即可加载数据
                    tvBean.setType(FBeanFootPrompt.TYPE_PAGE_SUCCEED);
                    loadData();
                }
                break;

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
        try {
            // 如果是正在加载下一页的时候，禁止加载
            if (tvBean.getType() != 1) {
                return;
            }

            // 先把底部文字改成正在加载
            tvBean.setType(FBeanFootPrompt.TYPE_LOAD_ING);

            // 模拟解析数据
            analyticalData();

            if (mAdapter == null) {
                mAdapter = new FreedomAdapter(this, mList);
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setItemAnimator(new DefaultItemAnimator());
                recycler.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }

            // 数据正常，把状态标记成读取完毕，可进行下页数据的读取
            tvBean.setType(FBeanFootPrompt.TYPE_PAGE_SUCCEED);
            page++;
        } catch (Exception e) {
            e.printStackTrace();

            if (tvBean != null) {
                // 如遇断网、数据解析失败等异常情况时，把状态标记成加载失败，点击可重新加载
                tvBean.setType(FBeanFootPrompt.TYPE_PAGE_FAILURE);
            }
        }
    }

    /**
     * 模拟解析数据
     */
    private void analyticalData() throws Exception {

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
            // 数据不足PageSize，说明最后一页
            tvBean.setType(FBeanFootPrompt.TYPE_ALL_SUCCEED);

            // 如果一条数据都没读到，说明是最后一页，就不用刷新列表，故return；
            if (list.size() == 0){
                return;
            }
        }

        // 如果mList为空，则进行初始化
        if (mList == null) {
            mList = new ArrayList();
        }

        // 移除页脚的提示文字
        if (mList.size() >= 1 && mList.get(mList.size() - 1) instanceof FBeanFootPrompt) {
            mList.remove(mList.size() - 1);
        }

        // 将数据放到数据源中
        mList.addAll(list);

        // 将页脚的提示文字放到数据源
        mList.add(tvBean);
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
