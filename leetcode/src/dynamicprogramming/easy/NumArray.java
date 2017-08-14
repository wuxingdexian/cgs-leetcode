package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * 303. Range Sum Query - Immutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * <p>
 * 1. 建模：（1）recurrence relation，Sn=pn + Sn-1；（2）两个集合的映射/函数 function
 * 2. 算法范式：动态编程
 * 3. 算法：bottom-up
 * 4. 数据结构：两个数组，映射；避免数组越界，同时将简化sumRange对数组第0个元素的临界判断
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/08/2017
 * @see
 * @since dynamicprogramming on  07/08/2017
 */
public class NumArray {
    int[] sum;
    public NumArray(int[] nums) {
        if (nums == null) {
            return;
        }
        /*
        这里精妙的将和的数组长度加1，避免数组越界，同时将简化sumRange对数组第0个元素的临界判断
         */
        sum = new int[nums.length+1];
        sum[0] = 0;
        for(int i=1;i<sum.length;i++) {
            sum[i] = sum[i-1]+nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j+1]-sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
//        int[] nums = {};

        int i = new NumArray(nums).sumRange(0, 2);

        System.out.println(i);

    }

}
