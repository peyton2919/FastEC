package cn.peyton.android.latte.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;


/**
 * <h3>多Item 实体类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.recycler.MultipleItemEntity
 * 创建时间 2017/12/13 15:10
 * 版本 1.0.0
 * </pre>
 */
public class MultipleItemEntity implements MultiItemEntity{

    /**  申明Item队列集合 */
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUENE = new ReferenceQueue<>();
    /**  申明多字段集合 */
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    /**  申明字段Reference 集合 */
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUENE);

    /**
     * 构造函数
     * @param fields 字段集合
     */
    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    /**
     * 创建 MultipleItemEntityBuilder 对象
     * @return
     */
    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }


    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }


}
