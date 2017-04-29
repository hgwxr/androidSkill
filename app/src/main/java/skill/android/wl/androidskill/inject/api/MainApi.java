package skill.android.wl.androidskill.inject.api;

import retrofit2.http.GET;
import rx.Observable;
import skill.android.wl.androidskill.inject.api.bean.CaipuBase;

/**
 * @author wl
 * @version :
 * @date 2017/4/14
 * @描述
 */

public interface MainApi {
    //http://www.tngou.CaipuBasenet/api/cook/classify
    @GET("api/cook/classify")
    Observable<CaipuBase> getCaiPuBase();
}
