package cn.peyton.android.latte.core.net.rx;

import java.io.IOException;

import cn.peyton.android.latte.core.util.storage.LattePreference;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.net.rx.AddCookieInterceptor
 * 项目名称 FastEC
 * 创建时间 2017/12/16 11:41
 * 版本 1.0.0
 * </pre>
 */
public final class AddCookieInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);

                    }
                });

        return chain.proceed(builder.build());
    }
}
