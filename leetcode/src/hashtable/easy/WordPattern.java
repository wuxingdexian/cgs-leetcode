package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/word-pattern/description/" />
 * 290. Word Pattern
 * Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

 Examples:
 pattern = "abba", str = "dog cat cat dog" should return true.
 pattern = "abba", str = "dog cat cat fish" should return false.
 pattern = "aaaa", str = "dog cat cat dog" should return false.
 pattern = "abba", str = "dog dog dog dog" should return false.
 Notes:
 You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * <p>
 * 0. 本质：relation(ordered-pair)、set+function
 * 1. 建模：surjection 单射 injection one-to-one
 * Examples:
 pattern = "abba", str = "dog cat cat dog" should return true. (a,dog),(b,cat) injection/one-to-one
 pattern = "abba", str = "dog cat cat fish" should return false. (a,dog),(a,fish) not function
 pattern = "aaaa", str = "dog cat cat dog" should return false. (a,dog),(a,cat) not function
 pattern = "abba", str = "dog dog dog dog" should return false. (a,dog),(b,dog) surjection
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：两个map，做injection
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class WordPattern {
    /**
     * 0. 本质：relation(ordered-pair)、set+function
     */
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> char2StringMap = new HashMap();
        Map<String, Character> String2CharMap = new HashMap();
        String[] subStrs = str.split(" ");
        if(subStrs.length != pattern.length()) {
            return false;
        }
        for(int i = 0; i < pattern.length(); i++) {

            if(char2StringMap.get(pattern.charAt(i)) != null && String2CharMap.get(subStrs[i]) != null) {
                if(!subStrs[i].equals(char2StringMap.get(pattern.charAt(i)))
                        || !String2CharMap.get(subStrs[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            } else if(char2StringMap.get(pattern.charAt(i)) == null && String2CharMap.get(subStrs[i]) == null){
                char2StringMap.put(pattern.charAt(i), subStrs[i]);
                String2CharMap.put(subStrs[i], pattern.charAt(i));
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean abba = new WordPattern().wordPattern("abba", "dog cat cat dog");
        System.out.println(abba);
    }
}
