package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/group-anagrams/description/" />
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note: All inputs will be in lower-case.
 * <p>
 * 0. 本质：函数
 * 1. 建模：
 * 变位词 anagram的特点是（1）长度相同；（2）相同字符个数相同；
 * 找一函数映射即可
 * 2. 算法范式：
 * 3. 算法：
 * （1）设函数f(string)=c(1)length(c(1))...进行字符和个数的排列，
 *
 * （2）或每个字符串的字符排序，相同的则放一起
 *
 * 上面两个复杂度有点高，设计算法如下
 * （3）设计key=length(string)+flag(string)+sum(string)，
 * 其中length(string)为字符长度；
 * flag(string)为字符串中每个字符的标记位，表示该字符是否存在；
 * sum(string)为字符串中所有字符的累加值
 *
 * （4）当然使用素数可以，只是在字符串长度小的时候能用，当长度过长，很容易溢出。因为一个整数能分解为一串唯一素数的乘积
 * 4. 数据结构：flag(string)使用int变量的前26个bit位
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 12/09/2017
 * @see
 * @since cgs-leetcode on  12/09/2017
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagramsUsingSorting(String[] strs) {
        Map<String, List<String>> cache = new HashMap();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (cache.containsKey(key)) {
                cache.get(key).add(str);
            } else {
                List<String> values = new ArrayList<>();
                values.add(str);
                cache.put(key, values);
            }
        }

        List<List<String>> solutions = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: cache.entrySet()) {
            solutions.add(entry.getValue());
        }
        return solutions;
    }

    /*
     * （3）设计key=length(string)+flag(string)+sum(string)，
    * 其中length(string)为字符长度；
    * flag(string)为字符串中每个字符的标记位，表示该字符是否存在；
    * sum(string)为字符串中所有字符的累加值
    *
    * // FIXME: 12/09/2017 有bug
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> cache = new HashMap();
        for(String str:strs) {
            cache(str, cache);
        }

        List<List<String>> solutions = new ArrayList();
        for(Map.Entry<String, List<String>> entry: cache.entrySet()) {
            solutions.add(entry.getValue());
        }
        return solutions;
    }

    void cache(String str, Map<String, List<String>> cache) {
        int sum = 0, flag = 0, length = str.length();
        for(int i = 0; i < str.length(); i++) {
            flag = flag | 1 << (str.charAt(i) - 'a');
            sum += str.charAt(i);
        }
        String key = length + "-" + flag + "-" + sum;
        List<String> solutions = cache.containsKey(key)? cache.get(key): new ArrayList();
        solutions.add(str);
        cache.put(key, solutions);
    }
}
