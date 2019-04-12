package com.bamboy.freedom.page;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bamboy.freedom.BuildConfig;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by liushaochen on 2018/12/4.
 */

public class BUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 需要判断的字符串
     * @return true即为空；false不为空
     */
    public static boolean isNull(String str) {
        if (str == null || "".equals(str) || "null".equals(str) || "[null]".equals(str) || "{null}".equals(str) || "[]".equals(str) || "{}".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断TextView或其内容是否为空
     *
     * @param tv 需要判断的TextView
     * @return true即为空；false不为空
     */
    public static boolean isNull(TextView tv) {
        if (tv == null || tv.getText() == null || isNull(tv.getText().toString())) {
            return true;
        }
        return false;
    }

    /**
     * 判断list或其内容是否为空
     *
     * @param list 需要判断的list
     * @return true即为空；false不为空
     */
    public static boolean isNull(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空List
     *
     * @param list 要清空的List
     */
    public static void clearList(List list) {
        if (!isNull(list)) {
            for (int i = list.size() - 1; i >= 0; i--) {
                list.remove(i);
            }
        }
    }

    /**
     * 打印错误日志【非打包模式下才会打印】
     *
     * @param e
     */
    public static void showException(Error e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    /**
     * 打印错误日志【非打包模式下才会打印】
     *
     * @param e
     */
    public static void showException(Exception e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    /**
     * 打印错误日志
     *
     * @param e
     */
    public static void showException(JSONException e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }
}
