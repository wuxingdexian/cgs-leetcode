package stack.easy;

import java.util.Stack;

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
    Stack<Integer> mainStack;
    Stack<Integer> slaveStack;
    /** Initialize your data structure here. */
//    public MyQueue() {
    public ImplementQueueUsingStacks() {
        mainStack = new Stack();
        slaveStack = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(!slaveStack.isEmpty()) {
            pushAll(slaveStack, mainStack);
        }
        mainStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!slaveStack.isEmpty()) {
            return slaveStack.pop();
        } else {
            pushAll(mainStack, slaveStack);
            return slaveStack.pop();
        }
    }

    /** Get the front element. */
    public int peek() {
        if(!slaveStack.isEmpty()) {
            return slaveStack.peek();
        } else {
            pushAll(mainStack, slaveStack);
            return slaveStack.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return mainStack.isEmpty() && slaveStack.isEmpty();
    }

    // to delay moving frequently
    private void pushAll(Stack<Integer> fromStack, Stack<Integer> toStack) {
        while(!fromStack.isEmpty()) {
            toStack.push(fromStack.pop());
        }
    }
}
