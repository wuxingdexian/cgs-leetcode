package depthfirstsearch.mediu;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/matchsticks-to-square/description/" />
 * 473. Matchsticks to Square
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

 Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

 Example 1:
 Input: [1,1,2,2,2]
 Output: true

 Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 Example 2:
 Input: [3,3,3,3,4]
 Output: false

 Explanation: You cannot find a way to form a square with all the matchsticks.
 Note:
 The length sum of the given matchsticks is in the range of 0 to 10^9.
 The length of the given matchstick array will not exceed 15.
 * <p>
 * 1. 建模：
 * 总和除以4，即得到每边的长度
 * （1）决策树。找到四次不重合子集，他们的和分别等于变长，并且四个子集的并为整个集合。
 * 有序对(side_length[], remainder[])找四次，使得side_length[]的和为边长，在第四次时判断remainder是否为空
 * 先按正序排序
 * 如                        [],[1,2,1,2,2]
 *                                  |
 *                          [1],[2,1,2,2]
 *                          ....
 * 判断条件：（1）side_length小于边长，继续深度遍历下一次；（2）side_length相等边长，执行下一次；（3）side_length大于边长，则返回失败；
 *
 * （2）recurrence relation。找四次变长为固定长度。这应该是0-1背包问题。// TODO: 15/08/2017
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {

//        Arrays.sort(nums);

        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int sideLength = sum / 4;
        if(sum % 4 != 0) {
            return false;
        }
        boolean[] flag = new boolean[nums.length];
        return isSquare(nums, flag, 0, sideLength, 0) && isSquare(nums, flag, 0, sideLength, 0) &&
                isSquare(nums, flag, 0, sideLength, 0);

    }

    boolean isSquare(int[] nums, boolean[] flag,  int index, int sideLength, int tmpSum) {

        for(int i = index; i < nums.length; i++) {
            if(!flag[i]) {
                flag[i] = !flag[i];
                tmpSum += nums[i];
                if(tmpSum == sideLength) {
                    return true;
                } else if(tmpSum < sideLength) {
                    boolean isSuccess = isSquare(nums, flag, index + 1, sideLength, tmpSum);
                    if (isSuccess) {
                        return true;
                    }
                } else {
                    tmpSum -= nums[i];
                    flag[i] = !flag[i];
                    boolean isSuccess = isSquare(nums, flag, index + 1, sideLength, tmpSum);
                    if (isSuccess) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
//        int[] nums = {1,1,2,2,2};
        int[] nums = {5,5,5,5,4,4,4,4,3,3,3,3};
        boolean makesquare = new MatchsticksToSquare().makesquare(nums);
        System.out.println(makesquare);
    }
}
