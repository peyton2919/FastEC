package cn.peyton.android.latte.core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import cn.peyton.android.latte.core.delegates.web.event.Event;
import cn.peyton.android.latte.core.delegates.web.event.EventManager;
import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;

/**
 * <h3>全局配置信息类{ 配置文件存储和获取}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.app.Configurator
 * 项目名 FestEC
 * 创建时间 2017-12-01 - 11:18
 * 版本 1.0.0
 * </pre>
 */
public class Configurator {

    /**  存储信息数据结构 HashMap*/
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    /** 字体图标集合 { IconFontDescriptor 对象}*/
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    /** 拦截器集合  */
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    /** 申明 Handler 管理者 */
    private static final Handler HANDLER = new Handler();

    /**
     * 构造函数
     */
    @SuppressWarnings("WeakerAccess")
    public Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);//加载还未完成 { 刚开始}
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER); //赋值
    }

    /**
     * 配置信息加载已经完成
     */
    public final void configure(){
        initIcons();//初始化字体图标
        Logger.addLogAdapter(new AndroidLogAdapter());//日志
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }


    //=============================== 获取 Configurator 配置项 ===================================
    /**
     * 检查配置信息完整性
     * @param key  ConfigKeys 枚举
     * @param <T> 泛型对象
     * @return 泛型对象
     */
    @SuppressWarnings({"unchecked", "unused"})
    final <T> T getConfiguration(Object key) {
        checkedConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (null == value) {
            throw new NullPointerException("【Configurator】" + key.toString() + "是空值");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    /**
     * 获取配置信息集合
     * @return 配置信息集合
     */
    final  HashMap<Object ,Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }
    //=============================== 获取 Configurator 配置项 ===================================

    //=============================== Configurator 配置项 ===================================
    /**
     * 配置Host信息
     * @param host 主机名
     * @return Configurator对象
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.APP_HOST, host);
        return this;
    }

    /**
     * 延迟加载
     * @param delayed 毫秒
     * @return Configurator对象
     */
    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 浏览器加载的host
     * @param host
     * @return
     */
    public final Configurator withWebHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
    }
    //=============================== Configurator 配置项 ===================================

    //=================================== 微信 begin ===========================================
    /**
     * 微信ID
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    /**
     * 微信密码
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * Activity 对象
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }
    //=================================== 微信 end ===========================================

    //=================================== WebView begin ========================================
    /**
     * WebView Javascript 名称
     * @param name Javascript 名称
     * @return
     */
    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    /**
     *
     * @param name
     * @param event
     * @return
     */
    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }
    //=================================== WebView end ========================================

    //============================ Interceptor begin ==========================================
    /**
     * 添加拦截器
     * @param interceptor 拦截器对象
     * @return Configurator对象
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    /**
     * 添加拦截器
     * @param interceptors 拦截器集合
     * @return Configurator对象
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    //============================ Interceptor end ==========================================


    //======================== 字体图标配置 开始 ===============================
    /**
     * 添加字体图标 {可自定义}
     * @param descriptor 字体图标IconFontDescriptor 对象
     * @return Configurator对象
     *
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }
    /**
     * 初始化字体图标集合
     */
    private void initIcons(){
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    //======================== 字体图标配置 结束 ===============================


    //====================线程安全懒汉模式 { 静态内部类 } 开始 ====================================================
    /**
     * 单例模式
     * @return
     */
    @SuppressWarnings({"JavaDoc", "WeakerAccess"})
    public static Configurator getInstance() {
        return  Holder.INSTANCE;
    }
    //静态内部类
    private static class  Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    //====================线程安全懒汉模式 { 静态内部类 } 结束 ====================================================


    /**
     * 检查配置项是否完成
     */
    private void checkedConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("【Configurator】 配置信息加载还没完成");
        }
    }

}
