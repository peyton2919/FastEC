package cn.peyton.android.latte.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.ui.date.DateDialogUtil;
import cn.peyton.android.latte.core.util.callback.CallbackManager;
import cn.peyton.android.latte.core.util.callback.CallbackType;
import cn.peyton.android.latte.core.util.callback.IGlobalCallback;
import cn.peyton.android.latte.core.util.log.LatteLogger;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.main.personal.list.ListBean;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.profile.UserProfileClickListener
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class UserProfileClickListener extends SimpleClickListener{

    private LatteDelegate DELEGATE;

    private String[] mGenders = new String[]{"男","女","保密"};

    public UserProfileClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) adapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        LatteLogger.d("ON_CROP",args);
                        System.out.println("ON_CROP: " + args);
                        //TODO
                        final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(DELEGATE)
                                .load(args)
                                .into(avatar);
                        //上传图片
                        RestClient.builder()
                                .url("http://192.168.3.3:8080/o2o/upload/upLoadFile.do")
                                .file(args.getPath())
                                .loader(DELEGATE.getContext())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        LatteLogger.d("on_crop_upload",response);

                                        //TODO 可以更新信息处理 通知服务器
                                    }
                                })
                                .build()
                                .upload();
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }

    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
