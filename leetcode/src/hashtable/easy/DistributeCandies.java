package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/distribute-candies/description/" />
 * 575. Distribute Candies
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.

 Example 1:
 Input: candies = [1,1,2,2,3,3]
 Output: 3
 Explanation:
 There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 The sister has three different kinds of candies.
 Example 2:
 Input: candies = [1,1,2,3]
 Output: 2
 Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
 The sister has two different kinds of candies, the brother has only one kind of candies.
 Note:

 The length of the given array is in range [2, 10,000], and will be even.
 The number in given array is in range [-100,000, 100,000].
 * <p>
 * 0. 本质：集合
 * 1. 建模：divide the set into 2 subset, make sure one subset contains maximum kinds.
 * 2. 算法范式：
 * 3. 算法：
 * traversal the array, when map don't have the num, then put it.
 * finally, the size is the answer. because add num to map again can't make the kind in this map become greater.
 * tips: the map's size can't greater than half of candies
 * 4. 数据结构：map
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        Map<Integer, Integer> cache = new HashMap();
        for(int num: candies) {
            if(!cache.containsKey(num) && cache.size() < (candies.length >> 1)) {
                cache.put(num, 1);
            }
        }
        return cache.size();
    }
}
