package dynamicprogramming.easy;

import dynamicprogramming.medium.MaximumProductSubarray;

/**
 * <p>
 * 背景描述：
 * 53. Maximum Subarray
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <p>
 * 0. 本质：组合
 * 1. 建模：
 * 模型一
 * （1）两次recurrence relation：
 *  MaxSum_k=max(MaxSum_k-1, Sum(x,k)), x={0,1,...,k};
 *  Sum(x,k)=Sum_k - Sum_x;Sum(x,k)的求取参考{@link NumArray}
 * （2）Sum(x,k)参考{@link NumArray}
 *
 * 模型二
 * 上面的模型一虽然使用了动态规划，但是面对连续子数组使得和最大这个限制条件，在动态规划结果的基础上还需要额外计算一次，这抵消了动态规划{@link NumArray}的性能优势。时间复杂度为O(n*n)
 * 所以应该另寻他路。TODO 从set+function的角度思考，题设要满足两个性质：（1）连续性；（2）和非递减；因为要使得连续子数组最大值，那这个连续子数组的和在动态变动过程中是递增的。
 * TODO 从上面的情况分析来看，已经找到了两个关键性质，那么影响这个关键点的就是负数了。。。。
 * 找到function：假设a[i]为负数，a[i-1]和a[i+1]为非负数，若sum(a[i-1] + a[i] + a[i+1]) > a[i-1]并且sum(a[i-1] + a[i] + a[i+1]) > a[i+1]，
 * 则称a[i]为好负数good negative，不影响连续性和非递减性；否则为坏负数，连续中断。
 *
 * 在一个序列中会有很多段连续的负数和连续的非负数，那此时就要把负数和非负数进行规整，得到多个片段，此时可以做预处理，将连续的负数累加得到一个负数，将连续的非负数累加得到一个非负数，
 * 然后在负数和非负数相互间隔的情况考虑，此时已经转换为上面的模型了。
 *
 * 这个问题本质上和{@link MaximumProductSubarray}分析的角度是类似的   TODO 组合题目，找到关键性质。找出影响性质的点；找出性质相关的函数；尝试映射空间、数据转换；尝试能想到的数据结构、排序等技巧。
 * TODO 根本还是分析关键点，找到性质，建模。
 *
 * 至此，可以遍历解决，也可以分治法解决，分治法感觉高端些~。~
 *
 * 模型三
 * // TODO: 27/08/2017  《cracking the coding interview》的方法更简单~~~
 *
 *
 * 提示：
 * （1）动态规划方面：该题目和在矩阵中求子矩阵使得和最大是一个概念，一个是二维、一个是三维。都可以利用动态规划求解，只是动态规划得到的只是中间结果，还需要额外计算。
 * （2）序列连续、和非递减性质方面：该题目和在矩阵中求子矩阵使得和最大是一个概念，都有一个比动态规划更优的解。可以把复杂度降低一个数量级。
 *
 * 2. 算法范式：dynamic programming
 * 3. 算法：bottom-up
 * 4. 数据结构：Sum(x,k)两个数组的function映射，参考{@link NumArray}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/08/2017
 * @see
 * @since DiscreteMathematics on  07/08/2017
 *
 * 注意：使用动态规划的这个方法不是最快的方法，最快的方法参考
 * <code>
 *public class Solution {

//prefix sum, time: O(n), space: O(n)
public int maxSubArray(int[] nums) {
int prefixMin = 0;

int runningTotal = 0;
int max = nums[0];

for (int i = 0; i < nums.length; i++) {
int n = nums[i];
runningTotal += n;
max = Math.max(max, runningTotal - prefixMin);

if (runningTotal < prefixMin) {
prefixMin = runningTotal;
}
}

return max;
}
}
 * </code>
 */
public class MaximumSubarray {

    int[] sum;
    public int maxSubArray(int[] nums) {
        NumArray numArray = new NumArray(nums);
//     numArray(nums);
        SubArray subArray=new SubArray(nums[0],0,0);

        for(int i=1;i<nums.length;i++) {
            for(int j=subArray.start;j<=i && (nums[i]>=nums[i-1] || nums[i]>0);j++) {
                if(numArray.sumRange(j,i) >=subArray.sum) {
                    subArray.sum=numArray.sumRange(j,i);
                    subArray.start=j;
                    subArray.end=i;
                }
            }
            // maxSubArraySum=max(, )
        }
        return subArray.sum;
    }

//    public void numArray(int[] nums) {
//        if (nums == null) {
//            return;
//        }
//        /*
//        这里精妙的将和的数组长度加1，避免数组越界，同时将简化sumRange对数组第0个元素的临界判断
//         */
//        sum = new int[nums.length+1];
//        sum[0] = 0;
//        for(int i=1;i<sum.length;i++) {
//            sum[i] = sum[i-1]+nums[i-1];
//        }
//    }
//
//    public int sumRange(int i, int j) {
//        return sum[j+1]-sum[i];
//    }

    private class SubArray{
        int sum;
        int start;
        int end;

        SubArray(int sum, int start, int end) {
            this.sum=sum;
            this.start=start;
            this.end=end;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        int maxSubArray = new MaximumSubarray().maxSubArray(nums);
        System.out.println(maxSubArray);
    }
}
