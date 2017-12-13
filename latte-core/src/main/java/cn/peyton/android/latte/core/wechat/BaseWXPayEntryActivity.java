package cn.peyton.android.latte.core.wechat;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
/**
 * <h3>微信支付入口 Activity 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 2017-12-06 15:01
 * 版本 1.0.0
 * </pre>
 */
public abstract class BaseWXPayEntryActivity extends BaseWXActivity {
    /** 微信支付成功 {0} */
    private static final int WX_PAY_SUCCESS = 0;
    /** 微信支付失败 {-1} */
    private static final int WX_PAY_FAIL = -1;
    /** 微信支付取消 {-2} */
    private static final int WX_PAY_CANCEL = -2;

    /**
     * 微信支付成功 { 指定子类实现 }
     */
    protected abstract void onPaySuccess();

    /**
     * 微信支付失败 { 指定子类实现 }
     */
    protected abstract void onPayFail();

    /**
     * 微信支付取消 { 指定子类实现 }
     */
    protected abstract void onPayCancel();

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case WX_PAY_SUCCESS:
                    onPaySuccess();
                    break;
                case WX_PAY_FAIL:
                    onPayFail();
                    break;
                case WX_PAY_CANCEL:
                    onPayCancel();
                    break;
                default:
                    break;
            }
        }
    }
}
