package cn.peyton.android.latte.core.net.callback;

/**
 * <pre>
 * 请求成功接口
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.callback.ISuccess
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:35
 * 版本 1.0.0
 */
@SuppressWarnings("ALL")
public interface ISuccess {

    /**
     * 成功
     * @param response 返回成功信息
     */
    void onSuccess(String response);
}
