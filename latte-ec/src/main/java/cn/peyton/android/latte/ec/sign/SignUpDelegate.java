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
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;

/**
 * <pre>
 *  注册逻辑
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.sign.SignUpDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 15:27
 * 版本 1.0.0
 */
public class SignUpDelegate extends LatteDelegate{

    private ISignListener mISignListener = null;

    @BindView(R2.id.edit_signup_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_signup_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_signup_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_signup_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_signup_repeatPwd)
    TextInputEditText mRepeatPwd = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }




    @OnClick(R2.id.btn_signup_register)
    void onClickSignup() {
        if (checkForm()) {
            RestClient.builder()
                   .url("http://192.168.1.2:8080/o2o/api/userprofile")
                   // .url("http://www.fj167.com/api/homedata")
                    .params("name",mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE",response);
                            SignHandler.onSignUp(response,mISignListener); //数据持久化
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

    @OnClick(R2.id.tv_signup_link_in)
    void onClickLink() {
        start(new SignInDelegate());
    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    /**
     * 判断检查
     */
    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String repeatPwd = mRepeatPwd.getText().toString();
        boolean isPass = true;//是否通过
        if (name.isEmpty()) {
            mName.setError("请输入姓名!");
            isPass = false;
        }else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误邮箱格式!");
            isPass = false;
        }else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误!");
            isPass = false;
        }else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码!");
            isPass = false;
        }else {
            mPassword.setError(null);
        }

        if (repeatPwd.isEmpty() || repeatPwd.length()<6 || !(repeatPwd.equals(password))) {
            mRepeatPwd.setError("密码验证错误!");
            isPass = false;
        }else {
            mRepeatPwd.setError(null);
        }
        return isPass;
    }
}
