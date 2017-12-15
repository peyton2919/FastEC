package cn.peyton.android.latte.core.fragment.delegates.web.event;

/**
 * <h3>{WebView} 事件接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.event.IEvent
 * 项目名称 FastEC
 * 创建时间 2017/12/14 13:32
 * 版本 1.0.0
 * </pre>
 */
public interface IEvent {
    /**
     * 完成
     * @param params 参数
     * @return 字符串
     */
    String execute(String params);
}
