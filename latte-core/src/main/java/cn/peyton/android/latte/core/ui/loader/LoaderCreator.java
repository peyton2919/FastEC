package cn.peyton.android.latte.core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * <h3>加载条 创建者</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.ui.loader.LoaderCreator
 * 项目名 FestEC
 * 创建时间 2017-12-03 12:19
 * 版本 1.0.0
 * </pre>
 */
public final class LoaderCreator {
    /** 申明 加载条集合 */
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    /**
     * 创建 AVLoadingIndicatorView 对象
     * @param type 加载条类型
     * @param context 上下文对象
     * @return AVLoadingIndicatorView 对象
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (null == LOADING_MAP.get(type)) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 获取 Indicator对象
     * @param name
     * @return Indicator对象
     */
    private static Indicator getIndicator(String name) {
        if (null == name || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
