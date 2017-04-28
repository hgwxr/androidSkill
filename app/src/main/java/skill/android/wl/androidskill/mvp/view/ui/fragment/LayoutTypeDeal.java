package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import skill.android.wl.androidskill.R;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class LayoutTypeDeal extends  BaseDeal {

    private TextView tv;
    @Override
    public void initView() {
        tv=ButterKnife.findById(view, R.id.tv);
    }
    @Override
    public void dealView() {
        if (view==null){
            return;
        }
          tv.setText("LayoutTypeDeal");
    }
}
