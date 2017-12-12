package cn.peyton.android.latte.core.net.callback;

import android.os.Handler;

import cn.peyton.android.latte.core.ui.loader.LatteLoader;
import cn.peyton.android.latte.core.ui.loader.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *  Rest Call 请求的回调
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.callback.RequestCallbacks
 * 项目名 FestEC
 * 创建时间 2017-12-03 11:13
 * 版本 1.0.0
 */
public class RequestCallbacks implements Callback<String>{
    /** 请求接口 { 用在加载条上} */
    private final IRequest REQUEST;
    /** 请求成功接口 */
    private final ISuccess SUCCESS;
    /** 请求错误接口 */
    private final IError ERROR;
    /** 请求失败接口  */
    private final IFailure FAILURE;
    /** 加载样式对象*/
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    /**
     * 构造函数
     * @param request 请求接口 { 用在加载条上}
     * @param success 请求成功接口
     * @param failure 请求错误接口
     * @param error 请求失败接口
     * @param loaderStyle 加载样式对象
     */
    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (null != SUCCESS) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (null != ERROR) {
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (null != FAILURE) {
            FAILURE.onFailure();
        }

        if (null != REQUEST) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    /**
     * 停止加载条
     */
    private  void  stopLoading() {
        if (null != LOADER_STYLE) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
