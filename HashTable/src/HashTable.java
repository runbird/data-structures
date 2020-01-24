import java.util.TreeMap;

/**
 * 类名： HashTable <br>
 * 描述：Object已经实现了hashcode，故K不需要再实现 <br>
 * 创建日期： 2020/1/23 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class HashTable<K, V> {


    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    // private static final int initCapacity = 7;
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

//    public HashTable(int m) {
//        this.M = m;
//        this.size = 0;
//        this.hashtable = new TreeMap[M];
//        for (int i = 0; i < hashtable.length; i++)
//            hashtable[i] = new TreeMap<>();
//    }

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        this.hashtable = new TreeMap[M];
        for (int i = 0; i < hashtable.length; i++)
            hashtable[i] = new TreeMap<>();
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
            //防止capacityIndex 数组下标越界
            if (size >= M * upperTol && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                //resize(M / 2);
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < M * lowerTol && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            throw new IllegalArgumentException(key + " is not exsist");
        }
    }

    public boolean contain(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        //1. resize m -> newM
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        //2. change M
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }
}
