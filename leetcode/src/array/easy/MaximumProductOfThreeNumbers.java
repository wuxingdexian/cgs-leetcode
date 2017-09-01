package array.easy;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-product-of-three-numbers/description/" />
 * 628. Maximum Product of Three Numbers
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 Note:
 The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 * <p>
 * 0. 本质：集合
 * 1. 建模：
 * // FIXME: 01/09/2017 "滑动窗口使得乘积最大{@link MaximumAverageSubarrayI} 和这个一个思路" 审题错了。老铁
 * 找集合中的三个数，组合问题：+++，+--，两种组合的数，取最大：max(三个最大正整数乘积、两个最大负整数和一个最大正整数乘积)
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：注意有0出现
 * 7. jdk知识：
 * follow up：求三个连续序列中的最大乘积，类似求和，也类似最大乘积的子串； 可以用队列来解决
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class MaximumProductOfThreeNumbers {

    // 这两个解法代码都很清晰。。。值得学习
    public int maximumProductStandard1(int[] nums) {

        if(nums.length < 3) {
            return 0;
        }
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num: nums) {
            if(num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);

    }

    public int maximumProductStandard2(int[] nums) {

        Arrays.sort(nums);
        //One of the Three Numbers is the maximum value in the array.

        int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        int b = nums[0] * nums[1] * nums[nums.length - 1];
        return a > b ? a : b;
    }

//    public int maximumProduct(int[] nums) {
//        if(nums == null || nums.length < 3) {
//            return 0;
//        }
//
//        Pair pair = getThreeConsecutiveProduct(nums, 0);
//        if(pair.product == 0) {
//            return 0;
//        }
//        int preProduct = pair.product;
//        int maxProduct = preProduct;
//
//        for(int i = pair.index + 1; i < nums.length; i++) {
//            if(nums[i] == 0) {
//                Pair innerPair = getThreeConsecutiveProduct(nums, 0);
//                preProduct = innerPair.product;
//                i = innerPair.index;
//                continue;
//            }
//            preProduct = preProduct * nums[i] / nums[i - 3];
//            maxProduct = Math.max(preProduct, maxProduct);
//        }
//        return maxProduct;
//    }
//
//    Pair getThreeConsecutiveProduct(int[] nums, int start) {
//        int counter = 0;
//        int product = 1;
//        for(;start < nums.length; start++) {
//            if(nums[start] == 0) {
//                product = 1;
//                counter = 0;
//            } else {
//                product *= nums[start];
//                counter++;
//            }
//            if(counter == 3) {
//                return new Pair(start, product);
//            }
//        }
//        return new Pair(start, 0);
//    }
//
//    class Pair {
//        int product;
//        int index;
//        Pair(int index, int product) {
//            this.index = index;
//            this.product = product;
//        }
//    }

    public static void main(String[] args) {
        int[] nums = {1,0,100};
        int i = new MaximumProductOfThreeNumbers().maximumProductStandard1(nums);
        System.out.println(i);
    }
}
