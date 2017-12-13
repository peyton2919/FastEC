package cn.peyton.android.latte.core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * <h3>轮播图 Holder类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.ui.launcher.LauncherHolder
 * 项目名 FestEC
 * 创建时间 2017-12-04 14:44
 * 版本 1.0.0
 * </pre>
 */
public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    /**
     * 每次拨动要更新的 UI
     * @param context
     * @param position
     * @param data
     */
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
