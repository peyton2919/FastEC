package cn.peyton.android.latte.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;

import cn.peyton.android.latte.core.ui.loader.LatteLoader;
import cn.peyton.android.latte.core.util.log.LatteLogger;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.pay.PayAsyncTask
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class PayAsyncTask extends AsyncTask<String,Void,String>{

    private final Activity ACTIVITY;
    private final IAlPayResultListener LISTENER;
    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单支付中
    private static final String AL_PAY_STATUS_PAYING= "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //订单取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //订单网络异常
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        //true 允许验证, 返回验证码
        return payTask.pay(alPaySign,true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        LatteLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        //支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        LatteLogger.d("AL_PAY_RESULT",resultInfo);
        LatteLogger.d("AL_PAY_RESULT",resultStatus);

        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (null != LISTENER) {
                    LISTENER.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (null != LISTENER) {
                    LISTENER.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (null != LISTENER) {
                    LISTENER.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (null != LISTENER) {
                    LISTENER.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (null != LISTENER) {
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
