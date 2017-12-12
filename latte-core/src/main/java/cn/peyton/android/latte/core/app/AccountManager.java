package cn.peyton.android.latte.core.app;

import cn.peyton.android.latte.core.util.storage.LattePreference;

/**
 * <pre>
 *  管理用户信息
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.app.AccountManager
 * 项目名 FestEC
 * 创建时间 2017-12-05 9:33
 * 版本 1.0.0
 */
public class AccountManager {

    /**
     * 登录方法
     * 保存用户登录状态,登录后调用
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    /**
     * 判断是否已经登录
     * @return
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查用户登录与未登录回调
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) { //已经登录
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }


    /**
     * 枚举
     */
    private enum SignTag{
        SIGN_TAG,
        ;
    }
}
