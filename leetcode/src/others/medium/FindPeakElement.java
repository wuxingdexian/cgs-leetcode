package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-peak-element/description/" />
 * 162. Find Peak Element
 * A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * <p>
 * 0. 本质：函数
 * 1. 建模：每个元素，只要比两边元素大即可
 * 2. 算法范式：
 * 3. 算法：
 * （1）从左到右依次遍历，比较每个元素的是否比左右元素大；
 * （2）若找到一个peak，则可以跳过一个元素
 *
 * // TODO: 10/09/2017 二叉搜索也是不错的选择
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/09/2017
 * @see
 * @since cgs-leetcode on  10/09/2017
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length == 1) {
            return 0;
        }
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                if(nums[0] > nums[1]) {
                    return 0;
                }
            } else if(i == nums.length - 1) {
                if(nums[nums.length - 1] > nums[nums.length - 2]) {
                    return nums.length - 1;
                }
            } else {
                if(nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                    return i;
                }
            }
        }
        return -1;
    }
}
