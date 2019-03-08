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
 *
 * 2019-2-15建模V2
 * 设字符串s总长为n，last_word_end_index(i)=x, 0<=x<=i<=n，表示截止字符i的最近的完整单词的结束字符的index。
 * 则判断x，需要依次判断sub(x,i)是否是单词，不是再继续判断上一个最近的单词结束字符(index=y)到i的子串sub(y,i), 0<=y<x, 是否是单词，
 * 如果找不到单词，则一直判断到sub(0, i)是否为单词，如果不是，则取x=last_word_end_index(i-1)。
 * 遍历结束后，判断last_word_end_index(n)=n是否成立，即可找到答案。
 * 当然中间可以缓存子字符串来避免子问题出现
 *
 * 2019-2-15建模V3
 * 建立二维数组m，m[i,j]=true/false，表示sub(i,j)是否是单词，得到一个图的二维矩阵表示，然后遍历这个图，看是否能找到最后一个字符
 *
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
