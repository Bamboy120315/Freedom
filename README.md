## 非约束列表(俗称：万能适配器)
  
### v2.0更新
- 增加对团队开发的支持  
- 增加对混合列表的支持  
  
### 简介
  
非约束列表，  
也是很多同学口中的“万能适配器”，  
不过我并不认同“万能”的这个说法，  
谁的适配器ListView和RecyclerView通用？  
  
预览一下效果先：  
1、单列列表  
![单列列表](https://img-blog.csdnimg.cn/20190319155608583.gif)
  
2、多列列表  
![多列列表](https://img-blog.csdnimg.cn/20190319155557726.gif)
  
3、混合列表  
![混合列表](https://img-blog.csdnimg.cn/20190319155546751.gif)
  
显然，  
从界面上看不出什么名堂，  
那就先说下我的非约束列表有什么优势吧。  
  
打个比方，  
某中学要给10台电脑升级程序，  
平均一台电脑操作一次需要一个小时。  
  
初中生可能会操作10次，  
耗时10小时。  
  
高中生可能会把所有电脑连起来，  
同时操控，  
只需要操作一次，  
耗时一个小时。  
  
我们用普通的Adapter的话，  
就好比初中生，  
每次修改一个条目，  
就把所有使用这个条目的类全改一遍，  
无论是Activity还是Adapter，  
但是用了非约束列表就不一样了，  
逻辑基本上都在Bean里，  
基本上只需要改itemLayout的样式，  
和Bean里的逻辑即可。  
  
到这里，  
可能有同学会疑惑，  
为什么不管Adapter？  
因为，  
我的项目只需要一个Adapter，  
一个Adapter，  
全场通用！  
  
说得细一点，  
就是我把逻辑全部拆散重组，  
Adapter架空，  
只负责Activity、ViewHolder、bean之间的连通。  
我个人喜欢把View和与其相对应的数据放一起，  
所以我把ViewHolder和逻辑放到了Bean里，  
通过一个接口来使两者进行结合，  
哦不，  
是将数据显示到View上。  
  
但是，  
ViewHolder那么多，  
Adapter怎么将其连通起来呢？  
所以我写了一个ViewHolderManager，  
ViewHolderManager负责根据条目的类型，  
找到相对应的ViewHolder，  
进行实例化并返回给Adapter。  
  
整体流程如下图：  
![freedom](http://img.blog.csdn.net/20170512145830991?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYmFtYm95Xw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

  
好了，  
原理就说到这里，  
━━━━━━━━━━━━━━━这是清理内存的分割线━━━━━━━━━━━━━━━  
下面开始聊聊我的非约束列表的使用步骤：  
  
一：  
新建一个Bean类，  
里边写个ViewHolder内部类，  
继承ViewHolderManager.ViewHolder，  
和普通的Adapter的内部类差不多，  
不过需要注意的是，  
构造里的参数不一样：  
```
/**
 * ViewHolder --> 主页的按钮
 */
public static class MusicViewHolder extends ViewHolderManager.ViewHolder {
    public RelativeLayout rl_music;
    public TextView tv_song;
    public TextView tv_singer;

    public MusicViewHolder(ViewGroup viewGroup) {
        // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
        super(viewGroup, R.layout.item_music);

        rl_music = (RelativeLayout) itemView.findViewById(R.id.rl_music);
        tv_song = (TextView) itemView.findViewById(R.id.tv_song);
        tv_singer = (TextView) itemView.findViewById(R.id.tv_singer);
    }
}
```
不难发现，  
构造里多了个参数，  
即该ViewHolder对应的LayoutXml.  
  
二：  
进入ViewHolderManager，  
对应你新写的ViewHolder，  
增加一个公开静态常量，  
只要不与其他的值重复即可，  
并put到itemMap里，  
key为你刚写的常量，  
value为刚写的ViewHolder的class。  
  
```
/**
 * 条目类型 --> ……
 */
public static final int //……
/**
 * 条目类型 --> 新闻卡片
 */
public static final int ITEM_TYPE_NEWS = 8;
/**
 * 条目类型 --> 音乐
 */
public static final int ITEM_TYPE_MUSIC = 9;

/**
 * 加载条目类型，以及对应的条目XML
 */
static {
    itemMap = new HashMap<>();
    ……
    itemMap.put(ITEM_TYPE_NEWS, BeanNews.NewsViewHolder.class);
    itemMap.put(ITEM_TYPE_MUSIC, BeanMusic.MusicViewHolder.class);
}
```
  
三：  
回到刚才写的Bean，  
继承FreedomBean，  
在initItemType()里，  
set一下刚在ViewHolderManager里定义的那个常量，  
  
```
@Override
protected void initItemType() {
    setItemType(ViewHolderManager.ITEM_TYPE_MUSIC);
}
```
  
在initBindView()里，  
把普通Adapter里onBindViewHolder()的代码挪过来。  
  
```
@Override
protected void initBindView(final List list) {

    setViewHolderBindListener(new ViewHolderBindListener() {
        @Override
        public void onBindViewHolder(final Activity activity, final ViewHolderManager.ViewHolder viewHolder, final int position) {
            final MusicViewHolder vh = (MusicViewHolder) viewHolder;
            final BeanMusic bean = (BeanMusic) list.get(position);

            vh.tv_song.setText(bean.getSong());
            vh.tv_singer.setText(bean.getSinger());

            vh.rl_music.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ……
                }
            });
        }
    });
}
```
  
如果需要和Activity交互，  
则Activity实现FreedomCallback接口，  
并在onClickCallback里编写代码，  
然后回到Bean里调用getCallback(activity).onClickCallback();  
```
vh.rl_music.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // 点击事件
        // 如果不需要和Activity进行交互，
        // 那么直接在这里写点击事件即可
        //
        // 如果需要和Activity进行交互，
        // 那么Activity实现FreedomCallback接口，
        // 并在onClickCallback里编写代码，
        // 即可触发回调，
        // 以和Activity进行交互。
        //
        // 注意：
        // 该Activity必须实现FreedomCallback接口才能触发回调，
        // 否则会报错
        getCallback(activity).onClickCallback(v, position, vh);
    }
});
```
  
例：  
类BeanMusic里initBindView方法里的vh.rl_music.setOnClickListener()。  
  
好了，  
Bean就完成了，  
Activity里使用它的方式和普通的没什么差别，  
只是声明数据源mList的时候，  
类型为Object或者不要加类型就好：  
```
/**
 * 数据源
 */
 private List mList;
```
  
然后就可以使用FreedomAdapter了：  
  
```
// 实例化RecyclerView
mAdapter = new FreedomAdapter(this, mList);
recycler.setLayoutManager(new LinearLayoutManager(this));
recycler.setItemAnimator(new DefaultItemAnimator());
recycler.setAdapter(mAdapter);
```
  
使用起来虽然没有比普通的简单太多，  
但也功能要比普通的强大太多啊不是么？  
  
代码很简单，  
注释我也写得很全，  
如果还是有疑问的地方，  
欢迎在文章下评论，  
或者加入QQ讨论群：569614530，  
群里找我，  
我是尘少。  
![扫码加入QQ讨论群](https://img-blog.csdnimg.cn/20190312095824708.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhbWJveV8=,size_16,color_FFFFFF,t_70)

本文github链接：  
https://github.com/Bamboy120315/Freedom  
  
也可以先下载apk安装体验：  
![扫码下载apk](https://img-blog.csdnimg.cn/20190319160730260.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhbWJveV8=,size_16,color_FFFFFF,t_70)