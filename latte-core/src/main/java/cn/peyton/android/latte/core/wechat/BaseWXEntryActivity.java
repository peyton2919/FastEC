package cn.peyton.android.latte.core.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.util.log.LatteLogger;

/**
 * <h3>基础微信入口 Activity 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.LatteWeChat.BaseWXEntryActivity
 * 项目名 FestEC
 * 创建时间 2017-12-05 23:18
 * 版本 1.0.0
 * </pre>
 */
public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 用户登录成功后回调 { 指定子类调用 }
     * @param userInfo 用户信息
     */
    protected abstract void onSignInSuccess(String userInfo);

    /**
     * 第三方应用发送请求到微信后的回调
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {

        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        //获取Auth,微信需要多次请求
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        LatteLogger.d("authUrl:", authUrl.toString());
        getAuth(authUrl.toString());

    }

    /**
     * 获取用户信息
     * @param authUrl 获取用户信息链接
     */
    private void getAuth(String authUrl) {
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");
                        //获取微信用户信息的URL
                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        LatteLogger.d("userInfoUrl:", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    /**
     * 获取用户真正信息
     * @param userInfoUrl 获取用户真正信息链接
     */
    private void getUserInfo(String userInfoUrl) {
        RestClient.builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response); //抽象方法
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

    /**
     * 微信发送请求到第三方应用后的回调
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

}
