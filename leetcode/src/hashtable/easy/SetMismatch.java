package hashtable.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/set-mismatch/description/" />
 * 645. Set Mismatch
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

 Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

 Example 1:
 Input: nums = [1,2,2,4]
 Output: [2,3]
 Note:
 The given array size will in the range [2, 10000].
 The given array's numbers won't have any order.
 * <p>
 * 0. 本质：集合
 * 1. 建模：集合求补得到duplicate的那个数，求差得到少的那个数
 *
 * 2. 算法范式：
 * 3. 算法：
 * 开一个int数组，和n一样大小，遍历统计出现两次的那个数
 * 然后用1到n的总和减去（数组求和-出现两次的那个数）
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int[] cache = new int[nums.length];
        int sum = 0;
        int duplicatedNum = -1;
        for(int num: nums) {
            sum += num;
            if(++cache[num-1] == 2) {
                duplicatedNum = num;
            }
        }
        int[] solutions = new int[2];
        solutions[0] = duplicatedNum;
        solutions[1] = nums.length * (nums.length + 1) / 2 - (sum - duplicatedNum);
        return solutions;
    }
}
