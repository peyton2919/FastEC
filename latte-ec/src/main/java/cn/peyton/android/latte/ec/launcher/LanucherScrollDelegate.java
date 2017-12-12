package cn.peyton.android.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import cn.peyton.android.latte.core.app.AccountManager;
import cn.peyton.android.latte.core.app.IUserChecker;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import cn.peyton.android.latte.core.ui.launcher.ILauncherListener;
import cn.peyton.android.latte.core.ui.launcher.LauncherHolderCreator;
import cn.peyton.android.latte.core.ui.launcher.OnLauncherFinishTag;
import cn.peyton.android.latte.core.ui.launcher.ScrollLaucherTag;
import cn.peyton.android.latte.core.util.storage.LattePreference;
import cn.peyton.android.latte.ec.R;

/**
 * <pre>
 *  启动图 滚动类
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.launcher.LanucherScrollDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 14:36
 * 版本 1.0.0
 */
public class LanucherScrollDelegate extends LatteDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mLauncherListener = null;



    @Override

    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS) //设置图片
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus}) //设置页码焦点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置页码居中
                .setOnItemClickListener(this)
                .setCanLoop(true); //设置可以循环

    }

    @Override
    public void onItemClick(int position) {

        //如果点击最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLaucherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (null != mLauncherListener) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (null != mLauncherListener) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOTSIGNED);
                    }
                }
            });
        }
    }
}
