package cn.peyton.android.latte.core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.wechat.callbacks.IWeChatSignInCallback;

/**
 * <h3>微信接口调用</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.LatteWeChat
 * 项目名 FestEC
 * 创建时间 2017-12-05 22:43
 * 版本 1.0.0
 * </pre>
 */
public class LatteWeChat {
    /** 申明 AppID */
    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    /** 申明 AppSecret */
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    /** 申明微信接口 */
    private final IWXAPI WXAPI;
    /** 申明微信登录回调接口 */
    private IWeChatSignInCallback mWeChatSignInCallback = null;

    /**
     * 构造函数
     */
    private LatteWeChat() {
        //初始化微信接口
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);//获取存在Configurator中Activity
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true); //创建微信接口
        WXAPI.registerApp(APP_ID); //注册app
    }

    /**
     * 获取微信接口
     * @return 微信接口
     */
    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    /**
     * 登录
     */
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req); //发送请求
    }

    /**
     * 登录成功回调
     * @param callback IWeChatSignInCallback 回调对象
     * @return LatteWeChat对象
     */
    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mWeChatSignInCallback = callback;
        return this;
    }

    /**
     * 获取登录成功回调
     * @return IWeChatSignInCallback 接口对象
     */
    public IWeChatSignInCallback getWeChatSignInCallback() {
        return mWeChatSignInCallback;
    }


    //=========================  单例懒汉模式  ===============================

    /**
     * 单例懒汉模式
     * @return LatteWeChat对象
     */
    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 内部类  单例懒汉模式
     */
    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    //=========================  单例懒汉模式  ===============================


}
