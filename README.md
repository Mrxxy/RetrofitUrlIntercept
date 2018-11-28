## RetrofitUrlIntercept
---
### 让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl

## Download
### Step1
在项目级build.gradle文件添加
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
### Step2
```
implementation 'com.github.Mrxxy:RetrofitUrlIntercept:1.0.0'
```

## Usage

### Step1
```
OkHttpClient httpClient = new OkHttpClient.Builder()
        .addInterceptor(new BaseUrlIntercept())
        .build();
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://gank.io/")
        .client(httpClient)
        .build();
```

### Step2
```
DomainManager.getInstance().putDomain("douban", "https://api.douban.com");
```

### Step3
```language
public interface ApiService {
     @Headers({"Domain-Name: douban"}) // Add the Domain-Name header
     @GET("/v2/book/{id}")
     Observable<ResponseBody> getBook(@Path("id") int id);
}
```
