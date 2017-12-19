package cn.peyton.android.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.ec.detail.GoodsDetailDelegate;

/**
 * <h3>index 项的点击类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.index.IndexItemClickListener
 * 创建时间 2017/12/13 17:23
 * 版本 1.0.0
 * </pre>
 */
public class IndexItemClickListener extends SimpleClickListener {
    /** 申明 LatteDelegate对象 */
    private final LatteDelegate DELEGATE;

    /**
     * 构造函数
     * @param delegate LatteDelegate对象
     */
    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 创建 SimpleClickListener 对象
     * @param delegate LatteDelegate对象
     * @return SimpleClickListener 对象
     */
    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }


    /**
     * Item 点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
