package cn.peyton.android.latte.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.peyton.android.latte.core.R;
import cn.peyton.android.latte.core.util.dimen.DimenUtil;

/**
 * <h3>加载条 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.ui.loader.LatteLoader
 * 项目名 FestEC
 * 创建时间 2017-12-03 12:30
 * 版本 1.0.0
 * </pre>
 */
public class LatteLoader {
    /** 设置屏幕缩放比为8 */
    private static final int LOADER_SIZE_SCALE = 8;
    /** 设置偏移量 为10*/
    private static final int LOADER_OFFSET_SCALE = 10;
    /** 默认loading 样式*/
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();
    /** 存储Loader 信息 */
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    /**
     * 显示 加载条
     * @param context 当前上下文对象
     * @param type 加载条类型枚举
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context,type.name());
    }

    /**
     * 显示 加载条
     * @param context 当前上下文对象
     * @param type 加载条类型 {名称LoaderStyle.xxxx.name()}
     */
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        //设置dialog 根
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth(); //获取屏幕宽度
        int deviceHeight = DimenUtil.getScreenHeight();//获取屏幕高度
        //创建Window对象
        final Window dialogWindow = dialog.getWindow();

        if (null != dialogWindow) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 显示 加载条
     * @param context 当前上下文对象
     */
    public static void showLoading(Context context) {
        showLoading(context,DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (null != dialog) {
                if (dialog.isShowing()) {
                    dialog.cancel();//用cancel方法会执行一些回调方法,而dismisss只是单纯的关闭
                }
            }
        }
    }

}
