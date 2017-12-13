package cn.peyton.android.fastec.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import cn.peyton.android.fastec.example.R;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.ISuccess;

/**
 * <h3>例子 Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.festec.example.delegates.ExampleDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 13:14
 * 版本 1.0.0
 * </pre>
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
}

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       // testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
               //.url("http://127.0.0.1/index")
                .url("http://www.fj167.com/api/homedata")
                //.loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),"onError",Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"IFailure",Toast.LENGTH_LONG).show();
                    }
                })
                .build().get();
    }
}
