package cn.peyton.android.latte.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * <h3></h3>
 * <pre>
 * 作者 <a href="http://www.peyton.cn">peyton</a>
 * 邮箱 <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 完整类名 cn.peyton.android.latte.core.widget.CircleTextView
 * 项目名称 FastEC
 * 创建时间 2017/12/16 12:15
 * 版本 1.0.0
 * </pre>
 */
public class CircleTextView extends AppCompatTextView{
    /** 背景画笔*/
    private final Paint PAINT;
    /**   */
    private final PaintFlagsDrawFilter FILTER;

    public CircleTextView(Context context) {
        this(context,null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        PAINT = new Paint();
        FILTER = new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG |Paint.FILTER_BITMAP_FLAG);
        PAINT.setColor(Color.WHITE);
        PAINT.setAntiAlias(true);
    }

    /**
     * 设置背景颜色
     * @param color
     */
    public final void setCircleBackground(@ColorInt int color) {
        PAINT.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final int height = getMaxHeight();
        final int max = Math.max(width, height);
        setMeasuredDimension(max,max);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(FILTER);
        canvas.drawCircle(getWidth()/2,getHeight()/2,Math.max(getWidth(),getHeight())/2,PAINT);
        super.draw(canvas);
    }
}
