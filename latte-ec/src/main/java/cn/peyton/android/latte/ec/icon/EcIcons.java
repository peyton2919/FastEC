package cn.peyton.android.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * <h3>图标 枚举</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.icon.EcIcons
 * 创建时间 2017/12/13 16:08
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public enum EcIcons implements Icon{
    /** 扫描 */
    icon_scan('\ue642'),
    /** 支付 */
    icon_ali_pay('\ue67c'),
    ;

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
