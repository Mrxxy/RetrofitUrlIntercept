package mrxxy.github.com.retrofiturlintercept;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/today")
    Call<GankBean> getData();

    @Headers(value = {"domain-name:test"})
    @GET("/v2/book/{id}")
    Call<DoubanBean> getTestData(@Path("id") int id);
}
