package stack.easy;

import others.hard.SlidingWindowMaximum;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/min-stack/discuss/" />
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 *
 * <p>
 * 0. 本质：偏序、序列
 * 1. 建模：
 * 序列，这个由栈的特性自然保持
 * minVal(i)=min(minValu(i-1), a[i])，关键问题在getMin()时复杂度为常数。
 *
 * // TODO: 24/08/2017 网上有个人的解感觉不错，封装类，用该类压栈，类分装了栈底到该元素的最小值，recurrence relation(dynamic programming) 666 缓存用过的值
 * 2. 算法范式：
 * 3. 算法：
 * 两个解法
 * 解法一：
 * 包装类，增加一个字段min，保存截止当前数据时的最小值。
 *
 * 解法二：
 * 利用另一个栈/队列，保存的当前的最小值，类似{@link SlidingWindowMaximum}滑动窗口的单调队列，只需尾部插入和删除
 *
 * 4. 数据结构：
 * 解法二：
 * 一个栈：用来保存数据的顺序性；
 * 另一个栈/队列：用来记录数据
 *
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link ArrayList#add(Object)}和{@link ArrayList#set(int, Object)}两个的区别：add会自动在末尾追加记录，当长度不够时，会自动追加；而set只是在已有的值进行改变，所有不会对动态增加数组的长度。
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class MinStack {

    //-----------------------------------使用ArrayList模拟栈--有点撮-------------------------------------
//    private List<ValWrapper> valWapper;
//    int index;
//    /** initialize your data structure here. */
//    public MinStack() {
//        this.valWapper = new ArrayList();
//        this.index = 0;
//    }
//
//    public void push(int x) {
//        int minimal = Math.min(x, index <= 0? Integer.MAX_VALUE: valWapper.get(index - 1).minimal);
//        // FIXME: 28/08/2017 {@link ArrayList#set(int, Object)}只是在已有的值进行改变，所有不会对动态增加数组的长度。
//        valWapper.set(index, new ValWrapper(x, minimal));
//        index++;
//    }
//
//    public void pop() {
//        index--;
//    }
//
//    public int top() {
//        if(index < 0 || valWapper.get(index - 1) == null) {
//            return Integer.MAX_VALUE;
//        }
//        return valWapper.get(index - 1).val;
//    }
//
//    public int getMin() {
//        if(index < 0 || valWapper.get(index - 1) == null) {
//            return Integer.MAX_VALUE;
//        }
//        return valWapper.get(index - 1).minimal;
//    }

    class ValWrapper{
        int val;
        int minimal;
        ValWrapper(int val, int minimal) {
            this.val = val;
            this.minimal = minimal;
        }
    }
    //-----------------------------------使用ArrayList模拟栈---------------------------------------


    //---------------------------------使用内置stack-方便快速--------------------------------------------
//    /** initialize your data structure here. */
//    Stack<ValWrapper> stackWrapper;
//    public MinStack() {
//        this.stackWrapper = new Stack();
//    }
//
//    public void push(int x) {
//        int minimal = x;
//        if(!stackWrapper.isEmpty()) {
//            minimal = Math.min(minimal, stackWrapper.peek().minimal);
//        }
//        stackWrapper.push(new ValWrapper(x, minimal));
//    }
//
//    public void pop() {
//        if(!stackWrapper.isEmpty()) {
//            stackWrapper.pop();
//        }
//    }
//
//    public int top() {
//        if(!stackWrapper.isEmpty()) {
//            return stackWrapper.peek().val;
//        }
//        return Integer.MAX_VALUE;
//    }
//
//    public int getMin() {
//        if(!stackWrapper.isEmpty()) {
//            return stackWrapper.peek().minimal;
//        }
//        return Integer.MAX_VALUE;
//    }
    //---------------------------------使用内置stack-方便快速--------------------------------------------

    // --------------------------------------jdk stack--double stack---------------------------
    Stack<Integer> valStack;
    Stack<Integer> minimalStack;
    public MinStack() {
        this.valStack = new Stack();
        this.minimalStack = new Stack();
    }

    public void push(int x) {
        valStack.push(x);

        if(!minimalStack.isEmpty()) {
            // 简单处理，这里相同的值也会保存
            if(x <= minimalStack.peek()) {
                minimalStack.push(x);
            }
        } else {
            minimalStack.push(x);
        }
    }

    public void pop() {

        if(valStack.isEmpty()) {
            return;
        }
        int val = valStack.pop();
        if(minimalStack.isEmpty()) {
            return;
        }

        if(val == minimalStack.peek()) {
            minimalStack.pop();
        }
    }

    public int top() {
        if(!valStack.isEmpty()) {
            return valStack.peek();
        }
        return Integer.MAX_VALUE;
    }

    public int getMin() {
        if(!minimalStack.isEmpty()) {
            return minimalStack.peek();
        }
        return Integer.MAX_VALUE;
    }



}
