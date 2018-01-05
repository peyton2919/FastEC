package cn.peyton.android.latte.ec.detail;


import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.detail.ScaleUpAnimator
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class ScaleUpAnimator  extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target,"scaleX",0.8f,1f,1.4f,1.2f,1),
                ObjectAnimator.ofFloat(target,"scaleY",0.8f,1f,1.4f,1.2f,1)
        );
    }
}
