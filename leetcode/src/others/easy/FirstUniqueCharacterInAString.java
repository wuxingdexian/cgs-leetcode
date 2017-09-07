package others.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/first-unique-character-in-a-string/description/" />
 * 387. First Unique Character in a String
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 保证有序性、保证字符个数
 * 2. 算法范式：
 * 3. 算法：
 * （1）一次遍历，将所有字符都保存到map，key为字符，value为个数
 * （2）再遍历一次，找到第一个符合个数为1的
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> cache = new HashMap();
        for(int i = 0; i < s.length(); i++) {
            cache.put(s.charAt(i), cache.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(int i = 0; i < s.length(); i++) {
            if(cache.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 数组效率高，并且用到了数组的映射关系 6 allChar[chars[i]]
    public int firstUniqCharStandard(String s) {
        if(s == null || s.length() == 0)
            return -1;
        if(s.length() == 1)
            return 0;
        char[] chars = s.toCharArray();
        int[] allChar = new int[128];

        for(int i = 0; i < chars.length; i++) {
            allChar[chars[i]] ++;
        }
        for(int i = 0; i < chars.length; i++) {
            // 这里再次使用，保证了顺序性
            if(allChar[chars[i]] == 1)
                return i;
        }
        return -1;
    }

    // 使用数组实现一次
    public int firstUniqCharMySelf(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            map[chars[i] - 'a']++;
        }
        // 顺序遍历字符数组，保证了顺序性
        for(int i = 0; i < chars.length; i++) {
            // 数组下标映射，找到个数
            if(map[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
