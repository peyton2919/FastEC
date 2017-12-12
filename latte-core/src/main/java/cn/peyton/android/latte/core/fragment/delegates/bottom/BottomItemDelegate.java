package cn.peyton.android.latte.core.fragment.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import cn.peyton.android.latte.core.R;
import cn.peyton.android.latte.core.fragment.delegates.LatteDelegate;

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

public abstract class BottomItemDelegate extends LatteDelegate
        implements View.OnKeyListener{

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (null != rootView) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }
}
