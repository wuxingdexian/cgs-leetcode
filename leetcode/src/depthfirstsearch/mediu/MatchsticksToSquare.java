package depthfirstsearch.mediu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
 * 本质：通过到目前集合划分情况总结看，这类问题和{@link dynamicprogramming.medium.PartitionEqualSubsetSum}同属将集合N等分的问题。
 * N等分中的每个都等价于一次0-1背包问题。
 * 分析至此，设集合为set[]，总和为sum(set)，要做N等分，division(i)为第i次等分。
 * 建模：
 * 设A={a(1),a(2),...,a(n)},W={w(1),w(2),...,w(n)},V={w(1),w(2),...,w(n)}，取物品价值集合等于物品重量集合
 * 在截止物品a(i)时，在限制最大重量为w的前提下，设有序对max_V(a(i), w), where i={x|0<=x<=n}, w={x|0<=x<=sum(W)}, and sum(W)是所有物品的重量和
 * 则有recurrence relation：
 * max_V(a(i), w)=max_V(a(i-1), w), when w(i) < w
 * max_V(a(i), w)=max(max_V(a(i-1), w), max_V(a(i-1), w-w(i)) + v(i)), when w(i) >= w
 *
 * 有两种思路：
 * （1）分析set是否可以找到子集subset，使得sum(subset) = sum(set) / N。
 * 若找到则把subset从set中取出，然后继续执行该步骤，看set中是否能找到另一个子集使得和等于sum(set) / N。执行N次，只要都成功，则证明找到答案。
 * 但是这个方法有个棘手的问题，如何找到subset。这里子问题只是记录了背包最大价格。// TODO: 19/08/2017 DP过程找元素这个问题需要继续思考
 * （2）可以绕开找subset元素，直接将问题看做是0-1背包最大价格取sum，执行问题求解后，看division(i)的那个子问题的实际最大价值等于该位置限定的最大价值，
 * 若所有division(i)都成立，则说明找到答案。
 *
 *
 *
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

    /*
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
        return isSquare(nums, 0, sideLength, new int[4]);
//        && isSquare(nums, 0, sideLength, 0, flag)
//                && isSquare(nums, 0, sideLength, 0, flag)

    }

    boolean isSquare(int[] nums,  int index, int sideLength, int[] tmpSum) {


        if (index >= nums.length) {
            if(tmpSum[0] == sideLength && tmpSum[1] == sideLength && tmpSum[2] == sideLength) {
                return true;
            }
            return false;
        }

        for(int i = 0; i < 4; i++) {
            if (tmpSum[i] + nums[index] > sideLength) {
                continue;
            }
            tmpSum[i] += nums[i];
            if (isSquare(nums, index + 1, sideLength, tmpSum)) {
                return true;
            }
            tmpSum[i] -= nums[i];
        }
        return false;
    }
*/

    //-----------------------dynamic programming------------------------
    // FIXME: 19/08/2017 目前虽然能找到轨迹节点，但是拿到的节点却不一定每次都能划分，如[5,5,5,5,4,4,4,4,3,3,3,3]如果一次拿到3个4，那就玩不转了。
    /*
    public boolean makesquare(int[] nums) {

        if(null == nums || nums.length < 4) {
            return false;
        }

        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int sideLength = sum / 4;
        if(sideLength + sideLength + sideLength + sideLength != sum) {
            return false;
        }
        List<Integer> numList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        while (!numList.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            boolean combination = getCombination(numList, path, sideLength);
            if (!combination) {
                return false;
            }
            for (Integer numIndex: path) {
                // 将出现相等的值删掉
                numList.remove(numIndex);
            }
        }
        return false;
    }

    boolean getCombination(List<Integer> numList, List<Integer> path, int sideLength) {
        int[][] values = new int[numList.size() + 1][sideLength + 1];

        // paths用来记录轨迹
        List[][] paths = new List[numList.size() + 1][sideLength + 1];
        for (int w = 0; w < paths[0].length; w++) {
            paths[0][w] = new ArrayList();
        }
        for(int i = 1; i < values.length; i++) {
            for(int w = 0; w < values[0].length; w++) {
                paths[i][w] = new ArrayList();
                if(numList.get(i - 1) > w) {
                    values[i][w] = values[i - 1][w];
                    paths[i][w].addAll(paths[i - 1][w]);
                } else{
//                    values[i][w] = Math.max(values[i - 1][w], values[i - 1][w - list.get(i - 1)] + list.get(i - 1));
                    if(values[i - 1][w] > values[i - 1][w - numList.get(i - 1)] + numList.get(i - 1)) {
                        values[i][w] = values[i - 1][w];
                        paths[i][w].addAll(paths[i - 1][w]);
                    } else {
                        values[i][w] = values[i - 1][w - numList.get(i - 1)] + numList.get(i - 1);
                        paths[i][w].addAll(paths[i - 1][w - numList.get(i - 1)]);
                        // 将值保存，或者另一个策略是保存index
                        paths[i][w].add(numList.get(i - 1));

                    }
                }
            }
        }
        if(values[values.length - 1][values[0].length - 1] == sideLength) {
            // 使用addAll操作，不要用=
            path.addAll(paths[values.length - 1][values[0].length - 1]);
            return true;
        }
        return false;
    }
//    */
    //-----------------------dynamic programming------------------------

    public boolean makesquareStandard(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;

        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[index] > target) continue;
            sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
            sums[i] -= nums[index];
        }

        return false;
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,2,2,2};
        int[] nums = {5,5,5,5,4,4,4,4,3,3,3,3};
//        int[] nums = {2,2,2,2,2,6};
//        int[] nums = {3,3,3,3,4};
        boolean makesquare = new MatchsticksToSquare().makesquareStandard(nums);
        System.out.println(makesquare);
    }
}
