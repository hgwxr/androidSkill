package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import skill.android.wl.androidskill.R;
import skill.android.wl.androidskill.mvp.view.ui.ScrollActivity;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class LayoutTypeDeal extends  BaseDeal {

    private Context context;
    private PtrClassicDefaultHeader ptr;

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    private TextView tv;
    @Override
    public void initView() {
        tv=ButterKnife.findById(view, R.id.tv);
        ptr = ButterKnife.findById(view, R.id.ptr);

    }
    @Override
    public void dealView() {
        if (view==null){
            return;
        }
          tv.setText("LayoutTypeDeal");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((Activity) getContext()), ScrollActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
