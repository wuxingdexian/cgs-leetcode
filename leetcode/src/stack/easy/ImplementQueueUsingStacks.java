package stack.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/implement-queue-using-stacks/description/" />
 * 232. Implement Queue using Stacks
 * Implement the following operations of a queue using stacks.

 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.
 Notes:
 You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since cgs-leetcode on  14/08/2017
 */
public class ImplementQueueUsingStacks {
    //     int[0] 用作哨兵
    int[] stack;
    int index;
    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {
        stack = new int[10000];
        index = 0;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack[++index] = x;
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return change();
    }

    private int change() {
        ImplementQueueUsingStacks tmpQueue = new ImplementQueueUsingStacks();
        while(index > 0) {
            tmpQueue.push(stackPop());
        }

        int popValue = tmpQueue.stackPop();
        while(tmpQueue.getIndex() > 0) {
            push(tmpQueue.stackPop());
        }
        return popValue;
    }

    public int stackPop() {
        if(index > 1) {
            return stack[index--];
        }
        return Integer.MIN_VALUE;
    }

    /** Get the front element. */
    public int peek() {
        return stack[index];
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return index == 0;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
