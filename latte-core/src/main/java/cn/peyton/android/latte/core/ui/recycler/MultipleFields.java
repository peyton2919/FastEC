package cn.peyton.android.latte.core.ui.recycler;

/**
 * <h3>多个字段 枚举</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.recycler.MultipleFields
 * 创建时间 2017/12/13 15:06
 * 版本 1.0.0
 * </pre>
 */
public enum MultipleFields {
    /** item 类型 */
    ITEM_TYPE,
    /** text */
    TEXT,
    /** image链接 */
    IMAGE_URL,
    /** banner */
    BANNERS,
    /** span大小 */
    SPAN_SIZE,
    /** 编号 */
    ID,
    /** 名称 */
    NAME,
    /** tag */
    TAG,

    //OrderItemFields
    /**  时间 */
    TIME,
    /**  地址 */
    ADDRESS,
    /** 手机 */
    PHONE,

    //ShopCartItemFields
    /** 标题 */
    TITLE,
    /** 描述 */
    DESC,
    /** 总数 */
    COUNT,
    /** 价格 */
    PRICE,
    /** 是否选中 */
    IS_SELECTED,
    /** 位置 */
    POSITION,
    ;
}
