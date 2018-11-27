package mrxxy.github.intercept;

import android.text.TextUtils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;


public class BaseUrlIntercept implements Interceptor {
    private static final String TAG = BaseUrlIntercept.class.getSimpleName();
    private static final String DOMAIN_NAME = "domain-name";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request == null) {
            return null;
        }

        String domainName = obtainDomain(request);
        if (TextUtils.isEmpty(domainName)) {
            return chain.proceed(request);
        }

        Request.Builder newBuilder = request.newBuilder();
        HttpUrl httpUrl = request.url();
        HttpUrl targetDomain = DomainManager.getInstance().getDomain(domainName);
        if (targetDomain != null) {
            HttpUrl newUrl = httpUrl.newBuilder()
                    .scheme(targetDomain.scheme())
                    .host(targetDomain.host())
                    .port(targetDomain.port()).build();
            return chain.proceed(newBuilder.url(newUrl).build());
        }
        return chain.proceed(request);
    }

    private String obtainDomain(Request request) {
        List<String> headers = request.headers(DOMAIN_NAME);
        if (headers == null || headers.size() == 0) {
            return null;
        }
        if (headers.size() > 1) {
            throw new IllegalArgumentException("each request, max domain size is 1");
        }
        return request.header(DOMAIN_NAME);
    }

}