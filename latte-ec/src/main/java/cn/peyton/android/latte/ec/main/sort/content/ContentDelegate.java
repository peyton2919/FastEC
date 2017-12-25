package cn.peyton.android.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import cn.peyton.android.latte.core.delegates.LatteDelegate;
import cn.peyton.android.latte.core.net.RestClient;
import cn.peyton.android.latte.core.net.callback.ISuccess;
import cn.peyton.android.latte.ec.R;
import cn.peyton.android.latte.ec.R2;

/**
 * <pre>
 *
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 */

public class ContentDelegate extends LatteDelegate{

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (null != args) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("o2o/api/sortcontentdata?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }
}
