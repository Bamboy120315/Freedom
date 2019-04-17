package com.bamboy.freedom.ui.freedom.manager;

import com.bamboy.freedom.fbean.FBeanDialogueLeft;
import com.bamboy.freedom.fbean.FBeanDialogueRight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 程序猿C on 2017/11/29.
 */
public class ManagerC {

    /**
     * 条目类型 和 对应的条目XML
     */
    private Map<Integer, Class> itemMap;

    private ManagerC() {

    }

    private static class ManagerHolder {
        private static final ManagerC INSTANCE = new ManagerC();
    }

    public static final ManagerC getManager() {
        return ManagerHolder.INSTANCE;
    }

    // _____________________________
    //  └——— 对话框相关的条目类型 ———┘
    /**
     * 条目类型 --> 左侧对话框
     */
    public static final int ITEM_TYPE_DIALOGUE_LEFT = 3000;
    /**
     * 条目类型 --> 右侧对话框
     */
    public static final int ITEM_TYPE_DIALOGUE_RIGHT = 3001;

    /**
     * 获取集合
     *
     * @return
     */
    public Map<Integer, Class> getMap() {
        if (itemMap == null) {
            itemMap = new HashMap<>();

            // ▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁
            // ━━━━━━━━━━━━━━━ 【对话框】相关 ━━━━━━━━━━━━━━━━━━━━━━━
            itemMap.put(ITEM_TYPE_DIALOGUE_LEFT, FBeanDialogueLeft.DialogueViewHolder.class);
            itemMap.put(ITEM_TYPE_DIALOGUE_RIGHT, FBeanDialogueRight.DialogueViewHolder.class);
        }

        return itemMap;
    }
}
