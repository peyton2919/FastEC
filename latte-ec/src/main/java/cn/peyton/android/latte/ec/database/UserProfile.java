package cn.peyton.android.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <h3>用户实体类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.database.UserProfile
 * 项目名 FestEC
 * 创建时间 2017-12-04 19:00
 * 版本 1.0.0
 * </pre>
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    /** 用户编号 */
    @Id
    private long userId;
    /** 用户名称 */
    private String name = null;
    /** 用户化身 */
    private String avatar = null;
    /** 用户性别 */
    private String gender = null;
    /** 用户地址 */
    private String address = null;

    /**
     * 构造函数
     * @param userId 用户编号
     * @param name 用户名称
     * @param avatar 用户化身
     * @param gender 用户性别
     * @param address 用户地址
     */
    @Generated(hash = 1202698052)
    public UserProfile(long userId, String name, String avatar, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }

    /**
     * 构造函数
     */
    @Generated(hash = 968487393)
    public UserProfile() {
    }

    /**
     * @return 用户编号
     */
    public long getUserId() {
        return this.userId;
    }

    /**
     * @param userId 用户编号
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return 用户名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 用户化身
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * @param avatar 用户化身
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return 用户性别
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * @param gender 用户性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return 用户地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @param address 用户地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
