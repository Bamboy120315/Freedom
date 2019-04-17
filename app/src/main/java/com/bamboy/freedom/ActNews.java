package com.bamboy.freedom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;
import com.bamboy.freedom.fbean.FBeanNewsImg;
import com.bamboy.freedom.fbean.FBeanNewsText;

import java.util.ArrayList;
import java.util.List;

public class ActNews extends AppCompatActivity implements FreedomCallback {


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
        setContentView(R.layout.act_freedom);
        initBack();

        setTitle("新闻列表");

        recycler = findViewById(R.id.recycler);

        initRecycler();
    }

    /**
     * 初始化列表数据
     */
    private void initRecycler() {
        // 初始化数据
        initData();

        // 实例化RecyclerView
        recycler.setLayoutManager(new LinearLayoutManager(this));
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
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 实例化List
        mList = new ArrayList();

        // 模拟加载数据，往mList里放一些新闻条目
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanNewsText("微信“附近的小程序”悄然上线了，反观小程序为微信带来了什么", "“附近的小程序”正式开放：\n有小程序的商户，可以快速将门店小程序或普通小程序展示在“附近”。当用户走到某个地点，打开“发现-小程序-附近的小程序”，就能将自己附近的小程序“收入囊中”。"));
        mList.add(new FBeanNewsText("手上长水泡是什么引起的", "一到夏天，很多人手上都会出现一些小水泡，它们有的是以透明的形式存在，有的则是以半透明的方式存在，而且伴随着不同程度的瘙痒……"));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new FBeanNewsText("**公司继续停牌2个月: 混改谈判还在进行中", "5月10日晚间公告，公司正在积极推进本次重大事项的相关工作，但由于本次重大事项的具体实施方案涉及多个政府主管部门的事前审批程序，还存在不确定性……"));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new FBeanNewsText("**公司继续停牌2个月: 混改谈判还在进行中", "5月10日晚间公告，公司正在积极推进本次重大事项的相关工作，但由于本次重大事项的具体实施方案涉及多个政府主管部门的事前审批程序，还存在不确定性……"));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
        mList.add(new FBeanNewsText("微软2017年Build大会: 无Win10更新 AI成压轴戏", "北京时间5月11日凌晨消息，微软2017年Build开发者大会于美国西雅图当地时间上午8点拉开帷幕。 此次大会不仅吸引了全球众多开发者前来交流……"));
        mList.add(new FBeanNewsText("**公司继续停牌2个月: 混改谈判还在进行中", "5月10日晚间公告，公司正在积极推进本次重大事项的相关工作，但由于本次重大事项的具体实施方案涉及多个政府主管部门的事前审批程序，还存在不确定性……"));
        mList.add(new FBeanNewsImg(R.drawable.picture_b, "这些水果狗狗不能吃，你知道吗？"));
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
