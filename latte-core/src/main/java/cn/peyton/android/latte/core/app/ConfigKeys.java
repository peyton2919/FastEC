package cn.peyton.android.latte.core.app;

/**
 * <h3>全局配置类型枚举</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.app.ConfigKeys
 * 项目名 FestEC
 * 创建时间 2017-12-01 - 11:21
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public enum ConfigKeys {
    /** 网络请求域名 */
    APP_HOST,
    /** 全局上下文 */
    APPLICATION_CONTEXT,
    /** 初始化状态{ 完成状态 } */
    CONFIG_READY,
    /** 项目初始化状态 */
    ICON,
    /** 拦截器 */
    INTERCEPTOR,
    /** 延迟加载*/
    LOADER_DELAYED,
    /** 微信App ID */
    WE_CHAT_APP_ID,
    /** 微信 secret*/
    WE_CHAT_APP_SECRET,
    /** 微信用到的上下文 */
    ACTIVITY,
    HANDLER,
    /** WebView Javascript */
    JAVASCRIPT_INTERFACE,
    /** 浏览器加载的host */
    WEB_HOST,
    ;
}
