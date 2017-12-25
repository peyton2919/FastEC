package cn.peyton.android.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import cn.peyton.android.latte.core.delegates.bottom.BaseBottomDelegate;
import cn.peyton.android.latte.core.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.core.delegates.bottom.BottomTabBean;
import cn.peyton.android.latte.core.delegates.bottom.ItemBuilder;
import cn.peyton.android.latte.ec.main.cart.ShopCartDelegate;
import cn.peyton.android.latte.ec.main.discover.DiscoverDelegate;
import cn.peyton.android.latte.ec.main.index.IndexDelegate;
import cn.peyton.android.latte.ec.main.sort.SortDelegate;
import cn.peyton.android.latte.ec.main.personal.PersonalDelegate;

/**
 * <h3>基础Bottom Delegate类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.EcBottomDelegate
 * 创建时间 2017/12/13 17:17
 * 版本 1.0.0
 * </pre>
 */
public class EcBottomDelegate extends BaseBottomDelegate{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
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
