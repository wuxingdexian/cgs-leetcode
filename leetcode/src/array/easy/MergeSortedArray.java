package array.easy;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/merge-sorted-array/description/" />
 * 88. Merge Sorted Array
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

 * <p>
 * 0. 本质：combinatorics generating permutation
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：// FIXME: 02/09/2017 不需要额外的数组；nums1有足够的空间；
 * 6. 启发：TODO 从尾部开始填充！！！
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1.length < m + n) {
            return;
        }
        int[] cache = Arrays.copyOf(nums1, m);

        int i = 0, j = 0, index = 0;
        while(i < m && j < n) {
            if(cache[i] <= nums2[j]) {
                nums1[index++] = cache[i++];
            } else {
                nums1[index++] = nums2[j++];
            }
        }
        while(i < m) {
            nums1[index++] = cache[i++];
        }
        while(j < n) {
            nums1[index++] = nums2[j++];
        }
    }

    public void mergeStandard(int nums1[], int m, int[] nums2, int n) {
        while(n > 0) {
            // 吊吊吊
            nums1[m + n - 1] = (m == 0 || nums1[m-1] < nums2[n-1])? nums2[--n]: nums1[--m];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[10];
        nums1[0] = 1;
        nums1[1] = 3;
        nums1[2] = 4;
        nums1[3] = 7;
        int[] nums2 = {2,7,9};
        new MergeSortedArray().mergeStandard(nums1, 4, nums2, 3);
        System.out.println(nums1);
    }
}
