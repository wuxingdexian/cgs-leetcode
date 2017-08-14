package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * 53. Maximum Subarray
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <p>
 * 1. 建模：
 * （1）两次recurrence relation：
 *  MaxSum_k=max(MaxSum_k-1, Sum(x,k)), x={0,1,...,k};
 *  Sum(x,k)=Sum_k - Sum_x;Sum(x,k)的求取参考{@link NumArray}
 * （2）Sum(x,k)参考{@link NumArray}
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
