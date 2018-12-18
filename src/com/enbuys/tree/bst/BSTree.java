package com.enbuys.tree.bst;

/**
 * @Author: Pace
 * @Data: 2018/12/18 11:19
 * @Version: v1.0
 * 二分搜索树
 */
// 其泛型需要可比较性
public class BSTree<E extends Comparable<E>> {

    // 节点
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size ;

    public BSTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    // 添加节点
    public void add(E e){
        root = add(root,e);
    }

    // 递归添加子节点
    private Node add(Node node,E e){
        // 判断节点是否为null，为null赋值并返回
        if(node == null){
            return new Node(e);
        }

        //节点不为空，该节点存在左子树或右子树，向下层递归，
        if(e.compareTo(node.e) > 0){ // 大于，放在右子树
            node.right = add(node.right ,e);
        }else if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }
        return node;
    }

    // 判断是否包含某个元素
    public boolean contains(E e){
        return contains(root,e);
    }
    // 递归查询
    private boolean contains(Node node,E e){
        // 如果node为空，返回false
        if(node == null){
            return false;
        }

        // 判断是否相等，相等返回true，不等再判断大或小，大了递归右子树，小了递归左子树
        if(e.compareTo(node.e) == 0) {
            return true;
        }else if(e.compareTo(node.e) > 0){ // 大了 递归右子树
            return contains(node.right ,e);
        }else{ // 小了，递归左子树
            return contains(node.left ,e);
        }
    }

    // 二叉树前序遍历
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node ){
        if(node == null){
            return;
        }
        System.out.println(node.e); // 先输出当前节点
        preOrder(node.left); // 再遍历左子树
        preOrder(node.right); // 最后遍历右子树
    }

    // 二叉树中序遍历 ， 中序遍历的结果是顺序的
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left); // 先找到最左的子树节点
        System.out.println(node.e); // 打印
        inOrder(node.right); // 最后打印右子树节点
    }

    // 二叉树后序遍历 ，应用于内存释放，先释放孩子节点，再处理节点本身
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left); // 先找到最左的子树节点
        postOrder(node.right); // 再打印右子树节点
        System.out.println(node.e); // 最后打印根节点
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }
}
