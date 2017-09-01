package binarysearchtree.medium;

import others.selfpractice.Tree;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/contains-duplicate-iii/discuss/" />
 * 220. Contains Duplicate III
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

 * <p>
 * 0. 本质：序列 关系
 * 1. 建模：
 * （1）维持滑动窗口为大小为k，窗口不断移动，直到最后一个元素；
 * （2）在窗口内的元素，根据关系对窗口内的元素进行排序，然后以某个元素为基准，找在左右t范围内是否存在其他的数，如果存在，则说明符合题意。
 *
 * 2. 算法范式：
 * 3. 算法：
 *
 * 因为i和j不能相同，所以先比较再维护tree
 *
 * （1）从1到k，逐渐维持窗口到k；每次数据a[i]，以a[i]为基准，找左右范围t内是否存在数据，如果存在，则得到答案；否则继续下一个；
 * （2）维护a[i]到二叉搜索树
 * 4. 数据结构：二叉搜索树（JDK红黑树）
 * 5. 改进：TODO 因为jdk的红黑树实现是TreeMap，基于了Map实现，那么就天然使得key只能唯一，TreeSet又是基于TreeMap实现，元素也是唯一。而本题目的元素是可以重复的。导致不能使在这样的情况下失败。
 * 如果要使用二叉搜索树，要么只能自己实现允许重复元素的二叉搜索树，需要同时兼顾数组差和下标差
 * 要么使用两个TreeMap，分别按nums[i]和下标index作为map的key：
 *  1）按nums[i]作为key的TreeMap可以用来完成“the absolute difference between nums[i] and nums[j] is at most t”；
 *  2）按index作为key的TreeMap用来完成“the absolute difference between i and j is at most k.”
 *
 * 6. 启发：TODO 在同时存在两个变量的情况，如何更好的方案？
 * 7. jdk知识：
 * {@link TreeSet}解释：A {@link NavigableSet} implementation based on a {@link TreeMap}，在{@link TreeSet#TreeSet()}构造函数中可以看到new了treeMap对象
 * {@link TreeMap}解释：A Red-Black tree based {@link NavigableMap} implementation. 红黑树是一棵接近平衡的二叉查找树
 * 注意：元素(key)唯一！！！
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 29/08/2017
 * @see
 * @since cgs-leetcode on  29/08/2017
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length <= 1 || k < 1 || t < 0) {
            return false;
        }
        //
        TreeMap<Integer/*value*/, Integer/*index*/> window = new TreeMap();
        TreeMap<Integer/*index*/, Integer/*value*/> indexMap = new TreeMap();

        for(int i = 0; i < nums.length; i++) {

            Map.Entry<Integer, Integer> floorEntry = window.floorEntry((nums[i]));
            Map.Entry<Integer, Integer> ceilingEntry = window.ceilingEntry((nums[i]));
            if((floorEntry != null && (long)nums[i] - (long)floorEntry.getKey() <= t && floorEntry.getValue() != i)
                    || ((ceilingEntry != null && (long)ceilingEntry.getKey() - (long)nums[i] <= t) && ceilingEntry.getValue() != i)) {
                return true;
            }

            // 若有重复，会覆盖前面相同值得index，因为是逐渐往后，允许更新
            if(window.get(nums[i]) != null) {
                indexMap.remove(window.get(nums[i]));
            }
            window.put(nums[i], i);
            indexMap.put(i, nums[i]);

            // 窗口比k大1
            if(i - indexMap.firstKey() >= k) {
                window.remove(indexMap.remove(indexMap.firstKey()));
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = {1,3,1};
//        int[] nums = {3,6,0,4};
        int[] nums = {-1,2147483647};
        boolean b = new ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 1,2147483647);
        System.out.println(b);


        TreeMap test = new TreeMap();
        test.put(5, 1);
        test.put(9, 1);
        test.put(1, 1);
        test.put(4, 1);
        test.put(3, 1);
        test.put(4, 2);

        System.out.println(test.firstEntry());
        System.out.println(test.lastEntry());
        System.out.println(test.floorEntry(2));
        System.out.println(test.ceilingEntry(2));

    }
}
