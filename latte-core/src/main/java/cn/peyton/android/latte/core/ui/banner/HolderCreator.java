package cn.peyton.android.latte.core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * <h3>banner Holder 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.banner.HolderCreator
 * 创建时间 2017/12/13 13:50
 * 版本 1.0.0
 * </pre>
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder>{
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
