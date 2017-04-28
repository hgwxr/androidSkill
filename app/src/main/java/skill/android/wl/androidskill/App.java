package skill.android.wl.androidskill;

import android.app.Application;
import android.content.Context;

/**
 * @author wl
 * @version :
 * @date 2017/4/28
 * @描述
 */

public class App extends Application {
    public static Context mContex;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static App getApp(){
        return ((App) mContex.getApplicationContext());
    }
}
