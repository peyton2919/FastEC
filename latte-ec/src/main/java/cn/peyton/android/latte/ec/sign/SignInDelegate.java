package cn.peyton.android.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.IError;
import cn.peyton.android.latte.core.net.callback.IFailure;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.util.log.LatteLogger;
import cn.peyton.android.latte.core.wechat.LatteWeChat;
import cn.peyton.android.latte.core.wechat.callbacks.IWeChatSignInCallback;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;

/**
 * <pre>
 *  登录页面
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.sign.SignInDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 17:14
 * 版本 1.0.0
 */
public class SignInDelegate extends LatteDelegate{

    private ISignListener mISignListener = null;

    @BindView(R2.id.edit_signin_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_signin_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_signin_login)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.1.2:8080/o2o/api/userprofile")
                    // .url("http://www.fj167.com/api/homedata")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE",response);
                            SignHandler.onSignIn(response,mISignListener); //数据持久化
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(),"IFailure",Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(),"code" + code + ": asdasdfas'"    + msg,Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
            //Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();
        }
    }
    //点击微信图标
    @OnClick(R2.id.itv_signin_we_chat)
    void onClickWeChat() {
        System.out.println("点击了微信图标");
        LatteWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

                Toast.makeText(getContext(),userInfo,Toast.LENGTH_LONG).show();
                //TODO
            }
        }).signIn();
    }

    @OnClick(R2.id.tv_signin_link_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {

        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    /**
     * 判断检查
     */
    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;//是否通过
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误邮箱格式!");
            isPass = false;
        }else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码!");
            isPass = false;
        }else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
