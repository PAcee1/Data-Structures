package com.enbuys.map;

/**
 * @Author: Pace
 * @Data: 2018/12/19 17:36
 * @Version: v1.0
 */
public interface Map<K,V> {
    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    int getSize();
    boolean isEmpty();
    void set(K key,V value);
}
