package com.bamboy.freedom.ui.smartrefresh.footer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bamboy.freedom.R;;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshFooter;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshKernel;
import com.bamboy.freedom.ui.smartrefresh.api.RefreshLayout;
import com.bamboy.freedom.ui.smartrefresh.constant.RefreshState;
import com.bamboy.freedom.ui.smartrefresh.internal.InternalAbstract;
import com.bamboy.freedom.ui.smartrefresh.util.DensityUtil;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * 虚假的 Footer
 * 用于 正真的 Footer 在 RefreshLayout 外部时，
 * Created by SCWANG on 2017/6/14.
 */
@SuppressWarnings("unused")
public class FalsifyFooter extends InternalAbstract implements RefreshFooter {

    private RefreshKernel mRefreshKernel;

    //<editor-fold desc="FalsifyHeader">
    public FalsifyFooter(Context context) {
        this(context, null);
    }

    public FalsifyFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FalsifyFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        final View thisView = this;
        if (thisView.isInEditMode()) {//这段代码在运行时不会执行，只会在Studio编辑预览时运行，不用在意性能问题
            int d = DensityUtil.dp2px(5);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(0xcccccccc);
            paint.setStrokeWidth(DensityUtil.dp2px(1));
            paint.setPathEffect(new DashPathEffect(new float[]{d, d, d, d}, 1));
            canvas.drawRect(d, d, thisView.getWidth() - d, thisView.getBottom() - d, paint);

            TextView textView = new TextView(thisView.getContext());
            textView.setText(thisView.getResources().getString(R.string.srl_component_falsify, getClass().getSimpleName(), DensityUtil.px2dp(thisView.getHeight())));
            textView.setTextColor(0xcccccccc);
            textView.setGravity(Gravity.CENTER);
            //noinspection UnnecessaryLocalVariable
            View view = textView;
            view.measure(makeMeasureSpec(thisView.getWidth(), EXACTLY), makeMeasureSpec(thisView.getHeight(), EXACTLY));
            view.layout(0, 0, thisView.getWidth(), thisView.getHeight());
            view.draw(canvas);
        }
    }
    //</editor-fold>

    //<editor-fold desc="RefreshFooter">

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        mRefreshKernel = kernel;
        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        if (mRefreshKernel != null) {
            mRefreshKernel.setState(RefreshState.None);
            //onReleased 的时候 调用 setState(RefreshState.None); 并不会立刻改变成 None
            //而是先执行一个回弹动画，LoadFinish 是介于 Refreshing 和 None 之间的状态
            //LoadFinish 用于在回弹动画结束时候能顺利改变为 None
            mRefreshKernel.setState(RefreshState.LoadFinish);
        }
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }

    //</editor-fold>

}
