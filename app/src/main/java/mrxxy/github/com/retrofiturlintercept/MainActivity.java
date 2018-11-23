package mrxxy.github.com.retrofiturlintercept;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import mrxxy.github.intercept.DomainManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = AppApplication.application.getRetrofit();
        apiService = retrofit.create(ApiService.class);
        DomainManager.getInstance().putDomain("test", "https://api.douban.com");
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiService.getData().enqueue(new Callback<GankBean>() {
            @Override
            public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                Log.d(TAG, "onResponse gank io: success");
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void test(View view) {
        apiService.getTestData(1220562).enqueue(new Callback<DoubanBean>() {
            @Override
            public void onResponse(Call<DoubanBean> call, Response<DoubanBean> response) {
                Log.d(TAG, "onResponse douban : success");
            }

            @Override
            public void onFailure(Call<DoubanBean> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }
}