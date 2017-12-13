package cn.peyton.android.latte.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.peyton.android.latte.core.net.RestCreator;
import cn.peyton.android.latte.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <h3>RxREST 网络请求 Builder类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.RestClientBuilder
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:33
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public class RxRestClientBuilder {
    /** 链接地址 */
    private String mUrl = null;
    /** 请求参数集合 */
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    /** RequestBody对象 */
    private RequestBody mBody = null;
    /** 文件对象 */
    private File mFile = null;
    /** 加载样式对象*/
    private LoaderStyle mLoaderStyle = null;
    /** 上下文对象*/
    private Context mContext = null;

    /**
     * 私有构造函数
     */
    RxRestClientBuilder(){ }

    /**
     *  添加 链接地址
     * @param url 链接地址
     * @return  RestClientBuilder 对象
     */
    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return  this;
    }

    /**
     *  添加 请求参数集合
     * @param params 请求参数集合
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder params(WeakHashMap<String,Object> params) {
        PARAMS.putAll(params);
        return  this;
    }

    /**
     *  添加 请求参数集合
     * @param key
     * @param value
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return  this;
    }

    /**
     * 添加文件 { 文件上传}
     * @param file 文件对象
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return  this;
    }

    /**
     * 添加文件 { 文件上传}
     * @param file 文件地址
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return  this;
    }



    /**
     * 添加 原始数据
     * @param raw 原始数据
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return  this;
    }


    /**
     * 添加 加载LoaderStyle样式
     * @param context 上下文对象
     * @param style LoaderStyle样式
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }
    /**
     * 添加 加载LoaderStyle样式
     * @param context 上下文对象
     * @return RestClientBuilder 对象
     */
    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    /**
     * build 对象
     * @return RestClient 对象
     */
    public final RxRestClient build(){

        return new RxRestClient(mUrl,PARAMS,mBody,mFile,mLoaderStyle,mContext);
    }
}
