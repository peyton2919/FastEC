package cn.peyton.android.latte.core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * <h3>基础装饰类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.recycler.BaseDecoration
 * 创建时间 2017/12/13 14:55
 * 版本 1.0.0
 * </pre>
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

    /**
     * 创建 BaseDecoration 对象
     * @param color 线条颜色ID
     * @param size 线条大小
     * @return BaseDecoration 对象
     */
    public static BaseDecoration create(@ColorInt int color , int size) {
        return new BaseDecoration(color, size);
    }

    /**
     * 内部类
     */
    static class DividerLookupImpl implements DividerItemDecoration.DividerLookup {
        /** 线条颜色ID */
        private final int COLOR;
        /** 线条大小 */
        private final int SIZE;

        /**
         * 构造函数
         * @param color 线条颜色ID
         * @param size 线条大小
         */
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
