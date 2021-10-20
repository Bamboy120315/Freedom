package com.bamboy.freedom.page.modle;

import com.bamboy.freedom.page.fitem.FitemWeibo;
import com.bamboy.freedom.page.fitem.FitemWeiboToJSON;

import java.io.Serializable;
import java.util.List;

public class WeiboNetworkModle implements Serializable {
    public int code;
    public String message;
    public List<FitemWeiboToJSON> data;
    public long request_time;
    public long response_time;
}
