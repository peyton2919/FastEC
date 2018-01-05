package cn.peyton.android.latte.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.peyton.android.latte.core.ui.recycler.ItemType;
import cn.peyton.android.latte.ec.R;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.main.personal.list.ListAdapter
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder>{

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ItemType.ITEM_AVATER, R.layout.arrow_item_avater);
        addItemType(ItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {
            case ItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ItemType.ITEM_AVATER:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            case ItemType.ITEM_SWITCH:
                helper.setText(R.id.tv_arrow_switch_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
