package array.easy;

import java.util.HashMap;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/two-sum/description/" />
 * 1. Two Sum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 * <p>
 * 1. 建模：决策树
 * 定义有序对((x,y), nums[], target, sum)，要找有序对(x,y)，使得(sum=x+y)=target
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：决策树递归太慢了，性能太差。两个for循环都比递归快。
 * // FIXME: 10/08/2017 参考了比较好的答案。 {@link TwoSum#twoSumStandard}，将遍历过程的痕迹留下来。。太屌了。 这个在字符串的遍历中应该提供了很好的思路
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class TwoSum {

    /**
     * 性能很差
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] solution = new int[2];
        boolean[] flag = new boolean[nums.length];
        generate(nums, target, solution, 0, 0, flag);
        return solution;


    }

    private boolean generate(int[] nums, int target, int[] solution, int step, int deep, boolean[] flag) {
        if(step == 2) {
            if(nums[solution[0]] + nums[solution[1]] == target) {
                return true;
            }
            return false;

        }
        for(int i = deep; i < flag.length; i++) {
            if(!flag[i]) {
                    flag[i] = !flag[i];
                    solution[step] = i;
                    if(generate(nums, target, solution, step + 1, deep + 1, flag)) {
                        return true;
                    }
                    flag[i] = !flag[i];
            }
        }
        return false;
    }

    /**
     * 一般思路的两个for循环
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumImprove(int[] nums, int target) {

        int[] solutions = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int difference = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == difference) {
                    solutions[0] = i;
                    solutions[1] = j;
                    return solutions;
                }
            }
        }

        return solutions;
    }

    /**
     * 这是参考https://leetcode.com/submissions/detail/113321009/的代码
     * 比较快速的，将遍历过程的足迹记录下来。屌屌屌
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumStandard(int[] nums, int target) {

        if (nums == null)
            return null;

        int[] res = new int[2];
        int len = nums.length;
        HashMap hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < len; i++)
        {
            if (hm.containsKey(nums[i])){
                int index = (int) hm.get(nums[i]);
                res[0] = index;
                res[1] = i;
                break;
            }
            else {
                hm.put(target - nums[i], i);
            }

        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] ints = new TwoSum().twoSumImprove(nums, 6);
        System.out.println(ints[0] + "," + ints[1]);
    }
}
