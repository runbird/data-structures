package com.scy.tree;

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
    public void afterOrder() {
        afterOrder(root);
    }

    private void afterOrder(Node node) {
        if (node == null) return;
        afterOrder(node.left);
        afterOrder(node.right);
        System.out.println(node.e);
    }
}
