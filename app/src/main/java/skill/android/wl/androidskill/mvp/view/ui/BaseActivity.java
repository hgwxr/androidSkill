package skill.android.wl.androidskill.mvp.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import skill.android.wl.androidskill.mvp.presenter.BasePresenter;
import skill.android.wl.androidskill.mvp.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=initPresenter();
        mPresenter.attach(this);
    }


    protected abstract T initPresenter() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.deAttach();
    }
}
