package cn.peyton.android.latte.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 */
@AutoValue
public abstract class RGBValue {

    public abstract int red();
    public abstract int green();
    public abstract  int blue();

    public static RGBValue create(int red, int green, int blue) {
        return new AutoValue_RGBValue(red, green, blue);
    }

}
