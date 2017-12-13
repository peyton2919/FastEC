package cn.peyton.android.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.net.callback.IRequest;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.util.file.FileUtil;
import okhttp3.ResponseBody;

/**
 * <h3>文件保存类 {异步任务调用}</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.core.net.download.SaveFileTask
 * 项目名 FestEC
 * 创建时间 2017-12-03 14:39
 * 版本 1.0.0
 * </pre>
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {
    /** 请求接口 */
    private final IRequest REQUEST;
    /** 成功接口 */
    private final ISuccess SUCCESS;

    /**
     * 构造函数
     * @param request 请求接口
     * @param success 成功接口
     */
    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (null == downloadDir || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (null == extension ||extension.equals("")) {
            extension = "";
        }
        if (null == name) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    /**
     * 执行完异步返回主线程操作
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (null != SUCCESS) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (null != REQUEST) {
            REQUEST.onRequestEnd();
        }
    }

    /**
     * 下载apk文件自动安装
     * @param file
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
