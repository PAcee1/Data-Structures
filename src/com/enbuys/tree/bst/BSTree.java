package com.enbuys.tree.bst;
import com.enbuys.stack.MyStack;
import com.enbuys.stack.Stack;

import javax.swing.tree.TreeNode;
import java.util.*;

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
        size++;
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

    // 二分搜索树前序遍历 非递归
    public void preOrderNR(){
        // 使用栈
        Stack<Node> stack = new MyStack<>();
        // 把root推入stack
        stack.push(root);
        // 遍历栈中的对象
        while(!stack.isEmpty()){
            // 弹出栈顶元素，即root根节点
            Node cur = stack.pop();
            System.out.println(cur.e);

            // 再判断左后节点是否有值，有值推入栈中，这样栈会继续循环
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left !=null){
                stack.push(cur.left);
            }
        }
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

    // 二分搜索树的层次遍历
    public void levelOrder(){
        // 使用队列实现
        Queue<Node> queue = new LinkedList<>();
        // 先放入根节点
        queue.add(root);
        while(!queue.isEmpty()){
            // 获取出队节点
            Node cur = queue.remove();
            System.out.println(cur.e);

            if(cur.left != null){ // 左孩子不为空，入队
                queue.add(cur.left);
            }
            if(cur.right != null){ // 右孩子不为空，入队
                queue.add(cur.right);
            }
        }
    }

    // 获取最小的节点
    public E minElement(){
        if(size == 0)
            throw  new IllegalArgumentException("BSTree is empty");
        return minElement(root).e;
    }
    private Node minElement(Node node){
        if(node.left == null)
            return node;
        return minElement(node.left);
    }

    // 获取最大的节点
    public E maxElement(){
        if(size == 0)
            throw  new IllegalArgumentException("BSTree is empty");
        return maxElement(root).e;
    }
    private Node maxElement(Node node){
        if(node.right == null)
            return node;
        return maxElement(node.right);
    }

    // 删除最小元素
    public E removeMin(){
        E ret = minElement();
        root = removeMin(root);
        return ret;
    }
    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        // 判断节点左子树是否为空，为空说明是最小节点需要删除
        if(node.left == null){
            // 将最小节点的右子树保存，设置为null，返回保存的右子树
            Node rightNode = node.right;
            node.right = null;
            size --;
            // 这里只需返回右子树节点，即二分树与右子树连接，和原先最小节点断开连接
            return rightNode;
        }
        // 将保存的右子树赋值给被删除父节点的左子树，避免最小节点的孩子节点连带删除了
        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大元素
    public E removeMax(){
        E max = maxElement();
        // 删除最大元素并返回删除后的二分搜索树
        root = removeMax(root);
        return max;
    }
    // 删除以node为根的二分搜索树中的最大节点
    private Node removeMax(Node node){
        // 判断是否存在右子树
        if(node.right == null){
            // 不存在右子树，说明当前节点为最大节点，删除，保存当前节点左孩子节点
            Node leftNode = node.left;
            node.left = null;
            size--;
            // 这里只需返回左子树节点，即二分树与左子树连接，和原先最大节点断开连接
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 删除树中元素为e的节点
    public void remove(E e){
        root = remove(root,e);
    }
    // 递归删除树中元素为e的节点
    private Node remove(Node node,E e){
        //判断是否为null
        if(node == null)
            return  null;
        // 查找元素e的位置
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else{ // 找到元素e的位置
            // 判断e节点左子树是否存在
            if(node.left == null){
                // 为null只需将该节点右子树返回即可
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 判断e节点右子树是否存在
            if(node.right == null){
                // 为null只需将该节点左子树返回即可
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 说明e节点左右子树都存在
            // 需要找到节点右子树最小的节点
            // 将该节点顶替被删除节点
            Node newNode = minElement(node.right);
            // 将被删除节点右子树与新节点右子树连接
            newNode.right = removeMin(node.right);
            // 将被删除节点左子树与新节点左子树连接
            newNode.left = node.left;
            // 去除node连接
            node.left = null;
            node.right =null;
            return newNode;
        }
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
