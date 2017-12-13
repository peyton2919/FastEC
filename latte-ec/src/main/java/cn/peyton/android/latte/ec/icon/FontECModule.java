package cn.peyton.android.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;


/**
 * <h3>自定义字体对象</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.icon.FontECModule
 * 创建时间 2017/12/13 16:10
 * 版本 1.0.0
 * </pre>
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
