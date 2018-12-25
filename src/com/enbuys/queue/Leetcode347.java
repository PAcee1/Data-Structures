package com.enbuys.queue;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: Pace
 * @Data: 2018/12/21 15:32
 * @Version: v1.0
 * 前K个高频元素
 */
public class Leetcode347 {

    public class Frep implements Comparable<Frep>{

        public int num;
        public int frep; //  出现频率

        public Frep(int num, int frep) {
            this.num = num;
            this.frep = frep;
        }

        @Override
        public int compareTo(Frep o) {
            // 判断频率
            if(this.frep > o.frep)
                return -1;
            else if(this.frep < o.frep)
                return 1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // 先使用TreeMap保存每个元素与其频率
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int num : nums){
            if(!map.containsKey(num)){
                // 如果不包含，直接放入
                map.put(num,1);
            }else{ // 包含，频率+1
                map.put(num,map.get(num)+1);
            }
        }

        // 在使用优先队列保存treeMap中的数据
        PriorityQueue<Frep> queue = new PriorityQueue();
        for(int key : map.keySet()){
            if(queue.getSize() < k){ // 如果没有满k容量，随便放入
                queue.enqueue(new Frep(key,map.get(key)));
            }else{ // 需要做判断
                if(map.get(key) > queue.getFront().frep){
                    // 删除队首
                    queue.dequeue();
                    queue.enqueue(new Frep(key,map.get(key)));
                }
            }
        }

        List<Integer> linkedList = new LinkedList<>();
        while (!queue.isEmpty()){
            linkedList.add(queue.dequeue().num);
        }
        return linkedList;
    }
}
