package cn.peyton.android.latte.core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * <h3>多Item Builder类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.recycler.MultipleItemEntityBuilder
 * 创建时间 2017/12/13 15:15
 * 版本 1.0.0
 * </pre>
 */
public class MultipleItemEntityBuilder {

    /** 申明 字段集合*/
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    /**
     * 构造函数
     */
    public MultipleItemEntityBuilder() {
        FIELDS.clear();//先清除数据
    }

    /**
     * 设置Item { 键:MultipleFields.ITEM_TYPE }
     * @param itemType 值
     * @return MultipleItemEntityBuilder 对象
     */
    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    /**
     *  设置Item
     * @param key 键
     * @param value 值
     * @return MultipleItemEntityBuilder 对象
     */
    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    /**
     * 设置Item
     * @param map 泛型对象集合
     * @return MultipleItemEntityBuilder 对象
     */
    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    /**
     * 创建 MultipleItemEntity 对象
     * @return MultipleItemEntity 对象
     */
    public final MultipleItemEntity build() {
       return new MultipleItemEntity(FIELDS);
    }
}
