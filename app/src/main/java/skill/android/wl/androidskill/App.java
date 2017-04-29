package skill.android.wl.androidskill;

import android.app.Application;
import android.content.Context;

import skill.android.wl.androidskill.inject.component.ApiServiceComponent;
import skill.android.wl.androidskill.inject.component.DaggerApiServiceComponent;
import skill.android.wl.androidskill.inject.model.ApiServiceModel;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class App extends Application {
    public static Context mContex;
    private ApiServiceComponent apiServiceComponent;

    @Override
    public void onCreate() {
        mContex=this;
        super.onCreate();
        apiServiceComponent = DaggerApiServiceComponent.builder().apiServiceModel(new ApiServiceModel()).build();

    }

    public ApiServiceComponent getApiServiceComponent() {
        return apiServiceComponent;
    }

    public static App getApp(){
        return ((App) mContex.getApplicationContext());
    }
}
