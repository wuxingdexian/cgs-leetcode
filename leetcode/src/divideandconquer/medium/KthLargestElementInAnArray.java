package divideandconquer.medium;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/description/" />
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ? k ? array's length.
 * <p>
 * 1. 建模：
 * 首先排序，f(n, k)为从长度为n的数组中找到第k大的数字。直接从a(n-k)定位，若a(n) - a(n-k+1)大于等于k-1，说明直接找到答案，因为两个元素之间正好相差k - 1个元素
 * f(n, k) = f(n-k, k - (a(n) - a(n-k+1)) >= k-1? k-1: (a(n) - a(n-k+1))) + g(n)
 * f(n,1)=a(n)，最大元素即最后一个
 *
 * 2. 算法范式：divided-and-conquer
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class KthLargestElementInAnArray {

    /**
     * 看来题目没完全说清楚，题目假设重复的也算
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        return nums[nums.length - k];

        // int count = 0;
        // for(int i = nums.length - 1; i >= 0; i--) {
        //     if(nums[i] <= nums[i + 1 >= nums.length? i: i + 1]) {
        //         count++;
        //     }
        //     if(count == k) {
        //         return nums[i];
        //     }
        // }
        // return nums[nums.length - 1];

    }
}
