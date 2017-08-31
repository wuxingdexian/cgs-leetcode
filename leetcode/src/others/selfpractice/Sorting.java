package others.selfpractice;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * 实现一遍各种排序
 * 主要包括快速排序、归并排序、桶排序、堆排序
 *
 * <p>
 * 0. 本质：
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
 * @author changgan on 23/08/2017
 * @see
 * @since cgs-leetcode on  23/08/2017
 */
public class Sorting {

    //--------------------------------堆排序------------------------------------------------------------------
    /**
     * 堆排序
     * 注意下标其实位置0，若从0开始，则父节点和子节点的位置关系：父节点位置为i，则左节点位置2*i+1，右节点位置2*i+2；
     * 从这些式子可以看出，要从子节点位置i推算父节点的位置，按下取整的规则，父节点位置为(i-1)/2
     *
     * 逆向排序：小顶堆
     * 正向排序：大顶堆
     *
     * 算法步骤：
     * 1. 建立树，从中间位置开始，递归
     * 2. 顶部和最后位置交换，调整堆，保持性质，注意：调整过程，要排除最后交换的节点
     */
    void heapDescendingSort(int[] nums) {
        buildMinHeap(nums);
        sortMinHeap(nums);
    }
    void heapAscendingSort(int[] nums) {
        buildMaxHeap(nums);
        sortMaxHeap(nums);
    }


    void sortMinHeap(int[] nums) {
        for (int end = nums.length; end > 0; end--) {
            swap(nums, 0, end - 1);
            adjustMinHeap(nums, 0, end - 1);
        }
    }

    void buildMinHeap(int[] nums) {
        int middle = nums.length / 2;
        for (int i = middle; i >= 0; i--) {
            adjustMinHeap(nums, i, nums.length);
        }
    }



    // 找子节点最小的那个交换，然后调整
    void adjustMinHeap(int[] nums, int start, int end) {
        int left = 2 * start + 1;
        int right = 2 * start + 2;

        int largest = start;
        if(left < end && nums[left] < nums[largest]) {
            largest = left;
        }
        if (right < end && nums[right] < nums[largest]) {
            largest = right;
        }

        if (start != largest) {
            swap(nums, start, largest);
            adjustMinHeap(nums, largest, end);
        }
    }

    void sortMaxHeap(int[] nums) {
        for (int end = nums.length; end > 0; end--) {
            swap(nums, 0, end - 1);
            adjustMaxHeap(nums, 0, end - 1);
        }
    }

    void buildMaxHeap(int[] nums) {
        int middle = nums.length / 2;
        for (int i = middle; i >= 0; i--) {
            adjustMaxHeap(nums, i, nums.length);
        }
    }

    // 找子节点最小的那个交换，然后调整
    void adjustMaxHeap(int[] nums, int start, int end) {
        int left = 2 * start + 1;
        int right = 2 * start + 2;

        int largest = start;
        if(left < end && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < end && nums[right] > nums[largest]) {
            largest = right;
        }

        if (start != largest) {
            swap(nums, start, largest);
            adjustMaxHeap(nums, largest, end);
        }
    }

    void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
    //--------------------------------堆排序------------------------------------------------------------------

    //--------------------------------归并排序------------------------------------------------------------------

    /**
     * 归并排序
     * 分治法
     * 1. split nums to 2 part, then sort them respectively.
     * 2. merge them into a sorting part
     * 注意：要用到辅助数组，当辅助数组的其中一半结束后，将另一半的剩下部分拷贝到原始数组
     * @param nums
     */
    void ascendingMergeSort(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        int middle = (start + end) >>> 1;
        ascendingMergeSort(nums, start, middle);
        ascendingMergeSort(nums, middle + 1, end);
        ascendingMerge(nums, start, middle, end);
    }

    void ascendingMerge(int[] nums, int start, int middle, int end) {
        int[] helper = copyArray(nums, start, end);

        int helpLeft = 0, helpMiddle = middle - start, helpRight = helpMiddle+1;
        int current = start;
        while (helpLeft <= helpMiddle && helpRight <= helper.length - 1) {
            if (helper[helpLeft] > helper[helpRight]) {
                nums[current++] = helper[helpRight++];
            } else {
                nums[current++] = helper[helpLeft++];
            }
        }

        // 将辅助数组中剩下的部分拷贝到原数组
        while (helpLeft <= helpMiddle) {
            nums[current++] = helper[helpLeft++];
        }
        while (helpRight < helper.length) {
            nums[current++] = helper[helpRight++];
        }
    }

    void descendingMergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (end + start) >>> 1;
        descendingMergeSort(nums, start, middle);
        descendingMergeSort(nums, middle + 1, end);
        descendingSort(nums, start, middle, end);
    }

    void descendingSort(int[] nums, int start, int middle, int end) {
        int[] helper = copyArray(nums, start, end);
        int helpLeft = 0;
        int helperMiddle = middle - start;
        int helperRight = helperMiddle + 1;
        int current = start;

        while (helpLeft <= helperMiddle && helperRight < helper.length) {
            if (helper[helpLeft] >= helper[helperRight]) {
                nums[current++] = helper[helpLeft++];
            } else {
                nums[current++] = helper[helperRight++];
            }
        }

        while (helpLeft <= helperMiddle) {
            nums[current++] = helper[helpLeft++];
        }
        while (helperRight < helper.length) {
            nums[current++] = helper[helperRight++];
        }
    }

    int[] copyArray(int[] sourceArray, int start, int end) {
        int[] helper = new int[end - start + 1];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = sourceArray[start++];
        }
        return helper;
    }
    //--------------------------------归并排序------------------------------------------------------------------


    //--------------------------------快速排序------------------------------------------------------------------

    /**
     * 快排
     * 1. 首选选择一个pivot，做为基准，保存到临时变量temp，一般选取第一个或最后一个元素。
     * 注意：选择基准，为了避免性能浪费，选择首、中、尾三个数进行比较，选择值为中间的那个，然后和首元素交换，然后从首部开始进行
     * 2. 设置收尾两个指针head和tail，选择不是基准的一头开始遍历，如选择head的元素作为基准，则首先从tail开始遍历。
     * 因为被选择的这个元素，它的值已经被保存到临时变量，那它原来的位置算是空。
     * 3. 若是tail移动，则比较其值，
     *  1）若小于等于基准，则把值复制到head指针指向的位置，然后head指针后移一位，然后从head指针方向开始移动；
     *  2）若大于基准，则tail指针继续向head方向移动一位，重新进行比较。
     * 4. 若是head移动，则比较其值，
     *  1）若小于等于基准，则head指针向tail方向移动一位，重新进行比较；
     *  2）若大于基准，则把值复制到tail指针的位置，然后tail指针向head方向移动一位，然后换head开始移动。
     * 5. 执行上面的（3）和（4）直到head和tail相等，此时把pivot值复制到head指针处。
     * 6. 然后分别指向上面pivot两部分的数据，直到子数组只有一个或两个元素。分治法
     *
     * @param nums
     */
    void ascendingQuickSort(int[] nums, int start, int end) {

        if (start >= end) {
            return;
        }
        int pivotIndex = ascendingSeparate(nums, start, end);
        ascendingQuickSort(nums, start, pivotIndex - 1);
        ascendingQuickSort(nums, pivotIndex + 1, end);
    }

    // 挑选中间的处于中间值的那个作为pivot
    void pickPivot(int[] nums, int start, int end) {
        int middle = (end + start) >>> 1;

        int pivotIndex = (nums[start] < nums[end])? (nums[end] < nums[middle]? end: middle):
                (nums[start] < nums[middle]? start: middle);

        swap(nums, start, pivotIndex);

    }

    int ascendingSeparate(int[] nums, int start, int end) {
        pickPivot(nums, start, end);
        int temp = nums[start];

//        boolean isStartValid = false;
        while (start < end) {
            // 从end开始，找到大于temp的值
            while (nums[end] > temp && start < end) {
                end--;
            }
            if (start < end) {
                nums[start++] = nums[end];
            }

            // 接着从start开始，找到小于等于temp的值
            while (nums[start] <= temp && start < end) {
                start++;
            }
            if (start < end) {
                nums[end--] = nums[start];
            }

            /*
            if(isStartValid) {
                if (nums[start] > temp) {
                    nums[end--] = nums[start];
                    isStartValid = !isStartValid;
                } else {
                    start++;
                }
            } else {
                if (nums[end] <= temp) {
                    nums[start++] = nums[end];
                    isStartValid = !isStartValid;
                } else {
                    end--;
                }
            }
            */
        }
        nums[start] = temp;
        return start;
    }

    void descendingQuickSort(int[] nums, int start, int end) {

        if (start >= end) {
            return;
        }
        int pivotIndex = descendingSeparate(nums, start, end);
        descendingQuickSort(nums, start, pivotIndex - 1);
        descendingQuickSort(nums, pivotIndex + 1, end);
    }

    int descendingSeparate(int[] nums, int start, int end) {
        pickPivot(nums, start, end);
        int temp = nums[start];

//        boolean isStartValid = false;
        while (start < end) {
            // 从end开始，找到大于temp的值
            while (nums[end] <= temp && start < end) {
                end--;
            }
            if (start < end) {
                nums[start++] = nums[end];
            }

            // 接着从start开始，找到小于等于temp的值
            while (nums[start] > temp && start < end) {
                start++;
            }
            if (start < end) {
                nums[end--] = nums[start];
            }

            /*
            if(isStartValid) {
                if (nums[start] <= temp) {
                    nums[end--] = nums[start];
                    isStartValid = !isStartValid;
                } else {
                    start++;
                }
            } else {
                if (nums[end] > temp) {
                    nums[start++] = nums[end];
                    isStartValid = !isStartValid;
                } else {
                    end--;
                }
            }
            */
        }
        nums[start] = temp;
        return start;
    }
    //--------------------------------快速排序------------------------------------------------------------------

    //--------------------------------基数排序------------------------------------------------------------------
    // TODO: 25/08/2017 实现基数排序

    //--------------------------------基数排序------------------------------------------------------------------

    public static void main(String[] args) {
        int[] nums = {9,5,2,8,6,4,0,1};
//        int[] ints = new Sorting().heapDescendingSort(nums);
//        new Sorting().heapAscendingSort(nums);
//        new Sorting().ascendingMergeSort(nums, 0, nums.length - 1);
//        new Sorting().descendingMergeSort(nums, 0, nums.length - 1);
        new Sorting().ascendingQuickSort(nums, 0, nums.length - 1);
//        new Sorting().descendingQuickSort(nums, 0, nums.length - 1);
        for (int num: nums) {
            System.out.print(num + ",");
        }
    }
}
