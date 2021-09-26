## 非约束列表(万能适配器) v4.0
  

### v4.0重磅更新
- 全新架构  
- 超轻量级  
- 增加玩法Demo  
  
  
### 简介  
  
>首先回顾一下非约束列表的设计理念。  
>非约束列表的设计理念是插件化，  
>每一个条目都是一个插件，  
>工程里所有页面的所有列表互通互用，  
>一个插件即一个条目，  
>你甚至可以认为他是一个自定义View。  
  
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
![项目结构](https://img-blog.csdnimg.cn/img_convert/3cac1759450018354888d682feea0ad3.png)  
两个interface就不说了，  
各自只有一个接口，  
我们来看下这三个class的代码量。  
  
>FreedomAdapter.class  
>只有两百行代码，  
>去除注释和空行，  
>只有95行。  
>  
>FreedomItem.class  
>代码不到一百行，  
>去除注释和空行，  
>只有26行。  
>  
>BaseViewHolder.class  
>去掉注释和空行17行。  
>但是这个类同样可以扮演封装工具类的角色，  
>我大约扩展了50个方法，  
>代码量大约增至了300行，  
>你也可以根据你的业务自行扩展，  
>扩展得越多，  
>功能就越强大。  
  
核心代码一共140行左右，  
现在可以理解为什么说超轻量级了吧。  
  
### 原理  
RecyclerView的条目处理逻辑，  
想必大家也都清楚，  
这里我用一张图简单概览一下：  
![关系概览](https://upload-images.jianshu.io/upload_images/6179866-b9fc5af28863a0cc.gif)   
那Adapter里面，   
是如何把Data变成条目的呢？  
![原生方案](https://upload-images.jianshu.io/upload_images/6179866-e6b312f4d973b692.gif)   
从上图中可以看出，
Adapter里，  
先是经过**getItemViewType()**  
根据Data来判断这个条目是哪个类型，  
然后在**onCreateViewHolder()**
根据类型来处理相对应的Layout，  
最后通过**onBindViewHolder()**
把Data里的数据绑定到相对应的View上。  
  
当然，  
这是原生的流程，  
那我们的非约束列表呢？  
  
请看下图：  
![非约束列表](https://upload-images.jianshu.io/upload_images/6179866-581a2ce3758f706f.gif)   
细心地同学应该发现了，  
非约束列表的方案里，
并没有改变太多原生的流程，  
只是把逻辑放在了Data里面来处理。  
毕竟140行代码也做不了太多事… [抠鼻.img]  
  
非约束列表中最大的改变，  
只是把onBindViewHolder()里的逻辑，  
转移到Data里处理。  
  
这么做的好处是什么？  
答案有二：  
一、把Adapter架空。  
二、一条Data就可以变成一个插件。

先说第一个好处，  
既然被称为万能适配器，  
那么就意味着在使用过程中，  
不需要为每个列表都重写一个Adapter，  
只需要 new 一个万能适配器的对象即可。  
  
再说第二个好处，  
插件式作为非约束列表的设计理念，  
那么就一定要拥有插件该有的特性，  
那就是独立、低耦合。  
  
每一个插件都是独立的，  
可以存在于任何一个列表中，  
而Data变成了插件，  
就意味着所有逻辑都可以在内部完成，  
才能实现万能适配器。
  
### 使用  
非约束列表的使用也很简单，  
先从GitHub上把Demo下载下来，  
（GitHub链接在文章底部）  
然后把工程里freedom Model下  
这图中这个文件夹复制到你得项目中，  
就可以使用了。  
![工程位置](https://upload-images.jianshu.io/upload_images/6179866-252afc408ce24dac.jpg)   
具体用法在Demo里都有，  
这里只说下注意事项。  
  
首先需要注意的就是  
bindLayoutId()  
这个方法一定要重写，  
因为是要告诉Adapter，  
这个插件的Type和所对应的layout.XML。  
  
>**注意：一个插件可以绑定多个layout.XML**  
>**但一个layout.XML不能被多个插件绑定**  
    
然后就是步长相关的两个方法：
```
/**
 * 当前条目所占总列数的比例
 *
 * @return
 */
protected float getSpanRatio() {
    return 0;
}

/**
 * 当前条目所占步长
 *
 * @return
 */
protected int getSpanLength() {
    return 0;
}
```
  
先说下步长这个概念，  
大家都知道GridLayoutManager，  
举个四列的例子  
![条目跨列](https://upload-images.jianshu.io/upload_images/6179866-57752af153300e50.jpg)  
有的条目需要跨3列，  
有的条目需要跨2列，  
有的条目不需要跨列，  

这个时候怎么办呢？  
很简单，  
只需要重写**getSpanLength()**  
  
但是好像还不能满足需求，  
再举个例子，  
有两个列表会用到同一个插件，  
第一个列表一共四列，  
第二个列表一共八列，  
这个插件在第一个列表里跨1列，  
在第二个列表里就要跨2列，  
那么在**getSpanLength()** 返回多少呢？
  
所以有了第二个方法，  
**getSpanRatio()**  
这个方法的意思是，  
当前条目所占总列数的比例。  
  
再回来看刚才的列子，  
**getSpanRatio()** 返回 1/4，  
就满足了不同列表中所跨的列数。    
  
>如果同时重写了getSpanLength() 和 getSpanRatio()  
>以getSpanLength()为准
  
  
  
  
### Demo  
  
为了让大家更轻松的上手，  
我也重新设计了一下Demo，  
后期会更新不同的玩法，  
来体现插件化的优势与魅力。  
![玩法Demo](https://upload-images.jianshu.io/upload_images/6179866-29d52606768b615f.jpg)  
  
  
老话说得好，  
程序员何苦为难程序员。  
  
v4.0的这个核心逻辑只有一百多行的代码，  
能在开发过程中有什么起到什么作用呢？  
  
咱们以第一个Demo为例，  
这个页面的条目是长这样的：  
![微博示例](https://upload-images.jianshu.io/upload_images/6179866-1855dc94b5c30a38.jpg)  
  
  
这个Activity的代码是这样的：  
![微博列表代码](https://img-blog.csdnimg.cn/img_convert/7491e5a5e98ad499c944875b9798bffe.png)  
  
当然，  
经验丰富的朋友一眼就看出来了，  
这个列表的逻辑主要在条目里，  
在这里我们不叫条目，  
而是插件，  
那下面我们来看下这个插件里的代码：  
![微博插件代码](https://img-blog.csdnimg.cn/img_convert/f7743f12b786e0a29d28c96a2dcba17e.png)  
  
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
  
老规矩上源码：  
[https://download.csdn.net/download/bamboy_/24335315](https://download.csdn.net/download/bamboy_/24335315)    
  
本文GitHub：  
[https://github.com/Bamboy120315/Freedom](https://github.com/Bamboy120315/Freedom)  
  
如果有疑问的地方，  
欢迎留言，  
或者加入QQ讨论群：569614530，  
群里找我，  
我是尘少。  
![QQ群二维码](https://img-blog.csdnimg.cn/img_convert/c0088aea221185612a907ac7fdf321d7.png)  
  