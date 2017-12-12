package cn.peyton.android.latte.core.util.log;

import com.orhanobut.logger.Logger;

/**
 * <pre>
 *  日志类
 * </pre>
 * 
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 2017-12-04 18:50
 * 版本 1.0.0
 */
public final class LatteLogger {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;

    //控制log等级
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(message);
        }
    }

    public static void d(String tag, Object message) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message) {
        if (LEVEL <= DEBUG) {
            Logger.d(message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(message);
        }
    }

    public static void json(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).json(message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(message);
        }
    }
}
