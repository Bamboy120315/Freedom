package com.bamboy.freedom;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboy.freedom.fbean.FBeanDialogueLeft;
import com.bamboy.freedom.fbean.FBeanDialogueRight;
import com.bamboy.freedom.fbean.FBeanMusic;
import com.bamboy.freedom.fbean.FBeanNewsImg;
import com.bamboy.freedom.fbean.FBeanNewsText;
import com.bamboy.freedom.fbean.FBeanPersonalAssets;
import com.bamboy.freedom.fbean.FBeanPersonalInfo;
import com.bamboy.freedom.fbean.FBeanText;
import com.bamboy.freedom.ui.freedom.FreedomAdapter;
import com.bamboy.freedom.ui.freedom.FreedomBean;
import com.bamboy.freedom.ui.freedom.FreedomCallback;
import com.bamboy.freedom.ui.freedom.ViewHolderManager;
import com.bamboy.freedom.ui.freedom.manager.ManagerA;
import com.bamboy.freedom.ui.freedom.manager.ManagerB;
import com.bamboy.freedom.ui.smartrefresh.SmartRefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshHeader;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.header.SlopeHeader;
import com.bamboy.freedom.ui.smartrefresh.listener.SimpleMultiPurposeListener;
import com.bamboy.freedom.ui.smartrefresh.util.BoundingUtil;

import java.util.ArrayList;
import java.util.List;

public class ActSmartRefreshPersonalCenter extends AppCompatActivity implements FreedomCallback {

    private RelativeLayout rl_title;
    private View view_status;
    private TextView tv_title;

    /**
     * 下拉刷新容器
     */
    private SmartRefreshLayout refreshLayout;
    /**
     * 头部图片容器
     */
    private RelativeLayout rl_top;
    /**
     * 头部图片
     */
    private View iv_top;
    /**
     * 下拉刷新 下拉的距离
     */
    private int mOffset = 0;
    /**
     * 列表滚动距离
     */
    private int mScrollY = 0;

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
        setContentView(R.layout.act_smartrefresh_personal_center);

        findView();

        init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 延迟200毫秒，模拟网络请求
                    Thread.sleep(200);

                    refreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            // 初始化列表数据
                            initRecycler();

                            // 初始化下拉刷新
                            initRefreshLayout();

                            // 进入页面自动加载数据
                            refreshLayout.autoRefresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化
     */
    private void init() {
        ImageView iv_back = findViewById(R.id.iv_back);

        // 返回按钮点击事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            // 沉浸式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 设置沉浸式
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

                // 返回按钮 移到状态栏下面
                view_status.getLayoutParams().height =
                        BoundingUtil.getBarHeight(ActSmartRefreshPersonalCenter.this);

                // 下拉刷新 由于沉浸式，所以要避免与状态栏重叠 同时避免被刘海遮挡
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        // 添加顶部间距 避免与状态栏重叠 同时避免被刘海遮挡
                        if (refreshLayout.getRefreshHeader() instanceof SlopeHeader) {
                            SlopeHeader header = (SlopeHeader) refreshLayout.getRefreshHeader();
                            header.setMarginTop(ActSmartRefreshPersonalCenter.this, refreshLayout);
                        }
                    }
                });
            }
        } catch (Exception e) {
            BUtil.showException(e);
        } catch (Error e) {
            BUtil.showException(e);
        }

    }

    /**
     * 初始化下拉刷新
     */
    private void initRefreshLayout() {

        // 改变进度条颜色
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                // 改变进度条颜色
                if (refreshLayout.getRefreshHeader() instanceof SlopeHeader) {
                    SlopeHeader header = (SlopeHeader) refreshLayout.getRefreshHeader();
                    header.setColors(ContextCompat.getColor(ActSmartRefreshPersonalCenter.this, R.color.white));
                }
            }
        });

        // 下拉刷新监听
        refreshListener();

        // RecyclerView滑动监听
        recyclerScrollListener();

        // 初始化 头部图片底部位置
        if (mList != null && mList.size() > 0) {
            for (FreedomBean bean : mList) {
                // 如果条目是个人资产条目，则初始化头部图片底部位置
                if (bean instanceof FBeanPersonalAssets) {
                    recycler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 初始化 头部图片底部位置
                            initTopImgLocation();
                        }
                    });
                    break;
                }
            }
        }

    }

    /**
     * 下拉刷新监听
     */
    private void refreshListener() {
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            /**
             * 下拉监听
             *
             * @param header header对象
             * @param isDragging 触点还在屏幕上
             * @param percent 当前下拉位置 与 header高度比例
             * @param offset 下拉长度
             * @param headerHeight header高度
             * @param maxDragHeight 最大下拉高度
             */
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset;
                rl_top.setTranslationY(mOffset - mScrollY);
            }

            /**
             * 开始刷新
             *
             * @param refreshLayout
             */
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                // 加载数据
                // loadData();

                // 模拟1000毫秒后数据加载完成，
                // 数据加载完成后调用refreshLayout.finishRefresh()，即可收起刷新头;
                refreshLayout.finishRefresh(1000);
            }

            /**
             * 刷新完成
             *
             * @param header header对象
             * @param success 是否成功
             */
            /*@Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
                super.onHeaderFinish(header, success);

                Toast.makeText(
                        ActSmartRefreshPersonalCenter.this,
                        success ? "刷新成功" : "刷新失败",
                        Toast.LENGTH_SHORT)
                        .show();
            }*/
        });
    }

    /**
     * recycler滚动监听
     */
    private void recyclerScrollListener() {
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 滑动监听
             *
             * @param recyclerView recyclerView对象
             * @param dx X轴滑动距离(仅此次滑动)
             * @param dy Y轴滑动距离(仅此次滑动)
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (false == recycler.canScrollVertically(-1)) {
                    mScrollY = 0;
                } else {
                    mScrollY += dy;
                }
                rl_top.setTranslationY(mOffset - mScrollY);

                // 改变titleBar底色
                if (mScrollY <= 260 * 2) {
                    float value = mScrollY / 260f;

                    if (value < 0) {
                        value = 0;
                    } else if (value > 1) {
                        value = 1;
                    }

                    // 计算titleBar底色色值
                    int color = ContextCompat.getColor(ActSmartRefreshPersonalCenter.this, R.color.colorPrimary);
                    int alpha = (int) (255f * value);
                    int titleColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
                    rl_title.setBackgroundColor(titleColor);

                    // 改变title文字透明度
                    tv_title.setAlpha(value);

                }
            }
        });
    }

    /**
     * 初始化 头部图片底部位置
     */
    private void initTopImgLocation() {
        int position;
        FBeanPersonalAssets bean = null;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i) instanceof FBeanPersonalAssets) {
                position = i;
                bean = (FBeanPersonalAssets) mList.get(position);
                break;
            }
        }

        if (bean == null || bean.getItemRoot() == null) {
            return;
        }

        ViewGroup root = bean.getItemRoot();

        int[] location = new int[2];
        root.getLocationInWindow(location);


        // 计算头部图片的底部位置
        int imgBottom = (int) (location[1] + root.getHeight() * 0.8f);

        // 设置头部图片底部位置
        initTopImgAlignBottom(imgBottom);

    }

    /**
     * 设置头部图片底部位置
     *
     * @param imgBottom
     */
    private void initTopImgAlignBottom(final int imgBottom) {

        int topImgHeight = rl_top.getHeight();

        // 图片长度不够，对图片进行拉伸
        if (rl_top.getHeight() < imgBottom + refreshLayout.getHeaderHeight() * 2) {

            topImgHeight = (int) (imgBottom + refreshLayout.getHeaderHeight() * 2);

            // 设置位置，以让头部图片底部对齐
            RelativeLayout.LayoutParams paramsImg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, topImgHeight);
            iv_top.setLayoutParams(paramsImg);
        }

        // 设置位置，以让头部图片底部对齐
        RelativeLayout.LayoutParams paramsRoot = (RelativeLayout.LayoutParams) rl_top.getLayoutParams();
        paramsRoot.topMargin = imgBottom - topImgHeight;

        // 计算 下拉最大高度
        refreshLayout.setHeaderMaxHeight(topImgHeight - imgBottom);

        // 头部图片位置已就位，开始动画显示
        rl_top.animate()
                .alpha(1)
                .setDuration(200);
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
        mList.add(new FBeanPersonalInfo("六六"));
        mList.add(new FBeanPersonalAssets("688.88", "6", "600.00", "16"));
        mList.add(new FBeanText(getString(R.string.smartrefresh_personal_center_introduce)));
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
     * findViewById
     */
    private void findView() {
        rl_title = findViewById(R.id.rl_title);
        view_status = findViewById(R.id.view_status);
        tv_title = findViewById(R.id.tv_title);
        refreshLayout = findViewById(R.id.refreshLayout);
        rl_top = findViewById(R.id.rl_top);
        iv_top = findViewById(R.id.iv_top);
        recycler = findViewById(R.id.recycler);
    }

}
