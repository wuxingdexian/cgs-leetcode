package dynamicprogramming.hard;

import dynamicprogramming.easy.BestTimeToBuyAndSellStock;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/" />
 * 123. Best Time to Buy and Sell Stock III
 *
 * <p>
 * 1. 建模：
 * （1）recurrence relation
 * 参考{@link BestTimeToBuyAndSellStock} 双向执行分别执行一次DP，然后遍历，找到左右相加后最大的值
 * https://segmentfault.com/a/1190000003483697 这里有思路提示
 *
 * （2）分治法
 * 按中点分，找到两边最大的值，然后两边相加，同时注意合并时中间附近数据递增的情况
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
 * @author changgan on 21/08/2017
 * @see
 * @since cgs-leetcode on  21/08/2017
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        int[] nums = {10,7,9,2,4,3,8,11};
        int i = new BestTimeToBuyAndSellStockIII().maxProfit(nums);
        System.out.println(i);
    }
}
