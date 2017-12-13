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
 * 作者 <a href="http://www.peyton.cn">peyton</a><br>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a><br>
 * 类全名 cn.peyton.android.latte.core.util.storage.LattePreference <br>
 * 项目名 FestEC<br>
 * 创建时间 2017-12-04 13:34<br>
 * 版本 1.0.0<br>
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
     * 获取 Preference 对象
     * @return
     */
    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    /**
     * 存入  {key : APP_PREFERENCES_KEY}
     * @param val 值
     */
    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    /**
     * 获取 Preference对象 {key : APP_PREFERENCES_KEY}
     * @return Preference对象
     */
    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    /**
     * 获取Json
     * @return json
     */
    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    /**
     * 移除Preference 对象
     */
    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    /**
     * 清空Preference 对象
     */
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

    /**
     * 获取全局 Flag值
     * @param key
     * @return
     */
    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    /**
     * 存入客户信息
     * @param key 键
     * @param val 值
     */
    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    /**
     * 获取客户信息
     * @param key 键
     * @return 值
     */
    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }
}
