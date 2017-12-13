package cn.peyton.android.latte.ec.sign;

/**
 * <h3>登录监听接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.sign.ISignListener
 * 项目名 FestEC
 * 创建时间 2017-12-05 9:40
 * 版本 1.0.0
 * </pre>
 */
public interface ISignListener {

    /**
     * 登录成功回调
     */
    void onSignInSuccess();

    /**
     * 注册成功回调
     */
    void onSignUpSuccess();
}
