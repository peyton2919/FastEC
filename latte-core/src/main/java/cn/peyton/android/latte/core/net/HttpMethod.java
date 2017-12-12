package cn.peyton.android.latte.core.net;

/**
 * <pre>
 * Rest请求时方法枚举
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.HttpMethod
 * 项目名 FestEC
 * 创建时间 2017-12-03 10:07
 * 版本 1.0.0
 */
@SuppressWarnings("ALL")
public enum HttpMethod {
    /** GET请求,查询 */
    GET,
    /** POST请求,添加 */
    POST,
    /** POST原始数据请求,添加 */
    POST_RAW,
    /** PUT请求,更新 */
    PUT,
    /** PUT原始数据请求,更新 */
    PUT_RAW,
    /** DELETE请求,删除 */
    DELETE,
    /** 上传 */
    UPLOAD,
    ;
}
