package others.hard;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/word-ladder-ii/description/" />
 * 126. Word Ladder II
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Note:
 Return an empty list if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 UPDATE (2017/1/20):
 The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 * <p>
 * 0. 本质：关系-》图
 * 1. 建模：
 * 对wordList建立图模型，两个单词只有一个字符之差的相邻。
 *
 * 2. 算法范式：
 * 3. 算法：
 * 建立单词的图模型，
 *
 * 按例子，如果使用dfs，可能得不到最短
 * 所以使用广度遍历
 *
 * 4. 数据结构：
 *
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class WordLadderII {

    int shortest = Integer.MAX_VALUE;
    void dfs(Map<String, List<String>> graph, String beginWord, String endWord, List<List<String>> solutions, Stack<String> stack, Map<String, String> cache) {
        List<String> values = graph.get(beginWord);
        if (values == null || values.isEmpty()) {
            return;
        }

        for (int i = 0; i < values.size(); i++) {
            if (cache.containsKey(values.get(i))) {
                continue;
            }

            cache.put(values.get(i), values.get(i));
            stack.push(values.get(i));
            if (values.get(i).equals(endWord)) {
                solutions.add(new ArrayList<>(stack));
                shortest = Math.min(shortest, stack.size());
            } else if (stack.size() < shortest) {
                dfs(graph, values.get(i), endWord, solutions, stack, cache);
            }
            stack.pop();
            cache.remove(values.get(i));
        }
    }

    List<List<String>> getShortestSolutions(List<List<String>> solutions) {
        if (solutions == null || solutions.isEmpty()) {
            return solutions;
        }
        Collections.sort(solutions, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.size() - o2.size();
            }
        });

        List<List<String>> shortestSolutions = new ArrayList<>();
        int shortestLength = solutions.get(0).size();
        shortestSolutions.add(solutions.get(0));
        for (int i = 1; i < solutions.size(); i++) {
            if (solutions.get(i).size() > shortestLength) {
                break;
            }
            shortestSolutions.add(solutions.get(i));
        }
        return shortestSolutions;
    }

    public static void main(String[] args) {
//        String beginWord = "hot";
//        String endWord = "dog";
//        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dog"));
//        String beginWord = "hit";
//        String endWord = "cog";
//        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        String beginWord = "qa";
        String endWord = "sq";
        List<String> wordList = new ArrayList<>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"));
//        String beginWord = "a";
//        String endWord = "c";
//        List<String> wordList = new ArrayList<>(Arrays.asList("a", "b", "c"));

        List<List<String>> ladders = new WordLadderII().findLadders(beginWord, endWord, wordList);
        System.out.println(ladders);
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
            // 避免for循环remove后，还有其他元素和这个是关联的
            visitedWordList = visitingWordList;
            for (String s: visitingWordList) {
                notVisitedWordList.remove(s);
            }
        }
        return cache;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        Set<String> notVisitedWordList = new HashSet<>(wordList);
        notVisitedWordList.remove(beginWord);

        Set<String> visitedWordList = new HashSet<>();
        visitedWordList.add(beginWord);

        // build graph
        Map<String, List<String>> graph = buildGraph(visitedWordList, notVisitedWordList);

        // get solutions
        List<List<String>> solutions = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(beginWord);

        Map<String, String> cache = new HashMap<>();
        cache.put(beginWord, beginWord);
        dfs(graph, beginWord, endWord, solutions, stack, cache);

        // sort, get shortest path
        return getShortestSolutions(solutions);

//        return bfs(graph, beginWord, endWord);
    }


    List<List<String>> bfs(Map<String, List<String>> graph, String beginWord, String endWord) {

        List<String> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(beginWord);

        Set<String> visitedNodes = new HashSet<>();
        visitedNodes.add(beginWord);

        Map<String, List<String>> pathMap = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(beginWord);
        pathMap.put(beginWord, values);

        List<List<String>> solutions = new ArrayList<>();

        // FIXME: 20/09/2017 在a - b - c - d  这样的图中，a到c有两个相同长度的路径
        //                        \    /
        //                          e
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

    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        Set<String> notVisitedWordList = new HashSet<>(wordList);
        notVisitedWordList.remove(beginWord);

        Set<String> visitedWordList = new HashSet<>();
        visitedWordList.add(beginWord);

        // build graph
        Map<String, List<String>> graph = buildGraph(visitedWordList, notVisitedWordList);

        // get shortest length
        List<List<String>> bfs = bfs(graph, beginWord, endWord);
        shortest = bfs.isEmpty()? 0: bfs.get(0).size();

        // get solutions
        List<List<String>> solutions = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(beginWord);

        Map<String, String> cache = new HashMap<>();
        cache.put(beginWord, beginWord);
        dfs(graph, beginWord, endWord, solutions, stack, cache);
        return solutions;
    }

}
