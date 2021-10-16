package com.bamboy.freedom.freedom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bamboy.freedom.freedom.clickcallback.OnClickCallback;
import com.bamboy.freedom.freedom.clickcallback.OnLongClickCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreedomAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<FreedomItem> mList;
    private Context mContext;
    /**
     * 条目点击事件Callback
     */
    private OnClickCallback mItemClickCallback, mViewClickCallback;
    /**
     * 条目长按事件Callback
     */
    private OnLongClickCallback mItemLongClickCallback, mViewLongClickCallback;
    /**
     * 变量集合
     */
    private Map<String, Object> mVariateMap;

    /**
     * Adapter 构造
     *
     * @param list
     */
    public FreedomAdapter(Context context, List list) {
        mContext = context;
        mList = list;
    }

    /**
     * Adapter 构造
     *
     * @param list
     */
    public FreedomAdapter(Context context, List list, OnClickCallback callback) {
        mContext = context;
        mList = list;
        setItemClickCallback(callback);
    }

    /**
     * 获取View点击事件
     *
     * @return
     */
    public OnClickCallback getViewClickCallback() {
        if (mViewClickCallback == null) {
            mViewClickCallback = (View view, int position, BaseViewHolder holder) -> {
            };
        }
        return mViewClickCallback;
    }

    /**
     * 获取条目点击事件
     *
     * @return
     */
    public OnClickCallback getItemClickCallback() {
        if (mItemClickCallback == null) {
            mItemClickCallback = (View view, int position, BaseViewHolder holder) -> {
            };
        }
        return mItemClickCallback;
    }

    /**
     * 设置View点击事件
     *
     * @param clickCallback
     */
    public void setViewClickCallback(OnClickCallback clickCallback) {
        this.mViewClickCallback = clickCallback;
    }

    /**
     * 设置条目点击事件
     *
     * @param clickCallback
     */
    public void setItemClickCallback(OnClickCallback clickCallback) {
        this.mItemClickCallback = clickCallback;
    }

    /**
     * 获取View长按事件
     *
     * @return
     */
    public OnLongClickCallback getViewLongClickCallback() {
        if (mViewLongClickCallback == null) {
            mViewLongClickCallback = (View view, int position, BaseViewHolder holder) -> {
                return false;
            };
        }
        return mViewLongClickCallback;
    }

    /**
     * 获取条目长按事件
     *
     * @return
     */
    public OnLongClickCallback getItemLongClickCallback() {
        if (mItemLongClickCallback == null) {
            mItemLongClickCallback = (View view, int position, BaseViewHolder holder) -> {
                return false;
            };
        }
        return mItemLongClickCallback;
    }

    /**
     * 设置View长按事件
     *
     * @param longClickCallback
     */
    public void setViewLondClickCallback(OnLongClickCallback longClickCallback) {
        this.mViewLongClickCallback = longClickCallback;
    }

    /**
     * 设置条目长按事件
     *
     * @param longClickCallback
     */
    public void setItemLondClickCallback(OnLongClickCallback longClickCallback) {
        this.mItemLongClickCallback = longClickCallback;
    }

    /**
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder(this, LayoutInflater.from(viewGroup.getContext())
                .inflate(viewType, viewGroup, false));
    }

    /**
     * item 和 ViewHolder 绑定
     *
     * @param vh
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder vh, int position) {
        if (mList == null || mList.isEmpty() || position >= mList.size()) {
            return;
        }

        // 如果设置了条目点击事件
        if (mItemClickCallback != null) {
            vh.setItemClick();
        }
        if (mItemLongClickCallback != null) {
            vh.setItemLongClick();
        }

        FreedomItem bean = mList.get(position);
        bean.setViewHolder(vh);
        bean.initBindView(mContext, vh, mList, position);
    }

    /**
     * 获取条目总数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (mList == null || mList.isEmpty()) {
            return 0;
        }

        return mList.size();
    }

    /**
     * 根据索引获取条目类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.isEmpty() || position >= mList.size()) {
            return -1;
        }

        return mList.get(position).getItemType();
    }

    /**
     * 获取条目所占步长
     *
     * @param spanCount
     * @param position
     * @return
     */
    public int getSpanSize(int spanCount, int position) {
        if (mList == null || mList.isEmpty() || position >= mList.size()) {
            return 1;
        }

        return mList.get(position).getSpanSize(spanCount);
    }

    /**
     * 获取变量
     */
    public <T extends Object> T getVar(String key, T defaultValue) {
        if (mVariateMap == null) {
            return defaultValue;
        }
        Object value = mVariateMap.get(key);
        if (value == null) {
            return defaultValue;
        }
        return (T) value;
    }

    /**
     * 设置变量
     */
    public void setVar(String key, Object value) {
        if (mVariateMap == null) {
            mVariateMap = new HashMap<>();
        }
        mVariateMap.put(key, value);
    }
}
