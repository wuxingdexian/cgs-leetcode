package dynamicprogramming.easy;

import java.util.Stack;

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
 * 从以前解法看，可以使用单调递增栈来计算
 *
 * 2. 算法范式：
 * 3. 算法：
 *
 * 2019-2-14使用
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

    /**
     * 使用递增栈实现
     * @param prices
     * @return
     */
    public int maxProfitWithAscendingStack(int[] prices) {
        Stack<Integer> stack = new Stack<>();

        int sum = 0;
        for(int i=0;i<prices.length;i++) {
            if(stack.isEmpty() || stack.peek()<=prices[i]) stack.push(prices[i]);
            else {
                sum=sum+stackSum(stack);
                stack.push(prices[i]);
            }
        }

        sum=sum+stackSum(stack);
        return sum;
    }

    private int stackSum(Stack<Integer> stack) {
        if(stack.isEmpty()) return 0;
        Integer one = stack.pop();
        Integer two = null;
        while(!stack.isEmpty()) {
            two=stack.pop();
        }
        if(two == null) return 0;
        else return one - two;
    }
}
