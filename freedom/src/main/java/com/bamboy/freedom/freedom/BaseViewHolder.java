package com.bamboy.freedom.freedom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    /**
     * View集合
     */
    private final SparseArray<View> views;
    /**
     * 所属的Adapter
     */
    public FreedomAdapter adapter;

    /**
     * 构造
     *
     * @param adapter 所属的Adapter
     * @param view    条目View
     */
    public BaseViewHolder(FreedomAdapter adapter, final View view) {
        super(view);
        this.adapter = adapter;
        this.views = new SparseArray<>();
    }

    /**
     * 根据ID获取View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于 TextView 常用属性  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置Textview文本
     */
    public BaseViewHolder setText(@IdRes int viewId, CharSequence value) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
            setText((TextView) view, value);
        }
        return this;
    }

    /**
     * 设置Textview文本
     */
    public BaseViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
            ((TextView) view).setText(strId);
        }
        return this;
    }

    /**
     * 设置Textview文本
     */
    public BaseViewHolder setText(TextView view, CharSequence value) {
        if (view != null) {
            if (value == null) {
                value = "";
            }
            view.setText(value);
        }
        return this;
    }

    /**
     * 获取Textview文本
     */
    public CharSequence getText(@IdRes int viewId) {
        return getText(getView(viewId));
    }

    /**
     * 获取Textview文本
     */
    public CharSequence getText(TextView view) {
        if (view != null) {
            return view.getText();
        }
        return "";
    }

    /**
     * 设置Textview字体颜色
     */
    public BaseViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
            setTextColor((TextView) view, textColor);
        }
        return this;
    }

    /**
     * 设置Textview字体颜色
     */
    public BaseViewHolder setTextColor(TextView view, @ColorInt int textColor) {
        if (view != null) {
            view.setTextColor(textColor);
        }
        return this;
    }

    /**
     * 设置Textview字体加粗
     */
    public BaseViewHolder setFakeBoldText(@IdRes int viewId, boolean fakeBoldText) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
            setFakeBoldText((TextView) view, fakeBoldText);
        }
        return this;
    }

    /**
     * 设置Textview字体加粗
     */
    public BaseViewHolder setFakeBoldText(TextView view, boolean fakeBoldText) {
        if (view != null) {
            view.getPaint().setFakeBoldText(fakeBoldText);
        }
        return this;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于 ImageView 常用属性  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置图片资源
     */
    public BaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        View view = getView(viewId);
        if (view != null && view instanceof ImageView) {
            setImageResource((ImageView) view, imageResId);
        }
        return this;
    }

    /**
     * 设置图片资源
     */
    public BaseViewHolder setImageResource(ImageView view, @DrawableRes int imageResId) {
        if (view != null) {
            view.setImageResource(imageResId);
        }
        return this;
    }

    /**
     * 设置图片Drawable
     */
    public BaseViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        View view = getView(viewId);
        if (view != null && view instanceof ImageView) {
            setImageDrawable((ImageView) view, drawable);
        }
        return this;
    }

    /**
     * 设置图片Drawable
     */
    public BaseViewHolder setImageDrawable(ImageView view, Drawable drawable) {
        if (view != null) {
            view.setImageDrawable(drawable);
        }
        return this;
    }

    /**
     * 设置图片Bitmap
     */
    public BaseViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        View view = getView(viewId);
        if (view != null && view instanceof ImageView) {
            setImageBitmap((ImageView) view, bitmap);
        }
        return this;
    }

    /**
     * 设置图片Bitmap
     */
    public BaseViewHolder setImageBitmap(ImageView view, Bitmap bitmap) {
        if (view != null) {
            view.setImageBitmap(bitmap);
        }
        return this;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于 Tag  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置View的Tag
     */
    public BaseViewHolder setTag(@IdRes int viewId, Object tag) {
        setTag(getView(viewId), tag);
        return this;
    }

    /**
     * 设置View的Tag
     */
    public BaseViewHolder setTag(View view, Object tag) {
        if (view != null) {
            view.setTag(tag);
        }
        return this;
    }

    /**
     * 设置View的Tag
     */
    public BaseViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        setTag(getView(viewId), key, tag);
        return this;
    }

    /**
     * 设置View的Tag
     */
    public BaseViewHolder setTag(View view, int key, Object tag) {
        if (view != null) {
            view.setTag(key, tag);
        }
        return this;
    }

    /**
     * 获取View的Tag
     */
    public <T extends Object> T getTag(@IdRes int viewId) {
        return getTag(getView(viewId));
    }

    /**
     * 获取View的Tag
     */
    public <T extends Object> T getTag(View view) {
        if (view != null && view.getTag() != null) {
            return (T) view.getTag();
        }
        return null;
    }

    /**
     * 设置View的boolean类型的Tag
     */
    public BaseViewHolder setBooleanTag(@IdRes int viewId, boolean boo) {
        setTag(getView(viewId), boo);
        return this;
    }

    /**
     * 设置View的boolean类型的Tag
     */
    public BaseViewHolder setBooleanTag(@IdRes int viewId, int key, boolean boo) {
        setTag(getView(viewId), key, boo);
        return this;
    }

    /**
     * 获取View的boolean类型的Tag
     */
    public boolean getBooleanTag(@IdRes int viewId) {
        return getBooleanTag(getView(viewId));
    }

    /**
     * 获取View的boolean类型的Tag
     */
    public boolean getBooleanTag(View view) {
        if (view != null && view.getTag() != null && view.getTag() instanceof Boolean) {
            return (Boolean) view.getTag();
        }
        return false;
    }

    /**
     * 获取View的boolean类型的Tag
     */
    public boolean getBooleanTag(View view, int key) {
        if (view != null && view.getTag(key) != null && view.getTag(key) instanceof Boolean) {
            return (Boolean) view.getTag(key);
        }
        return false;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于 View显示  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置View透明度
     */
    public BaseViewHolder setAlpha(@IdRes int viewId, float alpha) {
        setAlpha(getView(viewId), alpha);
        return this;
    }

    /**
     * 设置View透明度
     */
    public BaseViewHolder setAlpha(View view, float alpha) {
        if (view != null && view.getAlpha() != alpha) {
            view.setAlpha(alpha);
        }
        return this;
    }

    /**
     * 获取View透明度
     */
    public float getAlpha(@IdRes int viewId) {
        return getAlpha(viewId);
    }

    /**
     * 获取View透明度
     */
    public float getAlpha(View view) {
        if (view != null) {
            return view.getAlpha();
        }
        return 0;
    }

    /**
     * 设置View隐藏
     */
    public BaseViewHolder setGone(@IdRes int viewId) {
        setGone(getView(viewId));
        return this;
    }

    /**
     * 设置View隐藏
     */
    public BaseViewHolder setGone(View view) {
        if (view != null && !isGone(view)) {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 获取View是否是显示状态(Visible)
     *
     * @return
     */
    public boolean isGone(@IdRes int viewId) {
        return isGone(getView(viewId));
    }

    /**
     * 获取View是否是显示状态(Visible)
     *
     * @param view
     * @return
     */
    public boolean isGone(View view) {
        if (view != null) {
            return view.getVisibility() == View.GONE;
        }
        return false;
    }

    /**
     * 设置View显示
     */
    public BaseViewHolder setVisible(@IdRes int viewId) {
        setVisible(getView(viewId));
        return this;
    }

    /**
     * 设置View显示
     */
    public BaseViewHolder setVisible(View view) {
        if (view != null && !isVisible(view)) {
            view.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 获取View是否是显示状态(Visible)
     *
     * @return
     */
    public boolean isVisible(@IdRes int viewId) {
        return isVisible(getView(viewId));
    }

    /**
     * 获取View是否是显示状态(Visible)
     *
     * @param view
     * @return
     */
    public boolean isVisible(View view) {
        if (view != null) {
            return view.getVisibility() == View.VISIBLE;
        }
        return false;
    }

    /**
     * 设置View显示或隐藏
     *
     * @param viewId
     * @param isVisible 是否显示
     *                  【true：VISIBLE】
     *                  【false：GONE】
     * @return
     */
    public BaseViewHolder setVisible(@IdRes int viewId, boolean isVisible) {
        setVisible(getView(viewId), isVisible);
        return this;
    }

    /**
     * 设置View显示或隐藏
     *
     * @param view
     * @param isVisible 是否显示
     *                  【true：VISIBLE】
     *                  【false：GONE】
     * @return
     */
    public BaseViewHolder setVisible(View view, boolean isVisible) {
        if (view != null) {
            if (isVisible && view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            } else if (!isVisible && view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
            }
        }
        return this;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于点击事件  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置条目点击事件（通过ClickCallback）
     */
    public BaseViewHolder setItemClick() {
        setOnClickListener(itemView, v -> {
            if (adapter != null && adapter.getItemClickCallback() != null) {
                adapter.getItemClickCallback().onClick(v, getAdapterPosition(), BaseViewHolder.this);
            }
        });
        return this;
    }

    /**
     * 设置条目点击事件
     */
    public BaseViewHolder setItemClick(View.OnClickListener listener) {
        setOnClickListener(itemView, listener);
        return this;
    }

    /**
     * 设置View点击事件（通过ClickCallback）
     */
    public BaseViewHolder setOnClickListener(@IdRes final int viewId) {
        setOnClickListener(getView(viewId));
        return this;
    }

    /**
     * 设置View点击事件（通过ClickCallback）
     */
    public BaseViewHolder setOnClickListener(View view) {
        setOnClickListener(view, v -> {
            if (adapter != null && adapter.getViewClickCallback() != null) {
                adapter.getViewClickCallback().onClick(v, getAdapterPosition(), BaseViewHolder.this);
            }
        });
        return this;
    }

    /**
     * 设置View点击事件
     */
    public BaseViewHolder setOnClickListener(@IdRes final int viewId, View.OnClickListener listener) {
        setOnClickListener(getView(viewId), listener);
        return this;
    }

    /**
     * 设置View点击事件
     */
    public BaseViewHolder setOnClickListener(View view, View.OnClickListener listener) {
        if (view != null && listener != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置View点击事件
     */
    public BaseViewHolder setClickable(@IdRes final int viewId, boolean clickable) {
        setClickable(getView(viewId), clickable);
        return this;
    }

    /**
     * 设置View是否可点击
     */
    public BaseViewHolder setClickable(View view, boolean clickable) {
        if (view != null) {
            view.setClickable(clickable);
        }
        return this;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
    // ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅  以下是关于长按事件  ┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅
    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * 设置条目长按事件（通过ClickCallback）
     */
    public BaseViewHolder setItemLongClick() {
        setOnLongClickListener(itemView);
        return this;
    }

    /**
     * 设置条目长按事件
     */
    public BaseViewHolder setItemLongClick(View.OnLongClickListener listener) {
        setOnLongClickListener(itemView, listener);
        return this;
    }

    /**
     * 设置View长按事件（通过ClickCallback）
     */
    public BaseViewHolder setOnLongClickListener(@IdRes final int viewId) {
        setOnLongClickListener(getView(viewId));
        return this;
    }

    /**
     * 设置View长按事件（通过ClickCallback）
     */
    public BaseViewHolder setOnLongClickListener(View view) {
        setOnLongClickListener(view, v -> {
            if (adapter != null && adapter.getItemLongClickCallback() != null) {
                adapter.getItemLongClickCallback().onLongClick(v, getAdapterPosition(), BaseViewHolder.this);
                return true;
            } else {
                return false;
            }
        });
        return this;
    }

    /**
     * 设置View长按事件
     */
    public BaseViewHolder setOnLongClickListener(View view, View.OnLongClickListener listener) {
        if (view != null && listener != null) {
            view.setOnLongClickListener(listener);
        }
        return this;
    }
}
