package skill.android.wl.androidskill.inject.model;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import skill.android.wl.androidskill.App;
import skill.android.wl.androidskill.BuildConfig;
import skill.android.wl.androidskill.inject.api.MainApi;

/**
 * @author wl
 * @version :
 * @date 2017/4/29
 * @描述
 */

@Module
public class ApiServiceModel {
    public  static final String BASE_URL="http://www.tngou.net/";

    @Provides
    MainApi provideMainApi1() {
        return createService(MainApi.class);
    }

    public  <S> S createService(Class<S> sClass) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(provideOkHttpClient());
        return (S) builder.build().create(sClass);
    }

    private OkHttpClient provideOkHttpClient() {
        Cache cache = new Cache(App.getApp().getCacheDir(), 10240 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        builder.addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        return builder.build();
    }

    private  class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                    .build();
        }
    }
}
