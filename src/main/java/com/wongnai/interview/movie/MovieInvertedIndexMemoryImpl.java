package com.wongnai.interview.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;

@Component
public class MovieInvertedIndexMemoryImpl implements MovieInvertedIndexMemory {

    @Autowired
    private Map<String, HashSet<Long>> invertedIndexMap;

    @Override
    public void insert(String key, Long val) {
        if(invertedIndexMap.containsKey(key)) {
            invertedIndexMap.get(key).add(val);
        } else {
            HashSet<Long> newSet = new HashSet<>();
            newSet.add(val);
            invertedIndexMap.put(key, newSet);
        }
    }

    @Override
    public boolean containsKey(String key) {
        return invertedIndexMap.containsKey(key);
    }

    @Override
    public HashSet<Long> getIdSet(String key) {
        return new HashSet<>(invertedIndexMap.get(key));
    }

}
