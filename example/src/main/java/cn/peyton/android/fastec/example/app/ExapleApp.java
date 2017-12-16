package cn.peyton.android.fastec.example.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.peyton.android.fastec.example.R;
import cn.peyton.android.fastec.example.event.TestEvent;
import cn.peyton.android.latte.core.app.CrashHandler;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.net.interceptors.DebugInterceptor;
import cn.peyton.android.latte.core.net.rx.AddCookieInterceptor;
import cn.peyton.android.latte.ec.database.DatabaseManager;
import cn.peyton.android.latte.ec.icon.FontECModule;

/**
 * <h3>应用程序入口{application入口}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.festec.example.app.ExapleApp
 * 项目名 FestEC
 * 创建时间 2017-12-01 - 11:02
 * 版本 1.0.0
 * </pre>
 */
public class ExapleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        Latte.init(this)
                .withIcon(new FontECModule())
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(1000)
                //.withApiHost("http://127.0.0.1/")
                .withApiHost("http://192.168.3.3:8080/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                //微信传值,调用在ec包中
                .withWeChatAppId("wxb2d694b9076f1b36")
                .withWeChatAppSecret("d4624c36b6795d1d99dcf0547af5443d")
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .withInterceptor(new AddCookieInterceptor())//添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .configure();
       // initStetho();
        DatabaseManager.getInstance().init(this); //初始化
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
