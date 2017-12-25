package cn.peyton.android.latte.core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.delegates.web.WebDelegate;
import cn.peyton.android.latte.core.delegates.web.WebDelegateImpl;

/**
 * <h3>路由 {WebView} 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.web.route.Router
 * 项目名称 FastEC
 * 创建时间 2017/12/13 20:40
 * 版本 1.0.0
 * </pre>
 */
public class Router {

    /**
     * 构造函数
     */
    public Router() {}

    /**
     * 处理 Web Url
     * @param delegate WebDelegate 对象
     * @param url 链接地址
     * @return
     */
    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    /**
     * 加载 Web 页面
     * @param webView WebView对象
     * @param url 链接地址
     */
    public final void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView,url);
        }
    }

    /**
     * 加载 Web 页面
     * @param delegate WebDelegate对象
     * @param url 链接地址
     */
    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(),url);
    }

    /**
     * 加载 Web 页面
     * @param webView WebDelegate对象
     * @param url 链接地址
     */
    private void loadWebPage(WebView webView, String url) {
        if (null != webView) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("【Router】 WebView 为空!");
        }
    }

    /**
     * 加载本地页面
     * @param webView
     * @param url
     */
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    /**
     * 处理呼入电话
     * @param context 上下文对象
     * @param uri
     */
    private void callPhone(Context context,String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        context.startActivity(intent);
        ContextCompat.startActivity(context,intent,null);
    }

    //================================== 单例 Router对象 开始 =======================================
    /**
     * 获取 Router对象
     * @return Router对象
     */
    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 内部类
     */
    private static class Holder {
        private static final Router INSTANCE = new Router();
    }
    //================================== 单例 Router对象 结束 =======================================
}
