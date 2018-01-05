package cn.peyton.android.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.peyton.android.latte.core.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;
import cn.peyton.android.latte.ec.main.personal.address.AddressDelegate;
import cn.peyton.android.latte.ec.main.personal.list.ListAdapter;
import cn.peyton.android.latte.ec.main.personal.list.ListBean;
import cn.peyton.android.latte.ec.main.personal.order.OrderListDelegate;
import cn.peyton.android.latte.ec.main.personal.profile.UserProfileDelegate;
import cn.peyton.android.latte.ec.main.personal.settings.SettingsDelegate;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.PersonalDelegate
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class PersonalDelegate extends BottomItemDelegate{

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;


    //===================================== 事件 begin ==========================================
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE,"all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    //===================================== 事件 end ==========================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }



    //根据不同的tag打开不同 OrderList
    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());

        mRvSettings.setLayoutManager(manager);

        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
