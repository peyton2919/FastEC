package cn.peyton.android.latte.core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * <h3>请求码存储</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.camera.RequestCodes
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class RequestCodes {
    /**  拍照 */
    public static final int TAKE_PHOTO = 4;
    /**  选择图片 */
    public static final int PICK_PHOTO = 5;
    /**  剪裁图片 */
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    /** 剪裁图片失败  */
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    /**  扫描 */
    public static final int SCAN = 7;
}
