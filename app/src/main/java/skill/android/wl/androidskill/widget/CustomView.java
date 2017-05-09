package skill.android.wl.androidskill.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author wl
 * @version :
 * @date 2017/5/8
 * @描述
 */

public class CustomView extends LinearLayout {

    private static final String TAG = "Scroller";

    private Scroller mScroller;
    private int lastX;
    private int lastY;
    private int currentX;
    private int currentY;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                lastX =(int)event.getX();
                lastY =(int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX =(int)event.getX();
                currentY = ((int) event.getY());
                mScroller.startScroll(lastX,lastY,currentX,currentY);
                scrollBy(lastX-currentX,lastY=currentY);
                lastX=currentX;
                lastY=currentY;
                invalidate();
                Log.d(TAG, "onTouchEvent() called with: event = [" + lastX + "]");
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastX=0;
                lastY=0;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {

        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }
}
