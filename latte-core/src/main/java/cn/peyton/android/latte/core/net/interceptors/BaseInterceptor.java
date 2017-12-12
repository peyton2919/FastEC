package cn.peyton.android.latte.core.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * <pre>
 *  基础拦截器
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.interceptors.BaseInterceptor
 * 项目名 FestEC
 * 创建时间 2017-12-03 15:49
 * 版本 1.0.0
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取get请求 参数key与value
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();//个数
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i=0;i<size;i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 获取get请求 参数key与value
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 获取post请求 参数key与value
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();//个数
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * 获取post请求 参数key与value
     * @param chain
     * @param key
     * @return
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
