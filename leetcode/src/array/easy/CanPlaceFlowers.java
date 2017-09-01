package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/can-place-flowers/description/" />
 * 605. Can Place Flowers
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.
 * <p>
 * 0. 本质：计数
 * 1. 建模：flowerbed[i] = 1, when flowerbed[i-1]=0 && flowerbed[i+1]=0
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 01/09/2017
 * @see
 * @since cgs-leetcode on  01/09/2017
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int counter = 0;
        for(int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0) {
                if(((i - 1 > 0 && flowerbed[i - 1] == 0) || i - 1 < 0)
                        && ((i + 1 < flowerbed.length && flowerbed[i + 1] == 0)) || i + 1 >= flowerbed.length) {
                    flowerbed[i] = 1;
                    counter++;
                    i++;
                }
            } else if(flowerbed[i] == 1) {
                i++;
            }
        }
        return n <= counter;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,0,0};
        boolean b = new CanPlaceFlowers().canPlaceFlowers(nums, 1);
        System.out.println(b);
    }

}
