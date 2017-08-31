package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/isomorphic-strings/description/" />
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 For example,
 Given "egg", "add", return true.

 Given "foo", "bar", return false.

 Given "paper", "title", return true.

 Note:
 You may assume both s and t have the same length.
 * <p>
 * 0. 本质：function
 * 1. 建模：函数单射 映射到字母集合 自身
 * // FIXME: 31/08/2017 从例子看，没有的要求保持两个序列的顺序性一致，所以难度应该是降低的。
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * TODO 代码中对map和string进行了多次操作，导致速度慢，可以将相同步骤提取到前面；
 * TODO 可以使用char[]实现，如果字符串都是ascii码的话
 * 6. 启发：也是单射，和{@link WordPattern}是同样问题
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> caches = new HashMap();
        Map<Character, Character> cachet = new HashMap();

        for(int i = 0; i < s.length(); i++) {
            if(!caches.containsKey(s.charAt(i)) && !cachet.containsKey(t.charAt(i))) {
                caches.put(s.charAt(i), t.charAt(i));
                cachet.put(t.charAt(i), s.charAt(i));
            } else if(caches.containsKey(s.charAt(i)) && cachet.containsKey(t.charAt(i))) {
                if(caches.get(s.charAt(i)) != t.charAt(i) || cachet.get(t.charAt(i)) != s.charAt(i)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean isomorphic = new IsomorphicStrings().isIsomorphic("egg", "add");
        System.out.println(isomorphic);
    }
}
