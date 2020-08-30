package com.abc.bank.services.cacheService.impl.strategies;

import com.abc.bank.services.cacheService.impl.CacheStrategyAbstractService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Diluni on 25/08/20.
 */
@Service
public class LeastFrequentlyUsedStrategy extends CacheStrategyAbstractService {

    //Least Frequently Used Strategy Map
    public Map<Object, Long> lfuMap = new HashMap<>();

    @Override
    public void putObject(Object k) {
        Long frequency = Long.valueOf(1);
        if (lfuMap.containsKey(k)) {
            frequency = lfuMap.get(k) + 1;
        }
        lfuMap.put(k, frequency);
    }

    @Override
    public Object getKeyToRemove() {
        Object key = sortMap(lfuMap).entrySet().stream().findFirst().get().getKey();
        lfuMap.remove(key);
        return key;
    }

}
