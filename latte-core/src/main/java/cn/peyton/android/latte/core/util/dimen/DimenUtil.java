package cn.peyton.android.latte.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import cn.peyton.android.latte.core.app.Latte;

/**
 * <pre>
 * 测量工具类
 * </pre>
 *
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.util.dimen.DimenUtil
 * 项目名 FestEC
 * 创建时间 2017-12-03 12:41
 * 版本 1.0.0
 */
public class DimenUtil {

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
