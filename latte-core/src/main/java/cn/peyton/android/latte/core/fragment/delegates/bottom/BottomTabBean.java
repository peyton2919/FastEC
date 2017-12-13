package cn.peyton.android.latte.core.fragment.delegates.bottom;

/**
 * <h3>BottomTab 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.fragment.delegates.bottom.BottomTabBean
 * 创建时间 2017/12/13 15:52
 * 版本 1.0.0
 * </pre>
 */
public final class BottomTabBean {
    /** 图标 */
    private final CharSequence ICON;
    /** 标题 */
    private final CharSequence TITLE;

    /**
     * 构造函数
     * @param icon 图标
     * @param title 标题
     */
    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    /**
     * @return 图标
     */
    public CharSequence getIcon() {
        return ICON;
    }

    /**
     * @return 标题
     */
    public CharSequence getTitle() {
        return TITLE;
    }
}
