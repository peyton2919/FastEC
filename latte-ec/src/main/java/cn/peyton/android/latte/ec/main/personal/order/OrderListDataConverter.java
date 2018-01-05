package cn.peyton.android.latte.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import cn.peyton.android.latte.core.ui.recycler.DataConverter;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.core.ui.recycler.MultipleFields;
import cn.peyton.android.latte.core.ui.recycler.MultipleItemEntity;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.order.OrderListDataConverter
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class OrderListDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");

            final  MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,thumb)
                    .setField(MultipleFields.TITLE,title)
                    .setField(MultipleFields.PRICE,price)
                    .setField(MultipleFields.TIME,time)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
