package array.easy;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/third-maximum-number/description/" />
 * 414. Third Maximum Number
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:
 Input: [3, 2, 1]

 Output: 1

 Explanation: The third maximum is 1.
 Example 2:
 Input: [1, 2]

 Output: 2

 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 Example 3:
 Input: [2, 2, 3, 1]

 Output: 1

 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.
 * <p>
 * 0. 本质：combinatorics generating permutation 组合数学“生成式”排列
 * 1. 建模：
 * 找到去掉重复后的第三大元素
 * 2. 算法范式：
 * 3. 算法：
 * （1）TreeSet可以完成，天然去重
 *
 * （2）数组先排序，然后从最大的开始找
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class ThirdMaximumNumber {
    public int thirdMax1(int[] nums) {
        TreeSet<Integer> cache = new TreeSet();
        for(int num: nums) {
            cache.add(num);
        }
        if(cache.size() < 3) {
            return cache.last();
        }
        return cache.floor(cache.floor(cache.last() - 1) - 1);

    }

    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int index = nums.length - 1;
        int counter = 0;
        for(int i = nums.length - 1; i >= 0; i--) {

            if(nums[i] != nums[index]) {
                index = i;
                counter++;
            }
            if(counter == 2) {
                break;
            }
        }
        return counter == 2? nums[index]: nums[nums.length - 1];
    }

    public static void main(String[] args) {
//        int[] nums = {2,2,3,1};
        int[] nums = {-2147483648,-2147483648,-2147483648,-2147483648,1,1,1};
        int i = new ThirdMaximumNumber().thirdMax(nums);
    }
}
