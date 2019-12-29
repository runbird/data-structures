/**
 * @desc:
 * @author: Suocaiyuan
 * @date: 2019-12-29 17:45
 **/
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    //接受用户传进来的数组
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    //在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //左右为同一个区间
        if (l == r) {
            // = data[r]
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //预防 l+r 值溢出
        int mid = l + (r - l) / 2;

        //先创建 左右子树再进行 merge
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //根据业务含义定义merge
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //返回区间[queryL,queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryR > data.length ||
                queryL > data.length || queryR < 0 || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //在treeIndex为根的线段树中[l...r]的范围里,搜索区间[queryL,queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == r && queryL == queryR) {
            return tree[treeIndex];
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }


    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("the index is illegla");
        return data[index];
    }

    //获取完全二叉树 左子树索引
    private int leftChild(int treeIndex) {
        return 2 * treeIndex + 1;
    }

    private int rightChild(int treeIndex) {
        return 2 * treeIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                sb.append(tree[i]);
            else
                sb.append("null");
            if (i != tree.length - 1)
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
