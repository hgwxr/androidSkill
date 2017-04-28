package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.widget.TextView;

import butterknife.ButterKnife;
import skill.android.wl.androidskill.R;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class LayoutType1Deal extends BaseDeal {
    private TextView tv1;
    @Override
    public void initView() {
        tv1= ButterKnife.findById(view, R.id.tv1);
    }
    @Override
    public void dealView() {
        if (view==null){
            return;
        }
        tv1.setText("LayoutType1Deal");
    }
}
