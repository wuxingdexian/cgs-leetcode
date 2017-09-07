package others.medium;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/shuffle-an-array/description/" />
 * 384. Shuffle an Array
 * Shuffle a set of numbers without duplicates.

 Example:

 // Init an array with set 1, 2, and 3.
 int[] nums = {1,2,3};
 Solution solution = new Solution(nums);

 // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 solution.shuffle();

 // Resets the array back to its original configuration [1,2,3].
 solution.reset();

 // Returns the random shuffling of array [1,2,3].
 solution.shuffle();
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * TODO （1）{@link Arrays#copyOf(int[], int)}第二个是要拷贝的长度；（2）{@link Random#nextInt(int)} 参数int在随机数中不会包含 exclusive <code>new Random().nextInt(preserved1.length - i)</code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class ShuffleAnArray {
    int[] preserved;
    int[] preserved1;
    public ShuffleAnArray(int[] nums) {
        preserved = Arrays.copyOf(nums, nums.length);
        preserved1 = Arrays.copyOf(nums, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return preserved;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i = 0; i < preserved1.length - 1; i++) {
            int ramdonNum = new Random().nextInt(preserved1.length - i);
            int tmp = preserved1[i];
            preserved1[i] = preserved1[i + ramdonNum];
            preserved1[i + ramdonNum] = tmp;
        }
        return preserved1;
    }
}
