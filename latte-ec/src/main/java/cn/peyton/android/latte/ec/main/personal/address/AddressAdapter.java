package cn.peyton.android.latte.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.List;

import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.core.ui.recycler.MultipleFields;
import cn.peyton.android.latte.core.ui.recycler.MultipleItemEntity;
import cn.peyton.android.latte.core.ui.recycler.MultipleRecyclerAdapter;
import cn.peyton.android.latte.core.ui.recycler.MultipleViewHolder;
import cn.peyton.android.latte.ec.R;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.address.AddressAdapter
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class AddressAdapter extends MultipleRecyclerAdapter{
    /**
     * 构造函数
     *
     * @param data MultipleItemEntity对象集合
     */
    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(MultipleFields.PHONE);
                final String address = entity.getField(MultipleFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);

                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO 请求服务器
                        RestClient.builder()
                                .url("http://192.168.3.3:8080/o2o/api/address")
                                .params("id",id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(holder.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);

                break;
            default:
                break;
        }


    }
}
