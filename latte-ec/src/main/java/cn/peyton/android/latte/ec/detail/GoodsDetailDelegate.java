package cn.peyton.android.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.ec.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * <h3>商品详情 Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.detail.GoodsDetailDelegate
 * 创建时间 2017/12/13 17:04
 * 版本 1.0.0
 * </pre>
 */
public class GoodsDetailDelegate extends LatteDelegate{

    /**
     * 创建 GoodsDetailDelegate对象 {简单工厂模式}
     * @return
     */
    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    /**
     * 水平动画
     * @return
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
