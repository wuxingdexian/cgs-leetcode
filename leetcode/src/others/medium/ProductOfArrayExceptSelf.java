package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/product-of-array-except-self/description/" />
 * 238. Product of Array Except Self
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Solve it without division and in O(n).

 For example, given [1,2,3,4], return [24,12,8,6].

 Follow up:
 Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)


 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 设p(i)为第i个元素，符合“output[i] is equal to the product of all the elements of nums except nums[i].”
 * （1）使用除法：
 * 所有数相乘，然后分别除以nums[i] TODO  可能遇到0的情况，除数不能为0
 *
 * （2）recurrence relation
 * p(i) = l(i)*r(i)，l(i)为第i个元素左右的乘积，r(i)为第i个元素右边的乘积
 *
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * 预处理：分别从左到右和从右到左求l(i)和r(i)
 *
 * TODO   Follow up:
 * 既然如此，先计算l(i)，然后直接在nums基础上执行r(i)，最后在l(i)基础上执行操作，然后返回l
 * 4. 数据结构：数组
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];

        leftProduct[0] = 1;
        rightProduct[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i ++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }
        for(int i = nums.length - 2; i >=0; i--) {
            rightProduct[i] = rightProduct[i+1] * nums[i+1];
        }

        int[] solutions = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            solutions[i] = leftProduct[i] * rightProduct[i];
        }
        return solutions;

    }

    // follow up solution 减少空间使用
    public int[] productExceptSelfFollowUp(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] leftProduct = new int[nums.length];

        leftProduct[0] = 1;
        for(int i = 1; i < nums.length; i ++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }
        int tmp = nums[nums.length - 1];
        nums[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >=0; i--) {
            int tmpInner = nums[i];
            nums[i] = nums[i+1] * tmp;
            tmp = tmpInner;
        }

        for(int i = 0; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i] * nums[i];
        }
        return leftProduct;

    }

}
