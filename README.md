## 非约束列表(万能适配器) v4.0
  

### v4.0重磅更新
- 全新架构  
- 超轻量级  
- 增加玩法Demo  
  
  
### 简介  
  
    首先回顾一下非约束列表的设计理念。  
    非约束列表的设计理念是插件化，  
    每一个条目都是一个插件，  
    工程里所有页面的所有列表互通互用，  
    一个插件即一个条目，  
    你甚至可以认为他是一个View。  
  
比如同一个条目可能会出现在很多列表中，  
传统做法就是在各自列表的Adapter中，  
复制粘贴相同的代码，  
后期维护时悲愤万千。  
  
而非约束列表是插件化的理念，  
只需要写一个插件，  
再多的列表想要用它，  
直接拿来就可以用，  
无须再做额外开发。  
  
下面我们看下从内到外的全新设计，  
简直可以说是超轻量级了。  
  
整个库只有两个interface和三个class：  
![项目结构](https://upload-images.jianshu.io/upload_images/6179866-4e2eee14fc42f202.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
两个interface就不说了，  
各自只有一个接口，  
我们来看下这三个class的代码量。  
  
    FreedomAdapter.class  
    只有两百行代码，  
    去除注释和空行，  
    只有95行。  
  
    FreedomItem.class  
    代码不到一百行，  
    去除注释和空行，  
    只有26行。  
  
    BaseViewHolder.class  
    去掉注释和空行17行。  
    但是这个类同样可以扮演封装工具类的角色，  
    我大约扩展了50个方法，  
    代码量大约增至了300行，  
    你也可以根据你的业务自行扩展，  
    扩展得越多，  
    功能就越强大。  
  
核心代码一共140行左右，  
现在可以理解为什么说超轻量级了吧。  
  
另外，  
Demo界面也重新设计了一下，  
后期会更新不同的玩法，  
来体现插件化的优势与魅力。  
![玩法Demo](https://upload-images.jianshu.io/upload_images/6179866-03780441169b8b46.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)  
  
老话说得好，  
程序员何苦为难程序员。  
  
v4.0的这个核心逻辑只有一百多行的代码，  
能在开发过程中有什么起到什么作用呢？  
  
咱们以第一个Demo为例，  
这个页面的条目是长这样的：  
![微博示例_带壳](https://upload-images.jianshu.io/upload_images/6179866-ca4fbb6b3c2a5496.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)  
  
这个Activity的代码是这样的：  
![微博列表代码](https://upload-images.jianshu.io/upload_images/6179866-d71784ec4374fe4e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)  
  
当然，  
经验丰富的朋友一眼就看出来了，  
这个列表的逻辑主要在条目里，  
在这里我们不叫条目，  
而是插件，  
那下面我们来看下这个插件里的代码：  
![微博插件代码](https://upload-images.jianshu.io/upload_images/6179866-ef1239e9f605c0bc.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)  
  
还记得前面我说过，  
BaseViewHolder里，  
我扩展的那50个方法吗？  
虽然增加了两百多行代码，  
但链式调用真的太爽了。  
  
链式一时爽，  
一直链式一直爽……  
  
言归正传，  
这个页面除Layout外，  
所有代码都在这里了，  
就是这么简洁，  
甚至不需要写Adapter，  
谁让这个Adapter是万能的呢？  
  
暂时先说这么多，  
后续有时间继续补充。  
  
本文GitHub：  
[https://github.com/Bamboy120315/Freedom](https://github.com/Bamboy120315/Freedom)  
  
如果有疑问的地方，  
欢迎留言，  
或者加入QQ讨论群：569614530，  
群里找我，  
我是尘少。  
![QQ群二维码](https://upload-images.jianshu.io/upload_images/6179866-d4d7850f3d0b2123.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/360)  
  