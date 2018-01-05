package cn.peyton.android.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.core.util.callback.CallbackManager;
import cn.peyton.android.latte.core.util.callback.CallbackType;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;
import cn.peyton.android.latte.ec.main.personal.PersonalClickListener;
import cn.peyton.android.latte.ec.main.personal.address.AddressDelegate;
import cn.peyton.android.latte.ec.main.personal.list.ListAdapter;
import cn.peyton.android.latte.ec.main.personal.list.ListBean;

/**
 * <h3>系统设置 Delegate</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.settings.SettingsDelegate
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class SettingsDelegate extends LatteDelegate{

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {

        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder()
                .setItemType(ItemType.ITEM_SWITCH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //TODO
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"打开推送",Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"关闭推送",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(manager);

        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
