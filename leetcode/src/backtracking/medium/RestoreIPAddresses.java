package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/restore-ip-addresses/description/" />
 * 93. Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * <p>
 * 1. 建模：
 * 决策树建模，从原始串开始，原始串为根，下面分别有三个节点，都是第一个分割点的三中情况：分割点取在1到2字符间；分割点取2到3字符间；分割点取3到4分割点间；
 * 设定有序对（x, sub_IP_x, IP_x） x:{1,2,3}，sub_IP_x:{1,255}，为第x次分割，sub_IP_x为第x次分割得到的子部分，IP_x为第n次分割时的ip临时串
 * 2. 算法范式：backtracking
 * 3. 算法：
 * 4. 数据结构：借助临时字符串
 * 5. 改进：用String 太耗费性能
 * 下面写的代码更多是backtracking
 * String的函数要记住，含有要有括号  length()；concat()，中间没有t；substring()，中间的s是小写
 * System.out.println(Integer.valueOf("001"));输出的是1，要注意这个判断
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class RestoreIPAddresses {

    boolean isValid(String subIp, int step) {
        if(subIp.length() < 1 || (subIp.length() > 1 && subIp.charAt(0) == '0')) {
            return false;
        }
        if (step > 3) {
            return false;
        }
        Integer value = Integer.valueOf(subIp);
        if(value < 0 || value > 255) {
            return false;
        }

        return true;
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> ipList = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12) {
            return ipList;
        }

        splitIp(1, s.substring(0, 1), 0, 2, s.substring(0, 1).concat(".").concat(s.substring(1)), ipList);
        splitIp(1, s.substring(0, 2), 0, 3, s.substring(0, 2).concat(".").concat(s.substring(2)), ipList);
        splitIp(1, s.substring(0, 3), 0, 4, s.substring(0, 3).concat(".").concat(s.substring(3)), ipList);

        return ipList;
    }

    private void splitIp(int step, String subIp, int subIpStart, int subIpEnd, String ipTmp, List<String> ipList) {
        if (!isValid(subIp, step)) {
            return;
        }
        if(subIpEnd > ipTmp.length()){
            return;
        }

        if(step ==3) {
            if(ipTmp.substring(subIpEnd).indexOf("0") == 0 && ipTmp.substring(subIpEnd).length() > 1) {
                return;
            }
            int length = ipTmp.substring(subIpEnd).length();
            Integer value = Integer.valueOf(ipTmp.substring(subIpEnd));
            if(0 < length && length < 4 && 0 <= value && value <256) {
                ipList.add(ipTmp) ;
            } else {
                return;
            }
        }

        if(ipTmp.length() > subIpEnd + 1) {
            splitIp(step + 1, ipTmp.substring(subIpEnd, subIpEnd + 1), subIpEnd + 1, subIpEnd + 2,
                    ipTmp.substring(0, subIpEnd + 1).concat(".").concat(ipTmp.substring(subIpEnd + 1)), ipList);
        }
        if(ipTmp.length() > subIpEnd + 2) {
            splitIp(step + 1, ipTmp.substring(subIpEnd, subIpEnd + 2), subIpEnd + 2, subIpEnd + 3,
                    ipTmp.substring(0, subIpEnd + 2).concat(".").concat(ipTmp.substring(subIpEnd + 2)), ipList);
        }
        if(ipTmp.length() > subIpEnd + 3) {
            splitIp(step + 1, ipTmp.substring(subIpEnd, subIpEnd + 3), subIpEnd + 3, subIpEnd + 4,
                    ipTmp.substring(0, subIpEnd + 3).concat(".").concat(ipTmp.substring(subIpEnd + 3)),ipList);
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("001"));
        List<String> strings = new RestoreIPAddresses().restoreIpAddresses("0258730");

        System.out.println(strings);
    }
}
