package cn.peyton.android.fastec.example.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.fragment.activities.ProxyActivity;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.ui.launcher.ILauncherListener;
import cn.peyton.android.latte.core.ui.launcher.OnLauncherFinishTag;
import cn.peyton.android.latte.ec.main.EcBottomDelegate;
import cn.peyton.android.latte.ec.sign.ISignListener;
import cn.peyton.android.latte.ec.sign.SignInDelegate;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
            ISignListener,ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.hide();//隐藏头部
        }
        //全局Activity 放到配置信息里
        Latte.getConfigurator().withActivity(this);
        //去除状态栏
        StatusBarCompat.translucentStatusBar(this,true);

    }

    @Override
    public LatteDelegate setRootDelegate() {
    //    return new ExampleDelegate();
        return new EcBottomDelegate();
        //return new IndexDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                //TODO
                Toast.makeText(this,"启动结束, 用户登录了",Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOTSIGNED:
                Toast.makeText(this,"启动结束, 用户没登录",Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
