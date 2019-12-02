package com.scy.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 类名： BST <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class BST<E extends Comparable<E>> {

    private int size;
    private Node root;

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    public BST() {
        this.size = 0;
        this.root = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //新增
    public void add(E e) {
        root = add(e, root);
    }

    private Node add(E e, Node node) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) > 0) {
            node.right = add(e, node.right);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(e, node.left);
        }
        return node;
    }

    public boolean contain(E e) {
        return contain(e, root);
    }

    private boolean contain(E e, Node node) {
        //  return node == null ? null : node;
        if (node.e == null) return false;
        if (e.compareTo(node.e) > 0) {
            return contain(e, node.right);
        } else if (e.compareTo(node.e) < 0) {
            return contain(e, node.left);
        } else {
            return contain(e, node);
        }
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //前序遍历非递归
    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
    }

    //层序遍历
    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(Node node) {
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //获取BST最小值
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("empty BST");
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //获取BST最大值
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("empty BST");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    //删除最小元素
    public E removeMin() {
        E minimum = minimum();
        root = removeMinimum(root);
        return minimum;
    }

    //删除二分搜索树中最小值
    //并返回删除之后的BST根
    private Node removeMinimum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    //删除最大元素
    public E removeMax() {
        E maximum = maximum();
        root = removeMaximum(root);
        return maximum;
    }

    //删除二分搜索树中最大值
    //并返回删除之后的BST根
    private Node removeMaximum(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMaximum(node.right);
        return node;
    }

    //从BST中删除元素
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else {

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
                // 待删除节点右子树为空的情况
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                // 左右子树都不为空，取后继节点successor（右子树最小值  min(left)）
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node);
                successor.right = removeMinimum(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }
    }
}
