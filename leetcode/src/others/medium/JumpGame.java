package others.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/jump-game/description/" />
 * 55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.
 * <p>
 * 0. 本质：序列
 * 1. 建模：nums[next]=num[num[current]+current]
 * // FIXME: 22/10/2017 因为审题问题，上面建模错误
 *
 * 如[2,1,0,0]返回false；[2,5,0,0]返回true，得知，“maximum jump length”不是要一定跳这么长，而是可以小于等于，shit
 *
 * 再次建模，将每个元素能覆盖的范围都叠加起来，如果出现断裂，则false，否则true；如
 * [4,0,3,1,0,2,0,1]的覆盖下标范围是[0-4,1-1,2-5,3-4,4-4,5-7,6-6,7-]，中间无断层，最后能达到7，true
 * [1,0,3,1,0,2,0,1]的覆盖下标范围是[0-1,1-1,2-5,3-4,4-4,5-7,6-6,7-]，1到2出现断层，false
 * 2. 算法范式：
 * （1）backtracking
 * （2）dynamic programming
 * 3. 算法：
 * （0）设定指针index，指向元素位置。如果index超过数组长度，则返回false
 * （1）如果当前值是最后一个元素，返回true，否则做如下判断
 * （2）如果当前值小于等于0，则返回false
 * （3）否则，index=index+num[index]
 * // FIXME: 22/10/2017 因为审题问题，上面建模错误
 *
 * 再次建模后，有两种方法
 * backtracking
 * （1）从末尾开始，不断存在对接上的前一个元素，如果对接不上，则回退，比对下一个元素是否能进行。使用栈来保存路径；
 *
 * dynamic programming
 * （1）使用两个临时变量，maxCover_index表示截止current_index时能覆盖最远的index。
 * （2）如果maxCover_index大于等于last_index，则true；
 * （3）如果maxCover_index < current_index，则false；
 * （4）current_index++；然后重复执行（1）到（4）
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/10/2017
 * @see
 * @since cgs-leetcode on  22/10/2017
 */
public class JumpGame {

    // FIXME: 22/10/2017 错误审题，错误答案
//    public boolean canJump(int[] nums) {
//        if(nums==null || nums.length==0) {
//            return true;
//        }
//
//        int index = 0;
//        while(index < nums.length) {
//            if(index == nums.length-1) {
//                return true;
//            }
//            if(nums[index]<=0) {
//                return false;
//            }
//            index += nums[index];
//        }
//        return false;
//    }

    // dynamic programing
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int maxCoverIndex = 0;
        int currentIndex = 0;
        while (currentIndex < nums.length) {
            if(maxCoverIndex >= nums.length - 1) {
                return true;
            }
            if (maxCoverIndex < currentIndex) {
                return false;
            }
            maxCoverIndex = Math.max(maxCoverIndex, currentIndex + nums[currentIndex]);
            currentIndex++;
        }
        return false;

    }

    // TODO: 22/10/2017 可以尝试使用backtracking方式实现


    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("0", "1", "_1", "_2", "_a", "_abc", "_a_a", "&", "A", "~", "a", "好", "人", "。");
        stringList.sort(String::compareTo);
        System.out.println(stringList);

        HashMap<String, AtomicInteger> integerHashMap1 = new HashMap<>();
        integerHashMap1.put("a", new AtomicInteger(0));
        integerHashMap1.put("b", new AtomicInteger(0));
        integerHashMap1.put("c", new AtomicInteger(0));

        System.out.println(integerHashMap1);
        integerHashMap1.get("a").addAndGet(2);
        System.out.println(integerHashMap1);

        HashMap<String, AtomicInteger> integerHashMap2 = new HashMap<>(integerHashMap1);
        System.out.println(integerHashMap2);
        integerHashMap2.get("a").incrementAndGet();
        System.out.println(integerHashMap2);
        System.out.println();
        System.out.println(integerHashMap1);

        HashMap<String, AtomicInteger> clone = (HashMap<String, AtomicInteger>) integerHashMap1.clone();
        System.out.println(clone);
        clone.get("a").incrementAndGet();
        System.out.println(clone);
        System.out.println();
        System.out.println(integerHashMap1);
        System.out.println();
        System.out.println(integerHashMap2);


        Object object = new AtomicInteger(0);


    }

}
