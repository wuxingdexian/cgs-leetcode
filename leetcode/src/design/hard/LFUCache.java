package design.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/lfu-cache/description/" />
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LFUCache cache = new LFUCache( 2 /* capacity * / );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 * <p>
 * 0. 本质：函数映射
 * 1. 建模：
 * 注意：相同key重复put，在LRU看来，该key属于最近使用，相当于put并get了一次，所以要刷新其时间戳和访问次数
 *
 * 这里相当于两层关系：
 * （1）第一层为LFU，<访问次数, 节点列表> 的有序对
 * （2）第二层为LRU，节点列表 -> 规整为 <时间戳, 节点> 的有序对
 *
 * 数据结构考虑：
 * （1）每层都需要设计排序，
 * （2）既然是缓存，借助Map是很自然的选择
 * 这里选择TreeMap，默认按照key自然排序
 *
 * 但是这里TreeMap的创建、销毁也是比较耗费性能
 *
 * 同时题设要求在O(1)时间复杂度做到，这个TreeMap的调整也做不到啊
 *
 * 看其他人使用链表来实现，链表也能完全做到O(1)时间复杂度啊。但是会比TreeMap快
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 *
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 29/08/2018
 * @see
 * @since cgs-leetcode on  29/08/2018
 */
public class LFUCache {

    class Lfu {
        int key;
        int value;
        int time;
        int frequency = 0;

        public Lfu(int key, int value, int time) {
            this.key = key;
            this.value = value;
            this.time = time;
        }
    }

    // FIXME: 29/08/2018 这里反复删除和创建map， 性能不好
    Map<Integer/*key*/, Lfu> key2lfu = new HashMap<>();
    TreeMap<Integer/*frequency*/, TreeMap<Integer/*time*/, Lfu>> frequency2timeMap = new TreeMap<>();

    int globalTime = 0;
    int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Lfu lfu = key2lfu.get(key);
        if (lfu == null) {
            return -1;
        }

        TreeMap<Integer, Lfu> lfuTreeMap = frequency2timeMap.get(lfu.frequency);
        lfuTreeMap.remove(lfu.time);
        // 没有元素了则删除
        if (lfuTreeMap.isEmpty()) {
            frequency2timeMap.remove(lfu.frequency);
        }

        /* 按照频次重新防止 */
        lfu.frequency++;
        lfu.time = globalTime++;
        TreeMap<Integer, Lfu> treeMap = frequency2timeMap.getOrDefault(lfu.frequency, new TreeMap<>());
        treeMap.put(lfu.time, lfu);
        frequency2timeMap.put(lfu.frequency, treeMap);

        return lfu.value;
    }

    public void put(int key, int value) {
        if (capacity < 1) {
            return;
        }

        if (key2lfu.containsKey(key)) {
            update(key, value);
            return;
        }

        if (key2lfu.size() < capacity) {
            doPut(key, value);
        } else {
            remove();
            doPut(key, value);
        }
    }

    // 如果存在，则更新里面的time, value, frequency
    private void update(int key, int value) {

        Lfu lfu = key2lfu.get(key);
        // remove
        TreeMap<Integer, Lfu> lfuTreeMap = frequency2timeMap.get(lfu.frequency);
        lfuTreeMap.remove(lfu.time);
        if (lfuTreeMap.isEmpty()) {
            frequency2timeMap.remove(lfu.frequency);
        }

        lfu.time = globalTime++;
        lfu.value = value;
        lfu.frequency++;

        // update
        TreeMap<Integer, Lfu> treeMap = frequency2timeMap.getOrDefault(lfu.frequency, new TreeMap<>());
        treeMap.put(lfu.time, lfu);
        frequency2timeMap.put(lfu.frequency, treeMap);

    }

    private void remove() {
        TreeMap<Integer, Lfu> lfuTreeMap = frequency2timeMap.get(frequency2timeMap.firstKey());
        Lfu oldFlu = lfuTreeMap.get(lfuTreeMap.firstKey());
        lfuTreeMap.remove(oldFlu.time);
        if (lfuTreeMap.isEmpty()) {
            frequency2timeMap.remove(oldFlu.frequency);
        }

        key2lfu.remove(oldFlu.key);
    }

    private void doPut(int key, int value) {
        Lfu lfu = new Lfu(key, value, globalTime++);
        key2lfu.put(key, lfu);
        TreeMap<Integer, Lfu> timeMap = frequency2timeMap.getOrDefault(lfu.frequency, new TreeMap<>());
        timeMap.put(lfu.time, lfu);
        frequency2timeMap.put(lfu.frequency, timeMap);
    }

    public static void main(String[] args) {
//        LFUCache lfuCache = new LFUCache(3);
//        lfuCache.put(1,1);
//        lfuCache.put(2,2);
//        lfuCache.put(3,3);
//        lfuCache.put(4,4);
//        lfuCache.get(4);
//        lfuCache.get(3);
//        lfuCache.get(2);
//        lfuCache.get(1);
//
//        lfuCache.put(5,5);
//
//        lfuCache.get(1);
//        lfuCache.get(2);
//        lfuCache.get(3);
//        lfuCache.get(4);
//        lfuCache.get(5);

        LFUCache lfuCache = new LFUCache(2);
        lfuCache.get(2);
        lfuCache.put(2,6);

        lfuCache.get(1);

        lfuCache.put(1,5);
        lfuCache.put(1,2);

        int i = lfuCache.get(1);
        int i2 = lfuCache.get(2);
        System.out.println(i);
        System.out.println(i2);

    }

}
