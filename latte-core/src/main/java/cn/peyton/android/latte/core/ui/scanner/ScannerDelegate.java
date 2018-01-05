package cn.peyton.android.latte.core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.ui.camera.RequestCodes;
import cn.peyton.android.latte.core.util.callback.CallbackManager;
import cn.peyton.android.latte.core.util.callback.CallbackType;
import cn.peyton.android.latte.core.util.callback.IGlobalCallback;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.scanner.ScannerDelegate
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler{

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == mScanView) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);

    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result result) {

        final IGlobalCallback<String> callback = CallbackManager.getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (null != callback) {
            callback.executeCallback(result.getContents());
        }

        getSupportDelegate().pop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mScanView) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mScanView) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }
}
