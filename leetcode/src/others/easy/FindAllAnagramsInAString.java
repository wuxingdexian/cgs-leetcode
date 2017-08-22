package others.easy;

import sort.easy.ValidAnagram;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/description/" />
 * 438. Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * 1. 建模：
 * set+function
 * 设s和p分别对应题设的s和t，length(t)表示t的长度，sum(t)为计算t中所有字符相加的和
 * 集合set：
 * domain1：对于s，从0开始，分别截取连续长度为length(t)的子串sub_s(i)，集合取值范围无穷，对于确定的字符串，取值会确定
 * domain2：对于t，计算该字符串的sum(t)
 * codmain：所有连续长度为length(t)的字符sum，无穷
 * sum(t)，集合取值范围无穷，对于确定的t，有一个唯一的sum
 * 函数function：由s中从i开始的连续长度为length(t)的子串sub_s(i)，分别计算sum(sub_s(i))
 *
 * 上面就将s和t分别转换为domain1和domain2，domain2只映射到codomain的一个值，因为domain2只有一个字符串t。
 * domain1映射到codomain，得到大于等于1个值。
 *
 * 注意：这个function不是单射，因为对于ac和bb两个字符串有相同的sum。
 *
 * // TODO: 22/08/2017 明天看下别人如何使用滑动窗口的办法来解决这题
 * 2. 算法范式：
 * 3. 算法：
 * （1）计算t的和sum(t)
 * （2）预处理s，得到连续长度为length(t)的子串的和。// TODO: 22/08/2017  这一步将降低字符串的很多复杂度. 同时这里可以简单的dynamic programming应用
 * （3）遍历s预处理后的子串和，找到和sum(t)相同的值；单独遍历这个子串和t是否变位词
 * （4）对于两个子串比较是否为变位词，则有很多办法了  参考{@link ValidAnagram}
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：从set+function角度出来，这个更底层的思维方式和模式不错。 从{@link ValidAnagram}这个题目引发出来的感悟
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/08/2017
 * @see
 * @since cgs-leetcode on  22/08/2017
 */
public class FindAllAnagramsInAString {

    /**
     * 这是根据set+function启示设计的算法，accepted后，300ms，有点慢。// TODO: 23/08/2017 看下别人是如何做的
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null || s.length() == 0 || s.length() < p.length()) {
            return new ArrayList();
        }

        int[] sum = new int[s.length() - p.length() + 1];
        int sump = 0;
        for(int j = 0; j < p.length(); j++) {
            sump += p.charAt(j);
        }
        for(int j = 0; j < p.length(); j++) {
            sum[0] += s.charAt(j);
        }

        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + s.charAt(p.length() - 1 + i) - s.charAt(i - 1);
        }

        List<Integer> solutions = new ArrayList();
        for(int i = 0; i < sum.length; i++) {
            if(sum[i] == sump && isAnagrams(s, i, i + p.length(), p)) {
                solutions.add(i);
            }
        }
        return solutions;
    }

    // 这也是set+function的角度建模得来，单射的function，完美
    boolean isAnagrams(String s, int startOfs, int endOfs, String p) {
        boolean[] flag = new boolean[26];
        for(int i = startOfs; i < endOfs; i++) {
            flag[s.charAt(i) - 'a'] = !flag[s.charAt(i) - 'a'];
        }
        for(int i = 0; i < p.length(); i++) {
            flag[p.charAt(i) - 'a'] = !flag[p.charAt(i) - 'a'];
        }
        for(int i = 0; i < flag.length; i++) {
            if(flag[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> anagrams = new FindAllAnagramsInAString().findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}
