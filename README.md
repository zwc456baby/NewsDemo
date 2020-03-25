
# 新闻客户端

一个 MVVM 设计模式的新闻客户端 Demo 程序

使用 Kotlin 编写

本意是想了解和学习目前新的设计模式

使用的类库如下：`Gson` , `Glide` , `Okhttp` , 'RxJava' , 'Retrofit'

使用 Jetpack 组件，Fragment 之间切换使用 `Navigation`


网络数据大约 10分钟更新一次

支持上拉加载以及 下拉 刷新（暂时没有加载和刷新动画或提示,未使用 RecyclerView 类库，需要自行实现）

[APK 下载](https://nf.zwc365.com/release_assets/apk/app-release.apk)


页面展示：

![1](https://nf.zwc365.com/release_assets/images/news_home.png)

![2](https://nf.zwc365.com/release_assets/images/news_weburl.png)

#### other

贴一片关于 MVVM 设计模式的链接

[MVC、MVP、MVVM 如何选？](https://mp.weixin.qq.com/s/Kc1826MQ3ReMkoIWlsQGVw)


```
7.3 MVVM 架构模式的优缺点

优点：



结构清晰，职责划分清晰

模块间充分解耦

在 MVP 的基础上，MVVM 把 View 和 ViewModel 也进行了解耦



缺点：



Debug 困难，由于 View 和 ViewModel 解耦，导致 Debug 时难以一眼看出 View 的事件传递

代码复杂性增大
```


从 Demo 中，可以明确感受到 代码复杂性大过普通开发






