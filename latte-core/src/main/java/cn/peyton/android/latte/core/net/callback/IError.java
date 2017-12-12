package cn.peyton.android.latte.core.net.callback;

/**
 * <pre>
 * 请求错误接口
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.callback.IError
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:35
 * 版本 1.0.0
 */
public interface IError {
    /**
     * 错误
     * @param code 返回错误码
     * @param msg 返回错误信息
     */
    void onError(int code, String msg);
}
