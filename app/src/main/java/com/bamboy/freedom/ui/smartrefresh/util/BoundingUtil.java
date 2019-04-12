package com.bamboy.freedom.ui.smartrefresh.util;

import android.content.Context;
import android.os.Build;

/**
 * Created by liushaochen on 2019/4/11.
 */

public class BoundingUtil {

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return 状态栏高度
     */
    public static int getBarHeight(Context context) {
        try {
            int barHeight = 0;

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                barHeight = 0;
            } else {
                int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    barHeight = context.getResources().getDimensionPixelSize(resourceId);
                }
            }
            return barHeight;
        } catch (Exception e) {
            return 0;
        } catch (Error e) {
            return 0;
        }
    }

}
