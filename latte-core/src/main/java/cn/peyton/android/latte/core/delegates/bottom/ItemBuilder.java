package cn.peyton.android.latte.core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * <h3>Item Builder 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.bottom.ItemBuilder
 * 创建时间 2017/12/13 15:55
 * 版本 1.0.0
 * </pre>
 */
public final class ItemBuilder {
    /** 申明Item 集合 */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 创建 ItemBuilder对象 {简单工厂模式}
     * @return ItemBuilder对象
     */
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    /**
     * 添加Item
     * @param bean BottomTabBean对象
     * @param delegate BottomItemDelegate 对象
     * @return ItemBuilder对象
     */
    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    /**
     * 添加Item
     * @param items Item 集合
     * @return ItemBuilder对象
     */
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    /**
     * 创建 LinkedHashMap<BottomTabBean, BottomItemDelegate> 对象
     * @return 
     */
    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }

}
