package cn.peyton.android.latte.core.util.callback;

import java.util.WeakHashMap;

/**
 * <h3>全局回调管理</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.util.callback.CallbackManager
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class CallbackManager {
    /**  申明 全局回调集合 */
    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    /**
     * 添加全局回调
     * @param tag
     * @param callback IGlobalCallback对象
     * @return
     */
    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    /**
     * 获取全局回调 {IGlobalCallback对象}
     * @param tag
     * @return
     */
    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

    //======================================= 懒加载 =========================================

    /**
     * 获取 CallbackManager对象
     * @return
     */
    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

}
