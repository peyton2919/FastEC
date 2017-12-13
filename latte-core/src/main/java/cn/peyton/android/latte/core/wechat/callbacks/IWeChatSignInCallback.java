package cn.peyton.android.latte.core.wechat.callbacks;

/**
 * <h3>微信登录回调接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.callbacks.IWeChatSignInCallback
 * 项目名 FestEC
 * 创建时间 2017-12-06 14:25
 * 版本 1.0.0
 * </pre>
 */
public interface IWeChatSignInCallback {
    /**
     * 登录成功
     * @param userInfo 用户信息
     */
    void onSignInSuccess(String userInfo);
}
