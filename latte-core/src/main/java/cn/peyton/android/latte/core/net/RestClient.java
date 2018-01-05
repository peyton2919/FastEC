package cn.peyton.android.latte.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.IRequest;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.net.callback.RequestCallbacks;
import cn.peyton.android.latte.core.net.download.DownloadHandler;
import cn.peyton.android.latte.core.ui.loader.LatteLoader;
import cn.peyton.android.latte.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * <h3>REST 网络请求 类</h3>
 * <pre>
 *     封装 retrofit 网络请求
 *     http://square.github.io/retrofit/
 *     引用{
 *         compile 'com.squareup.okio:okio:1.13.0'
 *         compile 'com.squareup.okhttp3:okhttp:3.8.1'
 *         compile 'com.squareup.retrofit2:retrofit:2.3.0'
 *         compile 'com.squareup.retrofit2:converter-scalars:2.3.0'
 *     }
 * </pre>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.RestClient
 * 项目名 FestEC
 * 创建时间 2017-12-02 21:38
 * 版本 1.0.0
 * </pre>
 */
public class RestClient {

    /** 链接地址 */
    private final String URL;
    /** 请求参数集合 */
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    /** 请求接口 { 用在加载条上} */
    private final IRequest REQUEST;
    /** 文件下载目录 */
    private final String DWONLOAD_DIR;
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
    /** RequestBody对象 */
    private final RequestBody BODY;
    /** 加载样式对象*/
    private final LoaderStyle LOADER_STYLE;
    /** 文件对象 */
    private final File FILE;
    /** 上下文对象*/
    private final Context CONTEXT;

    /**
     * 构造函数
     * @param url 链接地址
     * @param params 请求参数集合
     * @param dwonloadDir 文件下载目录
     * @param extension 文件扩展名
     * @param name 下载文件名称
     * @param request 请求接口 { 用在加载条上}
     * @param success 请求成功接口
     * @param failure 请求失败接口
     * @param error 请求错误接口
     * @param body RequestBody对象
     * @param file 文件对象
     * @param loaderStyle 加载样式对象
     * @param context 上下文对象
     */
    public RestClient(String url, Map<String, Object> params,String dwonloadDir,String extension,
                      String name,IRequest request,
                      ISuccess success, IFailure failure, IError error, RequestBody body,
                      File file,LoaderStyle loaderStyle,Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DWONLOAD_DIR = dwonloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    /**
     * 创建builder对象
     * @return RestClientBuilder 对象
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * request方法
     * @param method 用来判断是哪种请求
     */
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null; //申明Callc对象
        if (null != REQUEST) {
            REQUEST.onRequestStart();
        }
        //开始显示 加载条
        if (null != LOADER_STYLE) {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW://post原始数据请求
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD: //文件上传
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (null != call) {
            call.enqueue(getRequestCallback());
        }
    }

    /**
     * 请求回复{Callback}
     * @return RequestCallbacks 对象
     */
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR,LOADER_STYLE);
    }


    //===============================================================================================
    /**
     * get请求
     */
    public final void get(){
        request(HttpMethod.GET);
    }
    /**
     * post请求
     */
    public final void post(){
        if (null == BODY) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("【RestClient】 post方式 参数不能为空");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    /**
     * put请求
     */
    public final void put(){
        if (null == BODY) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("【RestClient】 put方式 参数不能为空");
            }
            request(HttpMethod.PUT_RAW);
        }

    }
    /**
     * delete请求
     */
    @SuppressWarnings("unused")
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    /**
     * 下载
     */
    @SuppressWarnings("unused")
    public final void download() {
        new DownloadHandler(URL,REQUEST,DWONLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handleDownload();
    }
}
