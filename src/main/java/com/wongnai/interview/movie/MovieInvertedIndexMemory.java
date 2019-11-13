package com.wongnai.interview.movie;


import java.util.HashSet;

public interface MovieInvertedIndexMemory {
    void insert(String key, Long val);
    boolean containsKey(String key);
    HashSet<Long> getIdSet(String key);
}
