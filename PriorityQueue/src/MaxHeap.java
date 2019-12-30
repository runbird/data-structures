/**
 * 类名： MaxHeap <br>
 * 描述：最大堆 <br>
 * 创建日期： 2019/12/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回索引位置的父节点
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have element");
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return (index * 2) + 2;
    }

    //向堆中添加元素
    public void add(E e) {
        int lastIndex = data.lastIndexOf(e);
        //元素是否上浮
        siftUp(lastIndex);
    }
    //上浮方法
    private void siftUp(int index) {
        // 不处于根节点 && 当前节点位置值大于父亲节点
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
        }
    }
}
