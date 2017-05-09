package skill.android.wl.androidskill.widget;

import android.content.Context;
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
    private int width;
    private int height;
    private Scroller scroller;
    private boolean flagStart;
    private View childAt;
    private float distanceY;
    private ViewDragHelper viewDragHelper;

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
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int childCount = getChildCount();
        if (childCount >0) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("ACTION_DOWN)+","asda");

                    //if (flagStart){

                  // }
                    break;
                case MotionEvent.ACTION_MOVE:
                   // viewDragHelper.processTouchEvent(event);
                    //scrollBy(0,(int) (distanceY-(int) (event.getY())));
                    scroller.startScroll (scroller.getCurrX(),scroller.getCurrY(), 0, (int) (distanceY-(int) (event.getY())));
                    distanceY=event.getY();
                    invalidate();
                    Log.d("onInterceptTouchEvent", "onInterceptTouchEvent() called with: ev = ["+" "+flagStart+" "+ (int) (event.getRawY()-distanceY)+"  " + event.getRawY()+" "+event.getY()+" " + "]"+" "+ childAt.getY()+" "+childAt.getScrollY()+" "+ childAt.getHeight());

                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    distanceY=0;
                    break;
            }
        }

        return super.onTouchEvent(event);
    }

  @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {


        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int childCount = getChildCount();
        if (childCount >0) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    distanceY =event.getY();
                    if (childAt==null)
                        childAt = getChildAt(0);
                    if (childAt.getScrollY()==0){
                        flagStart =true;
                        return true;
                    }

                   // Log.d("onInterceptTouchEvent", "onInterceptTouchEvent() called with: ev = [" + event.getRawY()+" "+event.getY()+" " + "]"+" "+ childAt.getY()+" "+ childAt.getScrollY()+" "+ childAt.getHeight());
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    flagStart=false;
                    break;
            }
        }
        return super.dispatchTouchEvent(event);
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
     /*   if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }*/
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
