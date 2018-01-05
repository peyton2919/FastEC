package cn.peyton.android.latte.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import cn.peyton.android.latte.core.app.Latte;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.core.ui.recycler.DataConverter;
import cn.peyton.android.latte.core.ui.recycler.MultipleRecyclerAdapter;
/**
 * <h3>刷新 Handler 类</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.ui.refresh.RefreshHandler
 * 创建时间 2017/12/13 15:29
 * 版本 1.0.0
 * </pre>
 */
public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{
    /** 申明SwipeRefreshLayout 对象 */
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    /** 申明 分页对象 */
    private final PagingBean BEAN;
    /** 循环视图 */
    private final RecyclerView RECYCLERVIEW;
    /**  申明MultipleRecyclerAdapter 对象 */
    private MultipleRecyclerAdapter mAdapter = null;
    /**  申明 DataConverter 对象 */
    private final DataConverter CONVERTER;

    /**
     * 私有构造函数
     * @param swipeRefreshLayout SwipeRefreshLayout 对象
     * @param recyclerView RecyclerView对象
     * @param converter DataConverter对象
     * @param bean 分页对象
     */
    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView, DataConverter converter,
                          PagingBean bean){
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    /**
     * 创建 RefreshHandler对象
     * @param swipeRefreshLayout SwipeRefreshLayout 对象
     * @param recyclerView RecyclerView对象
     * @param converter DataConverter对象
     * @return
     */
    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    /**
     * 刷新
     */
    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO 进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    /**
     * 第一次加载页面
     * @param url 链接地址
     */
    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //TODO
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                }).build()
                .get();
    }

    @Override
    public void onLoadMoreRequested() {
        paging("o2o/api/fastec");
    }

    private void paging(final String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        System.out.println("链接地址： " + url);
        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    System.out.println(response);
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }

    }
}
