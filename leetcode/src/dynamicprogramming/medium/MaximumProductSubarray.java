package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-product-subarray/description/" />
 * 152. Maximum Product Subarray
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 * <p>
 * 1. 建模：
 * （1）找不到recurrence relation时，建立如下模型
 * 建立有序对p(i,j)，i为前面最近得第i个负数，j为这个负数得位置
 * 如2，5，-4，6，0，-7，8
 * 对应p_1(0,0)，p_2(0,0)，p_3(1,3)，p_4(1,3)，p_5(0,0)，p_6(1,6)，p_7(1,6)，
 * 则第截至第n个数，得到得最大值为max_n=max(max_n-1, product_n)，
 * product_n取值规则：当p_n得i为偶数，则product_n取前面最近0值后得第一个元素到n的乘积；
 * 当p_n的i为奇数，则product_n取最近0值后第一个负数后的第一个正数到数n得乘积
 * （2）recurrence relation，其实上面建立的模型已经接近答案了。
 * 在（1）中给出的max_n=max(max_n-1, product_n)就是recurrence relation得模型。需要注意这些情况：
 * 首先按0将数组分成了不同的小区域：___,0,_____,0,______；当product_n为0时，在下一次迭代过程从n+1元素开始
 * 然后不同的区域又可以分为正、负两种情况： + + - + - + ；每次遇到负数，negativeBeforeNum都加1
 *      当遇到第一个负数时，从该位置开始迭代乘积startNegativeProduct，则startNegativeProduct和max_n比较；
 *      从第一个负数后的第一个元素开始迭代乘积afterNegativeProduct，则afterNegativeProduct和max_n比较
 * 执行上面两个判断是因为偶数个负数的乘积为正数
 *
 * 2019.2.17 建模V3
 * 首先按0将数组分成了不同的小区域：___,0,_____,0,______；从每个小区域的值和0中取最大值。
 * 设p(i)为子区域内从0到i的的乘积；first_negative_index=x，为子区域内第一个为负数元素的索引；total_negative=y，为子区域内所有负数元素的个数
 * 则某区域的最大值，sub_max = p(n)，当total_negative为偶数
 *                           p(n) / p(x)，当total_negative为奇数
 *
 * 2. 算法范式：dynamic programming，
 * 当然分割0子数组，也可以分治法考虑，当遇到第一个0，则裂变左右两个数组，左边数组按上述不含0的情况处理，右边按包含0得情况递归。最后合并取两边得max
 * 3. 算法：
 * 4. 数据结构：有序对p(i,j)需要事项缓存，同时也可以把1到n的所有乘积缓存起来
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {

        if (null == nums || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        // 正常的0后第一个数
        int afterZeroProduct = 1;
        // 负数后的第一个数
        int startNegativeProduct = 1;
        int afterNegativeProduct = 1;
        int negativeBeforeNum = 0;
        int maxProduct = nums[0];
        for (int i = 0; i < nums.length; i++) {
            afterZeroProduct *= nums[i];

            maxProduct = Math.max(maxProduct, afterZeroProduct);
            if (negativeBeforeNum > 0) {
                afterNegativeProduct *= nums[i];
                maxProduct = Math.max(maxProduct, afterNegativeProduct);
            }

            if (nums[i] == 0) {
                afterZeroProduct = 1;
                negativeBeforeNum = 0;
                startNegativeProduct = 1;
                afterNegativeProduct = 1;
            } else if (nums[i] < 0) {
                negativeBeforeNum++;
            }

            if (negativeBeforeNum > 0) {
                startNegativeProduct *= nums[i];
                maxProduct = Math.max(maxProduct, startNegativeProduct);
            }
        }
        return maxProduct;
    }
    // ---------------------------------------------------------------------------------------------------------
    // 找不到上面模型（2）描述的计算乘积的起始位置，所以用这么冗长的代码进行
    /*
    public int maxProduct(int[] nums) {
        int[][] locations = new int[nums.length][2];
        // locations[0] nearest headmost negative number's position; locations[1] headmost negative count;
        if(nums[0] < 0) {
            locations[0][0] = 1;
            locations[0][1] = 0;
        }
        for(int i = 1; i< nums.length; i++) {
            if(nums[i] == 0) {
                locations[i][0] = 0;
                locations[i][1] = i;
            } else if(nums[i] < 0) {
                locations[i][0] = locations[i - 1][0] + 1;
                locations[i][1] = locations[i - 1][1] + 1;
            } else {
                locations[i][0] = locations[i - 1][0];
                locations[i][1] = locations[i - 1][1];
            }
        }

        int[] maxProduct = new int[nums.length];
        maxProduct[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == 0) {
                maxProduct[i] = Math.max(maxProduct[i - 1], 0);
            } else {
                maxProduct[i] = Math.max(maxProduct[i - 1], product(locations, nums, i));
            }
        }
        return maxProduct[nums.length - 1];

    }

    int product(int[][] locations, int[] nums, int index) {
        int flag = locations[index][0] % 2;
        int product = 1;
        if(nums[index] < 0) {
            product *= nums[index];
            for(int i = index - 1; i >= 0 && nums[i] != 0; i--) {

                if(nums[i] < 0 && locations[i][0] == flag) {
                    return product;
                }
                product *= nums[i];
            }
        } else if(nums[index] == 0) {
            return 0;
        } else {
            for(int i = index; i >= 0 && nums[i] != 0; i--) {

                if(nums[i] < 0 && locations[i][0] == flag) {
                    return product;
                }
                product *= nums[i];
            }
        }
        return product;

    }
    */
    // ---------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
//        int[] nums = {0,1,0,-1,2,0,3,-5,-2,0};
        int[] nums = {3,-1,4};
        int i = new MaximumProductSubarray().maxProduct(nums);
        System.out.println(i);
    }
}
