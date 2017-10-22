package others.medium;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/description/" />
 * 33. Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 * <p>
 * 0. 本质：序列
 * 1. 建模：ascending sequence在某个点被切换为两部分，每部分还是ascending subsequence
 * 如4 5 6 7 0 1 2，切分为两部分。
 * 注意有个重要特征，如果一个sequence是rotated的，那么首元素大于尾元素。
 *
 *
 * 2. 算法范式：
 * 3. 算法：二分搜索法
 *
 * 算法一
 * 寻找范围设定为sub(h, t)，先找到pivot点，简化逻辑；
 *
 * 首先，找pivot点
 * （1）二分搜索，如果(h+t)/2的位置等于target，则返回(h+t)/2；
 * （2）否则，判断，
 *      1）若nums[(h+t)/2-1]<nums[(h+t)/2]或nums[(h+t)/2]<nums[(h+t)/2+1]，则找到pivot出，
 * 得到两个子数组，sub(h,(h+t)/2-1)、sub((h+t)/2,t)或sub(h,(h+t)/2)、sub((h+t)/2+1,t)
 *      2）否则，若nums[h] < nums[(h+t)/2]，则从sub((h+t)/2 + 1,t)继续找pivot点
 *
 * 其次，根据划分的两个子串sub(h, t1)和sub(t1+1,t)，
 * （1）根据target定位其中一个子串，然后从中二分查找
 *
 * 算法二
 *  寻找范围设定为sub(h, t)，目标为target，
 * （1）当(h+t)/2的位置等于target，则返回(h+t)/2；
 * （2）当(h+t)/2的位置大于target，
 *      1）如果nums[h] < nums[(h+t)/2]
 *          （a）若nums[h]<target，则缩小范围，从sub(h,(h+t)/2 - 1)二分查找即可；
 *          （b）若nums[h]>target，则缩小范围，递归sub((h+t)/2 + 1,t)重新执行该算法；
 *          （c）若nums[h]=target，则返回h；
 *      2）如果nums[h] > nums[(h+t)/2]，则缩小范围，递归sub(h,(h+t)/2 - 1)重新执行该算法，因为(h+t)/2 + 1后的数都比target大；
 *
 * （3）当(h+t)/2的位置小于target，
 *      1）若nums[h] < nums[(h+t)/2]，则缩小范围，递归sub((h+t)/2 + 1,t)重新执行该算法；
 *      2）若nums[t] < target，则缩小范围，从sub((h+t)/2 + 1,t)二分查找即可。
 * // FIXME: 22/10/2017 算法二需要在优化，这些情况的组合太多，不适合
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link Arrays#binarySearch(int[], int, int, int)} toIndex不包含exclusive，如果找不到，则返回-(fromIndex-1)
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/10/2017
 * @see
 * @since cgs-leetcode on  22/10/2017
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length == 1) {
            return nums[0] == target? 0: -1;
        }

        int pivot = getPovit(nums);
        int index = -1;
        if(target <= nums[pivot] && nums[0] <= target) {
            index = Arrays.binarySearch(nums, 0, pivot + 1, target);
        } else {
            index = Arrays.binarySearch(nums, pivot + 1, nums.length, target);
        }
        return index >= 0? index: -1;
    }

    /**
     * 找到pivot点，即前面递增序列的最后一个元素
     */
    int getPovit(int[] nums) {
        if(nums[0] < nums[nums.length - 1]) {
            return nums.length - 1;
        }
        int h = 0;
        int t = nums.length - 1;
        int m = (h + t)>>1;
        while(m>h) {
            if(m-1 >= h && nums[m-1] > nums[m]) {
                return m-1;
            }
            if(m+1 < nums.length && nums[m] > nums[m+1]) {
                return m;
            }

            if(nums[h] > nums[m]) {
                t = m - 1;
            } else if(nums[m] > nums[t]) {
                h = m + 1;
            }
            m =  (h + t)>>1;
        }
        return m;
    }

    public static void main(String[] args) {

        int[] nums = {5,1,3};
        int search = new SearchInRotatedSortedArray().search(nums, 1);
        System.out.println(search);
    }
}
