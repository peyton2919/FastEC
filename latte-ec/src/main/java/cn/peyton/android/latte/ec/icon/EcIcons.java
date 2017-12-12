package cn.peyton.android.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * <pre>
 *
 * </pre>
 *
 * @作者 <a href="http://www.peyton.cn">peyton</a>
 * @邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @类全名 cn.peyton.android.latte.ec.icon.EcIcons
 * @项目名 FestEC
 * @创建时间 2017-12-01 - 12:48
 * @版本 1.0.0
 */
@SuppressWarnings("ALL")
public enum EcIcons implements Icon{
    icon_scan('\ue642'),
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
