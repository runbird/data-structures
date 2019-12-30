/**
 * 类名： Array <br>
 * 描述： 自定义数组 <br>
 * 创建日期： 2019/12/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Array<E> {
    private E[] data;
    private int size;

    //构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //默认容量10
    public Array() {
        this(10);
    }

    //获取数组容量
    public int getCapacity() {
        return data.length;
    }

    //获取数组中的元素个数
    public int size() {
        return size;
    }

    //返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        data[index] = e;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public int lastIndexOf(E e) {
        return data.length - 1;
    }

    //修改index位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("set failed index is illegal");
        data[index] = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed index is illegal");
        return data[index];
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    //从数组中删除index位置的元素，并返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed index is illegal");
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size --;
        //避免内存溢出
        data[size] =null;
        //自动缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length/2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    //移除元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    //查找元素
    public int find(E e) {
        for (int i = 0; i < data.length; i++) {
            if (e == data[i])
                return i;
        }
        return -1;
    }

    public void swap(int i, int j) {
        if (i < 0 || j < 0 || i > size || j > size)
            throw new IllegalArgumentException("swap failed index is illegal");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
    }
}
