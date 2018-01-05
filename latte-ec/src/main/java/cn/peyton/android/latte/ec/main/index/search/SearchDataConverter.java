package cn.peyton.android.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

import cn.peyton.android.latte.core.app.CommonTagNames;
import cn.peyton.android.latte.core.ui.recycler.DataConverter;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.core.ui.recycler.MultipleFields;
import cn.peyton.android.latte.core.ui.recycler.MultipleItemEntity;
import cn.peyton.android.latte.core.util.storage.LattePreference;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.index.search.SearchDataConverter
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class SearchDataConverter extends DataConverter{


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(CommonTagNames.TAG_PREFERENCE_SEARCH_HISTORY);

        if (!"".equals(jsonStr)) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final  int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final  MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(ItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
