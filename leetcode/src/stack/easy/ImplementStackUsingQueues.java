package stack.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/implement-stack-using-queues/description/" />
 * 225. Implement Stack using Queues
 * Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.
 Notes:
 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

 * <p>
 * 0. 本质：
 * 1. 建模：
 *
 * 使用两个队列
 * 主队列，负责保存数据，在连续pop时负责移动数据；从队列，注意负责push和peek操作
 * mainQueue: _,_,_,_,_,_,_,_,_,_,_,_,_,_,_,      slaveQueue: _,_,_,_
 * push = slaveQueue.add() + mainQueue.add(slaveQueue.remove()), make sure slaveQueue contain only one element
 * peek = slaveQueue.peek()
 * pop = slaveQueue.remove() + slave.add 执行mainQueue.size()次数，然后mainQueue.add(slave.remove()) 执行 slaveQueue.size()-1次数
 *
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：// FIXME: 28/08/2017 只使用一个队列即可，插入时永远把最新插入的值放在队手！！！ 666
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 28/08/2017
 * @see
 * @since cgs-leetcode on  28/08/2017
 */
public class ImplementStackUsingQueues {
    Queue<Integer> mainQueue;
    Queue<Integer> slaveQueue;
    /** Initialize your data structure here. */
//    public MyStack() {
    public ImplementStackUsingQueues() {
        mainQueue = new LinkedList();
        slaveQueue = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        slaveQueue.add(x);
        pop2Another(slaveQueue, slaveQueue.size() - 1, mainQueue);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(!slaveQueue.isEmpty()) {
            int val = slaveQueue.remove();
            if(!mainQueue.isEmpty()) {
                pop2Another(mainQueue, mainQueue.size(), slaveQueue);
                pop2Another(slaveQueue, slaveQueue.size() - 1, mainQueue);
            }
            return val;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /** Get the top element. */
    public int top() {
        if(!slaveQueue.isEmpty()) {
            return slaveQueue.peek();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return slaveQueue.isEmpty() && mainQueue.isEmpty();
    }

    private void pop2Another(Queue<Integer> fromQueue, int moveSize, Queue<Integer> toQueue) {
        while(moveSize-- > 0) {
            toQueue.add(fromQueue.remove());
        }
    }

    // -------------------666方案-----------吊吊吊--------------------
    class MyStack {
        Queue<Integer> queue = new LinkedList<Integer>();
        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            for (int i = 1; i < queue.size(); i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    // -------------------666方案-----------吊吊吊--------------------

    public static void main(String[] args) {

        ImplementStackUsingQueues queues = new ImplementStackUsingQueues();
        queues.push(1);
        queues.push(2);
        queues.push(3);
        queues.pop();
        queues.pop();
        queues.pop();
        int top = queues.top();
        System.out.println(top);
    }
}
