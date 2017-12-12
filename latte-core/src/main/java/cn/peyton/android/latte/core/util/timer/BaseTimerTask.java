package cn.peyton.android.latte.core.util.timer;

import java.util.TimerTask;

/**
 * <pre>
 *  基础定时器
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.util.timer.BaseTimerTask
 * 项目名 FestEC
 * 创建时间 2017-12-04 13:44
 * 版本 1.0.0
 */
public class BaseTimerTask extends TimerTask{
    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mTimerListener = timerListener;
    }
    @Override
    public void run() {
        if (null != mTimerListener) {
            mTimerListener.onTimer();
        }
    }
}
