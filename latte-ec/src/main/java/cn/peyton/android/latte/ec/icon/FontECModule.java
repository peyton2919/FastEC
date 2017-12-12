package cn.peyton.android.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * <pre>
 *  自定义字体对象
 * </pre>
 *
 * @作者 <a href="http://www.peyton.cn">peyton</a>
 * @邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @类全名 cn.peyton.android.latte.ec.icon.FontECModule
 * @项目名 FestEC
 * @创建时间 2017-12-01 - 12:44
 * @版本 1.0.0
 */
@SuppressWarnings("ALL")
public class FontECModule implements IconFontDescriptor {
    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
