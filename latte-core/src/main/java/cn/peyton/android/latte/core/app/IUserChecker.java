package cn.peyton.android.latte.core.app;

/**
 * <pre>
 *  用户状态检查
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.app.IUserChecker
 * 项目名 FestEC
 * 创建时间 2017-12-05 9:26
 * 版本 1.0.0
 */
public interface IUserChecker {

    /**
     * 有用户信息
     */
    void onSignIn();

    /**
     * 没有用户信息
     */
    void onNotSignIn();
}
