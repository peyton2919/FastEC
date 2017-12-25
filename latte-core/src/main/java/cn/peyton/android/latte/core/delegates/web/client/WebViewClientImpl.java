package cn.peyton.android.latte.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.delegates.web.IPageLoadListener;
import cn.peyton.android.latte.core.delegates.web.WebDelegate;
import cn.peyton.android.latte.core.delegates.web.route.Router;
import cn.peyton.android.latte.core.util.log.LatteLogger;
import cn.peyton.android.latte.core.util.storage.LattePreference;
import cn.peyton.android.latte.core.ui.loader.LatteLoader;

/**
 * <h3>{WebView} WebViewClient 实现类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.web.client.WebViewClientImpl
 * 项目名称 FastEC
 * 创建时间 2017/12/13 20:35
 * 版本 1.0.0
 * </pre>
 */
public class WebViewClientImpl extends WebViewClient {
    /** 申明WebDelegate对象  */
    private final WebDelegate DELEGATE;
    /**  申明 IPageLoadListener对象 */
    private IPageLoadListener mPageLoadListener = null;
    /** 申明 Handler对象  */
    private static final Handler HANDLER = Latte.getHandler();

    /**
     * 设置页面加载接口
     * @param listener
     */
    public void setPageLoadListener(IPageLoadListener listener) {
        this.mPageLoadListener = listener;
    }

    /**
     * 构造函数
     * @param delegate WebDelegate对象
     */
    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    /**
     * 页面开始加载
     * @param view
     * @param url
     * @param favicon
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (null != mPageLoadListener) {
            mPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    /**
     * 页面加载完成
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (null != mPageLoadListener) {
            mPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        },1000);
    }

    /**
     * 获取浏览器Cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        //这里的Cookie和API 请求的Cookie是不一样的，这个在网页不可见
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (null != webHost) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (null != cookieStr && !"".equals(cookieStr))
                    LattePreference.addCustomAppProfile("cookie",cookieStr);
            }
        }
    }

}
