package com.abc.bank.services.cacheService.impl;

import com.abc.bank.enumValues.enumValues;
import com.abc.bank.services.bankService.AbcBankApplicationService;
import com.abc.bank.services.cacheService.CacheMemoryService;
import com.abc.bank.services.cacheService.impl.strategies.LeastFrequentlyUsedStrategy;
import com.abc.bank.services.cacheService.impl.strategies.LeastRecentlyUsedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Diluni on 25/08/20.
 */
@Service
public class CacheMemoryImpl implements CacheMemoryService {

    //Cache Memory Map
    private Map<Object, Object> cacheMap = new HashMap<>();

    //Max size of cache memory map
    private static Integer cacheMapSize;

    //Eviction strategy type
    private static String strategy;

    @Autowired
    private LeastFrequentlyUsedStrategy leastFrequentlyUsedCacheStrategy;

    @Autowired
    private LeastRecentlyUsedStrategy leastRecentlyUsedCacheStrategy;

    @Autowired
    private AbcBankApplicationService abcBankApplicationService;

    @Override
    public void configureCache(Integer maxSize, String strategy) {
        this.cacheMapSize = maxSize;
        this.strategy = strategy;
    }

    public void putCache(Object k) {
        if (cacheMap.size() == cacheMapSize) {
            removeCache(k);
        }
        Map newRecord = abcBankApplicationService.getTransSummeryByCustomerId((Long) k);
        cacheMap.putAll(newRecord);
    }

    @Override
    public void clearAllCache() {
        cacheMap.clear();
    }

    public void checkCache(Object k) {
        if (!cacheMap.containsKey(k)) {
            putCache(k);
        }
    }

    @Override
    public Map<Object, Object> getCache(Object k) {
        checkCache(k);

        if (strategy.equals(enumValues.Strategies.LFU.toString())) {
            leastFrequentlyUsedCacheStrategy.putObject(k);
        } else {
            leastRecentlyUsedCacheStrategy.putObject(k);
        }
        return cacheMap.entrySet().stream()
                .filter(mapElement -> mapElement.getKey() == k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void removeCache(Object k) {
        Object removeKey = (strategy.equals(enumValues.Strategies.LFU.toString()) ? leastFrequentlyUsedCacheStrategy.getKeyToRemove() : leastRecentlyUsedCacheStrategy.getKeyToRemove());
        cacheMap.remove(removeKey);
    }

}
