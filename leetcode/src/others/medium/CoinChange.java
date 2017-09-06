package others.medium;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/coin-change/description/" />
 * 322. Coin Change
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1)

 Example 2:
 coins = [2], amount = 3
 return -1.

 Note:
 You may assume that you have an infinite number of each kind of coin.
 * <p>
 * 0. 本质：combinatoric combination counting
 * 1. 建模：
 * recurrence relation
 * f(n) = min(f(n-coins[1])+1, f(n-coins[2])+1,..., f(n-coins[i])+1, ...) where n-coins[i] > 1
 * 思路，f(n)为金额n置换为硬币的最小个数，最后一次分别使用不同的应别置换，
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 06/09/2017
 * @see
 * @since cgs-leetcode on  06/09/2017
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount+1];
        Arrays.sort(coins);
        Arrays.fill(cache, Short.MAX_VALUE);
        cache[0] = 0;

        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(i >= coins[j]) {
                    cache[i] = Math.min(cache[i-coins[j]]+1, cache[i]);
                } else {
                    break;
                }
            }
        }
        return cache[amount] >= Short.MAX_VALUE? -1: cache[amount];
    }
}
