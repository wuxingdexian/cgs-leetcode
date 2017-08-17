package dynamicprogramming.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/word-break/description/" />
 * 139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".

 UPDATE (2017/1/4):
 The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 * <p>
 * 1. 建模：
 * break(n) = break(n-i) && word(n-i,n)
 * 2. 算法范式：dynamic programming
 * 3. 算法：
 * 4. 数据结构：
 * 三角矩阵，同时借助map保存之前查询的结果
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link String#substring(int, int)} substring是小写，中间s不是大写
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class WordBreak {

    public boolean wordBreak1(String s, List<String> wordDict) {
        boolean[] flag = new boolean[s.length() + 1];
        Map<String, Boolean> wordMap = new HashMap();
        flag[0] = true;
        for(int i = 0; i < s.length() + 1; i++) {
            for (int j = i - 1; j >=0; j--) {
                if (flag[j]) {
                    String st = s.substring(j, i);
                    if(null != wordMap.get(st) && wordMap.get(st)) {
                        flag[i] = true;
                    } else {
                        boolean isContained = wordDict.contains(st);
                        wordMap.put(st, isContained);
                        if (isContained) {
                            flag[i] = true;
                            continue;
                        }
                    }
                }
            }
        }
        return flag[flag.length - 1];
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        list.add("a");
        list.add("abc");
        list.add("b");
        list.add("cd");
        boolean leetcode = new WordBreak().wordBreak1("leetcode", list);
        System.out.println(leetcode);
    }
}
