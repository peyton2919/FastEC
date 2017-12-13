package cn.peyton.android.latte.core.net.callback;

/**
 * <h3>请求失败接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.callback.IFailure
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:36
 * 版本 1.0.0
 * </pre>
 */
public interface IFailure {
    /**
     * 请求失败时调用
     */
    void onFailure();
}
