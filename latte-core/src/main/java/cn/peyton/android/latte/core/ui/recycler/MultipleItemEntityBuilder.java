package cn.peyton.android.latte.core.ui.recycler;

import java.util.LinkedHashMap;

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

public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        FIELDS.clear();//先清除数据
    }

    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
       return new MultipleItemEntity(FIELDS);
    }
}
