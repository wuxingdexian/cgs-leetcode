package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-harmonious-subsequence/description/" />
 * 594. Longest Harmonious Subsequence
 * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

 Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

 Example 1:
 Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 Note: The length of the input array will not exceed 20,000.
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 计数，找到以a[i]元素为中心，a[i]-1和a[i]+1的值是否存在，
 * longest_h_s=max(longest_h_s, max(a[i]-1, a[i]+1) + 1)，when a[i]-1 and a[i]+1 both exist.
 * longest_h_s=max(longest_h_s, a[i]-1 + 1)，when a[i]-1 exist but a[i]+1 not
 * longest_h_s=max(longest_h_s, a[i]+1 + 1)，when a[i]+1 exist but a[i]-1 not
 * 2. 算法范式：
 * 3. 算法：
 * （1）由建模的function可以用map来找前后的值；
 *
 * （2）另一种方法是排序后比对相邻的值
 * 4. 数据结构：
 * 因为不需要求出具体序列，只需要计数即可：map<Integer, Integer>,key:a[i], value:count
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
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        int longest = 0;

        Map<Integer, Integer> cache = new HashMap();
        for(int num: nums) {
            cache.put(num, cache.get(num) != null? cache.get(num) + 1: 1);
            if(cache.containsKey(num-1) && cache.containsKey(num+1)) {
                longest = Math.max(longest, Math.max(cache.get(num-1), cache.get(num+1)) + cache.get(num));
            } else if(cache.containsKey(num-1) && !cache.containsKey(num+1)) {
                longest = Math.max(longest, cache.get(num-1) + cache.get(num));
            } else if(!cache.containsKey(num-1) && cache.containsKey(num+1)) {
                longest = Math.max(longest, cache.get(num+1) + cache.get(num));
            }
        }
        return longest;
    }
}
