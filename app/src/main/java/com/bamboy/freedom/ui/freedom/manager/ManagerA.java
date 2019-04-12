package com.bamboy.freedom.ui.freedom.manager;

import com.bamboy.freedom.page.fbean.FBeanNewsImg;
import com.bamboy.freedom.page.fbean.FBeanNewsText;
import com.bamboy.freedom.page.fbean.FBeanPersonalAssets;
import com.bamboy.freedom.page.fbean.FBeanPersonalInfo;
import com.bamboy.freedom.page.fbean.FBeanStartActivityBtn;
import com.bamboy.freedom.page.fbean.FBeanText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 程序猿A on 2017/10/16.
 */
public class ManagerA {

    /**
     * 条目类型 和 对应的条目XML
     */
    private Map<Integer, Class> itemMap;

    private ManagerA() {

    }

    private static class ManagerHolder {
        private static final ManagerA INSTANCE = new ManagerA();
    }

    public static final ManagerA getManager() {
        return ManagerHolder.INSTANCE;
    }

    // ____________________________
    //  └——— 新闻相关的条目类型 ———┘
    /**
     * 条目类型 --> 文字新闻条目类型
     */
    public static final int ITEM_TYPE_NEWS_TEXT = 1000;
    /**
     * 条目类型 --> 图片新闻条目类型
     */
    public static final int ITEM_TYPE_NEWS_IMG = 1001;
    /**
     * 条目类型 --> 文本
     */
    public static final int ITEM_TYPE_TEXT = 1002;
    /**
     * 条目类型 --> 按钮
     */
    public static final int ITEM_TYPE_BUTTON = 1003;
    /**
     * 条目类型 --> 个人信息
     */
    public static final int ITEM_TYPE_PERSONAL_INFO = 1004;
    /**
     * 条目类型 --> 个人资产
     */
    public static final int ITEM_TYPE_PERSONAL_ASSETS = 1005;

    /**
     * 获取集合
     *
     * @return
     */
    public Map<Integer, Class> getMap() {
        if (itemMap == null) {
            itemMap = new HashMap<>();

            // ▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁
            // ━━━━━━━━━━━━━━━━━ 【新闻】相关 ━━━━━━━━━━━━━━━━━━
            itemMap.put(ITEM_TYPE_NEWS_TEXT, FBeanNewsText.NewsTextViewHolder.class);
            itemMap.put(ITEM_TYPE_NEWS_IMG, FBeanNewsImg.NewsImgViewHolder.class);
            itemMap.put(ITEM_TYPE_TEXT, FBeanText.TextViewHolder.class);
            itemMap.put(ITEM_TYPE_BUTTON, FBeanStartActivityBtn.BtnViewHolder.class);
            itemMap.put(ITEM_TYPE_PERSONAL_INFO, FBeanPersonalInfo.PersonalInfoViewHolder.class);
            itemMap.put(ITEM_TYPE_PERSONAL_ASSETS, FBeanPersonalAssets.PersonalAssetsViewHolder.class);
        }

        return itemMap;
    }
}
