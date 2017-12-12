package cn.peyton.android.latte.core.ui.refresh;

/**
 * <pre>
 * 存储分页相关数据
 * </pre>
 * <p>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 */

public final class PagingBean {
    /** 当前是第几页 */
    private int mPageIndex = 0;
    /** 总条数 */
    private int mTotal = 0;
    /**  每页显示几条数据 */
    private int mPageSize = 0;
    /**  当前已经显示了几条数据 */
    private int mCurrentCount = 0;
    /**  加载延迟 */
    private int mDelayed = 0;


    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    /**
     * 第一次加载
     * @return
     */
    PagingBean addIndex() {
        mPageIndex ++;
        return this;
    }
}
