package com.bamboy.freedom;

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
import com.bamboy.freedom.fbean.FBeanMusic;
import com.bamboy.freedom.fbean.FBeanNewsImg;
import com.bamboy.freedom.fbean.FBeanNewsText;
import com.bamboy.freedom.fbean.FBeanText;
import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;
import com.bamboy.freedom.ui.freedom.manager.ManagerB;
import com.bamboy.freedom.ui.smartrefresh.SmartRefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ActSmartRefresh extends AppCompatActivity implements FreedomCallback {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_smartrefresh);
        initBack();

        setTitle("默认风格");

        refreshLayout = findViewById(R.id.refreshLayout);
        recycler = findViewById(R.id.recycler);

        // 初始化列表数据
        initRecycler();

        // 初始化下拉刷新
        initRefreshLayout();

        // 进入页面自动加载数据
        refreshLayout.autoRefresh();
    }

    /**
     * 初始化下拉刷新
     */
    private void initRefreshLayout() {
        // 下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                // 加载数据
                // loadData();

                // 模拟1000毫秒后数据加载完成，
                // 数据加载完成后调用refreshLayout.finishRefresh()，即可收起刷新头;
                refreshLayout.finishRefresh(1000);
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
        initData();

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
        switch (holder.getItemViewType()) {
            case ManagerA.ITEM_TYPE_NEWS_IMG:
                Toast.makeText(this, "点击了图片新闻", Toast.LENGTH_SHORT).show();
                break;
            case ManagerA.ITEM_TYPE_NEWS_TEXT:
                Toast.makeText(this, "点击了文字新闻", Toast.LENGTH_SHORT).show();
                break;
            case ManagerB.ITEM_TYPE_MUSIC:
                if (mList.get(position) instanceof FBeanMusic) {
                    FBeanMusic bean = (FBeanMusic) mList.get(position);
                    Toast.makeText(this, "点击了歌曲：\n《" + bean.getSong() + "》", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 实例化List
        mList = new ArrayList();

        // 模拟加载数据，往mList里放一些乱七八糟的条目
        mList.add(new FBeanText(getString(R.string.smartrefresh_introduce)));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanMusic("成都", "赵雷 - 成都"));
        mList.add(new FBeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new FBeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new FBeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new FBeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new FBeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new FBeanDialogueRight("六六", "今晚单身狗吃狗粮"));
        mList.add(new FBeanDialogueLeft("喵喵", "狗粮不好吃，我要吃猫粮"));
        mList.add(new FBeanNewsText("微信“附近的小程序”悄然上线了，反观小程序为微信带来了什么", "“附近的小程序”正式开放：\n有小程序的商户，可以快速将门店小程序或普通小程序展示在“附近”。当用户走到某个地点，打开“发现-小程序-附近的小程序”，就能将自己附近的小程序“收入囊中”。"));
        mList.add(new FBeanNewsText("手上长水泡是什么引起的", "一到夏天，很多人手上都会出现一些小水泡，它们有的是以透明的形式存在，有的则是以半透明的方式存在，而且伴随着不同程度的瘙痒……"));
        mList.add(new FBeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new FBeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanDialogueLeft("喵喵", "铲屎的走了"));
        mList.add(new FBeanDialogueLeft("喵喵", "艾维巴蒂嗨起来！"));
        mList.add(new FBeanDialogueRight("六六", "铲屎的怎么还不回来，是不是外边有狗了？"));
        mList.add(new FBeanDialogueRight("六六", "我要吃肉肉…"));
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
