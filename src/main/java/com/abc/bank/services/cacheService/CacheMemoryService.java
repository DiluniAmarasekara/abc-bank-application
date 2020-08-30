package com.abc.bank.services.cacheService;

import java.util.Map;

/**
 * Created by Diluni on 25/08/20.
 */
public interface CacheMemoryService {

    Map<Object, Object> getCache(Object k);

    void clearAllCache();

    void configureCache(Integer maxSize, String strategy);

}
