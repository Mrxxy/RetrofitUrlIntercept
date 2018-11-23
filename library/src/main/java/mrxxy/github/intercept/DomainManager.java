package mrxxy.github.intercept;


import okhttp3.HttpUrl;

import java.util.HashMap;
import java.util.Map;

public class DomainManager {

    private static DomainManager instance = null;
    private static final boolean DEPENDENCY_OKHTTP;

    private final Map<String, HttpUrl> domainMap = new HashMap<>();

    static {
        boolean hasDependency;
        try {
            Class.forName("okhttp3.OkHttpClient");
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        DEPENDENCY_OKHTTP = hasDependency;
    }

    public static DomainManager getInstance() {
        if (instance == null) {
            synchronized (DomainManager.class) {
                if (instance == null) {
                    instance = new DomainManager();
                }
            }
        }
        return instance;
    }

    private DomainManager() {
        if (!DEPENDENCY_OKHTTP) {
            throw new IllegalStateException("Must be dependency Okhttp");
        }
    }

    public void putDomain(String domainName, String domainUrl) {
        synchronized (domainMap) {
            domainMap.put(domainName, parseUrl(domainUrl));
        }
    }

    public synchronized HttpUrl getDomain(String domainName) {
        return domainMap.get(domainName);
    }

    private HttpUrl parseUrl(String url) {
        HttpUrl parseUrl = HttpUrl.parse(url);
        if (null == parseUrl) {
            throw new IllegalArgumentException("url can never be null");
        } else {
            return parseUrl;
        }
    }

}