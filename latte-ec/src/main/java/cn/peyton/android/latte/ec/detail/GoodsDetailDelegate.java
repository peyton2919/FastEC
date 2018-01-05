package cn.peyton.android.latte.ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.ui.animation.BezierAnimation;
import cn.peyton.android.latte.core.ui.animation.BezierUtil;
import cn.peyton.android.latte.core.ui.banner.HolderCreator;
import cn.peyton.android.latte.core.widget.CircleTextView;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * <h3>商品详情 Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.ec.detail.GoodsDetailDelegate
 * 创建时间 2017/12/13 17:04
 * 版本 1.0.0
 * </pre>
 */
public class GoodsDetailDelegate extends LatteDelegate implements
        AppBarLayout.OnOffsetChangedListener,BezierUtil.AnimationListener{

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100,100);

    //=========================== 绑定 开始 ==============================
    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    //=========================== 绑定 结束 ==============================

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickAddShopCart() {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        BezierAnimation.addCart(this,mRlAddShopCart,mIconShopCart,animImg,this);
    }

    private void setShopCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (0 == mShopCount) {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    /**
     * 创建 GoodsDetailDelegate对象 {简单工厂模式}
     * @return
     */
    public static GoodsDetailDelegate create(@NonNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID ,goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (null != args) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
            Toast.makeText(getContext(),"商品ID： " + mGoodsId,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 水平动画
     * @return
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /**
     * toolbar 事件监听
     * @param appBarLayout
     * @param verticalOffset
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    private void initData() {
        RestClient.builder()
                .url("o2o/api/goodsdetail")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject data = JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initPager(data);
                        initGoodsInfo(data);
                        setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED); //分开Tab
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.app_main));//底线的颜色
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));//字的颜色
        mTabLayout.setBackgroundColor(Color.WHITE);//背景颜色
        mTabLayout.setupWithViewPager(mViewPager);

    }



    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mBanner.setPages(new HolderCreator(), images)
            .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            .setPageTransformer(new DefaultTransformer())
            .startTurning(3000)
            .setCanLoop(true);
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info,GoodsInfoDelegate.create(goodsData));
    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(500)
                .playOn(mIconShopCart);


        RestClient.builder()
                .url("o2o/api/shopcartcount")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mShopCount++;
                        mCircleTextView.setVisibility(View.VISIBLE);
                        mCircleTextView.setText(String.valueOf(mShopCount));
                    }
                })
                .params("count",mShopCount)
                .build()
                .post();
    }
}
