package com.bamboy.freedom.page.music;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomBean;
import com.bamboy.freedom.freedom.FreedomCallback;
import com.bamboy.freedom.freedom.ViewHolderManager;
import com.bamboy.freedom.freedom.manager.ManagerB;
import com.bamboy.freedom.page.music.bean.BeanMusic;

import java.util.ArrayList;
import java.util.List;

public class ActMusic extends AppCompatActivity implements FreedomCallback {


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

        setTitle("音乐列表");

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
            case ManagerB.ITEM_TYPE_MUSIC:
                if (mList.get(position) instanceof BeanMusic) {
                    BeanMusic bean = (BeanMusic) mList.get(position);
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

        // 模拟加载数据，往mList里放一些音乐条目
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("成都", "赵雷 - 成都"));
        mList.add(new BeanMusic("成全", "林宥嘉 - 翻唱合集"));
        mList.add(new BeanMusic("单身情歌", "林志炫 - 一人一首成名曲"));
        mList.add(new BeanMusic("浮夸", "林志炫 - 我是歌手"));
        mList.add(new BeanMusic("改变自己", "王力宏 - 改变自己"));
        mList.add(new BeanMusic("骨子里的我", "李代沫 - 敏感者"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
        mList.add(new BeanMusic("海角七号", "东来东往 - 路过·爱"));
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
