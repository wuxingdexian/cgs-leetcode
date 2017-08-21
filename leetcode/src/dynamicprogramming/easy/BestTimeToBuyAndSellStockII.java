package dynamicprogramming.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/" />
 * 122. Best Time to Buy and Sell Stock II
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p>
 * 1. 建模：
 * 给出一些数据，画个平面图，很容易得出答案
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
public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                max += (prices[i] - prices[i-1]);
            }
        }
        return max;
    }
}