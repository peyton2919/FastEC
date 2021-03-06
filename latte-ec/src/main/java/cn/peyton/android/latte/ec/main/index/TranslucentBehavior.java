package cn.peyton.android.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import cn.peyton.android.latte.core.ui.recycler.RGBValue;
import cn.peyton.android.latte.ec.R;

/**
 * <h3>半透明 Behavior类{Toolbar}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.index.TranslucentBehavior
 * 创建时间 2017/12/13 17:25
 * 版本 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    /** 顶部距离 */
    private int mDistanceY = 0;
    /** 颜色变化速度 */
    private static final int SHOW_SPEED = 3;
    /** 定义变化颜色 */
    private final RGBValue RGB_VALUE =RGBValue.create(255,124,2);

    /**
     * 构造函数
     * @param context 上下文对象
     * @param attrs AttributeSet对象
     */
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull Toolbar child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return true;
    }

    //处理逻辑方法
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull Toolbar child, @NonNull View target, int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加滑动距离
        mDistanceY +=dy;
        //toolbar高度
        final int targetHeight = child.getBottom();
        //当滑动时，并且距离小于 toolbar 高度时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            //设置颜色
            child.setBackgroundColor(Color.argb((int)alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));

        } else if (mDistanceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }

    }
}
