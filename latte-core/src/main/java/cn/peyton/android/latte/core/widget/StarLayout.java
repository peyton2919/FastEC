package cn.peyton.android.latte.core.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import cn.peyton.android.latte.core.R;

/**
 * <h3>星星布局</h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.widget.StarLayout
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener{

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECTED = "{fa-star}";

    private static final int STAR_TOTAL_COUNT = 5;
    private static final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context,null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    @Override
    public void onClick(View view) {

        final IconTextView star = (IconTextView) view;
        //获取 第几个星星
        final  int count = (int) star.getTag(R.id.star_count);
        //获取点击状态
        final boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
        if (!isSelect) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }
    }

    /**
     * 获取 星星的数量
     * @return
     */
    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag(R.id.star_is_select);
            if (isSelect) {
                count ++;
            }
        }
        return count;
    }


    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count,i);
            star.setTag(R.id.star_is_select,false);
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    /**
     * 选中星星
     * @param count
     */
    private void selectStar(int count) {
        for (int i = 0; i <= count; i++) {
            if (i <= count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_SELECTED);
                star.setTextColor(Color.rgb(255,136,0));
                star.setTag(R.id.star_is_select,true);
            }
        }
    }

    /**
     *
     * @param count
     */
    private void unSelectStar(int count) {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            if (i > count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_select,false);
            }
        }
    }
}
