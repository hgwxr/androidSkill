package skill.android.wl.androidskill.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * @author wl
 * @version :
 * @date 2017/5/8
 * @描述
 */

public class FleixViewGroup extends FrameLayout {
    private static final String TAG = FleixViewGroup.class.getSimpleName();
    private int width;
    private int height;
    private Scroller scroller;
    private boolean flagStart;
    private View childAt;
    private float distanceY;
    private ViewDragHelper viewDragHelper;
    private boolean flagStart1;
    private float flagStart2;
    private Rect rect;

    public FleixViewGroup(@NonNull Context context) {
        super(context,null);
        init(context);
    }

    public FleixViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public FleixViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FleixViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = getChildAt(0);
        rect = new Rect();
        rect.set(childAt.getLeft(),childAt.getTop(),childAt.getRight(),childAt.getBottom());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: " );
        boolean b=false;
        if (this.getParent()!=null) {
           b = !(this.getParent() instanceof FleixViewGroup);
            Log.e(TAG, "requestDisallowInterceptTouchEvent: "+b );
           requestDisallowInterceptTouchEvent(b);

        }else {
        }
        Log.e(TAG, "onTouchEvent() called with: event = [");
        int childCount = getChildCount();
       // if (childCount >0) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("ACTION_DOWN)+","asda");
                    distanceY=event.getRawY();
                    flagStart2 = distanceY;
                    break;
                case MotionEvent.ACTION_MOVE:
                   // viewDragHelper.processTouchEvent(event);
                    int rawY = (int) (event.getRawY());
                    scrollBy(0,(int) (distanceY- rawY));  //滑动child
                   // scrollViewGroup();
                   // scroller.startScroll (scroller.getCurrX(),scroller.getCurrY(), 0, (int) (distanceY-(int) (event.getY())));
                    distanceY=rawY;
                    //invalidate();
//                    Log.d("onInterceptTouchEvent", "onInterceptTouchEvent() called with: ev = ["+" "+flagStart+" "+ (int) (event.getRawY()-distanceY)+"  " + event.getRawY()+" "+event.getY()+" " + "]"+" "+ childAt.getY()+" "+childAt.getScrollY()+" "+ childAt.getHeight());

                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    distanceY=0;
                    //scrollTo(rect.left,rect.top);
                    scroller.startScroll(scroller.getCurrX(),scroller.getCurrY(),0,rect.top-scroller.getCurrY());
                    invalidate();
                    flagStart2=0;
                    break;
            }
       // }
        return b;
    }

    private void scrollViewGroup() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        Log.e(TAG, "requestDisallowInterceptTouchEvent: "+disallowIntercept );
            super.requestDisallowInterceptTouchEvent(disallowIntercept);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent() called with: ev =  ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //先判断mScroller滚动是否完成
        if (scroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
