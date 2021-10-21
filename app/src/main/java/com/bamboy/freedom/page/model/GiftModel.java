package com.bamboy.freedom.page.model;

import java.io.Serializable;

public class GiftModel implements Serializable {

    /**
     * 礼物Id
     */
    public int goods_id;
    /**
     * 图片链接
     */
    public int images_static;
    /**
     * 礼物名称
     */
    public String name;
    /**
     * 礼物价格
     */
    public long beans;
}
