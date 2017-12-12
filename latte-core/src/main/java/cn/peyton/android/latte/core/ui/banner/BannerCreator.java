package cn.peyton.android.latte.core.ui.banner;


import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import cn.peyton.android.latte.core.R;

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

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner, ArrayList<String> banners,
                                  OnItemClickListener clickListener) {

        convenientBanner.setPages(new HolderCreator(), banners)
                        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setOnItemClickListener(clickListener)
                        .setPageTransformer(new DefaultTransformer())
                        .startTurning(3000)
                        .setCanLoop(true);


    }
}
