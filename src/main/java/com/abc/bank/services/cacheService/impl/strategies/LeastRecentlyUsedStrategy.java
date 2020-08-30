package com.abc.bank.services.cacheService.impl.strategies;

import com.abc.bank.services.cacheService.impl.CacheStrategyAbstractService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Diluni on 25/08/20.
 */
@Service
public class LeastRecentlyUsedStrategy extends CacheStrategyAbstractService {

    //Least Recently Used Strategy Map
    public Map<Object, Long> lruMap = new HashMap<>();

    @Override
    public void putObject(Object k) {
        lruMap.put(k, System.nanoTime());
    }

    @Override
    public Object getKeyToRemove() {
        Object key = sortMap(lruMap).entrySet().stream().findFirst().get().getKey();
        lruMap.remove(key);
        return key;
    }

}
