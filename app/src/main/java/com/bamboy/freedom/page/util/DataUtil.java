package com.bamboy.freedom.page.util;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;
import com.bamboy.freedom.R;
import com.bamboy.freedom.freedom.FreedomItem;
import com.bamboy.freedom.page.AssortedSpanActivity;
import com.bamboy.freedom.page.CalendarActivity;
import com.bamboy.freedom.page.DynamicSpanActivity;
import com.bamboy.freedom.page.PhotoListActivity;
import com.bamboy.freedom.page.SingleCheckedActivity;
import com.bamboy.freedom.page.SingleCheckedDefaultActivity;
import com.bamboy.freedom.page.WeChatItemShakeActivity;
import com.bamboy.freedom.page.WeiboActivity;
import com.bamboy.freedom.page.WeiboJsonActivity;
import com.bamboy.freedom.page.fitem.FitemDynamisSpan;
import com.bamboy.freedom.page.fitem.FitemFunction;
import com.bamboy.freedom.page.fitem.FitemGift;
import com.bamboy.freedom.page.fitem.FitemIntroduce;
import com.bamboy.freedom.page.fitem.FitemPhoto;
import com.bamboy.freedom.page.fitem.FitemWeChat;
import com.bamboy.freedom.page.fitem.FitemWeibo;
import com.bamboy.freedom.page.modle.GiftModle;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    /**
     * 获取数据 --> 微博列表
     * @return
     */
    @SuppressLint("ResourceType")
    public static List<FreedomItem> getDataToMain(){
        List<FreedomItem> list = new ArrayList<>();

        list.add(new FitemIntroduce("简单用法示例："));
        list.add(new FitemFunction("仿微博",
                "单列场景\n单一一种条目类型",
                R.drawable.iv_example_weibo,
                WeiboActivity.class));

        list.add(new FitemIntroduce("多条目类型跨列示例："));
        list.add(new FitemFunction("图库列表",
                "多列场景\n单一一种条目类型\n条目内部点击示例",
                R.drawable.iv_example_photo,
                PhotoListActivity.class));
        list.add(new FitemFunction("动态跨列列表",
                "多列场景\n单一一种条目类型\n动态跨列示例",
                R.drawable.iv_example_dunamic_span,
                DynamicSpanActivity.class));
        list.add(new FitemFunction("混合跨列列表",
                "多列场景\n正方形条目 + 长方形条目\n多类型跨列示例",
                R.drawable.iv_example_assorted_span,
                AssortedSpanActivity.class));

        list.add(new FitemIntroduce("单选示例："));
        list.add(new FitemFunction("条目单选",
                "多列场景\n单一一种条目类型\nItemClick示例\n单选按钮示例",
                R.drawable.iv_example_gift,
                SingleCheckedActivity.class));
        list.add(new FitemFunction("条目单选(默认选中)",
                "多列场景\n单一一种条目类型\nItemClick示例\n单选按钮示例",
                R.drawable.iv_example_gift_select,
                SingleCheckedDefaultActivity.class));

        list.add(new FitemIntroduce("其他玩法示例："));
        list.add(new FitemFunction("JSON直接解析成条目",
                "单列场景\n(仅支持部分网络解析方式)",
                R.drawable.iv_example_weibo,
                WeiboJsonActivity.class));
        list.add(new FitemFunction("仿微信炸弹条目摇晃",
                "条目暴露摇晃接口",
                R.drawable.iv_example_wechat,
                WeChatItemShakeActivity.class));
        list.add(new FitemFunction("日历",
                "没啥用\n看看就行",
                R.drawable.iv_example_calendar,
                CalendarActivity.class));

        return list;
    }

    /**
     * 获取数据 --> 微博列表
     * @return
     */
    public static List<FitemWeibo> getDataToWeiBo(){
        List<FitemWeibo> list = new ArrayList<>();

        FitemWeibo item_01 = new FitemWeibo();
        item_01.headId = R.drawable.iv_head_00001;
        item_01.nickname = "不吃卤面条";
        item_01.time = "15:01";
        item_01.content = "扫黑风暴，越更新剧情越拖沓，预告看着挺好看，怎么一到正片就开始拖沓。";
        item_01.contentImg = 0;
        item_01.location = "上海";
        item_01.shareCount = 0;
        item_01.speechCount = 3;
        item_01.likeCount = 0;
        list.add(item_01);

        FitemWeibo item_02 = new FitemWeibo();
        item_02.headId = R.drawable.iv_head_00002;
        item_02.nickname = "西瓜皮味瓜子壳";
        item_02.time = "14:56";
        item_02.content = "我就想点个鸡排，看哈评价，都能碰到bkpp";
        item_02.contentImg = R.drawable.iv_weibo_content_02;
        item_02.shareCount = 1;
        item_02.speechCount = 0;
        item_02.likeCount = 0;
        list.add(item_02);

        FitemWeibo item_03 = new FitemWeibo();
        item_03.headId = R.drawable.iv_head_00003;
        item_03.nickname = "XX日报";
        item_03.time = "14:40";
        item_03.content = "【中广联合会召开座谈会：#对违法失德失范艺人零容忍#】8月19日，中国广播电视社会组织联合会职业道德建设委员会在京召开广播电视文艺工作者加强职业道德建设座谈会，就最近一段时间个别艺人的违法失德失范行为发声。\n制片人张明智，导演郑晓龙、阎建钢……";
        item_03.contentImg = R.drawable.iv_weibo_content_03;
        item_03.location = "北京";
        item_03.shareCount = 54;
        item_03.speechCount = 194;
        item_03.likeCount = 1009;
        list.add(item_03);

        FitemWeibo item_04 = new FitemWeibo();
        item_04.headId = R.drawable.iv_head_00004;
        item_04.nickname = "XX科技";
        item_04.time = "2个小时前";
        item_04.content = "【LG与韩国酒店合作推广生发头盔】\n\nLG电子宣布将陆续与韩国数家豪华酒店合作，为酒店客户提供免费体验LG生发头盔、超声波洗面奶、超声波身体清洁器和眼部皮肤护肤装置的机会。简单的说就是酒店房间内将会标配这些产品，住多久就可以用多久[杰瑞]。";
        item_04.contentImg = R.drawable.iv_weibo_content_04;
        item_04.shareCount = 2;
        item_04.speechCount = 7;
        item_04.likeCount = 2;
        list.add(item_04);

        FitemWeibo item_05 = new FitemWeibo();
        item_05.headId = R.drawable.iv_head_00005;
        item_05.nickname = "X视新闻";
        item_05.time = "6个小时前";
        item_05.content = "【网友：#聂海胜太空照里有我#[哈哈]】今天，两名航天员成功出舱后，#聂海胜和地球合影#。留存“太空大片”↓↓↓网友：地球上有我，所以照片里有我！骄傲了~";
        item_05.location = "北京";
        item_05.shareCount = 16;
        item_05.speechCount = 39;
        item_05.likeCount = 188;
        list.add(item_05);

        FitemWeibo item_06 = new FitemWeibo();
        item_06.headId = R.drawable.iv_head_00006;
        item_06.nickname = "XX头条";
        item_06.time = "今天凌晨";
        item_06.content = "据@新华社 消息：中国载人航天工程办公室20日透露，目前，神舟十二号载人飞行任务已经进入第三个月。后续，航天员乘组将继续开展空间科学实验和技术试验，计划9月中旬返回东风着陆场。返回前，神舟飞船还将进行绕飞及径向交会试验。";
        item_06.shareCount = 17;
        item_06.speechCount = 12;
        item_06.likeCount = 92;
        list.add(item_06);

        FitemWeibo item_07 = new FitemWeibo();
        item_07.headId = R.drawable.iv_head_00007;
        item_07.nickname = "科技新一";
        item_07.time = "昨天";
        item_07.content = "一提到拍照好的手机，你第一印象会想到：____________。";
        item_07.location = "深圳";
        item_07.shareCount = 7;
        item_07.speechCount = 135;
        item_07.likeCount = 189;
        list.add(item_07);

        FitemWeibo item_08 = new FitemWeibo();
        item_08.headId = R.drawable.iv_head_00008;
        item_08.nickname = "X浪新闻热搜热榜";
        item_08.time = "昨天";
        item_08.content = "一款雪糕，中外市场用两种料？今天#联合利华承认梦龙中外用料双标#，又一个网红产品翻车。网红高端产品为何频繁成全网黑惹众怒？消费者究竟是为了营销买单还是为了品质买单？#网红产品水有多深#？一起来揭密>>";
        item_08.shareCount = 7;
        item_08.speechCount = 2;
        item_08.likeCount = 5;
        list.add(item_08);

        FitemWeibo item_09 = new FitemWeibo();
        item_09.headId = R.drawable.iv_head_00009;
        item_09.nickname = "ZXXX知客";
        item_09.time = "前天";
        item_09.content = "快进到付费宝贝靠前展示？";
        item_09.contentImg = R.drawable.iv_weibo_content_09;
        item_09.shareCount = 1;
        item_09.speechCount = 0;
        item_09.likeCount = 20;
        list.add(item_09);

        FitemWeibo item_10 = new FitemWeibo();
        item_10.headId = R.drawable.iv_head_00010;
        item_10.nickname = "科技XX";
        item_10.time = "8月2日";
        item_10.content = "真是起名鬼才 ……";
        item_10.contentImg = R.drawable.iv_weibo_content_10;
        item_10.location = "杭州";
        item_10.shareCount = 1;
        item_10.speechCount = 14;
        item_10.likeCount = 0;
        list.add(item_10);

        return list;
    }

    /**
     * 获取数据 --> 微博列表（JSON）
     * @return
     */
    public static String getDataToWeiBoJSON(){
        List<FitemWeibo> list = getDataToWeiBo();

        String json = JSON.toJSONString(list);
        json = "{\"code\":200,\"message\":\"\",\"data\":" + json + ",\"request_time\":1623750442016,\"response_time\":1623750442141}";

        return json;
    }

    /**
     * 获取数据 --> 图库列表
     * @return
     */
    @SuppressLint("ResourceType")
    public static List<FreedomItem> getDataToPhoto(){
        List<FreedomItem> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.add(new FitemPhoto(R.drawable.iv_photo_01));
            list.add(new FitemPhoto(R.drawable.iv_photo_02));
            list.add(new FitemPhoto(R.drawable.iv_photo_03));
            list.add(new FitemPhoto(R.drawable.iv_photo_04));
            list.add(new FitemPhoto(R.drawable.iv_photo_05));
            list.add(new FitemPhoto(R.drawable.iv_photo_06));
            list.add(new FitemPhoto(R.drawable.iv_photo_07));
            list.add(new FitemPhoto(R.drawable.iv_photo_08));
            list.add(new FitemPhoto(R.drawable.iv_photo_09));
            list.add(new FitemPhoto(R.drawable.iv_photo_10));
            list.add(new FitemPhoto(R.drawable.iv_photo_11));
            list.add(new FitemPhoto(R.drawable.iv_photo_12));
            list.add(new FitemPhoto(R.drawable.iv_photo_13));
            list.add(new FitemPhoto(R.drawable.iv_photo_14));
            list.add(new FitemPhoto(R.drawable.iv_photo_15));
            list.add(new FitemPhoto(R.drawable.iv_photo_16));
            list.add(new FitemPhoto(R.drawable.iv_photo_17));
            list.add(new FitemPhoto(R.drawable.iv_photo_18));
            list.add(new FitemPhoto(R.drawable.iv_photo_19));
        }

        return list;
    }

    /**
     * 获取数据 --> 动态跨列
     * @return
     */
    public static List<FreedomItem> getDataToDynamicSpan(){
        List<FreedomItem> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.add(new FitemDynamisSpan(3));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemDynamisSpan(4));
            list.add(new FitemDynamisSpan(3));
            list.add(new FitemDynamisSpan(2));
            list.add(new FitemDynamisSpan(2));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemDynamisSpan(3));
        }

        return list;
    }

    /**
     * 获取数据 --> 混合跨列
     * @return
     */
    @SuppressLint("ResourceType")
    public static List<FreedomItem> getDataToAssortedSpan(){
        List<FreedomItem> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.add(new FitemPhoto(R.drawable.iv_photo_01));
            list.add(new FitemPhoto(R.drawable.iv_photo_02));
            list.add(new FitemPhoto(R.drawable.iv_photo_03));

            list.add(new FitemDynamisSpan(2));
            list.add(new FitemPhoto(R.drawable.iv_photo_04));

            list.add(new FitemPhoto(R.drawable.iv_photo_05));
            list.add(new FitemDynamisSpan(2));

            list.add(new FitemDynamisSpan(1));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemPhoto(R.drawable.iv_photo_06));

            list.add(new FitemDynamisSpan(2));
            list.add(new FitemPhoto(R.drawable.iv_photo_07));

            list.add(new FitemPhoto(R.drawable.iv_photo_08));
            list.add(new FitemPhoto(R.drawable.iv_photo_09));
            list.add(new FitemPhoto(R.drawable.iv_photo_10));

            list.add(new FitemDynamisSpan(3));

            list.add(new FitemPhoto(R.drawable.iv_photo_11));
            list.add(new FitemDynamisSpan(2));

            list.add(new FitemPhoto(R.drawable.iv_photo_12));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemDynamisSpan(1));

            list.add(new FitemDynamisSpan(2));
            list.add(new FitemPhoto(R.drawable.iv_photo_13));

            list.add(new FitemPhoto(R.drawable.iv_photo_14));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemPhoto(R.drawable.iv_photo_15));

            list.add(new FitemDynamisSpan(2));
            list.add(new FitemPhoto(R.drawable.iv_photo_16));

            list.add(new FitemPhoto(R.drawable.iv_photo_17));
            list.add(new FitemDynamisSpan(2));

            list.add(new FitemPhoto(R.drawable.iv_photo_18));
            list.add(new FitemDynamisSpan(1));
            list.add(new FitemPhoto(R.drawable.iv_photo_19));

            list.add(new FitemDynamisSpan(3));
        }

        return list;
    }

    /**
     * 获取数据 --> 礼物列表
     * @return
     */
    public static List<FitemGift> getDataToGiftList(){
        List<FitemGift> list = new ArrayList<>();

        GiftModle modle_01 = new GiftModle();
        modle_01.goods_id = 1;
        modle_01.images_static = R.drawable.iv_gift_01;
        modle_01.name = "粉丝牌";
        modle_01.beans = 10;
        list.add(new FitemGift(modle_01));

        GiftModle modle_02 = new GiftModle();
        modle_02.goods_id = 2;
        modle_02.images_static = R.drawable.iv_gift_02;
        modle_02.name = "玫瑰花";
        modle_02.beans = 1314;
        list.add(new FitemGift(modle_02));

        GiftModle modle_03 = new GiftModle();
        modle_03.goods_id = 3;
        modle_03.images_static = R.drawable.iv_gift_03;
        modle_03.name = "拿去花";
        modle_03.beans = 123;
        list.add(new FitemGift(modle_03));

        GiftModle modle_04 = new GiftModle();
        modle_04.goods_id = 4;
        modle_04.images_static = R.drawable.iv_gift_04;
        modle_04.name = "私人飞机";
        modle_04.beans = 8888;
        list.add(new FitemGift(modle_04));

        GiftModle modle_05 = new GiftModle();
        modle_05.goods_id = 5;
        modle_05.images_static = R.drawable.iv_gift_05;
        modle_05.name = "想你了";
        modle_05.beans = 521;
        list.add(new FitemGift(modle_05));

        GiftModle modle_06 = new GiftModle();
        modle_06.goods_id = 6;
        modle_06.images_static = R.drawable.iv_gift_06;
        modle_06.name = "西瓜";
        modle_06.beans = 55;
        list.add(new FitemGift(modle_06));

        GiftModle modle_07 = new GiftModle();
        modle_07.goods_id = 7;
        modle_07.images_static = R.drawable.iv_gift_07;
        modle_07.name = "乘风破浪";
        modle_07.beans = 1888;
        list.add(new FitemGift(modle_07));

        GiftModle modle_08 = new GiftModle();
        modle_08.goods_id = 8;
        modle_08.images_static = R.drawable.iv_gift_08;
        modle_08.name = "粉丝卡";
        modle_08.beans = 10;
        list.add(new FitemGift(modle_08));

        GiftModle modle_09 = new GiftModle();
        modle_09.goods_id = 9;
        modle_09.images_static = R.drawable.iv_gift_09;
        modle_09.name = "棒棒糖";
        modle_09.beans = 1;
        list.add(new FitemGift(modle_09));

        GiftModle modle_10 = new GiftModle();
        modle_10.goods_id = 10;
        modle_10.images_static = R.drawable.iv_gift_10;
        modle_10.name = "蛋糕";
        modle_10.beans = 88;
        list.add(new FitemGift(modle_10));

        GiftModle modle_11 = new GiftModle();
        modle_11.goods_id = 11;
        modle_11.images_static = R.drawable.iv_gift_11;
        modle_11.name = "粽意你";
        modle_11.beans = 521;
        list.add(new FitemGift(modle_11));

        GiftModle modle_12 = new GiftModle();
        modle_12.goods_id = 12;
        modle_12.images_static = R.drawable.iv_gift_12;
        modle_12.name = "吃豆豆";
        modle_12.beans = 122;
        list.add(new FitemGift(modle_12));

        GiftModle modle_13 = new GiftModle();
        modle_13.goods_id = 13;
        modle_13.images_static = R.drawable.iv_gift_13;
        modle_13.name = "丘比特";
        modle_13.beans = 2;
        list.add(new FitemGift(modle_13));

        GiftModle modle_14 = new GiftModle();
        modle_14.goods_id = 14;
        modle_14.images_static = R.drawable.iv_gift_14;
        modle_14.name = "bear";
        modle_14.beans = 188;
        list.add(new FitemGift(modle_14));

        GiftModle modle_15 = new GiftModle();
        modle_15.goods_id = 15;
        modle_15.images_static = R.drawable.iv_gift_15;
        modle_15.name = "快乐星球";
        modle_15.beans = 588;
        list.add(new FitemGift(modle_15));

        GiftModle modle_16 = new GiftModle();
        modle_16.goods_id = 16;
        modle_16.images_static = R.drawable.iv_gift_16;
        modle_16.name = "魅影五周年";
        modle_16.beans = 999;
        list.add(new FitemGift(modle_16));

        GiftModle modle_17 = new GiftModle();
        modle_17.goods_id = 17;
        modle_17.images_static = R.drawable.iv_gift_17;
        modle_17.name = "干杯";
        modle_17.beans = 22;
        list.add(new FitemGift(modle_17));

        GiftModle modle_18 = new GiftModle();
        modle_18.goods_id = 18;
        modle_18.images_static = R.drawable.iv_gift_18;
        modle_18.name = "小龙虾";
        modle_18.beans = 99;
        list.add(new FitemGift(modle_18));

        GiftModle modle_19 = new GiftModle();
        modle_19.goods_id = 19;
        modle_19.images_static = R.drawable.iv_gift_19;
        modle_19.name = "篝火晚会";
        modle_19.beans = 168;
        list.add(new FitemGift(modle_19));

        GiftModle modle_20 = new GiftModle();
        modle_20.goods_id = 20;
        modle_20.images_static = R.drawable.iv_gift_20;
        modle_20.name = "篝火";
        modle_20.beans = 68;
        list.add(new FitemGift(modle_20));

        GiftModle modle_21 = new GiftModle();
        modle_21.goods_id = 21;
        modle_21.images_static = R.drawable.iv_gift_21;
        modle_21.name = "爆照";
        modle_21.beans = 68;
        list.add(new FitemGift(modle_21));

        GiftModle modle_22 = new GiftModle();
        modle_22.goods_id = 22;
        modle_22.images_static = R.drawable.iv_gift_22;
        modle_22.name = "面基";
        modle_22.beans = 69;
        list.add(new FitemGift(modle_22));

        GiftModle modle_23 = new GiftModle();
        modle_23.goods_id = 23;
        modle_23.images_static = R.drawable.iv_gift_23;
        modle_23.name = "凯风";
        modle_23.beans = 88;
        list.add(new FitemGift(modle_23));

        GiftModle modle_24 = new GiftModle();
        modle_24.goods_id = 24;
        modle_24.images_static = R.drawable.iv_gift_24;
        modle_24.name = "歌神";
        modle_24.beans = 121;
        list.add(new FitemGift(modle_24));

        GiftModle modle_25 = new GiftModle();
        modle_25.goods_id = 25;
        modle_25.images_static = R.drawable.iv_gift_25;
        modle_25.name = "美妙音符";
        modle_25.beans = 521;
        list.add(new FitemGift(modle_25));

        GiftModle modle_26 = new GiftModle();
        modle_26.goods_id = 26;
        modle_26.images_static = R.drawable.iv_gift_26;
        modle_26.name = "荧光棒";
        modle_26.beans = 99;
        list.add(new FitemGift(modle_26));

        GiftModle modle_27 = new GiftModle();
        modle_27.goods_id = 27;
        modle_27.images_static = R.drawable.iv_gift_27;
        modle_27.name = "水枪";
        modle_27.beans = 199;
        list.add(new FitemGift(modle_27));

        GiftModle modle_28 = new GiftModle();
        modle_28.goods_id = 28;
        modle_28.images_static = R.drawable.iv_gift_28;
        modle_28.name = "爱你";
        modle_28.beans = 521;
        list.add(new FitemGift(modle_28));

        GiftModle modle_29 = new GiftModle();
        modle_29.goods_id = 29;
        modle_29.images_static = R.drawable.iv_gift_29;
        modle_29.name = "玉米";
        modle_29.beans = 188;
        list.add(new FitemGift(modle_29));

        GiftModle modle_30 = new GiftModle();
        modle_30.goods_id = 30;
        modle_30.images_static = R.drawable.iv_gift_30;
        modle_30.name = "萝卜";
        modle_30.beans = 166;
        list.add(new FitemGift(modle_30));

        GiftModle modle_31 = new GiftModle();
        modle_31.goods_id = 31;
        modle_31.images_static = R.drawable.iv_gift_31;
        modle_31.name = "棒棒";
        modle_31.beans = 11;
        list.add(new FitemGift(modle_31));

        GiftModle modle_32 = new GiftModle();
        modle_32.goods_id = 32;
        modle_32.images_static = R.drawable.iv_gift_32;
        modle_32.name = "呦呦";
        modle_32.beans = 66;
        list.add(new FitemGift(modle_32));

        GiftModle modle_33 = new GiftModle();
        modle_33.goods_id = 33;
        modle_33.images_static = R.drawable.iv_gift_33;
        modle_33.name = "福袋";
        modle_33.beans = 5;
        list.add(new FitemGift(modle_33));

        GiftModle modle_34 = new GiftModle();
        modle_34.goods_id = 34;
        modle_34.images_static = R.drawable.iv_gift_34;
        modle_34.name = "炸弹";
        modle_34.beans = 99;
        list.add(new FitemGift(modle_34));

        return list;
    }

    /**
     * 获取数据 --> 微信
     * @return
     */
    @SuppressLint("ResourceType")
    public static List<FreedomItem> getDataToWeichat(){
        List<FreedomItem> list = new ArrayList<>();

        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "下班", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "才五点半", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "还有四个小时下班", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "你们牛", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "晚上十点下班？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "是啊 悲催", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00003, "修道院的救赎", "你这是违法行为", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "？", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "好吧", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "这还不举报？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "10点下班。。", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "加班吧？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "这不正常", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "没有加班费重点是", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "管饭", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "事情搞大让电视台去你们公司采访一下", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "😏", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "我们可以调休", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "只有周末才能调休", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "🐂🍺", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "这不跑路？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "等着干啥呢", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "干到10点", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00005, "Charm", "能别那么卷吗", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "太狠了点", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00005, "Charm", "下班多坐半个钟我都觉得对不起同事", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "确实", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "一般不是上线都是准时走", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "羡慕", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "辞职", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "我们要是下班敢晚走都是千古罪人", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "😂😂", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "@小黄牛 你的网名符号你们公司的经营理念😏", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "符合", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00006, "野子", "内卷还这么严重？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "不会吧，你们都不加班?", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "谁加班谁就是千古罪人，你说呢？😉", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "我们加班超过两小时就能调休", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "一般确实没人加班", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "慕了", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00007, "小五", "@小黄牛 这不辞职？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00002, "小黄牛", "刚来试用期还没过", true));
        list.add(new FitemWeChat(R.drawable.iv_head_00010, "先人", "@柚子 我们3个小时", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00010, "先人", "我来了几个月，一次班没加过，到点打卡下班", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "试用期更要跑路", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00001, "曾经", "刚开始都这样", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "试用期都这样了", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00006, "野子", "1:企业文化不行 2:领导不行", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00006, "野子", "3:氛围不好 勾心斗角 办公室内卷", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00009, "柚子", "最讨厌加班内卷了", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00006, "野子", "内卷还这么严重？", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00010, "先人", "加锤子", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00010, "先人", "加班就走人", false));
        list.add(new FitemWeChat(R.drawable.iv_head_00006, "野子", "惯得臭毛病？", false));

        return list;
    }

}
