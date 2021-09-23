package com.bamboy.freedom.page.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.bamboy.freedom.page.util.DensityUtils;

public class BroadLayout extends RelativeLayout {
    public BroadLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BroadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BroadLayout(Context context) {
        super(context);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        // 计算宽度
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        // 因为是跨两列，所以计算高度时，需要去掉列之间的间距3dp
        childWidthSize -= DensityUtils.dip2px(getContext(), 3);
        // 高度是宽度的一半
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize / 2, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
