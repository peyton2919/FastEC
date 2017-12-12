package cn.peyton.android.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.peyton.android.latte.core.fragment.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.main.sort.content.ContentDelegate;
import cn.peyton.android.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.main.index.IndexDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 13:04
 * 版本 1.0.0
 */
public class SortDelegate extends BottomItemDelegate{


    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
