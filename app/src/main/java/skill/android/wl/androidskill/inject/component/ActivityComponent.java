package skill.android.wl.androidskill.inject.component;

import dagger.Component;
import skill.android.wl.androidskill.MainActivity;
import skill.android.wl.androidskill.api.ApiService;
import skill.android.wl.androidskill.inject.ActivityScope;
import skill.android.wl.androidskill.inject.model.ActivityModel;

/**
 * @author wl
 * @version :
 * @date 2017/4/29
 * @描述
 */
@ActivityScope
@Component(modules = {ActivityModel.class},dependencies = {ApiServiceComponent.class})
public interface ActivityComponent {
    void  inject (MainActivity mainActivity);
}
