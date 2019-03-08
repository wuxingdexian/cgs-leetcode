package stack.hard;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * 假设不限制交易次数，找最大的解，是否可以这么做？
 * 如{8,8,9,2,4,3,8,11}
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 此处尝试使用递增栈方实现，区别于DP解
 *
 * 假设不限制交易次数，得到通解：
 * 在递增区间的最大交易额为尾减去头，遍历一次数组，可以找出所有的递增区间，并得到所有区间的最大收益，取前两个最大收益即得到解
 *
 * 在求递增区间时，使用两个空间大小的递增栈：
 * （1）当栈为空，则压栈；
 * （2）当值比栈顶大，
 *      （a）且栈有一个元素，则压栈；
 *      （b）且栈有两个元素，则栈顶出栈，当前值压栈；
 * （3）当值比栈顶小，
 *      （a）且栈有一个元素，则栈顶出栈，当前值压栈；
 *      （b）且栈有两个元素，则上一个递增区间结束，计算上一个递增区间的最大收益：栈顶减去栈底；然后将当前值压栈；
 *
 *  得到所有递增区间最大收益，可以（1）排序取最大两个；（2）或使用一个大小为2的递减栈计算
 * 2. 算法范式：
 * 3. 算法：
 * 此处尝试使用递增栈方式实现
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/10/2018
 * @see
 * @since cgs-leetcode on  14/10/2018
 */
public class BestTimeToBuyAndSellStockWithInfiniteTimes {

    // --start-----------------------increasing stack 递增栈求解-------------------
    public int maxProfit(int[] prices) {
        Stack<Integer> increasingStack = new Stack();
        Stack<Integer> decreasingStack = new Stack();

        for(int num: prices) {
            if(increasingStack.isEmpty()) {
                increasingStack.push(num);
            } else if(num > increasingStack.peek()) {
                while(increasingStack.size()>1) {
                    increasingStack.pop();
                }
                increasingStack.push(num);
            } else if(num < increasingStack.peek()) {
                if(increasingStack.size()==1) {
                    increasingStack.pop();
                    increasingStack.push(num);
                } else{
                    Integer currentMaxProfit = getCurrentMaxProfit(increasingStack);
                    saveMaxProfit(decreasingStack, currentMaxProfit);
                    increasingStack.push(num);
                }
            }
        }

        // 最后一次还可能有值在递增栈中
        if(increasingStack.size()>1) {
            Integer currentMaxProfit = getCurrentMaxProfit(increasingStack);
            saveMaxProfit(decreasingStack, currentMaxProfit);
        }

        int sum = 0;
        while(!decreasingStack.isEmpty()) {
            sum = sum + decreasingStack.pop();
        }
        return sum;

    }

    private Integer getCurrentMaxProfit(Stack<Integer> increasingStack) {
        Integer max = increasingStack.pop();
        Integer min = null;
        while(!increasingStack.isEmpty()) {
            min = increasingStack.pop();
        }
        return max-min;
    }

    private void saveMaxProfit(Stack<Integer> decreasingStack, Integer maxProfit) {
        if(decreasingStack.isEmpty()) {
            decreasingStack.push(maxProfit);
        } else {
            Integer tmp = null;
            while (!decreasingStack.isEmpty() && decreasingStack.peek() < maxProfit) {
                tmp = decreasingStack.pop();
            }
            if(decreasingStack.size() < 2) {
                decreasingStack.push(maxProfit);
            }
            if (decreasingStack.size() < 2 && tmp != null) {
                decreasingStack.push(tmp);
            }
        }
    }
    // --end-----------------------increasing stack 递增栈求解-------------------

    public static void main(String[] args) {
        int[] nums = {8,8,9,2,4,3,8,11};
        int i = new BestTimeToBuyAndSellStockWithInfiniteTimes().maxProfit(nums);
        System.out.println(i);
    }
}
