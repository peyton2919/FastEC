package cn.peyton.android.latte.core.ui.launcher;

/**
 * <h3>轮播图 接口</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.ui.launcher.ILauncherListener
 * 项目名 FestEC
 * 创建时间 2017-12-05 16:59
 * 版本 1.0.0
 * </pre>
 */
public interface ILauncherListener {

    /**
     *  加载完成
     * @param tag OnLauncherFinishTag 枚举
     */
    void onLauncherFinish(OnLauncherFinishTag tag);
}
