package cn.peyton.android.latte.core.fragment.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.fragment.delegates.web.WebDelegate;

/**
 * <h3>{WebView} 事件类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.event.Event
 * 项目名称 FastEC
 * 创建时间 2017/12/14 13:33
 * 版本 1.0.0
 * </pre>
 */
public abstract class Event implements IEvent {
    /** 申明 上下文对象 */
    private Context mContent = null;
    /** 申明 动作 */
    private String mAction = null;
    /** 申明 WebDelegate 对象 */
    private WebDelegate mDelegate = null;
    /**  申明 链接地址 */
    private String mUrl = null;
    /** 申明 WebView对象 */
    private WebView mWebView = null;

    /**
     * @return 上下文对象
     */
    public Context getContext() {
        return mContent;
    }

    /**
     * @param content 上下文对象
     */
    public void setContext(Context content) {
        this.mContent = content;
    }

    /**
     * @return WebView对象
     */
    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    /**
     * @return 动作
     */
    public String getAction() {
        return mAction;
    }

    /**
     * @param action 动作
     */
    public void setAction(String action) {
        this.mAction = action;
    }

    /**
     * @return WebDelegate 对象
     */
    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    /**
     * @param delegate WebDelegate 对象
     */
    public void setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
    }

    /**
     * @return  链接地址
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * @param url 链接地址
     */
    public void setUrl(String url) {
        this.mUrl = url;
    }
}
