package cn.peyton.android.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * <h3> 数据库 管理类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.database.DatabaseManager
 * 项目名 FestEC
 * 创建时间 2017-12-04 21:49
 * 版本 1.0.0
 * </pre>
 */
public class DatabaseManager {
    /** DaoSession对象 */
    private DaoSession mDaoSession = null;
    /** UserProfileDao对象 */
    private UserProfileDao mDao = null;

    /**
     * 初始化 DatabaseManager 对象
     * @param context 上下文对象
     * @return
     */
    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    /**
     * 获取 UserProfileDao对象
     * @return UserProfileDao对象
     */
    public final UserProfileDao getmDao() {
        return mDao;
    }

    /**
     * 初始化 DatabaseManager对象
     * @param context 上下文对象
     */
    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    // ============================ 单例加载  =================================

    /**
     * 单例加载
     * @return
     */
    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private DatabaseManager(){}

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
    // ============================ 单例加载  =================================
}

