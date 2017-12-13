package cn.peyton.android.latte.core.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * <h3>轮播图 Holder创建者</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.ui.launcher.LauncherHolderCreator
 * 项目名 FestEC
 * 创建时间 2017-12-04 14:43
 * 版本 1.0.0
 * </pre>
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
