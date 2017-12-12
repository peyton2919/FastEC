package cn.peyton.android.latte.core.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.net.rx.RxRestService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <pre>
 * Rest请求 创建者
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.RestCreator
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:09
 * 版本 1.0.0
 */
public class RestCreator {

    /** 全局的请求参数集合 */
    public static WeakHashMap<String,Object> getParams () {
        return ParamsHolder.PARAMS;
    }

    /**
     * 获取 RestService对象 {Rest 网络请求接口}
     * @return Rest 网络请求接口
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 获取 RxRestService对象 {Rest 网络请求接口}
     * @return Rest 网络请求接口
     */
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }


    //========================================= inside Holder method begin =====================================================
    /**
     * <pre>
     * Retrofit 支持
     * </pre>
     *
     * 作者 <a href="http://www.peyton.cn">peyton</a>
     * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * 创建时间 2017-12-03 11:27
     * 版本 1.0.0
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfiguration(ConfigKeys.APP_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**
     * <pre>
     * OKHttp 支持
     * </pre>
     *
     * 作者 <a href="http://www.peyton.cn">peyton</a>
     * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * 创建时间 2017-12-03 11:27
     * 版本 1.0.0
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if (null != INTERCEPTORS && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * <pre>
     * RestService 支持
     * </pre>
     *
     * 作者 <a href="http://www.peyton.cn">peyton</a>
     * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * 创建时间 2017-12-03 11:27
     * 版本 1.0.0
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * <pre>
     * RxRestService 支持
     * </pre>
     *
     * 作者 <a href="http://www.peyton.cn">peyton</a>
     * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * 创建时间 2017-12-03 11:27
     * 版本 1.0.0
     */
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    /**
     * <pre>
     * Params 参数 支持
     * </pre>
     *
     * 作者 <a href="http://www.peyton.cn">peyton</a>
     * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * 创建时间 2017-12-03 11:28
     * 版本 1.0.0
     */
    private static final class ParamsHolder{
        /** 全局的请求参数集合 */
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    //========================================= inside Holder method end =====================================================
}
