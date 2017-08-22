package sort.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays/description/" />
 * 349. Intersection of Two Arrays
 * Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

 Note:
 Each element in the result must be unique.
 The result can be in any order.
 * <p>
 * 1. 建模：
 * 求两个集合的交集，若无序，则需要m*n比较，复杂度O(m*n)
 * 排序后，都是相同顺序规则，然后指针移动比较
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）分别排序
 * （2）两个指针指向头部，
 * （3）比较策略：
 *      1）相等将值放入相等集合，然后两个指针分别移动到不重复的下一个数
 *      2）不等，则移动值小的那个指针到下一个不重复的数
 *      3）执行上述步骤，直到一个指针到达尾部
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * 数组间的拷贝，可以使用jdk{@link System#arraycopy(Object, int, Object, int, int)}，注意：必须是数组间
 * 如：
 * int[] result1 = new int[repeat.size()];
 * int[] result2 = new int[repeat.size()];
 * System.arraycopy(result1, 0, result2, 0, result1.length);
 * 其实{@link ArrayList#toArray(Object[])}底层也是使用了{@link System#arraycopy(Object, int, Object, int, int)}方法。
 * 但是不能直接初始化一个int[]数组，然后调用{@link ArrayList#toArray(Object[])}方法，因为会被泛型标记为错误
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/08/2017
 * @see
 * @since cgs-leetcode on  22/08/2017
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index1 = 0;
        int index2 = 0;

        List<Integer> repeat = new ArrayList();
        while(index1 < nums1.length && index2 < nums2.length) {
            if(nums1[index1] == nums2[index2]) {
                repeat.add(nums1[index1]);
                index1 = move2nextInequalIndex(nums1, index1);
                index2 = move2nextInequalIndex(nums2, index2);
            } else if(nums1[index1] < nums2[index2]) {
                index1 = move2nextInequalIndex(nums1, index1);
            } else {
                index2 = move2nextInequalIndex(nums2, index2);
            }
        }
        return getIntersectionResult(repeat);
    }

    int[] getIntersectionResult(List<Integer> repeat) {
        int[] result = new int[repeat.size()];
        int i = 0;
        for(Integer val: repeat) {
            result[i++] = val;
        }
        return result;
    }

    int move2nextInequalIndex(int[] nums, int start) {
        int value = nums[start];
        for(int i = start + 1; i < nums.length; i++) {
            if(nums[i] != nums[start]) {
                return i;
            }
        }
        return nums.length;
    }
}
