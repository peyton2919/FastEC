package cn.peyton.android.latte.core.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import cn.peyton.android.latte.core.net.RestCreator;
import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.IRequest;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *  文件下载
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.download.DownloadHandler
 * 项目名 FestEC
 * 创建时间 2017-12-03 14:30
 * 版本 1.0.0
 */
public class DownloadHandler {

    /** 链接地址 */
    private final String URL;
    /** 请求参数集合 */
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    /** 请求接口 { 用在加载条上} */
    private final IRequest REQUEST;
    /** 文件下载目录 */
    private final String DOWNLOAD_DIR;
    /** 文件扩展名 */
    private final String EXTENSION;
    /**  下载文件名称 */
    private final String NAME;

    /** 请求成功接口 */
    private final ISuccess SUCCESS;
    /** 请求失败接口 */
    private final IError ERROR;
    /** 请求错误接口 */
    private final IFailure FAILURE;

    /**
     * 构造函数
     * @param url 链接地址
     * @param dwonloadDir 文件下载目录
     * @param extension 文件扩展名
     * @param name 下载文件名称
     * @param request 请求接口 { 用在加载条上}
     * @param success 请求成功接口
     * @param failure 请求失败接口
     * @param error 请求错误接口
     */
    public DownloadHandler(String url, IRequest request, String dwonloadDir,
                           String extension,String name, ISuccess success, IFailure failure, IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = dwonloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    public final void handleDownload() {
        if (null != REQUEST) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                            //一定要注意判断,否则文件下载不全
                            if (task.isCancelled()) {
                                if (null != REQUEST) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (null != ERROR) {
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (null != FAILURE) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
