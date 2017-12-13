package cn.peyton.android.latte.core.net.callback;

/**
 * <h3>请求接口 { 用在加载条上}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.callback.IRequest
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:36
 * 版本 1.0.0
 * </pre>
 */
public interface IRequest {

    /**
     * 请求开始
     */
    void onRequestStart();

    /**
     * 请求结束
     */
    void onRequestEnd();
}
