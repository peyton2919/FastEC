package cn.peyton.android.latte.core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import cn.peyton.android.latte.core.R;
import cn.peyton.android.latte.core.delegates.LatteDelegate;

/**
 * <h3>BottomItem Delegate类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.delegates.bottom.BottomItemDelegate
 * 创建时间 2017/12/13 16:05
 * 版本 1.0.0
 * </pre>
 */
public abstract class BottomItemDelegate extends LatteDelegate{
    /** 退出时间 */
    private long TOUCH_TIME = 0;
    /** 再点一次退出程序时间设置 */
    private static final Long WAIT_TIME = 2000L;


    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity,"双击退出 Latte", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}
