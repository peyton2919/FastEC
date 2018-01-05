package cn.peyton.android.latte.core.delegates;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import cn.peyton.android.latte.core.app.ConfigKeys;
import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.ui.camera.CameraImageBean;
import cn.peyton.android.latte.core.ui.camera.LatteCamera;
import cn.peyton.android.latte.core.ui.camera.RequestCodes;
import cn.peyton.android.latte.core.ui.scanner.ScannerDelegate;
import cn.peyton.android.latte.core.util.callback.CallbackManager;
import cn.peyton.android.latte.core.util.callback.CallbackType;
import cn.peyton.android.latte.core.util.callback.IGlobalCallback;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * <h3>中间层，用来权限的判定{android 6.0 的动态权限判定}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.delegates.PermissionCheckedDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-02 - 13:00
 * 版本 1.0.0
 * </pre>
 */
@RuntimePermissions
public abstract class PermissionCheckedDelegate extends BaseDelegate {

    /**
     * 这个真正调用方法{ 检查 }
     */
    public void startCameraWithCheck() {
        PermissionCheckedDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    /**
     * 扫描二维码(不直接调用)
     */
    @NeedsPermission({Manifest.permission.CAMERA})
    void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(),RequestCodes.SCAN);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckedDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    public void startScanWithCheck(BaseDelegate delegate) {
        PermissionCheckedDelegatePermissionsDispatcher.startScanWithPermissionCheck(this,delegate);
    }

    /**
     * 不是直接调用方法 { 注册生成 代码 }
     */
    @NeedsPermission({Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_COARSE_LOCATION})
    void startCamera() {
        LatteCamera.start(this);
    }

    @OnPermissionDenied({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_COARSE_LOCATION})
    void onCameraDenied() {
        Toast.makeText(getContext(),"不允许",Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_COARSE_LOCATION})
    void onCameraNever() {
        Toast.makeText(getContext(),"永久拒绝权限",Toast.LENGTH_LONG).show();
    }

    @OnShowRationale({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_COARSE_LOCATION})
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }



    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();

                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(400,400)
                            .start(getContext(),this);

                    break;
                case RequestCodes.PICK_PHOTO:
                    if (null != data) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath,Uri.parse(pickCropPath))
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    final IGlobalCallback<Uri> callback = CallbackManager.getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (null != callback) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(),"剪裁出错.",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;

            }
        }
    }


}
