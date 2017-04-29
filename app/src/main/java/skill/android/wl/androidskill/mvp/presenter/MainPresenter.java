package skill.android.wl.androidskill.mvp.presenter;

import javax.inject.Inject;

import skill.android.wl.androidskill.inject.api.MainApi;
import skill.android.wl.androidskill.mvp.view.IMainView;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class MainPresenter extends BasePresenter<IMainView> {
    private MainApi mainApi;

    @Inject
    public MainPresenter(MainApi mainApi) {
        this.mainApi = mainApi;
    }
}
