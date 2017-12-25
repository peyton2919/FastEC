package cn.peyton.android.latte.core.delegates.web.event;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

/**
 * <h3>{WebView} 事件管理类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.web.event.EventManager
 * 项目名称 FastEC
 * 创建时间 2017/12/14 13:32
 * 版本 1.0.0
 * </pre>
 */
public class EventManager {
    /** 申明 事件集合 */
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    /**
     * 构造函数
     */
    private EventManager(){}

    /**
     * 添加 事件
     * @param name 键{名称}
     * @param event 值{Event对象}
     * @return
     */
    public EventManager addEvent(@NonNull String name, Event event) {
        EVENTS.put(name, event);
        return this;
    }

    /**
     * 创建 事件
     * @param action 键 {名称}
     * @return Event对象
     */
    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (null == event) {
            return new UndefineEvent();
        }
        return event;
    }

    //================================ 懒性 加载 开始 ======================================

    /**
     * 创建 单例 EventManager 对象
     * @return
     */
    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 内部类
     */
    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }
    //================================ 懒性 加载 结束 ======================================
}

