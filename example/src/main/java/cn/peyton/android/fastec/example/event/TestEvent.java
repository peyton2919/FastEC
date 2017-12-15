package cn.peyton.android.fastec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import cn.peyton.android.latte.core.fragment.delegates.web.event.Event;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.fastec.example.event.TestEvent
 * 项目名称 FastEC
 * 创建时间 2017/12/14 17:34
 * 版本 1.0.0
 * </pre>
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_LONG).show();
        if ("test".equals(getAction())) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall()",null);
                }
            });
        }
        return null;
    }
}
