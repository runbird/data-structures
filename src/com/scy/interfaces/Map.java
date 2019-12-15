package com.scy.interfaces;

/**
 * 类名： Map <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public interface Map<K,V> {
    int size();

    boolean containsKey(K key);

    V get(K key);

}
