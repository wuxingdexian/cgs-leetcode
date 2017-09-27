package others.medium;

import others.hard.WordLadderII;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/word-ladder/description/" />
 * 127. Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 UPDATE (2017/1/20):
 The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.

 * <p>
 * 0. 本质：关系-》图
 * 1. 建模：
 * {@link WordLadderII} 建立单词之间的无向图
 * 对wordList建立图模型，两个单词只有一个字符之差的相邻。
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/09/2017
 * @see
 * @since cgs-leetcode on  19/09/2017
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Set<String> notVisitedWordList = new HashSet<>(wordList);
        notVisitedWordList.remove(beginWord);

        Set<String> visitedWordList = new HashSet<>();
        visitedWordList.add(beginWord);

        // build graph
        Map<String, List<String>> graph = buildGraph(visitedWordList, notVisitedWordList);

        // get solutions
        Stack<String> stack = new Stack<>();
        stack.push(beginWord);

        Map<String, String> cache = new HashMap<>();
        cache.put(beginWord, beginWord);

        List<List<String>> solutions = bfs(graph, beginWord, endWord);
        return solutions.isEmpty()? 0: solutions.get(0).size();
    }

    Map<String, List<String>> buildGraph(Set<String> visitedWordList, Set<String> notVisitedWordList) {
        Map<String, List<String>> cache = new HashMap<>();
        while (!visitedWordList.isEmpty()) {
            Set<String> visitingWordList = new HashSet<>();
            for (String s : visitedWordList) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < s.length(); i++) {
                    char tmp = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String tmpString = String.valueOf(chars);
                        if (notVisitedWordList.contains(tmpString)) {
                            visitingWordList.add(tmpString);

                            // adjacency list
                            List<String> list = cache.getOrDefault(s, new ArrayList<>());
                            list.add(tmpString);
                            cache.put(s, list);

                            // adjacency list
                            List<String> reverseList = cache.getOrDefault(tmpString, new ArrayList<>());
                            reverseList.add(s);
                            cache.put(tmpString, reverseList);
                        }
                    }
                    chars[i] = tmp;

                }
            }
            visitedWordList = visitingWordList;
            for (String s: visitingWordList) {
                notVisitedWordList.remove(s);
            }
        }
        return cache;
    }


    List<List<String>> bfs(Map<String, List<String>> graph, String beginWord, String endWord) {

        List<String> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(beginWord);

        Set<String> visitedNodes = new HashSet<>();
        visitedNodes.add(beginWord);

        // 保存到当前节点的路径
        Map<String, List<String>> pathMap = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(beginWord);
        pathMap.put(beginWord, values);

        List<List<String>> solutions = new ArrayList<>();

        while (!currentLevelNodes.isEmpty() && solutions.isEmpty()) {
            List<String> nextLevelNodes = new ArrayList<>();
            for (String s: currentLevelNodes) {
                List<String> list = graph.get(s);
                if (list == null || list.isEmpty()) {
                    continue;
                }
                for (String s1: list) {
                    if (visitedNodes.contains(s1) && !s1.equals(endWord)) {
                        continue;
                    }
                    List<String> path = new ArrayList<>(pathMap.get(s));
                    path.add(s1);
                    pathMap.put(s1, path);

                    if (s1.equals(endWord)) {
                        solutions.add(new ArrayList<>(path));
                    }

                    nextLevelNodes.add(s1);
                    visitedNodes.add(s1);
                }
            }
            currentLevelNodes = nextLevelNodes;
        }

        return solutions;
    }
}
