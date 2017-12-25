package cn.peyton.android.latte.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * <h3>RGB 颜色类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 * </pre>
 */
@AutoValue
public abstract class RGBValue {

    /** 申明 红色 {指定子类实现} */
    public abstract int red();
    /** 申明 绿色 {指定子类实现} */
    public abstract int green();
    /** 申明 兰色 {指定子类实现} */
    public abstract  int blue();

    /**
     * 创建RGB
     * @param red 红色
     * @param green 绿色
     * @param blue 兰色
     * @return RGBValue对象
     */
    public static RGBValue create(int red, int green, int blue) {
        return new AutoValue_RGBValue(red, green, blue);
    }

}
