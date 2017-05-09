package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import skill.android.wl.androidskill.R;
import skill.android.wl.androidskill.mvp.view.ui.FleixActivity;
import skill.android.wl.androidskill.mvp.view.ui.ScrollActivity;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class LayoutTypeAlert extends  BaseDeal {

    private Context context;
    private TextView toFleixActivity;

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    private TextView pop;
    @Override
    public void initView() {
        pop=ButterKnife.findById(view, R.id.pop);
        toFleixActivity = ButterKnife.findById(view, R.id.to_scrollView);
    }
    @Override
    public void dealView() {
        if (view==null){
            return;
        }
        toFleixActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,FleixActivity.class);
                activity.startActivity(intent);
            }
        });
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //pop
                alertPop();

//                transPop();
            }
        });

    }

    private void transPop() {
        View contentView;
        LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
        contentView = mLayoutInflater.inflate(R.layout.pop,
                null);
        PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        // 产生背景变暗效果
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
//        pop.setTouchable(true);
       popupWindow.setFocusable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       popupWindow.setOutsideTouchable(true);
       popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
       popupWindow.update();
       popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });

    }

    private void alertPop() {
        View pop = LayoutInflater.from(getActivity()).inflate(R.layout.pop, null);
        PopupWindow popupWindow = new PopupWindow(pop, LinearLayout.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop));
        popupWindow.setContentView(pop);
      //  popupWindow.showAtLocation(this.pop,Gravity.BOTTOM,0,0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(this.pop);
      /*  popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        int[] location = new int[2];
        pop.getLocationOnScreen(location);

//        popupWindow.showAtLocation(pop, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());
        popupWindow.showAsDropDown(pop);*/
    }
}
