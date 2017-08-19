package design.hard;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/description/" />
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * Design a data structure that supports all following operations in average O(1) time.

 Note: Duplicate elements are allowed.
 insert(val): Inserts an item val to the collection.
 remove(val): Removes an item val from the collection if present.
 getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 Example:

 // Init an empty collection.
 RandomizedCollection collection = new RandomizedCollection();

 // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 collection.insert(1);

 // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 collection.insert(1);

 // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 collection.insert(2);

 // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 collection.getRandom();

 // Removes 1 from the collection, returns true. Collection now contains [1,2].
 collection.remove(1);

 // getRandom should return 1 and 2 both equally likely.
 collection.getRandom();
 * <p>
 * 1. 建模：
 * 很容想到map来解决，插入complexity自然是1，remove自然也是1，但是随机读取就有点麻烦了。
 * 要使得操作负责度都是1。这么来：
 * 使用两个数据结构：
 * （1）HashMap<Integer, LinkedHashMap>，用来负责插入和删除；LinkedHashMap是为了保持数据紧凑，同时为了在删除操作时和另一个val交换位置时容易定位。
 * （2）ArrayList，用来负责随机读取；数组的定位能力满足元素重复性，和下标读取负责度为1；
 * 一切看起来很好，插入、随机读取都能满足要求。就是在删除时HashMap要同时兼顾ArrayList，保证两个数据结构中的数据一致。自然要把两者关联起来。
 * 关联：将HashMap的value键保存对应数据在ArrayList的位置信息。当删除数据val1时，
 * （1）从val1的HashMap的value键（LinkedHashMap）中拿到该元素的一个位置position；
 * （2）根据position定位val1，和ArrayList的最后一个数据val2进行交换；
 * （3）更新HashMap中val2的位置信息。
 * （4）删除val1的HashMap的value键（LinkedHashMap）中拿到该元素的位置position的元素
 * （5）若val1的HashMap的value键为空，则把val1删除掉。
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/08/2017
 * @see
 * @since cgs-leetcode on  18/08/2017
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {
    // TODO: 18/08/2017 实现
}
