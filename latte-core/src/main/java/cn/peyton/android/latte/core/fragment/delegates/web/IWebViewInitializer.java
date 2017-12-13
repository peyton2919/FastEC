package cn.peyton.android.latte.core.fragment.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * <h3>WebView 初始化 接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.IWebViewInitializer
 * 创建时间 2017/12/13 15:41
 * 版本 1.0.0
 * </pre>
 */
public interface IWebViewInitializer {
    /** 初始化 WebView */
    WebView initWebView(WebView webView);
    /** 初始化 WebViewClient*/
    WebViewClient initWebViewClient();
    /** 初始化 WebChromeClient */
    WebChromeClient initWebChromeClient();
}
