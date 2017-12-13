package cn.peyton.android.latte.core.ui.refresh;

/**
 * <h3>存储分页相关数据</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间 ${date} ${time}
 * 版本 1.0.0
 * </pre>
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

    /**
     * @return 当前是第几页
     */
    public int getPageIndex() {
        return mPageIndex;
    }

    /**
     * @param pageIndex 当前是第几页
     * @return PagingBean 对象
     */
    public PagingBean setPageIndex(int pageIndex) {
        this.mPageIndex = pageIndex;
        return this;
    }

    /**
     * @return 总条数
     */
    public int getTotal() {
        return mTotal;
    }

    /**
     * @param total 总条数
     * @return PagingBean 对象
     */
    public PagingBean setTotal(int total) {
        this.mTotal = total;
        return this;
    }

    /**
     * @return 每页显示几条数据
     */
    public int getPageSize() {
        return mPageSize;
    }

    /**
     * @param pageSize 每页显示几条数据
     * @return PagingBean 对象
     */
    public PagingBean setPageSize(int pageSize) {
        this.mPageSize = pageSize;
        return this;
    }

    /**
     * @return  当前已经显示了几条数据
     */
    public int getCurrentCount() {
        return mCurrentCount;
    }

    /**
     * @param currentCount  当前已经显示了几条数据
     * @return PagingBean 对象
     */
    public PagingBean setCurrentCount(int currentCount) {
        this.mCurrentCount = currentCount;
        return this;
    }

    /**
     * @return 加载延迟
     */
    public int getDelayed() {
        return mDelayed;
    }

    /**
     * @param delayed 加载延迟
     * @return PagingBean 对象
     */
    public PagingBean setDelayed(int delayed) {
        this.mDelayed = delayed;
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
