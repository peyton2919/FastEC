package cn.peyton.android.latte.core.fragment.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.fragment.delegates.web.route.RouteKeys;

/**
 * <h3>Web {WebView} 页面基础核心 抽象类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.WebDelegate
 * 创建时间 2017/12/13 15:45
 * 版本 1.0.0
 * </pre>
 */
public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer{
    /** 申明 WebView对象 */
    private WebView mWebView = null;
    /** 申明WebView队列集合 */
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    /** 申明Url */
    private String mUrl = null;
    /** 是否可获得WebView */
    private boolean mIsWebViewAvailable = false;
    /** 申明 LatteDelegate 对象 */
    private LatteDelegate mTopDelegate = null;
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
        initWebView();
    }

    /**
     * 设置顶部Delegate
     * @param delegate LatteDelegate对象
     */
    public void setTopDelegate(LatteDelegate delegate) {
       this.mTopDelegate = delegate;
    }

    /**
     * 获取顶部Delegate
     * @return LatteDelegate对象
     */
    public LatteDelegate getTopDelegate() {
        if (null == mTopDelegate) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    /**
     * 获取WebView对象
     * @return WebView对象
     */
    public WebView getWebView() {
        if (null == mWebView) {
            throw new NullPointerException("【WebDelegate】 WebView 为空!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    /**
     * 获取链接地址
     * @return
     */
    public String getUrl() {
        if (null == mUrl) {
            throw new NullPointerException("【WebDelegate】 WebView 为空!");
        }
        return mUrl;
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

                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);

                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAvailable = true;
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
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mWebView) {
            mWebView.destroy();
        }
    }
}
