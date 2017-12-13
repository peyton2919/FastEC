package cn.peyton.android.latte.core.fragment.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.peyton.android.latte.core.fragment.activities.ProxyActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * <h3>基础Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.fragment.delegates.BaseDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 12:48
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public abstract class BaseDelegate extends SwipeBackFragment {

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
}
