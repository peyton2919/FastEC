package cn.peyton.android.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

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

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            default:
                break;
        }
    }
}
