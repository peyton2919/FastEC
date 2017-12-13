package cn.peyton.android.latte.core.fragment.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.fragment.delegates.web.route.RouteKeys;

/**
 * <h3>Web 页面基础核心</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.WebDelegate
 * 创建时间 2017/12/13 15:45
 * 版本 1.0.0
 * </pre>
 */
public abstract class WebDelegate extends LatteDelegate {
    /** 申明 WebView对象 */
    private WebView mWebView = null;
    /** 申明WebView队列集合 */
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    /** 申明Url */
    private String mUrl = null;
    /** 是否可获得WebView */
    private boolean mIsWebViewAbailable = false;
    /** 构造函数 */
    public WebDelegate() { }

    /**
     * 初始化 IWebViewInitializer {指定子类实现}
     * @return
     */
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final  Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
    }

    /**
     * 初始化WebView
     */
    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (null != mWebView) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final  IWebViewInitializer initializer = setInitializer();
            if (null != initializer) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                mWebView.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAbailable = true;
            } else {
                throw new NullPointerException("【WebDelegate】 初始器为空！");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mWebView) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mWebView) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mWebView) {
            mWebView.destroy();
        }
    }
}
