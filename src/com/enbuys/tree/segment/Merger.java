package com.enbuys.tree.segment;

/**
 * @Author: Pace
 * @Data: 2018/12/24 10:54
 * @Version: v1.0
 */
public interface Merger<E> {
    E merge(E a, E b);
}
