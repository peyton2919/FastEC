package cn.peyton.android.latte.core.ui.recycler;

import java.util.ArrayList;

/**
 * <pre>
 * 数据转换约束
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 */

public abstract class DataConverter {


    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (null == mJsonData || mJsonData.isEmpty()) {
            throw new NullPointerException("【DataConverter】 数据为空");
        }
        return mJsonData;
    }

}
