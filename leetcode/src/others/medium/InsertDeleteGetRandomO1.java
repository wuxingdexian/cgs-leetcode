package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/description/" />
 * 380. Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();
 * <p>a
 * 0. 本质：集合
 * 1. 建模：
 * 写操作能达到O(1)复杂度的数据结构：数组、栈、队列、map；读操作能达到O(1)复杂度的数据结构：数组、map，栈顶、队列头尾、堆顶；
 * 删除操作能达到O(1)复杂度的数据结构：数组最后元素、栈顶、队列头尾、map；
 * map在写和确定读/删除某Object时能做到O(1)，但是在随机性的时候满足不了要求。
 * 数组在写入和随机读时能做到O(1)，但是在确定读/删除某Object时需要遍历O(n)
 *
 * 综上，使用Map+数组的形式，Map<Object, 数组Index>，Array[]
 * 写的时候往Array末尾和Map同时写入；随机读取的时候，random到Array下标，直接读该元素即可；
 * 删除时，读取Map，拿到Object对应的数组下标，然后在数组定位这个元素，并和末尾元素交换，删除末尾的元素，同时更新被交换元素的下标值。666
 *
 * TODO 数据能重复么？不能重复
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：HashMap+ArrayList
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link Random#nextInt(int)} 当参数bound <= 0时，抛异常，所以需要额外判断
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/09/2017
 * @see
 * @since cgs-leetcode on  10/09/2017
 */
public class InsertDeleteGetRandomO1 {
    Map<Integer, Integer> cacheMap;
    List<Integer> nums;
    Random random;
    /** Initialize your data structure here. */
//    public RandomizedSet() {
    public InsertDeleteGetRandomO1() {
        cacheMap = new HashMap();
        nums = new ArrayList();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(cacheMap.containsKey(val)) {
            return false;
        }
        nums.add(val);
        cacheMap.put(val, nums.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = cacheMap.get(val);
        if(index == null) {
            return false;
        }
        Integer lastElement = nums.get(nums.size() - 1);
        // update map
        cacheMap.put(lastElement, index);

        nums.set(index, lastElement);
        nums.remove(nums.size() - 1);
        cacheMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if(nums.size() == 0) {
            return 0;
        }
        int index = random.nextInt(nums.size());
        return nums.get(index);
    }
}
