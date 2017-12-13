package cn.peyton.android.latte.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * <h3>多个视图 Holder 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 * </pre>
 */

public class MultipleViewHolder extends BaseViewHolder{

    /**
     * 私有构造函数
     * @param view 视图对象
     */
    private MultipleViewHolder(View view) {
        super(view);
    }

    /**
     * 简单工厂模式
     * @param view 视图
     * @return MultipleViewHolder对象
     */
    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
