package skill.android.wl.androidskill.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wl
 * @version :
 * @date 2017/5/10
 * @描述
 */

public class BesierView  extends View {
    public BesierView(Context context) {
        this(context,null);
    }

    public BesierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BesierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
}
