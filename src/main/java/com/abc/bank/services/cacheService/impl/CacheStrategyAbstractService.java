package com.abc.bank.services.cacheService.impl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Diluni on 25/08/20.
 */
public abstract class CacheStrategyAbstractService {

    public CacheStrategyAbstractService() {
    }

    public abstract void putObject(Object key);

    public abstract Object getKeyToRemove();

    public Map<Object, Long> sortMap(Map<Object, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
