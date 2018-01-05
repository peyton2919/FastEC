package cn.peyton.android.latte.core.ui.camera;

import android.net.Uri;

import cn.peyton.android.latte.core.delegates.PermissionCheckedDelegate;
import cn.peyton.android.latte.core.util.file.FileUtil;

/**
 * <h3>照相机调用类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.camera.LatteCamera
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckedDelegate delegate) {
        new CameraHanlder(delegate).beginCameraDialog();;
    }


}
