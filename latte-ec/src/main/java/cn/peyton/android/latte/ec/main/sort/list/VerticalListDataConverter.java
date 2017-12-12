package cn.peyton.android.latte.ec.main.sort.list;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import cn.peyton.android.latte.core.ui.recycler.DataConverter;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.core.ui.recycler.MultipleFields;
import cn.peyton.android.latte.core.ui.recycler.MultipleItemEntity;

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

public final class VerticalListDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity =
                    MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG, true);
        }

        return dataList;
    }
}
