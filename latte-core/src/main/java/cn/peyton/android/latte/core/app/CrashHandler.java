package cn.peyton.android.latte.core.app;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 *     UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-15 12:27
 * </pre>
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**  */
    private static final String TAG = "CrashHandler";
    /** 系统默认的UncaughtException处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /** CrashHandler实例  */
    private static CrashHandler INSTANCE = new CrashHandler();
    /**上下文对象 */
    private Context mContext;
    /** 用来存储设备信息和异常信息  */
    private Map<String ,String> infos = new HashMap<String, String>();
    /** 用于格式化日期,作为日志文件名的一部分 */
    private DateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
    /** 包名      */
    private String packageName = "default";
    /** 保证只有一个CrashHandler实例 */
    private CrashHandler(){}
    /** 获取CrashHandler实例 ,单例模式 */
    public  static CrashHandler getInstance(){
        return  INSTANCE;
    }


    /**
     * 当UncaughtException发生时会转入该函数来处理
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && null != mDefaultHandler) {
            //如果用户没有处理则让系统默认异常处理器来处理
            mDefaultHandler.uncaughtException(thread,ex);
        }else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "uncaughtException: " + e.getMessage());
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 初始化
     * @param context 上下文
     */
    public void init(Context context){
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private boolean handleException(Throwable ex)
    {
        if (null == ex){
            return  false;
        }
        //使用Toast 来显示异常信息
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,"很抱歉,程序出现异常,即将退出.",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志信息
        saveCrashInfoToFile(ex);
        return  true;
    }

    /**
     * 收集设备参数信息
     * @param context
     */
    private void collectDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),PackageManager.GET_ACTIVITIES);
            if (null != pi){
                String versionName = pi.versionName == null ?"null":pi.versionName;
                String versionCode = pi.versionCode + "";
                this.packageName = pi.packageName;

                infos.put("versionName",versionName);
                infos.put("versionCode",versionCode);
            }
        }catch (NameNotFoundException ex){
            Log.e(TAG, "collectDeviceInfo: 收集崩溃信息时出错[{}]", ex);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields){
            try{
                field.setAccessible(true);
                infos.put(field.getName(),field.get(null).toString());
                Log.d(TAG, field.getName() +": " + field.get(null));
            }catch (Exception ex){
                Log.e(TAG, "collectDeviceInfo: 收集崩溃信息时出错[{}]", ex);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     * @return 返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry :infos.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + " = " + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (null != cause){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        FileOutputStream fos = null;
        String fileName = "";
        try {
            long timestamp = System.currentTimeMillis();
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            String time = formatter.format(new Date());

            fileName = packageName + "-" + time + "-" +timestamp + ".log";
            String path;

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cn.peyton.crash.ex/";
            }else{
                path = Environment.getExternalStoragePublicDirectory("").getAbsolutePath() + "/cn.peyton.crash.ex/";
            }
            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }
            fos = new FileOutputStream(path + fileName);
            fos.write(sb.toString().getBytes("UTF-8"));
            fos.flush();


        }catch (Exception e){
            Log.e(TAG, "saveCrashInfoToFile: 写入文件时出错 [{}]", e);
        }finally {
            if (null != fos) {

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

       return fileName;
    }


}
