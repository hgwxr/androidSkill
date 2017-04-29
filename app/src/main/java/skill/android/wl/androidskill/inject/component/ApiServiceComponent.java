package skill.android.wl.androidskill.inject.component;

import javax.inject.Singleton;

import dagger.Component;
import skill.android.wl.androidskill.MainActivity;
import skill.android.wl.androidskill.inject.api.MainApi;
import skill.android.wl.androidskill.inject.model.ApiServiceModel;
import skill.android.wl.androidskill.inject.model.AppModel;

/**
 * @author wl
 * @version :
 * @date 2017/4/29
 * @描述
 */
@Component(modules = {ApiServiceModel.class})
public interface ApiServiceComponent {
    MainApi provideMainApi();

}
