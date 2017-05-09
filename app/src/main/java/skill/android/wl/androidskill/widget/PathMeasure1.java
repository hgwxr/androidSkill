package skill.android.wl.androidskill.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import skill.android.wl.androidskill.utils.DimensUtils;

/**
 * @author wl
 * @version :
 * @date 2017/5/5
 * @描述
 */

public class PathMeasure1 extends View{
    private Paint paint;
    private Context context;
    private Path path;
    private int width;
    private int height;
    private float length;
    private float itemLength;
    private Path srcPath;

    public PathMeasure1(Context context) {
        this(context,null);
    }

    public PathMeasure1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasure1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DimensUtils.dipToPx(context,1));
        path = new Path();
        srcPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        path.moveTo(DimensUtils.dipToPx(context,5),DimensUtils.dipToPx(context,5));
        path.lineTo(width-DimensUtils.dipToPx(context,5),DimensUtils.dipToPx(context,5));
        path.lineTo(width-DimensUtils.dipToPx(context,5),height-DimensUtils.dipToPx(context,5));
        path.lineTo(DimensUtils.dipToPx(context,5),height-DimensUtils.dipToPx(context,5));
        path.lineTo(DimensUtils.dipToPx(context,5),DimensUtils.dipToPx(context,5));
        final PathMeasure pathMeasure = new PathMeasure(path,true);
        length = pathMeasure.getLength();
        itemLength = length/100;
        ValueAnimator animator = ValueAnimator.ofInt(0, 100).setDuration(2000);
        animator
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer animatedValue = (Integer) animation.getAnimatedValue();
                        pathMeasure.getSegment(0,itemLength*animatedValue,srcPath,true);
                        invalidate();
                    }
                });
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(srcPath,paint);
    }
}
