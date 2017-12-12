package cn.peyton.android.latte.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

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

public class MultipleViewHolder extends BaseViewHolder{

    private MultipleViewHolder(View view) {
        super(view);
    }


    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
