package cn.peyton.android.latte.core.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.rx.RxRestService
 * 项目名 FestEC
 * 创建时间 2017-12-04 12:36
 * 版本 1.0.0
 */
public interface RxRestService {

    /**
     * get请求 {查询} Observable可观察对象
     * @param url 链接地址
     * @param params 参数以key value值形式
     * @return
             */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post请求 {添加}
     * @param url 链接地址
     * @param params  参数以key value值形式
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * post原始数据请求
     * @param url  链接地址
     * @param body RequestBody 对象
     * @return
     */
    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);
    /**
     * put请求 {更新}
     * @param url 链接地址
     * @param params 参数以key value值形式
     * @return
     */
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * put原始数据请求
     * @param url  链接地址
     * @param body RequestBody 对象
     * @return
     */
    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    /**
     * delete请求 {删除}
     * @param url 链接地址
     * @param params 参数以key value值形式
     * @return
     */
    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载请求
     * @param url 链接地址
     * @param params 参数以key value值形式
     * @return
     */
    @Streaming //一边下载一边写入文件
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传请求
     * @param url 链接地址
     * @param file 参数以key value值形式
     * @return
     */
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
