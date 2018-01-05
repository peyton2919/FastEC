package cn.peyton.android.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.peyton.android.latte.core.delegates.bottom.BottomItemDelegate;
import cn.peyton.android.latte.core.ui.recycler.BaseDecoration;
import cn.peyton.android.latte.core.ui.refresh.RefreshHandler;
import cn.peyton.android.latte.core.util.callback.CallbackManager;
import cn.peyton.android.latte.core.util.callback.CallbackType;
import cn.peyton.android.latte.core.util.callback.IGlobalCallback;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;
import cn.peyton.android.latte.ec.main.EcBottomDelegate;
import cn.peyton.android.latte.ec.main.index.search.SearchDelegate;

/**
 * <h3>index Delegate 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 类全名 cn.peyton.android.latte.ec.main.index.IndexDelegate
 * 项目名 FestEC
 * 创建时间 2017-12-04 13:04
 * 版本 1.0.0
 * </pre>
 */
public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener{
    /** 申明刷新Handler */
    private RefreshHandler mRefreshHandler = null;
    //==================  视图绑定 开始 =====================
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIndexMessage = null;
    //==================  视图绑定 结束 =====================

    //=======================  控件点击事件 开始  ============================
    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(getParentDelegate());
    }


    //=======================  控件点击事件 结束  ============================
    @Override
    public Object setLayout() {

        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        //TODO 处理回调
                        Toast.makeText(getContext(), "得到的二维码是： " + args,Toast.LENGTH_LONG).show();
                    }
                });
        //搜索框点击事件
        mSearchView.setOnFocusChangeListener(this);
    }

    /**
     * 懒加载视图
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        //TODO
        mRefreshHandler.firstPage("o2o/api/fastec");
    }

    /**
     * 初始化RecyclerView布局
     */
    private void initRecyclerView() {
        //风格布局
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),5));
        //TODO
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    /**
     * 初始化刷新Layout
     */
    private void initRefreshLayout() {
        //设置颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //设置位置
        mRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }
}
