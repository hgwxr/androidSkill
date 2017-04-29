package skill.android.wl.androidskill.inject.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import skill.android.wl.androidskill.App;

/**
 * @author wl
 * @version :
 * @date 2017/4/29
 * @描述
 */

@Module
public class AppModel {
    private App application;

    public AppModel(App application){
        this.application = application;
    }

    @Provides
    @Singleton
    public App provideApplication(){
        return application;
    }
}
