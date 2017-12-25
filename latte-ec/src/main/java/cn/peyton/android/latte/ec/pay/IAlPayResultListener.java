package cn.peyton.android.latte.ec.pay;

/**
 * <h3>支付宝支付返回回调监听</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.pay.IAlPayResultListener
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public interface IAlPayResultListener {
    /**  支付成功回调 */
    void onPaySuccess();
    /**  支付中回调 */
    void onPaying();
    /** 支付失败回调  */
    void onPayFail();
    /** 支付取消回调  */
    void onPayCancel();
    /** 支付连接异常回调  */
    void onPayConnectError();
}
