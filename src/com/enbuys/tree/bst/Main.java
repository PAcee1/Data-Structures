package com.enbuys.tree.bst;

import java.util.ArrayList;

/**
 * @Author: Pace
 * @Data: 2018/12/18 11:51
 * @Version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        //bst.levelOrder();
//        System.out.println(bst.removeMin());
//        System.out.println(bst.removeMax());
        bst.remove(3);
        System.out.println(bst);
    }
}
