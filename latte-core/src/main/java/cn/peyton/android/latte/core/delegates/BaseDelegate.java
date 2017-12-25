package cn.peyton.android.latte.core.delegates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.peyton.android.latte.core.activities.ProxyActivity;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * <h3>基础Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.delegates.BaseDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 12:48
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public abstract class BaseDelegate extends Fragment implements ISupportFragment {

    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    protected FragmentActivity _mActivity = null;

    /** 申明 Unbinder 对象  */
    private Unbinder mUnbinder = null;
    /**
     * <pre>
     * 设置 Layout {指定子类实现}
     * 子类传入布局,可以是View也可以是Layout的Id
     * </pre>
     * @return 视图
     */
    public abstract Object setLayout();

    /**
     *  绑定视图 {指定子类实现}
     * @param savedInstanceState Bundle对象
     * @param rootView 根视图
     */
    public abstract void onBindView( @Nullable Bundle savedInstanceState,View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DELEGATE.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        DELEGATE.onSaveInstanceState(outState);
    }

    /**
     * 获取 ProxyActivity 对象
     * @return
     */
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity; //当前Activity
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View rootView ;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("【BaseDelegate】 类型转换异常！");
        }
        //绑定黄油刀
        mUnbinder = ButterKnife.bind(this,rootView);
        //调用onBindView 方法
        onBindView(savedInstanceState,rootView);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        DELEGATE.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        DELEGATE.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        DELEGATE.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DELEGATE.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportInvisible();
    }

    @Override
    public boolean isSupportVisible() {
        return DELEGATE.isSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    @Override
    public boolean onBackPressedSupport() {
        return DELEGATE.onBackPressedSupport();
    }
}
