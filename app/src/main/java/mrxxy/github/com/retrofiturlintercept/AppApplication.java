package mrxxy.github.com.retrofiturlintercept;

import android.app.Application;
import mrxxy.github.intercept.BaseUrlIntercept;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoyichao
 * @date 2018/11/23
 * @function
 */
public class AppApplication extends Application {

    public static AppApplication application;

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new BaseUrlIntercept())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
