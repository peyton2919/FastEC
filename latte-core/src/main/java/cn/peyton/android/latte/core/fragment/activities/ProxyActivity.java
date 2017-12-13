package cn.peyton.android.latte.core.fragment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import cn.peyton.android.latte.core.R;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * <h3>基础代理 Activity 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.fragment.activities.ProxyActivity
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 12:46
 * 版本 1.0.0
 * </pre>
 */
public abstract class ProxyActivity extends SupportActivity {

    /**
     * 用来返回根 Delegate {指定子类实现}
     * @return LatteDelegate 对象
     */
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState); //初始化容器
    }

    /**
     * 初始化容器
     * @param savedInstanceState Bundle对象
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (null == savedInstanceState) {
            //加载根Fragment
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc(); //垃圾清理
        System.runFinalization();//垃圾清理
    }
}
