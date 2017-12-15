package cn.peyton.android.latte.core.fragment.delegates.web;

/**
 * <h3>Web {WebView} 页面加载 监听接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.IPageLoadListener
 * 项目名称 FastEC
 * 创建时间 2017/12/14 17:43
 * 版本 1.0.0
 * </pre>
 */
public interface IPageLoadListener {
    /**
     *  页面加载开始
     */
    void onLoadStart();

    /**
     * 页面加载结束
     */
    void onLoadEnd();

}
