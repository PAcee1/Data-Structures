package com.enbuys.tree.segment;

/**
 * @Author: Pace
 * @Data: 2018/12/24 10:58
 * @Version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        /*SegmentTree tree = new SegmentTree(nums, new Merger<Integer>() {
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });*/
        SegmentTree<Integer> tree = new SegmentTree<>(nums,(a, b) -> a + b);
        System.out.println(tree);
        tree.set(0,-1);
        System.out.println(tree.query(0,2));
        /*System.out.println(tree.query(2,5));
        System.out.println(tree.query(0,5));*/
    }
}
