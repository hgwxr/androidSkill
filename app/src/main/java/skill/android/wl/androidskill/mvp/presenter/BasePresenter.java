package skill.android.wl.androidskill.mvp.presenter;

import skill.android.wl.androidskill.mvp.view.IBaseView;
import skill.android.wl.androidskill.mvp.view.ui.BaseActivity;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class BasePresenter<V extends IBaseView> {

    protected  V mView;
    public void attach(V iBaseView) {
       mView=  iBaseView;
    }

    public void deAttach() {
        mView=null;
    }


}
