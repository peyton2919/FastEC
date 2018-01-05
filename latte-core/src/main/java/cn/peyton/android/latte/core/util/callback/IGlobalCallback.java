package cn.peyton.android.latte.core.util.callback;

import android.support.annotation.Nullable;

/**
 * <h3>全局回调接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.util.callback.IGlobalCallback
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public interface IGlobalCallback<T> {

    /**
     * 回调
     * @param args 泛型对象
     */
    void executeCallback(@Nullable T args);
}
