package others.medium;

import linkedlist.medium.LinkedListCycleII;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-the-duplicate-number/description/" />
 * 287. Find the Duplicate Number
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * 1. 建模：
 * （1）有序序列  sequence  然后二分查找。但这个违反了题设“不能修改数组”
 *
 * （2）数学公式，前n项等差数列，和sum(n)=((n+1)*n)/2，将数组中n+1个数求和后，减去sum(n) // FIXME: 21/08/2017 这个违反了一个数可以重复超过一次
 *
 * （3）序列+鸽巢原理+环
 * a[i]为数组，i={x|1<=x<=n}
 * 首选1到n有n+1个元素，那么肯定存在两个重复的。首先将元素进行处理，做函数映射。
 * 由于题设，n+1个元素的值都在1到n范围内，使用值作为数组下标的映射。
 * 设s(1)=f(a[1]),s(2)=f(f(a[i]))=f(s(1)),s(3)=f(s(2)),...s(n)=f(s(n-1)),s(n+1)=f(s(n))
 * 然后得到一个序列，这个序列在某个位置开始形成环
 * 参考解释：http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
 *
 * 设定快慢指针，两个指针在这儿序列中环内相遇，此时{@link LinkedListCycleII}的解释的公式，可以发现，慢指针和从头开始的指针同时同步一定，走剩下环的长度后肯定在环入口相遇
 *
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 21/08/2017
 * @see
 * @since cgs-leetcode on  21/08/2017
 */
public class FindTheDuplicateNumber {
//    public int findDuplicate(int[] nums) {
//        int sum = 0;
//        for(int num: nums) {
//            sum += num;
//        }
//        // arithmetic progression
//        return sum - ((((nums.length - 1) + 1) * (nums.length - 1)) >>> 1) ;
//    }

//    public int findDuplicate(int[] nums) {
//        int slow = 0;
//        int fast = 0;
//        while(true) {
//            if(nums[slow] == nums[fast]) {
//                return nums[slow];
//            }
//            slow = (slow + 1) % (nums.length);
//            fast = (fast + 2) % (nums.length);
//        }
//        // return -1;
//    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0]; // 相当于移动一步
        int fast = nums[nums[0]]; // 相当于移动两步
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 下表index=0，作为链表的头，不会被包含在环内，因为数组元素的值在1到n方位内。
        int head = 0;
        while (slow != head) {
            head = nums[head];
            slow = nums[slow];
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,1,4,5,6,7,1,9};
//        int[] nums = {1,3,4,2,1};
        int duplicate = new FindTheDuplicateNumber().findDuplicate(nums);
        System.out.println(duplicate);
    }
}
