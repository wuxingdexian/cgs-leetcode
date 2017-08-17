package divideandconquer.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/majority-element/description/" />
 * 169. Majority Element
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * 1. 建模：
 * recurrence relation
 * count(a(n)) = count(a(i)) + 1，此时a(n)=a(i)
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：map方法在查看个数大于二分之一长度时即得到答案
 * 6. 启发：
 * 7. jdk知识：
 * {@link Map.Entry} 大写
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.get(nums[i]) != null? countMap.get(nums[i])  + 1: 0);
            if(countMap.get(nums[i]) > (nums.length >>> 1)) {
                return nums[i];
            }
        }

        int majorityElement = nums[0];
        int count = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            if(count < entry.getValue()) {
                majorityElement = entry.getKey();
                count = entry.getValue();
            }
        }
        return majorityElement;
    }

    /**
     * 如果题设肯定有majority，那排序后中间的值肯定是
     * @param nums
     * @return
     */
    public int majorityElementStandard1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElementStandard2(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;

        }
        return major;
    }
}
