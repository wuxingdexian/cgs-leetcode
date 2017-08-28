package others.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sliding-window-maximum/description/" />
 * 239. Sliding Window Maximum
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note:
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?
 * <p>
 * 0. 本质：relation，偏序，关于“大于等于”关系
 * 1. 建模：
 * max(i) = max(nums[i],nums[i+1],...,nums[i+k-1],), i={1,2,...,k}
 * 2. 算法范式：
 * 3. 算法：
 * （1）大顶堆建模
 *      1）先为前面k个元素建模，得到大顶堆，此时前k个元素最大值即为栈顶，输出。
 *      2）窗口前移一个位置，在大顶堆中定位到要删除的那个元素，用最新元素替换，并维持堆得执行，最后输出堆顶。
 *      3）依次执行上面两个步骤
 * 复杂度O(n*log(n))
 *
 * （2）直观方式
 * 设定window(i,i+k)，窗口有从i到i+k-1的k个元素
 *      1）找到window(i,i+k)前面k个元素的最大元素x，得到其位置position(x)，若position(x)>i，则窗口逐渐前移position(x)-i个位置，每次移动需要不断更新最大值。
 *      2）若前移position(x)-i个位置后，最大值还是x，且位置还是position(x)，窗口前移一位，重新执行 1）
 *      3）若前移position(x)-i个位置后，最大值有更新，并前移了，那此时按照 1）后半句的策略，逐渐执行。
 *      因为position(x)>i，则窗口前移position(x)-i个位置是安全的。
 * 最坏复杂度：O(k*(n-k+1))，如非递增序列，如10,9,8,7,6,5,4,3,2,1
 *
 * （3）单调队列
 * 队列元素，有序对：(value, index)
 * 建立单调递减队列（记忆功能），执行如下操作：
 *  1）尾部插入：将待插入元素和队列尾部元素比较，如果
 *      a）尾部元素小于等于带插入元素，则队尾出队，继续执行该操作，直到队尾元素的值大于待插入元素或队列为空，则该元素到队列尾部
 *      b）尾部元素大于带插入元素，则不插入
 *  2）头部删除：如果头部的index不在窗口的范围内，则删除，公式：index + k - 1 < i，即index < i + k - 1，此时删除队首元素。
 * 复杂度O(n)
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * TODO 问题分析/总结：可以从集合+函数的角度来思考，但是复杂度就会比较高了。比如这里的domain就是不同窗口内的k个元素组成的集合（是整数集合的子集）作为元素而组成的集合，codomain是整数集合，function是max(i)，
 * TODO 将domain中不同子集中最大的值找出来，映射到codomain的整数。 这个想法的实现正好是brute force，可见在本质属于关系relation的题目中，集合+function的办法不好。
 * TODO 思维突破：既然是关系relation，那么肯定具有关系的四个性质中的一个或多个：自反性、对称性、反对称性、传递性。性质组合又可以得到特定关系，如而同时具有“自反性、对称性、传递性”的则是全序关系，
 * TODO 同时具备“自反性、反对称性、传递性”三个性质的则具有的偏序。此外还有等价关系。里面最重要的是偏序关系，在很多场景都有应用，如整数的自然排序、字母的字典排序、格（信息流流转）、拓扑排序等。
 *
 * TODO 关系的呈现，又可以用其他模型或数据结构表示，如图、矩阵。如《cracking the coding interview》，在8.18章节的18.13题目，找单词组成最大矩形，本质“组合+关系”，从关系角度思考，肯定有有序对。
 * 此时设定有序对(head_character, tail_character)，关系为同一个单词的首尾两个字符组成有序对。得到有序，可以进一步具化模型，（1）使用有向多边图Directed multigraph表示，（2）使用矩阵表示。
 * 这里建议使用矩阵，26*26的矩阵，不会很大。
 * 扩展有序对：(length(word), head_character, tail_character)，加入长度，此时长度相同的单词都会规整在一起，使用Map+List数据结构不错。
 *
 * 关系/组合题目
 * （0）关系或关系式（函数），这个首先要明确，这是根本。如最大值，那关系式“大于等于”，关系式（函数）maxValue = max(a[0], a[1], ..., a[n])
 * （1）很重要的一个优化方向是利用已经得到的先前结果或遍历过的元素。利用各种数据结构来保存或缓存想要的值。这里单调队列和堆其实都是这样一个思路啊。还有recurrence relation相关的dynamic programming
 * （2）排序，如快排、合并排序等，这里堆排序由于结构特殊，同时能保存并利用之前的元素！！！
 * （3）由于是关系，那各个元素符合这个关系，我想应该存在一个recurrence relation，关联不同的元素或在元素上操作的function，想想recurrence relation相关的dynamic programming
 *
 * 7. jdk知识：{@link Deque}队列的函数，其他方法名字不是很直观，还是使用什么remove、removeLast、addLast、getFirst等，容易理解
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindowUseQueue(nums, k);
    }

    //-------------------------这是使用单调队列来实现--------------------------------
    private int[] maxSlidingWindowUseQueue(int[] nums, int k) {
        if(k == 0 || nums.length == 0) {
            return new int[0];
        }

        int[] solutions = new int[nums.length - k + 1];
        Deque<Pair> deque = new LinkedList();

        for(int i = 0; i < k; i++) {
            insertQueue(deque, new Pair(nums[i], i));
        }

        solutions[0] = deque.getFirst().value;
        for(int i = k; i < nums.length; i++) {
            insertQueue(deque, new Pair(nums[i], i));
            deleteQueue(deque, i, k);
            solutions[i - k + 1] = deque.getFirst().value;
        }

        return solutions;
    }

    // insert element to tail
    void insertQueue(Deque<Pair> deque, Pair pair) {
        while(!deque.isEmpty()) {
            if(deque.getLast().value <= pair.value) {
                deque.removeLast();
            } else {
                deque.addLast(pair);
                return;
            }
        }
        if(deque.isEmpty()) {
            deque.addLast(pair);
            return;
        }
    }

    // delete element from head
    void deleteQueue(Deque<Pair> deque, int currentIndex, int k) {
        while(!deque.isEmpty()) {
            if(deque.getFirst().index + k - 1 < currentIndex) {
                deque.removeFirst();
            } else {
                return;
            }
        }
    }

    class Pair {
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    //-------------------------这是使用单调队列来实现--------------------------------

    //-------------------------这是使用堆来实现--------------------------------
    // 锻炼堆结构，这里使用堆来实现
    private int[] maxSlidingWindowUseHeap(int[] nums, int k) {
        if(k == 0 || nums.length == 0) {
            return new int[0];
        }

        int[] heap = new int[k+1];
        for(int i = 0; i < k; i++) {
            heap[i+1] = nums[i];
        }
        buildMaxHeap(heap);

        int[] solutions = new int[nums.length - k + 1];
        solutions[0] = heap[1];
        for(int i = 1; i < solutions.length; i++) {
            replace(heap, nums[i - 1], nums[i + k - 1]);
            solutions[i] = heap[1];
        }
        return solutions;

    }

    void replace(int[] heap, int oldVal, int newVal) {
        int index = search(heap, oldVal, 1);
        if (-1 != index) {
            heap[index] = newVal;
            buildMaxHeap(heap);
        }
    }

    // 搜索返回index
    int search(int[] heap, int val, int index) {
        if(index >= heap.length) {
            return -1;
        }
        if(heap[index] == val) {
            return index;
        }
        if(heap[index] < val) {
            return -1;
        }

        int leftIndex = search(heap, val, index * 2);
        if(leftIndex != -1) {
            return leftIndex;
        }
        int rightIndex = search(heap, val, index * 2 + 1);
        if (rightIndex != -1) {
            return rightIndex;
        }
        return -1;
    }

    // 使用大顶堆实现 复杂度 n*log(n)

    void buildMaxHeap(int[] heap) {
        for(int i = heap.length / 2; i > 0; i--) {
            adjustHeap(heap, i);
        }
    }

    // 递归调整堆性质
    void adjustHeap(int[] heap, int index) {
        int left = index * 2;
        int right = index * 2 + 1;
        int largest = index;
        if(left < heap.length && heap[largest] < heap[left]) {
            largest = left;
        }

        if(right < heap.length && heap[largest] < heap[right]) {
            largest = right;
        }

        if(largest != index) {
            swap(heap, largest, index);
            adjustHeap(heap, largest);
        }
    }

    void swap(int[] heap, int index1, int index2) {
        int tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }
    //-------------------------这是使用堆来实现--------------------------------

    public static void main(String[] args) {
//        int[] nums = {4,3,11,2,12,9,21,43,01,5,9,91};
        int[] nums = {1,-1};
        int[] ints = new SlidingWindowMaximum().maxSlidingWindow(nums, 1);
        System.out.println(ints);
    }
}
