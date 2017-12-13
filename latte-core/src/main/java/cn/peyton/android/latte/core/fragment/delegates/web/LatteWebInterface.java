package cn.peyton.android.latte.core.fragment.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * <h3>Latte Web 接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.web.LatteWebInterface
 * 创建时间 2017/12/13 15:43
 * 版本 1.0.0
 * </pre>
 */
public class LatteWebInterface {
    /**  申明 WebDelegate 对象 */
    private final WebDelegate DELEGATE;

    /**
     * 私有构造函数
     * @param delegate WebDelegate对象
     */
    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 创建WebDelegate对象{简单工厂模式}
     * @param delegate WebDelegate对象
     * @return LatteWebInterface对象
     */
    public static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
