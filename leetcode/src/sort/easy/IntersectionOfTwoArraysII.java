package sort.easy;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/description/" />
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * <p>
 * 0. 本质：
 * （1）关系relation  有序对“等于”，这里有序对需要额外限制：若相等，则这个元素就只能使用一次
 * （2）组合，分别从两个集合，找相同的元素组合在一起。元素只能使用一次
 * 1. 建模：
 * （1）relation，找出相等的
 * （2）组合，函数映射，找出相等的
 * 2. 算法范式：
 * 3. 算法：
 * （1）排序，两个指针逐渐移动。小于的一方主动向前移动；等于时两个同时移动一次；知道一方先结束
 * （2）domain为nums1的元素组成；codomain为nums2的元素组成；function为相等。若相等，则codomain将该元素删除或标记为不能再使用
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 23/08/2017
 * @see
 * @since cgs-leetcode on  23/08/2017
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(null == nums1 || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        return function(nums1, nums2);
    }

    int[] relation(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int head1=0, head2=0;
        List<Integer> solutions = new ArrayList();
        while(head1 < nums1.length && head2 < nums2.length) {
            if(nums1[head1] == nums2[head2]) {
                solutions.add(nums1[head1]);
                head1++;
                head2++;
            } else if(nums1[head1] < nums2[head2]) {
                head1++;
            } else {
                head2++;
            }
        }
        int[] solutionArray = new int[solutions.size()];
        for(int i = 0; i < solutions.size(); i++) {
            solutionArray[i] = solutions.get(i);
        }
        return solutionArray;
    }

    int[] function(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums2Map = new HashMap();
        for(int i = 0; i < nums2.length; i++) {
//            nums2Map.put(nums2[i], nums2Map.getOrDefault(nums2Map.get(nums2[i]), 0) + 1);
            Integer value = nums2Map.get(nums2[i]) != null? nums2Map.get(nums2[i]): 0;
            nums2Map.put(nums2[i], value + 1);
        }

        List<Integer> solutions = new ArrayList();
        for(int i = 0; i < nums1.length; i++) {
            if(nums2Map.get(nums1[i]) != null && nums2Map.get(nums1[i]) > 0) {
                solutions.add(nums1[i]);
                nums2Map.put(nums1[i], nums2Map.get(nums1[i]) - 1);
            }
        }
        int[] solutionArray = new int[solutions.size()];
        for(int i = 0; i < solutions.size(); i++) {
            solutionArray[i] = solutions.get(i);
        }
        return solutionArray;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] intersect = new IntersectionOfTwoArraysII().intersect(nums1, nums2);
        System.out.println(intersect);
    }
}
