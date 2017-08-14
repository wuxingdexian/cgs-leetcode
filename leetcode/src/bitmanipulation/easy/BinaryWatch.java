package bitmanipulation.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * https://leetcode.com/problems/binary-watch/description/
 * 401. Binary Watch
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.
 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

 * <p>
 * 1. 建模：计数原理，加法
 * 分别求取hour和minute中不同bit数对应的数有哪些，然后排列组合
 * 2. 算法范式：穷举
 * 3. 算法：遍历穷举
 * 4. 数据结构：Map+位操作
 * 5. 改进：Integer有原生的求取bit个数的函数
 * 如何求取一个数中有多少个1？用1不断右移，然后和该数进行与操作，若结果不为0，则该位有一个1
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class BinaryWatch {

    /**
     * 参考代码
     * // TODO: 08/08/2017 理解下{@link Integer#bitCount(int)}
     * @param num
     * @return
     */
    public List<String> readBinaryWatchStandard(int num) {
        List<String> res = new ArrayList<String>();
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 60; j++){
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    String str = i + ":";
                    if(j < 10){
                        str += "0" + j;
                    }else{
                        str += j;
                    }
                    res.add(str);
                }
            }
        }
        return res;
    }

    public List<String> readBinaryWatch(int num) {
        if(num < 0 || num >  8) {
            return new ArrayList<String>();
        }
        Map<Integer, List<Integer>> hourMap = new HashMap<Integer, List<Integer>>(4);
        for(int i = 0; i < 12; i++) {
            List<Integer> hourList = null != hourMap.get(getOneCounts(i))? hourMap.get(getOneCounts(i)): new ArrayList<Integer>(8);
            hourList.add(i);
            hourMap.put(getOneCounts(i), hourList);
        }
        Map<Integer, List<Integer>> minuteMap = new HashMap<Integer, List<Integer>>(8);
        for(int i = 0; i< 60; i++) {
            List<Integer> minuteList = null != minuteMap.get(getOneCounts(i))? minuteMap.get(getOneCounts(i)): new ArrayList<Integer>(16);
            minuteList.add(i);
            minuteMap.put(getOneCounts(i), minuteList);
        }

        List<String> timeStringList = new ArrayList<String>();
        for(int i = 0; i <= num && i <= 3; i++) {
            if (null != hourMap.get(i) && null != minuteMap.get(num - i)) {
                timeStringList.addAll(formatTime(hourMap.get(i), minuteMap.get(num - i)));
            }
        }
        return timeStringList;
    }

    List<String> formatTime(List<Integer> hourList, List<Integer> minuteList) {
        List<String> timeStringList = new ArrayList<String>();

        for(Integer hour: hourList) {
            for(Integer minute: minuteList) {
                timeStringList.add(formatTime(hour, minute));
            }
        }
        return timeStringList;
    }

    String formatTime(int hour, int minute) {
        if(minute < 10) {
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    int getOneCounts(int num) {
        int counter = 0;
        while(num > 0) {
            counter += num & 0X01;
            num >>= 1;
        }
        return counter;
    }

    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        List<String> strings = binaryWatch.readBinaryWatch(5);
        System.out.println(strings);
    }
}
