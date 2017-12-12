package cn.peyton.android.latte.core.wechat.templates;

import cn.peyton.android.latte.core.wechat.BaseWXEntryActivity;
import cn.peyton.android.latte.core.wechat.LatteWeChat;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.templates.WXEntryTemplate
 * 项目名 FestEC
 * 创建时间 2017-12-05 19:07
 * 版本 1.0.0
 */
public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getWeChatSignInCallback().onSignInSuccess(userInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }
}
