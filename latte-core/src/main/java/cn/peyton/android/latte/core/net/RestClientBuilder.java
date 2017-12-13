package cn.peyton.android.latte.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.IRequest;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <h3>REST 网络请求 Builder类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.RestClientBuilder
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:33
 * 版本 1.0.0
 * </pre>
 */
public class RestClientBuilder {
    /** 链接地址 */
    private String mUrl = null;
    /** 请求参数集合 */
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    /** 请求接口 { 用在加载条上} */
    private IRequest mRequest = null;
    /** 文件下载目录 */
    private String mDwonloadDir = null;
    /** 文件扩展名 */
    private String mExtension = null;
    /** 下载文件名称 */
    private String mName = null;

    /** 请求成功接口 */
    private ISuccess mSuccess = null;
    /** 请求失败接口 */
    private IFailure mFailure = null;
    /** 请求错误接口 */
    private IError mError = null;
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
    RestClientBuilder(){ }

    /**
     *  添加 链接地址
     * @param url 链接地址
     * @return  RestClientBuilder 对象
     */
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return  this;
    }

    /**
     *  添加 请求参数集合
     * @param params 请求参数集合
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder params(WeakHashMap<String,Object> params) {
        PARAMS.putAll(params);
        return  this;
    }

    /**
     *  添加 请求参数集合
     * @param key
     * @param value
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder params(String key,Object value) {
        PARAMS.put(key, value);
        return  this;
    }

    /**
     * 添加文件 { 文件上传}
     * @param file 文件对象
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return  this;
    }

    /**
     * 添加文件 { 文件上传}
     * @param file 文件地址
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return  this;
    }

    /**
     * 添加文件名称
     * @param name 文件名称
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return  this;
    }
    /**
     * 添加文件下载目录
     * @param dir 文件下载目录
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder dir(String dir) {
        this.mDwonloadDir = dir;
        return  this;
    }

    /**
     * 添加文件扩展名
     * @param extension 文件扩展名
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return  this;
    }

    /**
     * 添加 原始数据
     * @param raw 原始数据
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return  this;
    }

    /**
     * 添加  请求接口 { 用在加载条上}
     * @param iRequest  请求接口 { 用在加载条上}
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return  this;
    }

    /**
     * 添加 请求成功接口
     * @param iSuccess 请求成功接口
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return  this;
    }

    /**
     * 添加 请求错误接口
     * @param iError 请求错误接口
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return  this;
    }

    /**
     * 添加 请求失败接口
     * @param iFailure 请求失败接口
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return  this;
    }

    /**
     * 添加 加载LoaderStyle样式
     * @param context 上下文对象
     * @param style LoaderStyle样式
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }
    /**
     * 添加 加载LoaderStyle样式
     * @param context 上下文对象
     * @return RestClientBuilder 对象
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    /**
     * build 对象
     * @return RestClient 对象
     */
    public final RestClient build(){

        return new RestClient(mUrl,PARAMS,mDwonloadDir,mExtension,mName,
                mRequest,mSuccess,mFailure,mError,mBody,mFile,mLoaderStyle,mContext);
    }
}
