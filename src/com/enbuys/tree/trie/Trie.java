package com.enbuys.tree.trie;

import java.util.TreeMap;

/**
 * @Author: Pace
 * @Data: 2018/12/25 9:46
 * @Version: v1.0
 */
public class Trie {

    public class Node{
        private Boolean isWord;
        private TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    // 向字典树添加一个新单词
    public void add(String word){
        Node cur = root;
        // 遍历单词，循环添加字符
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            // 判断树中是否有这个单词
            if(cur.next.get(c) == null){ // 没有这个单词
                cur.next.put(c,new Node());
            }
            // 有这个单词，跳向下一个节点
            cur = cur.next.get(c);
        }
        // 遍历完修改isWord为true
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 查询字典树中是否有单词
    public boolean contains(String word){
        Node cur = root;
        // 循环单词中字符
        for(char c : word.toCharArray()){
            // 如果字符不存在，返回false
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        // 遍历完说明字符都存在，判断是否为单词
        return cur.isWord;
    }

    // 查询字典树中是否有前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        // 循环单词中字符
        for(char c : prefix.toCharArray()){
            // 如果字符不存在，返回false
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        // 遍历完说明字符都存在，判断是否为单词
        return true;
    }
}
