package cn.peyton.android.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import cn.peyton.android.latte.core.app.Latte;
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
 * 完整类名 cn.peyton.android.latte.ec.main.cart.ShopCartAdapter
 * 项目名称 FastEC
 * 创建时间 2017/12/17 13:13
 * 版本 1.0.0
 * </pre>
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter{
    /**   */
    private boolean mIsSelectedAll = false;
    /**   */
    private ICartItemListener mCartItemListener = null;
    /**  商品总价  */
    private double mTotalPrice = 0.00;


    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    /**
     * 构造函数
     *
     * @param data MultipleItemEntity对象集合
     */
    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(MultipleFields.PRICE);
            final int count = entity.getField(MultipleFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车 item布局
        addItemType(ItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice() {
        return  mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.SHOP_CART_ITEM:
                //取出 所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(MultipleFields.TITLE);
                final String desc = entity.getField(MultipleFields.DESC);
                final int count = entity.getField(MultipleFields.COUNT);
                final double price = entity.getField(MultipleFields.PRICE);


                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);


                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);

                //在左侧checkbox 数据状态显示
                entity.setField(MultipleFields.IS_SELECTED,mIsSelectedAll);
                final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);

                //根据数据状态显示左侧选中状态
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(),R.color.app_main));
                }else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧check点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final boolean currentSelected = entity.getField(MultipleFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(MultipleFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(),R.color.app_main));
                            entity.setField(MultipleFields.IS_SELECTED, true);
                        }
                    }
                });
                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(MultipleFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("o2o/api/shopmp")
                                    .loader(mContext)
                                    .params("count",currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum --;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (null != mCartItemListener) {
                                               mTotalPrice = mTotalPrice - price;
                                               final double itemTotal = countNum * price;
                                               mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();


                        }
                    }
                });
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(MultipleFields.COUNT);
                        RestClient.builder()
                                .url("o2o/api/shopmp")
                                .loader(mContext)
                                .params("count",currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum ++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (null != mCartItemListener) {
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemTotal = countNum * price;
                                            mCartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                break;
                default:
                    break;
        }
    }
}
