package cn.peyton.android.latte.core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

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

public class HolderCreator implements CBViewHolderCreator<ImageHolder>{
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
