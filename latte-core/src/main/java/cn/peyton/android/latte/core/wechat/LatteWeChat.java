package cn.peyton.android.latte.core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.wechat.callbacks.IWeChatSignInCallback;

/**
 * <pre>
 *  微信接口调用
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.LatteWeChat
 * 项目名 FestEC
 * 创建时间 2017-12-05 22:43
 * 版本 1.0.0
 */
public class LatteWeChat {

    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mWeChatSignInCallback = null;

    /**
     * 构造函数
     */
    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mWeChatSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getWeChatSignInCallback() {
        return mWeChatSignInCallback;
    }


    //=========================  单例懒汉模式  ===============================
    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    //=========================  单例懒汉模式  ===============================


}
