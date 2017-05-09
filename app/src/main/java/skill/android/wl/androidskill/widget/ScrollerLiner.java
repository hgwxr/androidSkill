package skill.android.wl.androidskill.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.init;

/**
 * @author wl
 * @version :
 * @date 2017/5/8
 * @描述
 */

public class ScrollerLiner  extends ViewGroup {
    private  Context context;
    private int startX;

    public ScrollerLiner(Context context) {
        super(context);
        init(context);
    }



    public ScrollerLiner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerLiner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount>=0){
            for (int i=0;i<childCount;i++) {
                View childAt = getChildAt(i);
                childAt.layout(childAt.getLeft(),childAt.getTop(),childAt.getRight(),childAt.getBottom());
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int currX = (int) event.getX();
                scrollBy(currX-startX,0);
                startX= currX;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                    break;
        }
        return super.onTouchEvent(event);
    }
}
