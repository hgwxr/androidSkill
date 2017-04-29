package skill.android.wl.androidskill.mvp.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import skill.android.wl.androidskill.App;
import skill.android.wl.androidskill.inject.component.ActivityComponent;
import skill.android.wl.androidskill.inject.component.DaggerActivityComponent;
import skill.android.wl.androidskill.inject.model.ActivityModel;
import skill.android.wl.androidskill.mvp.presenter.BasePresenter;
import skill.android.wl.androidskill.mvp.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;
    private ActivityComponent build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPresenter=
        build = DaggerActivityComponent.builder().activityModel(new ActivityModel()).apiServiceComponent(App.getApp().getApiServiceComponent()).build();

        initPresenter(build);
        mPresenter.attach(this);
    }


    protected abstract void initPresenter(ActivityComponent build) ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.deAttach();
    }
}
