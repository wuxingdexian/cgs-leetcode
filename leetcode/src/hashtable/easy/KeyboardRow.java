package hashtable.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/keyboard-row/description/" />
 * 500. Keyboard Row
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


 American keyboard


 Example 1:
 Input: ["Hello", "Alaska", "Dad", "Peace"]
 Output: ["Alaska", "Dad"]
 Note:
 You may use one character in the keyboard more than once.
 You may assume the input string will only contain letters of alphabet.
 * <p>
 * 0. 本质：集合
 * 1. 建模：divide set into three subset, then check the word's letters whether all come from the same subset.
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：map
 * 5. 改进：// FIXME: 31/08/2017 这里不用string的substring函数，会更快。直接改为使用Character吧
 * 6. 启发：
 * 7. jdk知识：
 * {@link List#toArray(Object[])}使用该方法，那么就传入一个具体类的数组 如<code>return solutions.toArray(new String[solutions.size()]);</code>
 * {@link String#substring(int)} 中间s小写
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class KeyboardRow {
    public String[] findWords(String[] words) {
        Map<String, Integer> rowsMap = initalizeMap();
        List<String> solutions = new ArrayList();
        for(String subStr: words) {
            int row = rowsMap.get(subStr.substring(0, 1).toLowerCase());
            for(int i = 0; i < subStr.length(); i++) {
                if(row != rowsMap.get(subStr.substring(i, 1+i).toLowerCase())) {
                    break;
                }
                if(i == subStr.length() - 1) {
                    solutions.add(subStr);
                }
            }
        }

        return solutions.toArray(new String[solutions.size()]);

    }

    Map<String, Integer> initalizeMap() {
        Map<String, Integer> rows = new HashMap();
        rows.put("q", 1);
        rows.put("w", 1);
        rows.put("e", 1);
        rows.put("r", 1);
        rows.put("t", 1);
        rows.put("y", 1);
        rows.put("u", 1);
        rows.put("i", 1);
        rows.put("o", 1);
        rows.put("p", 1);

        rows.put("a", 2);
        rows.put("s", 2);
        rows.put("d", 2);
        rows.put("f", 2);
        rows.put("g", 2);
        rows.put("h", 2);
        rows.put("j", 2);
        rows.put("k", 2);
        rows.put("l", 2);

        rows.put("z", 3);
        rows.put("x", 3);
        rows.put("c", 3);
        rows.put("v", 3);
        rows.put("b", 3);
        rows.put("n", 3);
        rows.put("m", 3);
        return rows;
    }
}
