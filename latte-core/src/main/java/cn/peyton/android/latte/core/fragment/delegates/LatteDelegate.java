package cn.peyton.android.latte.core.fragment.delegates;

/**
 * <h3>供外部使用的Delegate</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.fragment.delegates.LatteDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 13:03
 * 版本 1.0.0
 * </pre>
 */
public abstract class LatteDelegate extends PermissionCheckedDelegate {


    /**
     * 获取 父Delegate
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
