package cn.peyton.android.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.peyton.android.latte.core.fragment.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.ec.R;

/**
 * <h3>发现 Delegate类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.discover.DiscoverDelegate
 * 创建时间 2017/12/13 17:18
 * 版本 1.0.0
 * </pre>
 */
public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
