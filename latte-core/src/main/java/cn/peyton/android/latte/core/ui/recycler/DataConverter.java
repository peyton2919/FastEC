package cn.peyton.android.latte.core.ui.recycler;

import java.util.ArrayList;

/**
 * <h3>数据转换器{约束}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.recycler.DataConverter
 * 创建时间 2017/12/13 15:03
 * 版本 1.0.0
 * </pre>
 */
public abstract class DataConverter {

    /** 申明 MultipleItemEntity对象集合 */
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    /** 申明 Json数据 */
    private String mJsonData = null;
    /** 数据转换器 {指定子类实现} */
    public abstract ArrayList<MultipleItemEntity> convert();

    /**
     * 设置Json数据
     * @param json Json数据
     * @return DataConverter 对象
     */
    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    /**
     * 获取Json数据
     * @return Json数据
     */
    protected String getJsonData() {
        if (null == mJsonData || mJsonData.isEmpty()) {
            throw new NullPointerException("【DataConverter】 数据为空");
        }
        return mJsonData;
    }

}
