package sort.easy;

import java.util.Arrays;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/valid-anagram/description/" />
 * 242. Valid Anagram
 * Given two strings s and t, write a function to determine if t is an anagram of s.

 For example,
 s = "anagram", t = "nagaram", return true.
 s = "rat", t = "car", return false.

 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * <p>
 * 1. 建模：
 * 首先理解anagram这个词，改词是“变位词”的意思，即一个字符串，交换某两个字符的位置，可以得到不同单词，两个单词互为变位词。
 * 集合角度
 * 单词都来自同一个集合，并且是这个集合的不同排列；即得到不同的序列sequence
 *
 *  follow up:
 * 集合的排列，permutation，backtracking是个不错的方法
 *
 * 2. 算法范式：
 *
 * 3. 算法：
 * （1）排序，然后比较两个字符是否相等 // TODO: 22/08/2017 从集合序列的角度思考，有序性
 * （2）分别统计这两个字符串中不同字符出现的有序对(letter， quantity) // TODO: 22/08/2017 这是在《编程珠玑》中得到提示。其实也是函数转换集合的思路。
 * 集合：domain1 为字符串集合，无穷；domain2为字符串集合，无穷。codomain为字母加数子的字符串，无穷
 * 单射函数：统计两个字符串内不同单词的个数，然后按相同顺序排序规则排序，如升序。两个分别来自domain1和domain2的字符串都映射到codainm的相同值，则两个字符串相等。
 * 
 * （3）给定26长度的数组，分别对应a到z，当出现这个字符，就标记 // TODO: 22/08/2017 也可以从函数的角度思考验证 
 * 集合：domain1是字符串s内的字符，domain2是字符串t内的字符。codomain是26长度数组，元素分别从a~z，其实这里只需要0~25数组，即数组下标即可以了。
 * 单射函数：f=字符-'a'映射到codomain的[0~25]空间。将domain1和domain2分别映射到codomain，并且最后映射的值都成对出现（通过累加或者true、false翻转）都行。
 * （4）给定函数f，用来求取字符的和，然后判断两个字符串的和是否相等 // FIXME: 22/08/2017 这个有bug，因为sum(bb)=sum(ac)，
 * 注意：算法（4）思路有BUG，不是单射函数，导致domain空间的多个值映射到codomain的一个值。这里domain的元素是字符串，空间为无穷，codomain的元素是整数，空间是无穷。
 * // FIXME: 22/08/2017 当出现不是单设函数的时候，（1）要么调整集合空间；（2）要么调整函数；（3）对一个domain映射到多个codomain的情况做额外补偿操作
 * 
 * 上述方法（1）比较通用，缺点是空间取决于字符串长度；（2）和（3）在字符串只有a到z的英文字符时很好用，但是当超过这个范围就不行了。
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：可以有函数+集合的思维思考问题，如上面算法2~4可以从函数角度思考 // TODO: 22/08/2017 锻炼函数+集合的思维 
 * // TODO: 22/08/2017 增强些函数的思维，从题设可以分析出一个或多个集合空间。可以创造性的构造一个函数或一个集合空间（也可能是题设集合本身），
 * // TODO: 22/08/2017 通过这个函数进行映射得到另一个空间，转化为解另一个空间的问题；或者是否能映射，通过这一条件（函数）判断就能得出答案。答案在映射空间，或者在函数
 * 7. jdk知识：
 * （1）字符串的排序？// TODO: 22/08/2017 String是不可改变类，没有排序的方法. 可以将string的字符都拿出来，保存到char数组，然后调用{@link Arrays#sort(char[])}
 * （2）
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/08/2017
 * @see
 * @since cgs-leetcode on  22/08/2017
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
            return false;
        }

        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();

        Arrays.sort(schars);
        Arrays.sort(tchars);

        for(int i = 0; i < schars.length; i++) {
            if(schars[i] != tchars[i]) {
                return false;
            }
        }
        return true;
    }


    // FIXME: 22/08/2017 bug，加法函数在这里domain和codomain之间不是单射函数。 可以看看是否bit操作能找到一个单射函数？
    private boolean goodSumIdea(String s, String t) {
        int sSum = 0;
        int tSum = 0;
        for(int i = 0; i < s.length(); i++) {
            sSum += s.charAt(i);
            tSum += t.charAt(i);
        }
        return sSum == tSum;
    }
}
