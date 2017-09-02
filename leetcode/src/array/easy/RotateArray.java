package array.easy;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/rotate-array/description/" />
 * 189. Rotate Array
 * Rotate an array of n elements to the right by k steps.

 For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

 Note:
 Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

 [show hint]

 Related problem: Reverse Words in a String II
 * <p>
 * 0. 本质：combinatorics 排列
 * 1. 建模：
 * 建模一：
 * two part AB to BA
 *
 * 建模二：
 * 将问题拆解为ABC，A和B有相同长度，A和B先交换，然后得到CBA，此时C或者A固定不需要改变，然后对剩下的CB或BA在进行移动，移动策略一样，即为递归过程（divide-and-conquer）
 * 注意：A和C的长度选择 length = min(k, n-k)
 *
 * 建模三：
 * 现将整个数组逆转 reverse，然后再分别逆转前k个和后n-k个
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 *
 * // FIXME: 02/09/2017 还是没注意引用传递
 <code>
 public void rotate(int[] nums, int k) {
 k = k % nums.length;

 int[] newnums = new int[nums.length];
 for(int i = 0; i < k; i++) {
 newnums[i] = nums[nums.length - k + i];
 }
 for(int i = k; i < nums.length; i++) {
 newnums[i] = nums[i - k];
 }
 nums = newnums;
 }
 将得到的新数组赋值给旧数组，“nums = newnums;”有问题。因为参数传递的是复制的引用地址
 </code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * {@link java.util.Arrays#copyOf(int[], int)}要有长度参数
 * @author changgan on 02/09/2017
 * @see
 * @since cgs-leetcode on  02/09/2017
 */
public class RotateArray {

    // 引用问题-------------
//    public void rotate(int[] nums, int k) {
//        k = k % nums.length;
//
//        int[] newnums = new int[nums.length];
//        for(int i = 0; i < k; i++) {
//            newnums[i] = nums[nums.length - k + i];
//        }
//        for(int i = k; i < nums.length; i++) {
//            newnums[i] = nums[i - k];
//        }
//        nums = newnums;
//    }
    // 引用问题-------------

    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int[] newnums = Arrays.copyOf(nums, nums.length);
        for(int i = 0; i < k; i++) {
            nums[i] = newnums[nums.length - k + i];
        }
        for(int i = k; i < nums.length; i++) {
            nums[i] = newnums[i - k];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2};
        new RotateArray().rotate(nums, 1);
        System.out.println();
    }
}
