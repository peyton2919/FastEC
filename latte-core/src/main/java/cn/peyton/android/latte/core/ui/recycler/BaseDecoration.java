package cn.peyton.android.latte.core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

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

public class BaseDecoration extends DividerItemDecoration {

    /**
     * 构造函数
     * @param color 线的颜色
     * @param size 线的大小
     */
    private BaseDecoration(@ColorInt int color , int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color , int size) {
        return new BaseDecoration(color, size);
    }
    /**
     * 内部类
     */
    static class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

        private final int COLOR;
        private final int SIZE;

        public DividerLookupImpl(int color, int size) {
            this.COLOR = color;
            this.SIZE = size;
        }

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder()
                    .size(SIZE)
                    .color(COLOR)
                    .build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder()
                    .size(SIZE)
                    .color(COLOR)
                    .build();
        }
    }
}
