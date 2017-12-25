package cn.peyton.android.latte.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.peyton.android.latte.core.delegates.web.client.WebViewClientImpl;
import cn.peyton.android.latte.core.delegates.web.route.RouteKeys;
import cn.peyton.android.latte.core.delegates.web.route.Router;

/**
 * <h3>Web {WebView} 页面基础核心 实现类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.web.WebDelegateImpl
 * 项目名称 FastEC
 * 创建时间 2017/12/13 18:05
 * 版本 1.0.0
 * </pre>
 */
public class WebDelegateImpl extends WebDelegate{

    /**  申明 IPageLoadListener对象 */
    private IPageLoadListener mPageLoadListener = null;

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (null != getUrl()) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());

        }
    }

    /**
     * 设置页面加载接口
     * @param listener
     */
    public void setPageLoadListener(IPageLoadListener listener) {
        this.mPageLoadListener = listener;
    }
    /**
     * 创建 WebDelegateImpl对象{简单工厂模式}
     * @param url 链接地址
     * @return WebDelegateImpl对象
     */
    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    //================================ IWebViewInitializer 接口 开始 ===============================
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClient();
    }
    //================================ IWebViewInitializer 接口 结束 ===============================
}
