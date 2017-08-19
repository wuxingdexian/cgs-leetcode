package design.hard;

import java.util.LinkedHashMap;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/all-oone-data-structure/discuss/" />
 * 432. All O`one Data Structure
 * Implement a data structure supporting the following operations:

 Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 Challenge: Perform all these in O(1) time complexity.
 * <p>
 * 1. 建模：
 * HashMap + bucket（ArrayList+LinkedHashMap）
 *
 * HashMap保存key，用来的定位key在bucket的位置
 * ArrayList+HashMap类似邻接链表的效果，ArrayList的下表表示key的个数，个数相同的key都保存在ArrayList的一个元素连接的LinkedHashMap中
 *
 * （0）准备两个指针，分别指向ArrayList的最小个数位置和最大个数位置。当最大个数位置为空，则最大个数指针左移动一位。
 * （1）定位由HashMap.get(key)获取key在ArrayList的位置；
 * （2）key减1时，ArrayList[count]获取key后，在ArrayList的LinkedHashMap中找到该元素，移到ArrayList[count-1]的LinkedHashMap中。最后在ArrayList[count]中删除。
 * （3）key加1时，做相反操作。
 * 注意：要判断指针的位置，随时最好移动。
 *
 * // TODO: 18/08/2017 实现
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：注意{@link LinkedHashMap#size()}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/08/2017
 * @see
 * @since cgs-leetcode on  18/08/2017
 */
public class AllOoneDataStructure {
}
