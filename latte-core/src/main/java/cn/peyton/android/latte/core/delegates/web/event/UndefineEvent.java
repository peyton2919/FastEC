package cn.peyton.android.latte.core.delegates.web.event;

import cn.peyton.android.latte.core.util.log.LatteLogger;

/**
 * <h3>{WebView} 取消定义事件 </h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.web.event.UndefineEvent
 * 项目名称 FastEC
 * 创建时间 2017/12/14 13:45
 * 版本 1.0.0
 * </pre>
 */
public class UndefineEvent extends Event {
    /** 日志 TAG */
    private static final String TAG = "UndefineEvent";

    @Override
    public String execute(String params) {
        LatteLogger.e(TAG,params);
        return null;
    }
}
