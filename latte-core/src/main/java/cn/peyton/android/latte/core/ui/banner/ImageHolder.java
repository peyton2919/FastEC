package cn.peyton.android.latte.core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * <h3>图片 Holder 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.banner.ImageHolder
 * 创建时间 2017/12/13 14:11
 * 版本 1.0.0
 * </pre>
 */
public class ImageHolder implements Holder<String>{
    /** ImageView 对象 */
    private AppCompatImageView mImageView = null;

    /**
     * 设置图片加载策略
     */
    private static final RequestOptions REQUEST_OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    /**
     * 更新UI
     * @param context
     * @param position
     * @param data
     */
    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(REQUEST_OPTIONS)
                .into(mImageView);
    }
}
