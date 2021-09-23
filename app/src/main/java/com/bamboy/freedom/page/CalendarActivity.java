package com.bamboy.freedom.page;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.BaseViewHolder;
import com.bamboy.freedom.freedom.FreedomAdapter;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.freedom.clickcallback.ItemClickCallback;
import com.bamboy.freedom.page.fitem.FitemCalendar;
import com.bamboy.freedom.page.util.CalendarDataUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity implements ItemClickCallback {

    /**
     * 标题
     */
    private TextView tv_date;
    /**
     * 上个月按钮
     */
    private ImageView iv_back;
    /**
     * 下个月按钮
     */
    private ImageView iv_front;
    /**
     * 今天的点
     */
    private View view_today_dot;
    /**
     * 最后选中的条目
     */
    private FitemCalendar lastItem;
    /**
     * 日历列表
     */
    private RecyclerView recycler;
    /**
     * 日历列表适配器
     */
    private FreedomAdapter mAdapter;
    /**
     * 日历列表数据源
     */
    public List<FitemCalendar> mList;
    /**
     * 是否动画执行中
     */
    private boolean isAnimateIng = false;
    private CalendarDataUtil mDataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setTitle("日历");

        recycler = findViewById(R.id.recycler);
        tv_date = findViewById(R.id.tv_date);
        view_today_dot = findViewById(R.id.view_today_dot);
        iv_back = findViewById(R.id.iv_back);
        iv_front = findViewById(R.id.iv_front);

        mDataUtil = new CalendarDataUtil();

        // 处理监听
        initListener();

        // 初始化日历
        recycler.post(() -> initCalendar());
    }

    /**
     * 初始化数据
     *
     * @return
     */
    private void notifyList() {
        if (mAdapter == null) {
            // 把每行平分成7份
            GridLayoutManager manager = new GridLayoutManager(this, 7);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    FreedomItem bean = mList.get(position);
                    // 获取当前这个条目的步长
                    return bean.getSpanSize(manager.getSpanCount());
                }
            });
            recycler.setLayoutManager(manager);
            mAdapter = new FreedomAdapter(this, mList);
            recycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 条目点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    @Override
    public void onClick(View view, int position, BaseViewHolder holder) {
        if (mList == null || position >= mList.size()) {
            return;
        }
        clickToDay(mList.get(position));
    }


    // =============================================================================================
    // ======================== 以下是关于日历数据 ===================================================
    // =============================================================================================


    /**
     * 手指按下的位置
     */
    private float downX, downY;

    /**
     * 处理监听
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        iv_back.setOnClickListener(this::clickToToBack);
        iv_front.setOnClickListener(this::clickToToFront);

        // 处理RecyclerView横向滑动监听
        initTouchListener();
    }

    /**
     * 处理RecyclerView横向滑动监听
     */
    private void initTouchListener() {
        recycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    // 手指按下
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX();
                        downY = motionEvent.getY();
                        break;

                    // 手指移动
                    case MotionEvent.ACTION_MOVE:
                        if (downX == -1 && downY == -1) {
                            break;
                        }
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        // 判断手势
                        if (Math.abs(y - downY) < Math.abs(x - downX) && Math.abs(x - downX) > 50) {
                            if (downX < x) {
                                // 切到上个月
                                clickToToBack(null);
                            } else {
                                // 切到下个月
                                clickToToFront(null);
                            }
                            downX = -1;
                            downY = -1;
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {
            }
        });
    }

    /**
     * 初始化日历
     */
    private void initCalendar() {

        // 当前时间
        long time = System.currentTimeMillis();

        // 处理日期数据
        mDataUtil.setDate(time);

        // 切换月份
        switchMonth();

        mAdapter.setItemClickCallback(this);

        // 跳到当天
        recycler.post(() -> jumpToDay());
    }

    /**
     * 切换月份
     */
    private void switchMonth() {

        // 处理titleBar的显示
        initTitleBar();

        // 处理日历条目数据
        initItemData();
    }

    /**
     * 处理titleBar的显示
     */
    private void initTitleBar() {
        // 左按钮是否高亮
        boolean leftIsLight = true;
        // 右按钮是否高亮
        boolean rightIsLight = true;

        // 限制一年
        if (mDataUtil.mYear < mDataUtil.mCurrentYear) {
            mDataUtil.mYear = mDataUtil.mCurrentYear - 1;
            if (mDataUtil.mMonth <= mDataUtil.mCurrentMonth) {
                mDataUtil.mMonth = mDataUtil.mCurrentMonth;
                leftIsLight = false;
            }
        } else if (mDataUtil.mYear > mDataUtil.mCurrentYear) {
            mDataUtil.mYear = mDataUtil.mCurrentYear + 1;
            if (mDataUtil.mMonth >= mDataUtil.mCurrentMonth) {
                mDataUtil.mMonth = mDataUtil.mCurrentMonth;
                rightIsLight = false;
            }
        }

        iv_back.setAlpha(leftIsLight ? 1f : 0.4f);
        iv_back.setClickable(leftIsLight);
        iv_front.setAlpha(rightIsLight ? 1f : 0.4f);
        iv_front.setClickable(rightIsLight);

        // 显示当前年月
        tv_date.setText(mDataUtil.mYear + "年" + mDataUtil.mMonth + "月");
    }

    /**
     * 处理条目数据
     */
    private void initItemData() {
        // 获取周几
        mDataUtil.mWeek = mDataUtil.getMonthFirstWeekLabel();

        // 当月多少天
        mDataUtil.mMonthLastDay = mDataUtil.getMonthLastDay();

        // 共需多少个条目
        int itemCount = mDataUtil.mWeek - 1 + mDataUtil.mMonthLastDay;

        // 当月有几行
        int rowCount = itemCount / 7;
        if (itemCount % 7 > 0) {
            rowCount++;
        }

        // 条目高度
        int itemHeight = (int) ((float) recycler.getHeight() / (float) rowCount);

        if (mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }

        if (mDataUtil.mWeek != 1) {
            for (int i = 0; i < mDataUtil.mWeek - 1; i++) {
                // 添加空条目
                mList.add(new FitemCalendar(0, itemHeight));
            }
        }
        for (int i = 0; i < mDataUtil.mMonthLastDay; i++) {
            // 添加空条目
            mList.add(new FitemCalendar((i + 1), itemHeight));
        }

        // 刷新数据
        notifyList();
    }

    // =============================================================================================
    // ======================== 以下是关于日历切换 ===================================================
    // =============================================================================================

    /**
     * 动画切换
     *
     * @param isBack     是否是后退
     * @param isClickDay 动画结束后是否自动点击今日
     */
    private void animSwitch(boolean isBack, boolean isClickDay) {
        isAnimateIng = true;
        recycler
                .animate()
                .alpha(0)
                .translationX(isBack ? 50 : -50)
                .setDuration(150)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        // 切换数据
                        switchMonth();

                        recycler.setTranslationX(isBack ? -50 : 50);
                        view_today_dot.setVisibility(View.GONE);

                        recycler
                                .animate()
                                .alpha(1)
                                .translationX(0)
                                .setDuration(150)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        isAnimateIng = false;

                                        if (isClickDay) {
                                            imitateClickToDay(mDataUtil.mDay);
                                        } else if (mDataUtil.mYear == mDataUtil.mLastClickYear && mDataUtil.mMonth == mDataUtil.mLastClickMonth) {
                                            // 模拟点击最后一次点击的日期
                                            imitateClickToDay(mDataUtil.mLastClickDay);
                                        }
                                    }
                                });
                    }
                });

        if (view_today_dot.getVisibility() == View.VISIBLE && view_today_dot.getAlpha() > 0.1f) {
            float currentTranslationX = view_today_dot.getTranslationX();
            view_today_dot
                    .animate()
                    .alpha(0)
                    .setInterpolator(mDataUtil.interpolatorLinear)
                    .translationX(isBack ? currentTranslationX + 25 : currentTranslationX - 25)
                    .setDuration(75);
        }
        lastItem = null;
    }

    /**
     * 跳转回今天
     */
    public void jumpToDay() {
        if (mDataUtil.mYear == mDataUtil.mCurrentYear && mDataUtil.mMonth == mDataUtil.mCurrentMonth) {
            if (mDataUtil.mLastClickDay == mDataUtil.mCurrentDay) {
                return;
            }
            // 模拟点击今日
            imitateClickToDay(mDataUtil.mDay);
        } else {

            boolean isBack;

            if (mDataUtil.mYear < mDataUtil.mCurrentYear) {
                isBack = false;
                mDataUtil.mYear = mDataUtil.mCurrentYear;
            } else if (mDataUtil.mYear > mDataUtil.mCurrentYear) {
                isBack = true;
                mDataUtil.mYear = mDataUtil.mCurrentYear;
            } else {
                if (mDataUtil.mMonth < mDataUtil.mCurrentMonth) {
                    isBack = false;
                    mDataUtil.mMonth = mDataUtil.mCurrentMonth;
                } else {
                    isBack = true;
                    mDataUtil.mMonth = mDataUtil.mCurrentMonth;
                }
            }

            // 动画切换
            animSwitch(isBack, true);
        }

    }

    /**
     * 点击事件 --> 上个月按钮
     */
    private void clickToToBack(View view) {
        if (isAnimateIng) {
            return;
        }
        if (mDataUtil.mYear < mDataUtil.mCurrentYear && mDataUtil.mMonth <= mDataUtil.mCurrentMonth) {
            return;
        }

        // 是否是一月
        if (mDataUtil.mMonth == 1) {
            // 切到上年12月
            mDataUtil.mMonth = 12;
            mDataUtil.mYear--;
        } else {
            // 切到本年上个月
            mDataUtil.mMonth--;
        }

        // 动画切换
        animSwitch(true, false);
    }

    /**
     * 点击事件 --> 下个月按钮
     */
    private void clickToToFront(View view) {
        if (isAnimateIng) {
            return;
        }
        if (mDataUtil.mYear > mDataUtil.mCurrentYear && mDataUtil.mMonth >= mDataUtil.mCurrentMonth) {
            return;
        }

        // 是否是12月
        if (mDataUtil.mMonth == 12) {
            // 切到下年1月
            mDataUtil.mMonth = 1;
            mDataUtil.mYear++;
        } else {
            // 切到本年下个月
            mDataUtil.mMonth++;
        }

        // 动画切换
        animSwitch(false, false);

    }

    // =============================================================================================
    // ======================== 以下是关于日期点击 ===================================================
    // =============================================================================================

    /**
     * 点击日历中的条目
     *
     * @param item
     */
    public void clickToDay(FitemCalendar item) {
        if (isAnimateIng) {
            return;
        }
        if (lastItem != null && lastItem.day == item.day) {
            return;
        }

        // 处理今日标识的点的显示
        initDayDotUI(item);
    }

    /**
     * 模拟点击今日
     */
    private void imitateClickToDay(int day) {
        for (FitemCalendar item : mList) {
            if (item.day == day) {
                // 找到今天的条目，触发点击事件
                clickToDay(item);
                return;
            }
        }
    }

    /**
     * 处理今日标识的点的显示
     *
     * @param item 条目
     */
    private void initDayDotUI(FitemCalendar item) {
        if (item == null || item.day == 0 || item.tv_date_day == null) {
            return;
        }
        if (mDataUtil.dotTop == 0) {
            int[] location = new int[2];
            view_today_dot.getLocationOnScreen(location);
            mDataUtil.dotTop = location[1];
        }

        // 获取条目的中心点
        int[] location = item.getCentralPointLocation();

        if (view_today_dot.getVisibility() == View.GONE) {
            view_today_dot.setAlpha(0);
            view_today_dot.setVisibility(View.VISIBLE);
            view_today_dot.post(() -> {
                int left = location[0] - view_today_dot.getWidth() / 2;
                int top = location[1] - view_today_dot.getHeight() / 2 - mDataUtil.dotTop;

                view_today_dot.setTranslationX(left);
                view_today_dot.setTranslationY(top);

                // 显示点
                revealContent(view_today_dot, true, 300);
            });
        } else {
            int left = location[0] - view_today_dot.getWidth() / 2;
            int top = location[1] - view_today_dot.getHeight() / 2 - mDataUtil.dotTop;

            view_today_dot
                    .animate()
                    .translationX(left)
                    .translationY(top)
                    .setInterpolator(mDataUtil.interpolator)
                    .setDuration(250);

            // 上个条目变黑
            if (lastItem != null) {
                updateTextColor(lastItem.tv_date_day, true);
            }
        }

        // 文字变白渐变
        updateTextColor(item.tv_date_day, false);

        // 记录条目
        lastItem = item;

        mDataUtil.mLastClickYear = mDataUtil.mYear;
        mDataUtil.mLastClickMonth = mDataUtil.mMonth;
        mDataUtil.mLastClickDay = item.day;
    }

    /**
     * TextView变色
     *
     * @param tv   TextView
     * @param dark 是否是深色
     */
    private void updateTextColor(TextView tv, boolean dark) {
        ValueAnimator animator = ValueAnimator.ofFloat(dark ? 1 : 0, dark ? 0 : 1);
        animator.setDuration(150);
        animator.addUpdateListener((ValueAnimator animation) -> {
            float value = (float) animation.getAnimatedValue();
            int colorValue = (int) (0x33 + (0xFF - 0x33) * value);
            int color = Color.rgb(colorValue, colorValue, colorValue);
            tv.setTextColor(color);
        });
        animator.start();
    }

    /**
     * 揭露工具箱
     */
    public void revealContent(View view, boolean isShow, int duration) {
        int bX = view.getWidth() / 2;
        int by = view.getHeight();

        // 结束时半径
        float endRadius = (float) Math.hypot(bX, by);

        // 弹窗圆形动画
        Animator animator = ViewAnimationUtils.createCircularReveal(view,
                bX,
                by,
                isShow ? 0 : endRadius,
                isShow ? endRadius : 0);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(2f));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (isShow) {
                    view.setAlpha(1f);
                } else {
                    view.setAlpha(0.99f);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isShow) {
                    view.setVisibility(View.GONE);
                } else {
                    view.setAlpha(1f);
                }
            }
        });
        animator.start();
    }
}