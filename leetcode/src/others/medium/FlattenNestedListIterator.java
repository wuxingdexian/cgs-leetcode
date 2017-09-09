package others.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/flatten-nested-list-iterator/description/" />
 * 341. Flatten Nested List Iterator
 * Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

 * <p>
 * 0. 本质：集合、序列
 *
 * 1. 建模：
 * 将整个集合元素扁平化，都放在大集合里，即变为一维数据
 * 2. 算法范式：
 * 3. 算法：
 * 两种方法
 * （1）预处理，遍历所有的元素，将所有元素保存到一维数组
 *
 * （2）保存读取路径，用栈来保存路径 TODO
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2017
 * @see
 * @since cgs-leetcode on  08/09/2017
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
    List<Integer> cache;
    int counter;
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        cache = new ArrayList();
        getElements(nestedList);
        counter = cache.size();
    }

    @Override
    public Integer next() {
        counter--;
        return cache.get(cache.size() - (counter + 1));
    }

    @Override
    public boolean hasNext() {
        return counter > 0;
    }

    private void getElements(List<NestedInteger> nestedList) {
        for(NestedInteger e: nestedList) {
            if(e.isInteger()) {
                cache.add(e.getInteger());
            } else {
                getElements(e.getList());
            }
        }
    }

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }

    public static void main(String[] args) {
        Integer a = 1;
        if (a instanceof Integer) {

        }
    }
}
