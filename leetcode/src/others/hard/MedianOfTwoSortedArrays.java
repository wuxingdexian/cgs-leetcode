package others.hard;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/description/" />
 * 4. Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5
 * <p>
 * 0. 本质：序列 sequence
 * 1. 建模：找到两个有序序列合并后的中位数
 * 当m+n为odd时，只有一个，当为even时，为中间两个数的和的平均数
 *
 * 2. 算法范式：Bruce force
 * 3. 算法：
 * 算法1
 * （0）设定两个指针p1和p2，分别从头开始遍历，并设定一个counter=1，用来统计目前已经遍历了多少个元素
 * （1）比较两个指针当前值的大小，值小的往后移动一位，counter++；
 * （2）比较counter是否等于(m+n)/2，
 *      1）若不等，则继续执行（1）
 *      2）若相等，则判断：若m+n为odd，则返回p1和p2中指向值小的那位；否则为even，返回p1和p2指向值得平均数
 *      
 * // FIXME: 19/10/2017 上面的算法要涉及到数组首尾情况的极端判断，两个数组的组合情况就比较多了，难控制，太烦人。这个算法不好，改进如下
 *
 * 算法2
 * （1）设定一个数组tmp，用来保存中位数的原始值，在odd情况只有一个，在even情况下有两个。
 * （2）设定两个指针p1和p2，分别从头开始遍历，并设定一个counter=0，用来统计目前已经遍历了多少个元素
 * （3）比较counter是否等于(m+n)/2+1，
 *      1）若不等，则继续执行（1）
 *      2）若相等，则判断：若m+n为odd，则p1和p2中指向值小的那位保存到tmp，并返回tmp的平均值；否则为even，p1和p2指向值都保存到tmp，然后返回平均数
 * （4）counter++；比较两个指针当前值的大小，较小的值保存到tmp（通过counter取模来定位tmp位置），并将较小值的指针往后移动一位；
 *
 * 注意：（1）这里还要考虑一个数组已经被遍历完毕的情况；（2）整数除法向下取整对终止条件的影响。如1,2,3,4,5  这几个数可以看出取“counter < (totalNum>>1) + 1”的终止条件
 * 4. 数据结构：辅助数组tmp，哨兵在数组的变种使用方法，减少对数组首尾极端情况的判断
 * 5. 改进：链表中很容易通过哨兵来简化操作，因为很容易增删元素；而在已有数组的头部增加哨兵，则会使得数组后移，不可取，通过使用额外临时数组（或变量也行）来作为哨兵，简化操作！！！
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/10/2017
 * @see
 * @since cgs-leetcode on  18/10/2017
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 初始情况判断
        if(nums1==null && nums2==null) {
            return 0.0;
        }

        // 正常情况
        int totalNum = nums1.length + nums2.length;
        int tmp[] = new int[(totalNum&1) == 1? 1: 2];
        int index1 = 0;
        int index2 = 0;
        for (int counter = 0; counter < (totalNum>>1) + 1; counter++){
            if (nums1==null || index1>=nums1.length) {
                tmp[counter%tmp.length] = nums2[index2++];
            } else if (nums2==null || index2>=nums2.length) {
                tmp[counter%tmp.length] = nums1[index1++];
            } else {
                if (nums1[index1] <= nums2[index2]) {
                    tmp[counter%tmp.length] = nums1[index1++];
                } else {
                    tmp[counter%tmp.length] = nums2[index2++];
                }
            }
        }

        return tmp.length == 1? tmp[0]/1.0: (tmp[0]+tmp[1])/2.0;

    }

    public static void main(String[] args) {
        int[] a1 = {2};
        int[] a2 = {1,3,4,5,6,7};
//        int[] a1 = {1,3};
//        int[] a2 = {2,4};
        double medianSortedArrays = new MedianOfTwoSortedArrays().findMedianSortedArrays(a1, a2);
        System.out.println(medianSortedArrays);
    }

//    算法1，组合情况太多，还有bug，废弃
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        if(nums1 == null || nums1.length == 0) {
//            return nums2 == null || nums2.length == 0? 0.0:
//                    ((nums2.length & 1) == 1? nums2[nums2.length / 2]/1.0: (nums2[(nums2.length-1) / 2] + nums2[(nums2.length-1) / 2 + 1])/2.0);
//        }
//
//        if(nums2 == null || nums2.length == 0) {
//            return nums1 == null || nums1.length == 0? 0.0:
//                    ((nums1.length & 1) == 1? nums1[nums1.length / 2]/1.0: (nums1[(nums1.length-1) / 2] + nums1[(nums1.length-1) / 2 + 1])/2.0);
//        }
//
//        int index1 = -1;
//        int index2 = -1;
//        int totalNum = nums1.length + nums2.length;
//        int counter = 0;
//        while(index1 < nums1.length && index2 < nums2.length) {
//            /*返回逻辑判断*/
//            if(counter == totalNum / 2) {
//                if(totalNum % 2 == 1) {
//
//                    if (counter % 2 ==0) {
//                        return index1 == -1?
//                                nums2[index2]:
//                                (index2 == -1? nums1[index1]: (nums1[index1] <= nums2[index2] ? nums1[index1]/1.0: nums2[index2]/1.0));
//                    }
//                } else {
//                    if(index1 == - 1) {
//                        return index2 == nums2.length - 1? (nums1[index1+1] + nums2[index2])/2.0: (nums2[index2] + nums2[index2+1])/2.0;
//                    } else if (index2 == -1) {
//                        return index1 == nums1.length - 1? (nums1[index1] + nums2[index2+1])/2.0: (nums1[index1] + nums1[index1+1])/2.0;
//                    } else {
//                        return (nums1[index1] + nums2[index2])/2.0;
//                    }
//                }
//            }
//
//            /*指针偏移逻辑判断*/
//            if (index1 == -1 && index2 == -1) {
//                if (nums1[index1+1] <= nums2[index2+1]) {
//                    index1++;
//                } else {
//                    index2++;
//                }
//            } else if(index1 == -1) {
//                if(nums1[index1+1] <= nums2[index2]) {
//                    index1++;
//                } else {
//                    index2++;
//                }
//            } else if(index2 == -1) {
//                if(nums1[index1] <= nums2[index2+1]) {
//                    index1++;
//                } else {
//                    index2++;
//                }
//            } else {
//                if (nums1[index1] < nums2[index2]) {
//                    index1++;
//                } else if (nums1[index1] > nums2[index2]) {
//                    index2++;
//                } else {
//                /* special case
//                [1,2],[1,1]
//                */
//                    if (index1 + 1 == nums1.length) {
//                        index2++;
//                    } else if (index2 + 1 == nums2.length) {
//                        index1++;
//                    } else if (nums1[index1 + 1] > nums2[index2 + 1]) {
//                        index2++;
//                    } else {
//                        index1++;
//                    }
//                }
//            }
//            counter++;
//        }
//
//        int[] tmps = index1 < nums1.length? nums1: nums2;
//        int indexTmp = index1 < nums1.length? index1: index2;
//        counter--; // index1 或index2超过其长度，无效一次
//
//        while(indexTmp < tmps.length) {
//            if(counter == totalNum / 2) {
//                return totalNum % 2 == 1? tmps[indexTmp+1]/1.0: (tmps[indexTmp]+tmps[indexTmp+1])/2.0;
//            }
//            counter++;
//            indexTmp++;
//        }
//
//        return 0.0;
//    }


}
