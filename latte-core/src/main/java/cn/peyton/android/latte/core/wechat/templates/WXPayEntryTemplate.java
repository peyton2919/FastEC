package cn.peyton.android.latte.core.wechat.templates;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;

import cn.peyton.android.latte.core.activities.ProxyActivity;
import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.wechat.BaseWXPayEntryActivity;

/**
 * <h3>微信支付入口 模板</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.wechat.templates.WXPayEntryTemplate
 * 项目名 FestEC
 * 创建时间 2017-12-05 19:08
 * 版本 1.0.0
 * </pre>
 */
public class WXPayEntryTemplate extends BaseWXPayEntryActivity{

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this,"支付成功",Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this,"支付失败",Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this,"支付取消",Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
