package others.hard;

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
 * 1. 建模：
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
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class SlidingWindowMaximum {
    // 锻炼堆结构，这里使用堆来实现
    public int[] maxSlidingWindow(int[] nums, int k) {
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

    public static void main(String[] args) {
        int[] nums = {4,3,11,2,12,9,21,43,01,5,9,91};
        int[] ints = new SlidingWindowMaximum().maxSlidingWindow(nums, 3);
        System.out.println(ints);
    }
}
