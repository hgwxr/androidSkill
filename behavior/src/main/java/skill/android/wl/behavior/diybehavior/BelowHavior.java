package skill.android.wl.behavior.diybehavior;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author wl
 * @version :
 * @date 2017/5/3
 * @描述
 */

public class BelowHavior  extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = BelowHavior.class.getSimpleName();

    public BelowHavior() {
        super();
    }

    public BelowHavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
       // AppBarLayout.ScrollingViewBehavior
        super.onAttachedToLayoutParams(params);
    }

    @Override
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public int getScrimColor(CoordinatorLayout parent, View child) {
        return super.getScrimColor(parent, child);
    }

    @Override
    public float getScrimOpacity(CoordinatorLayout parent, View child) {
        return super.getScrimOpacity(parent, child);
    }

    @Override
    public boolean blocksInteractionBelow(CoordinatorLayout parent, View child) {
        return super.blocksInteractionBelow(parent, child);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY()+dependency.getHeight());
//        dependency.setTranslationY(child.getY()+child.getHeight());
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
    }

    @Override
    public boolean isDirty(CoordinatorLayout parent, View child) {
        return super.isDirty(parent, child);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll() called with: coordinatorLayout = ["+ ViewCompat.SCROLL_AXIS_HORIZONTAL  + "], child = [" +ViewCompat.SCROLL_AXIS_VERTICAL + "], directTargetChild = ["  + "], target = ["  + "], nestedScrollAxes = [" + nestedScrollAxes + "]");
        switch (nestedScrollAxes) {
            case ViewCompat.SCROLL_AXIS_HORIZONTAL:
                return false;//child 自己处理
           // break;//水平滑动
            case ViewCompat.SCROLL_AXIS_VERTICAL:
              //  break;//竖直滑动
                return true;//parent 处理
        }
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onNestedScrollAccepted() called with: coordinatorLayout = ["  + "], child = ["  + "], directTargetChild = ["  + "], target = ["  + "], nestedScrollAxes = [" + nestedScrollAxes + "]");
        //onStartNestedScroll返回true 时，调用
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

    }
    int offsetTotal = 0;
    boolean scrolling = false;

    public void offset(View child,int dy){
        int old = offsetTotal;
        int top = offsetTotal - dy;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        if (old == offsetTotal){
            scrolling = false;
            return;
        }
        int delta = offsetTotal-old;
        child.offsetTopAndBottom(delta);
        scrolling = true;
    }
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        Log.d(TAG, "onStopNestedScroll() called with: coordinatorLayout = ["  + "], child = ["  + "], target = ["  + "]");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, "onNestedScroll() called with: coordinatorLayout = ["  + "], child = ["  + "], target = ["  + "], dxConsumed = [" + dxConsumed + "], dyConsumed = [" + dyConsumed + "], dxUnconsumed = [" + dxUnconsumed + "], dyUnconsumed = [" + dyUnconsumed + "]");
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
         //child处理onNestedPreScroll的滑动剩余
       // offset(child, dyConsumed);
    }
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        Log.d(TAG, "onNestedPreScroll() called with: coordinatorLayout = [" +child + "], child = [" +target + "], target = ["  + "], dx = [" + dx + "], dy = [" + dy + "], consumed = [" + consumed + "]");
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //child  自己滑动 时调用

    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling() called with: coordinatorLayout = ["  + "], child = ["  + "], target = ["  + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "], consumed = [" + consumed + "]");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.d(TAG, "onNestedPreFling() called with: coordinatorLayout = ["  + "], child = ["  + "], target = ["  + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
      //child 处理惯性滑动
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @NonNull
    @Override
    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, View child, WindowInsetsCompat insets) {
        return super.onApplyWindowInsets(coordinatorLayout, child, insets);
    }

    @Override
    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View child, Rect rectangle, boolean immediate) {
        return super.onRequestChildRectangleOnScreen(coordinatorLayout, child, rectangle, immediate);
    }

    @Override
    public void onRestoreInstanceState(CoordinatorLayout parent, View child, Parcelable state) {
        super.onRestoreInstanceState(parent, child, state);
    }

    @Override
    public Parcelable onSaveInstanceState(CoordinatorLayout parent, View child) {
        return super.onSaveInstanceState(parent, child);
    }

    @Override
    public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull Rect rect) {
        return super.getInsetDodgeRect(parent, child, rect);
    }
}
