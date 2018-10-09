package others.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/description/" />
 * Given a string, sort it in decreasing order based on the frequency of characters.

 Example 1:

 Input:
 "tree"

 Output:
 "eert"

 Explanation:
 'e' appears twice while 'r' and 't' both appear once.
 So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 Example 2:

 Input:
 "cccaaa"

 Output:
 "cccaaa"

 Explanation:
 Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 Note that "cacaca" is incorrect, as the same characters must be together.
 Example 3:

 Input:
 "Aabb"

 Output:
 "bbAa"

 Explanation:
 "bbaA" is also a valid answer, but "Aabb" is incorrect.
 Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2018
 * @see
 * @since cgs-leetcode on  08/09/2018
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {

       Map<Character, Integer> map = new HashMap<>();

        for (char c: s.toCharArray()) {
            Integer count = map.getOrDefault(c, 0);
            count++;
            map.put(c, count);
        }

        List<Map.Entry<Character, Integer>> collect = map.entrySet().stream().sorted(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).collect(Collectors.toList());
        char[] chars = new char[s.length()];
        int j = 0;
        for (Map.Entry<Character, Integer> entry: collect) {
            for (int i = 0; i < entry.getValue(); i++) {
                chars[j++] = entry.getKey();
            }
        }
        return String.valueOf(chars);

    }
}
