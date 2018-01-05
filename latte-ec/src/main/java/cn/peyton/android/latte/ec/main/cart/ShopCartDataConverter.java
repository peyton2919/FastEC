package cn.peyton.android.latte.ec.main.cart;


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
 * 完整类名 cn.peyton.android.latte.ec.main.cart.ShopCartDataConverter
 * 项目名称 FastEC
 * 创建时间 2017/12/17 12:43
 * 版本 1.0.0
 * </pre>
 */
public class ShopCartDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,thumb)
                    .setField(MultipleFields.TITLE,title)
                    .setField(MultipleFields.DESC,desc)
                    .setField(MultipleFields.COUNT,count)
                    .setField(MultipleFields.PRICE,price)
                    .setField(MultipleFields.IS_SELECTED,false)
                    .setField(MultipleFields.POSITION,i)
                    .build();
            dataList.add(entity);
        }

        return dataList;
    }
}
