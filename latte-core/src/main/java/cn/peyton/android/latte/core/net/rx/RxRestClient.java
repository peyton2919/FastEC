package cn.peyton.android.latte.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.peyton.android.latte.core.net.HttpMethod;
import cn.peyton.android.latte.core.net.RestCreator;
import cn.peyton.android.latte.core.ui.loader.LatteLoader;
import cn.peyton.android.latte.core.ui.loader.LoaderStyle;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * <pre>
 * RxREST 请求对象
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.RestClient
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 21:38
 * 版本 1.0.0
 */
public class RxRestClient {

    /** 链接地址 */
    private final String URL;
    /** 请求参数集合 */
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();

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
     * @param body RequestBody对象
     * @param file 文件对象
     * @param loaderStyle 加载样式对象
     * @param context 上下文对象
     */
    public RxRestClient(String url, Map<String, Object> params, RequestBody body,
                        File file, LoaderStyle loaderStyle, Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    /**
     * 创建builder对象
     * @return RestClientBuilder 对象
     */
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    /**
     * request方法
     * @param method 用来判断是哪种请求
     */
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null; //申明Observable 对象

        //开始显示 加载条
        if (null != LOADER_STYLE) {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL,PARAMS);
                break;
            case POST_RAW://post原始数据请求
                observable = service.postRaw(URL,BODY);
                break;
            case PUT:
                observable = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable = service.delete(URL,PARAMS);
                break;
            case UPLOAD: //文件上传
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(),requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;
            default:
                break;

        }

        return observable;
    }

    /**
     * get请求
     */
    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }
    /**
     * post请求
     */
    public final Observable<String> post(){
        if (null == BODY) {
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("【RestClient】 post方式 参数不能为空");
            }
            return request(HttpMethod.POST_RAW);
        }
    }
    /**
     * put请求
     */
    public final Observable<String> put(){
        if (null == BODY) {
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("【RestClient】 put方式 参数不能为空");
            }
           return request(HttpMethod.PUT_RAW);
        }

    }
    /**
     * delete请求
     */
    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    /**
     * 上传 请求
     */
    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    /**
     * 下载 请求
     * @return
     */
    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody>  responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}
