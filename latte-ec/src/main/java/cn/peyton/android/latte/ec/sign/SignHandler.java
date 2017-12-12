package cn.peyton.android.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.peyton.android.latte.core.app.AccountManager;
import cn.peyton.android.latte.ec.database.DatabaseManager;
import cn.peyton.android.latte.ec.database.UserProfile;

/**
 * <pre>
 *  Sign 处理者
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.sign.SignHandler
 * 项目名 FestEC
 * 创建时间 2017-12-04 21:57
 * 版本 1.0.0
 */
public class SignHandler {

    /**
     * 注册
     * @param response
     * @param signListener
     */
    public static void onSignUp(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //保存
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册成功并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    /**
     * 登录
     * @param response
     * @param signListener
     */
    public static void onSignIn(String response,ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //保存
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册成功并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
