package cn.peyton.android.latte.core.app;

import android.content.Context;
import android.os.Handler;

/**
 * <pre>
 *  库程序入口 供外部调用类
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.app.Latte
 * 项目名 FestEC
 * 创建时间 2017-12-01 - 11:01
 * 版本 1.0.0
 */
@SuppressWarnings("ALL")
public final class Latte {

    /**
     * 初始化数据{ 对象引用引到配置项中 }
     * @param context 上下文对象 {建议使用 getApplicationContext() }
     * @return
     */
    public static Configurator init(Context context) {
        Configurator.getInstance().getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取配置信息集合
     * @return 配置信息集合
     */
//    public static HashMap<Object,Object> getConfigurators(){
//        return Configurator.getInstance().getLatteConfigs();
//    }

    /**
     * 获取配置信息集合
     * @return
     */
    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取Context对象
     * @return Context对象
     */
    public static Context getApplicationContext(){

        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
