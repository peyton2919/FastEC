package cn.peyton.android.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import cn.peyton.android.latte.core.fragment.delegates.bottom.BaseBottomDelegate;
import cn.peyton.android.latte.core.fragment.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.core.fragment.delegates.bottom.BottomTabBean;
import cn.peyton.android.latte.core.fragment.delegates.bottom.ItemBuilder;
import cn.peyton.android.latte.ec.main.discover.DiscoverDelegate;
import cn.peyton.android.latte.ec.main.index.IndexDelegate;
import cn.peyton.android.latte.ec.main.sort.SortDelegate;

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

public class EcBottomDelegate extends BaseBottomDelegate{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
