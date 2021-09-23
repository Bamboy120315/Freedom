package com.bamboy.freedom.page.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;

public class DensityUtils {
    public DensityUtils() {
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5F);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5F);
    }

    public static int getStatusHeight(Activity activity) {
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int statusHeight = localRect.top;
        if (0 == statusHeight) {
            try {
                Class<?> localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException var6) {
                var6.printStackTrace();
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            } catch (InstantiationException var8) {
                var8.printStackTrace();
            } catch (NumberFormatException var9) {
                var9.printStackTrace();
            } catch (IllegalArgumentException var10) {
                var10.printStackTrace();
            } catch (SecurityException var11) {
                var11.printStackTrace();
            } catch (NoSuchFieldException var12) {
                var12.printStackTrace();
            }
        }

        return statusHeight;
    }
}
