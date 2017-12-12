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
 * <pre>
 *  Web 页面基础核心
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 */

public abstract class WebDelegate extends LatteDelegate {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = false;

    public WebDelegate() { }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final  Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
    }

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
