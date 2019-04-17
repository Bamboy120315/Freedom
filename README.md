## 万能适配器 + 下拉刷新 + 无感分页
  

### v3.1更新
- 完善更多下拉刷新场景
- 增加【上拉分页加载】和【无感分页加载】

  
### v3.0更新
- 增加超强功能的下拉刷新  

  
### v2.0更新
- 增加对团队开发的支持  
- 增加对混合列表的支持  
  
### 简介
  
非约束列表，  
也是很多同学口中的“万能适配器”，    
近期增加了下拉刷新 和 无感分页。

![list](https://upload-images.jianshu.io/upload_images/6179866-ef8f1850a62221f5.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    
#### 一、万能适配器  
![万能适配器](https://img-blog.csdnimg.cn/20190319155546751.gif)  
这个是代码上的技术，  
所以界面上看不到太多的内容，  
想要了解更多，  
可以查看博客：  
https://bamboy.blog.csdn.net/article/details/71727461  
  
#### 二、下拉刷新  
![默认风格](https://img-blog.csdnimg.cn/20190417154938507.gif)  
![经典风格](https://img-blog.csdnimg.cn/2019041715492010.gif)  
![个人中心](https://img-blog.csdnimg.cn/20190417154855950.gif)  
![头部固定](https://img-blog.csdnimg.cn/20190417154837889.gif)  
SmartRefreshLayout  
是github上的一位大神写的下拉刷新布局，  
功能丰富强大，  
使用方便易懂。   
  
本Demo就是整合了SmartRefresh，  
然后进行了简单的精简，  
并且为了与本Demo风格统一，  
额外写了一种刷新样式，  
并置为默认样式。  
  
使用起来非常简单，  
XML里将RecyclerView  
用SmartRefreshLayout包裹住。  
然后Java里加个监听就可以了。  
详见可以见大神的github：  
https://github.com/scwang90/SmartRefreshLayout 
  
#### 三、分页加载  
![上拉加载](https://img-blog.csdnimg.cn/20190417154756787.gif)  
![自动、无感加载](https://img-blog.csdnimg.cn/20190417154721824.gif)  
分页加载，  
本Demo中提供了两种方案，  
一种是经典的上拉方案，  
这个功能和下拉刷新一样，  
也是在SmartRefreshLayout里面的。  
  
至于第二种无感分页，  
则是一种轻量级的方案，  
不需要三方，  
简单的滑动监听即可实现，  
详情见博客：  
https://bamboy.blog.csdn.net/article/details/54408691  
  
  
如果有疑问的地方，  
欢迎在文章下评论，  
或者加入QQ讨论群：569614530，  
群里找我，  
我是尘少。  
![扫码加入QQ讨论群](https://img-blog.csdnimg.cn/20190312095824708.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhbWJveV8=,size_16,color_FFFFFF,t_70)
  
本文github链接：  
https://github.com/Bamboy120315/Freedom  
  
也可以先下载apk安装体验：  
![扫码下载apk](https://upload-images.jianshu.io/upload_images/6179866-e08fb8bdf9392ee9.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)