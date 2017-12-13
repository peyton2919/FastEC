package cn.peyton.android.latte.core.net.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import java.io.IOException;

import cn.peyton.android.latte.core.util.file.FileUtil;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <h3>测试 拦截器{用来测试数据}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.interceptors.DebugInterceptor
 * 项目名 FestEC
 * 创建时间 2017-12-03 16:03
 * 版本 1.0.0
 * </pre>
 */
public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String url, int rawId) {
        this.DEBUG_URL = url;
        this.DEBUG_RAW_ID = rawId;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }

    /**
     * 获取 Response 对象
     * @param chain
     * @param json
     * @return
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }
}
