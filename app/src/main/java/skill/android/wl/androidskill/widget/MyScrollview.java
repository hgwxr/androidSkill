package skill.android.wl.androidskill.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author wl
 * @version :
 * @date 2017/5/11
 * @描述
 */

public class MyScrollview  extends ScrollView {
    private static final String TAG = MyScrollview.class.getSimpleName();
    private int height;
    private boolean isScrolledToTop;
    private boolean isScrolledToBottom;
    private float startY;

    public MyScrollview(Context context) {
        this(context,null);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
    }



    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop()-getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=
               // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
        }
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //if    滚动到 顶部 或者  底部   有 父容器接管事件
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                requestDisallowInterceptTouchEvent(false);
            case MotionEvent.ACTION_MOVE:
              /* Log.e("FleixViewGroup", "onTouchEvent: "+ isScrolledToBottom+ " "+isScrolledToTop);
               if ((isScrolledToBottom&&!isScrolledToTop )||(!isScrolledToBottom&&isScrolledToTop) ){//顶部或者底部

                    if (ev.getY()-startY!=0) {
                        Log.e(TAG, "onTouchEvent: "+ isScrolledToBottom+ " "+isScrolledToTop);

                        requestDisallowInterceptTouchEvent(false);
                        return false;
                    }else{
                        requestDisallowInterceptTouchEvent(true);
                    }
               }*/
             /*     }else{
                    requestDisallowInterceptTouchEvent(true);
                }*/
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startY=0;
//                requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void init() {

    }
}
