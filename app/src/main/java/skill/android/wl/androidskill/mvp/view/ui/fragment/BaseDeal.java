package skill.android.wl.androidskill.mvp.view.ui.fragment;

import android.view.View;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public abstract class BaseDeal {
    protected View view;
    public  void attach(View view){
        this.view=view;
    }

    public  void deAttach(){
        view=null;
    }
    public abstract void initView();
    public abstract void dealView();
}
