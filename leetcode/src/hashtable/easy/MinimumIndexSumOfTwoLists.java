package hashtable.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-index-sum-of-two-lists/description/" />
 * 599. Minimum Index Sum of Two Lists
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

 You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

 Example 1:
 Input:
 ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 Output: ["Shogun"]
 Explanation: The only restaurant they both like is "Shogun".
 Example 2:
 Input:
 ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 ["KFC", "Shogun", "Burger King"]
 Output: ["Shogun"]
 Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 Note:
 The length of both lists will be in the range of [1, 1000].
 The length of strings in both lists will be in the range of [1, 30].
 The index is starting from 0 to the list length minus 1.
 No duplicates in both lists.
 * <p>
 * 0. 本质：集合求交集
 * 1. 建模：
 * 集合交集，并加上min判断
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
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
public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> cache = new HashMap();
        for(int i = 0; i < list1.length; i++) {
            cache.put(list1[i], i);
        }
        int leastIndexSum = Integer.MAX_VALUE;
        List<String> solutions = new ArrayList();
        for(int i = 0; i < list2.length; i++) {
            if(cache.containsKey(list2[i])) {
                int sumIndex = cache.get(list2[i]) + i;
                if(leastIndexSum == sumIndex) {
                    solutions.add(list2[i]);
                } else if(leastIndexSum > sumIndex) {
                    solutions.clear();
                    solutions.add(list2[i]);
                    // 别忘记这里更新
                    leastIndexSum = sumIndex;
                }
            }
        }
        return solutions.toArray(new String[solutions.size()]);
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = {"KFC","Shogun","Burger King"};
        String[] restaurant = new MinimumIndexSumOfTwoLists().findRestaurant(list1, list2);
        System.out.println(restaurant);
    }
}
