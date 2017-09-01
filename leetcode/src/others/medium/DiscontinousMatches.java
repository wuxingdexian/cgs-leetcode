package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=230006&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B2%5D%3D2%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26sortid%3D311" />
 * discontinous matches
 * 1. 给两个字符串，返回第一个在第二个里面discontinous matches的数量。discontinous matches的定义是 （所有匹配的character中没有任何两个是相邻的）
 * 举例：
 * Input: “cat”, “catapult”
 * Output: 1
 * catapult => not good
 * catapult => not good
 * cactapult => good
 * <p>
 * 来自网络Google面试题目
 * <p>
 * 0. 本质：（1）关系，多对一的ordered-pair；（2）排列，ascending sequence with adjacent position in longer string
 * 1. 建模：
 * 对长字符串S2遍历，得到字符和位置的有序对：(char(i), position_list[])
 * 根据S1，找到所有字符的位置排列，找出排列中不存在位置相邻并且是升序的序列，该序列即为一个解。
 * 排列可以用决策树画出解的序列情况
 *
 * <p>
 * 2. 算法范式：backtracking
 * 3. 算法：dfs
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：压栈和出栈，一定要记得配对
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class DiscontinousMatches {

    int counter = 0;
    Stack<Integer> permutation = new Stack<>();

    int matchNum(String s1, String s2) {
        Map<Character, List<Integer>> char2positionMap = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            List<Integer> positionList = char2positionMap.getOrDefault(s2.charAt(i), new ArrayList<>());
            positionList.add(i);
            char2positionMap.put(s2.charAt(i), positionList);
        }
        dfs(s1, 0, char2positionMap);
        return counter;
    }

    void dfs(String s1, int index, Map<Character, List<Integer>> char2positionMap) {
        if(permutation.size() == s1.length()) {
            counter++;
        }
        if (index >= s1.length() || !char2positionMap.containsKey(s1.charAt(index))) {
            return;
        }

        List<Integer> positions = char2positionMap.get(s1.charAt(index));
        for (int i = 0; i < positions.size(); i++) {
            // 压栈和出栈，一定要记得配对
            if(permutation.isEmpty() || permutation.peek() + 1 < positions.get(i)) {
                permutation.push(positions.get(i));
                dfs(s1, index + 1, char2positionMap);
                permutation.pop();
            }
        }
    }

    public static void main(String[] args) {
        int i = new DiscontinousMatches().matchNum("cat", "ccatapualttt");
//        int i = new DiscontinousMatches().matchNum("cat", "catacpault");
        System.out.println(i);
    }
}
