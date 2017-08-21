package others.hard;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/description/" />
 * 440. K-th Smallest in Lexicographical Order
 *Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

 Note: 1 ≤ k ≤ n ≤ 109.

 Example:

 Input:
 n: 13   k: 2

 Output:
 10

 Explanation:
 The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

 * <p>
 * 1. 建模：
 * （1）建立前缀树 trie
 * 数据结构(val,nextNodes[],isNumber)，nextNodes从0到9。
 * 遍历搜索数字后，建立前缀树，然后preOrder前序遍历，找到第k个
 * 这个思路和LeetCode的一个解是同一个思路，但是对方给出了更快的方法~。~参考https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/
 *
 *
 * （2）建立大顶堆maxHeap数据结构。就维护k个元素即可。
 * // TODO: 19/08/2017 前缀树trie在其他题型有实践过，这里使用大顶堆练练。
 *
 * 2. 算法范式：
 * 3. 算法：
 * （2）构建大顶堆模型的思路：
 *      1) 先构建一个k长度的数组，然后遍历到k，先填满这个大顶堆。填入过程自底向上维护大顶堆性质不变；bottom-up
 *      2）当大顶堆满了，则后续k+1个数的遍历，依次和堆顶对比，若比堆顶小，则和堆顶交换，然后维护堆性质。up-down
 * 4. 数据结构：
 * （2）数组形式构建大顶堆实现minHeap或maxHeap
 * 5. 改进：
 * 这里大顶堆的字典排序借助了{@link String#compareTo(String)}方法，性能低，可以优化这部分
 * 6. 启发：
 * （1）借助堆结构：
 *      1）当要找出一堆元素中最大的k个元素或者第k大元素，则要利用小顶堆。因为堆顶就是最大k个元素中最小的那个
 *      2）当要找出一堆元素中最小的k个元素或者第k小元素，则要利用大顶堆。因为堆顶就是最小k个元素中最大的那个
 *
 * （2）小功能要拆分，不然很容易思维混乱。在面试中更是不可取
 * 7. jdk知识：{@link String#compareTo(String)} compareTo按照字典顺序排序
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/08/2017
 * @see
 * @since cgs-leetcode on  19/08/2017
 */
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        if (k > n) {
            return 0;
        }

        int[] heap = new int[k + 1];
        for(int i = 1; i <= k; i++) {
            buildMaxHeap(heap, i, i);
        }
        for (int i = k + 1; i <= n; i++) {
            updateHeap(heap, i);
        }
        return heap[1];
    }

    // FIXME: 20/08/2017 这建堆得代码和逻辑太啰嗦。参考《算法导论》的建堆代码递归实现吧。更通用。
    // 构建大顶堆
    void buildMaxHeap(int heap[], int index, int val) {
        heap[index] = val;
        justFromBottomUp(heap, index);
    }

    // 从底向上进行校正，保证堆的有序性
    void justFromBottomUp(int heap[], int index) {
        int parentIndex = index / 2;
        while (parentIndex > 0) {
            if(String.valueOf(heap[parentIndex]).compareTo(String.valueOf(heap[index])) >= 0) {
                break;
            }
            swap(heap, index, parentIndex);

            // 相邻节点也需要比较校正
            int adjacentIndex = (index & 1) == 0? index + 1: index - 1;
            if(adjacentIndex < heap.length && String.valueOf(heap[parentIndex]).compareTo(String.valueOf(heap[adjacentIndex])) < 0) {
                swap(heap, adjacentIndex, parentIndex);
            }
            index = parentIndex;
            parentIndex = index / 2;
        }
    }

    void swap(int[] heap, int index1, int index2) {
        int tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    // 从顶向下进行校正，保证堆得有序性
    void justFromUpDown(int heap[]) {
        int index = 1;
        while (index < heap.length) {
            int childIndex = pickBiggerIndex(heap, index);
            if (childIndex >= heap.length) {
                break;
            }
            if (String.valueOf(heap[childIndex]).compareTo(String.valueOf(heap[index])) > 0) {
                swap(heap, index, childIndex);
            } else {
                break;
            }
            index = childIndex;
        }
    }

    // 挑选最小的子节点index
    int pickBiggerIndex(int[] heap, int index) {
        int biggerChildIndex = index * 2;
        if(biggerChildIndex >= heap.length) {
            return biggerChildIndex;
        }
        if(biggerChildIndex + 1 >= heap.length) {
            return biggerChildIndex;
        }

        return String.valueOf(heap[biggerChildIndex]).compareTo(String.valueOf(heap[biggerChildIndex + 1])) > 0? biggerChildIndex: biggerChildIndex + 1;
    }

    void updateHeap(int heap[], int val) {
        if(String.valueOf(heap[1]).compareTo(String.valueOf(val)) > 0) {
            heap[1] = val;
            justFromUpDown(heap);
        }
    }


    public static void main(String[] args) {
        int kthNumber = new KthSmallestInLexicographicalOrder().findKthNumber(4289384, 1922239);
        System.out.println(kthNumber);
    }
}
