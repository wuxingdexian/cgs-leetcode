package dynamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/continuous-subarray-sum/description/" />
 * 523. Continuous Subarray Sum
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 Note:
 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * <p>
 * 1. 建模：都是利用recurrence relation，但是处理上目前想到两个办法：
 * （1）先预处理数组
 * 首先用到余数的数学性质  (a + b) mod m = ((a mod m) + (b mod m)) mod m
 * 数组进行mod后得新的对应数组。
 * （2）不预处理数组，但是使用过程中使用连续子数组的和去划分int范围内表示6的最大次方数，若除尽则表示连续子数组是6的幂方
 *
 * 模型
 * sum_n = sum_n-1 + a[n]
 * P_n = sum_n - sum_i，使得0 < sum_n - sum_i <= target，当等于target即得到答案
 * 判断是否能相等，若等则证明找到了答案
 * 为了参考之前模型，从《编程珠玑》得到启示，使用预处理数组的方式
 * 注意：（1）mod操作，要拿到非负数余数；（2）map.get(sum_n - sum) ，其中sum_n为前面n个数累加得和，
 *
 * FIXME: 13/08/2017 上面的recurrence relation不好重新建模
 * sum_n = sum_n-1 + a[n]
 * 设mod_n = sum_n mod target，
 * 原理：用到余数的数学性质  (a + b) mod m = ((a mod m) + (b mod m)) mod m
 * 判断mod_n是否和前面得某个数i得mod_i相等，若相等，则从i到n之间累加得到target的倍数
 * (sum_n - sum_i) mod target = ((sum_n mod target) - (sum_i mod target)) mod target = (mod_n - mod_i) mod target
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：同余余数性质  (a + b) mod m = ((a mod m) + (b mod m)) mod m，同时这个+号也可以是减号
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap(){
            {put(0, -1);}
        };

        int sum = 0;
        Integer remainder = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 避免0
            remainder = sum;

            if (k != 0) {
                remainder = sum % k;
            }
            Integer preMod = modMap.get(remainder);
            if (preMod != null) {
                if (i - preMod > 1) {
                    return true;
                }
            } else {
                // 要在else里面，不能覆盖前面的
                modMap.put(remainder, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySumStandar(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // 第二次建模得到后得代码，但是在下面测试用例时失败
    // target = 75, int[] nums = {470,161,377,184,118,91,413,73,224,167,505,413,521,5,7,372,393,523,211,479,90,516,238,467,410,51,337,31,442,297,100,75,260,33,490,477,21,374,233,41,215,87,84,153,271,241,169,275,323,358,291,176,423,426,296,175,413,423,387,416,27,266,257,136,27,155,77,142,60,335,401,443,52,153,345,71,117,443,177,238,433,464,323,79,156,429,79,327,64,335,92,147,350,480,277,335,97,317,420,453};
    /*
    public boolean checkSubarraySum(int[] nums, int k) {
        if (null == nums || nums.length < 2) {
            return false;
        }

        k = k < 0? 0 - k: k;
        for (int i = 0; i < nums.length; i++) {
            if(k != 0){
                nums[i] = nums[i] % k;
                // java取模会负数
                nums[i] = nums[i] < 0? 0 - nums[i]: nums[i];
            }
        }

        int sum = nums[0];
        int startIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum > k) {
                for (int j = startIndex; j < i - 1 && sum > k; ) {
                    sum -= nums[j];
                    j++;
                    startIndex = j;
                }
            }
            // 判断和为0得情况
            if (sum == k || sum == 0) {
                return true;
            }
        }

        return false;
    }
    */
    //-------------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------------------------
    // 未调通得第一次代码
    /*
    public boolean checkSubarraySum(int[] nums, int k) {


        k = k > 0? k: 0 - k;
        if(k != 0) {
            for(int i = 0; i < nums.length; i++) {
                nums[i] = nums[i] % k;
            }
        }

        int start = 0;
        int sum = 0;
        for(int end = 1; end < nums.length; end++) {
            if(nums[end] > k) {
                start = end + 1;
                end++;
                continue;
            }

            sum = sum(nums, start, end);
            if(k == 0) {
                if(sum == k) {
                    return true;
                }
            } else {
                if(sum % k == 0) {
                    return true;
                }
            }

            if(sum > k) {
                start = adjustStart(nums, start, end, k);
            }
            // sum = sum(nums, start, end);

        }

        return false;
    }

    int sum(int[] nums, int start, int end) {
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    int adjustStart(int[] nums, int start, int end, int k) {
        int sum = 0;
        for(int i = end; i >= start; i--) {
            sum += nums[i];
            if(sum > k) {
                return i + 1;
            }
        }
        return start;
    }
    */
    //-------------------------------------------------------------------------------------------------------------------------


    public static void main(String[] args) {
        int[] nums = {470,161,377,184,118,91,413,73,224,167,505,413,521,5,7,372,393,523,211,479,90,516,238,467,410,51,337,31,442,297,100,75,260,33,490,477,21,374,233,41,215,87,84,153,271,241,169,275,323,358,291,176,423,426,296,175,413,423,387,416,27,266,257,136,27,155,77,142,60,335,401,443,52,153,345,71,117,443,177,238,433,464,323,79,156,429,79,327,64,335,92,147,350,480,277,335,97,317,420,453};
//        int[] nums = {0,1,0,1,0,1};
        boolean b = new ContinuousSubarraySum().checkSubarraySum(nums, 75);
        System.out.println(b);
    }
}
