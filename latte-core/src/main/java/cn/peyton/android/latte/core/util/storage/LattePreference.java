package cn.peyton.android.latte.core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.peyton.android.latte.core.app.Latte;

/**
 * <pre>
 *  数据持久化工具类 {Preference}
 *  提示:
 * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
 * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
 * Context.getSharedPreferences(String name,int mode)生成name.xml
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.util.storage.LattePreference
 * 项目名 FestEC
 * 创建时间 2017-12-04 13:34
 * 版本 1.0.0
 */
public class LattePreference {

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    /** 申明 key 默认为 profile */
    private static final String APP_PREFERENCES_KEY = "profile";

    /**
     * 获取
     * @return
     */
    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    /**
     * 存入
     * @param val
     */
    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    /**
     * 存入全局Flag {第一次使用存入true}
     * @param key
     * @param flag
     */
    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag) //第一次使用存入true
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }
}
