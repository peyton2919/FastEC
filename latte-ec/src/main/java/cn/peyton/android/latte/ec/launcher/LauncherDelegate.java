package cn.peyton.android.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import cn.peyton.android.latte.core.app.AccountManager;
import cn.peyton.android.latte.core.app.IUserChecker;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.ui.launcher.ILauncherListener;
import cn.peyton.android.latte.core.ui.launcher.OnLauncherFinishTag;
import cn.peyton.android.latte.core.ui.launcher.ScrollLaucherTag;
import cn.peyton.android.latte.core.util.storage.LattePreference;
import cn.peyton.android.latte.core.util.timer.BaseTimerTask;
import cn.peyton.android.latte.core.util.timer.ITimerListener;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;

/**
 * <h3>启动 Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.launcher.LauncherDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 13:43
 * 版本 1.0.0
 * </pre>
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    /** 定时器 */
    private Timer mTimer = null;
    /** 计时数 */
    private int mCount = 5;
    /** 轮播图接口 */
    private ILauncherListener mLauncherListener = null;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;


    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (null != mTimer) {
            mTimer.cancel();
            mTimer = null;
            checkedIsShowScroll(); //
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null != mTvTimer) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount --;
                    if (mCount < 0) {
                        if (null != mTimer) {
                            mTimer.cancel();
                            mTimer = null;
                            checkedIsShowScroll(); //
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 判断是否显示滚动启动页
     */
    private void checkedIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLaucherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LanucherScrollDelegate(),SINGLETASK); //启动
        }else {
            //检查用户是否登录APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (null != mLauncherListener) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (null != mLauncherListener) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOTSIGNED);
                    }
                }
            });
        }
    }

    /**
     * 初始化Timer
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        //第一个参数是要执行的任务, 第二个参数开始时间, 第三个参数是间隔时间
        mTimer.schedule(task,0,1000);
    }
}
