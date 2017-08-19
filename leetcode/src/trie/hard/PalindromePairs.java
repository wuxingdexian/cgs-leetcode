package trie.hard;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/palindrome-pairs/description/" />
 * 336. Palindrome Pairs
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * <p>
 * 1. 建模：
 * 根据题设，这是问题，如果的有n个单词，那么就有 n * (n-1)个组合存在可能。 O(n * n)
 * 根据回文的性质：前后对称。要判断是否回文，最简单的方法就是收尾两个指针，同时往中间收缩，比对是否相等。
 * 为了一开始就能匹配上，利用前缀树
 * 建立两棵前缀树，一个为正常单词前缀树，另一个为逆向单词前缀树。
 * 作如下判断
 * （1）若某个单词和其他单词组合起来能得到回文，那这个单词的开头字符在两个前缀树都能搜索到该字符。
 * （2）若两边前缀树字符不匹配，则该单词不能形成回文。否则继续，当搜索到单词搜索到最后一个字符，逆向前缀树也到达单词结束标识，
 * 则这个单词一定和另一个单词组成回文。注意：若此时逆向前缀树还有以该逆向前缀为前缀的逆向单词，则参考（6）
 * （3）若搜索单词到中间某个字符时，逆向单词前缀树结束，那此时单独判断单词剩下的字符是否构成回文，若构成，则两个单词构成回文，否则失败。
 * （4）若搜索单词到结束，逆向前缀树还未到达单词判断节点，则参考（6）
 *
 * （6）遍历以该逆向前缀为前缀的所有逆向单词，判断逆向前缀后的所有字符是否能否构成回文，若能则该逆向单词和正向单词构成前缀树。
 *
 * 如["abcd", "dcba", "lls", "s", "sssll"]的逆向单词为
 *   ["dcba", "abcd", "sll", "s", "llsss"]
 *   建立两科树
 *
 * 上述（6）的特殊处理，在情况["s","lls","lllls"]时存在，此时[0,1] [0,2]都能构成回文
 *
 * 主要判断这几个情况：正向前缀小于逆向前缀、正向前缀大于逆向前缀、相等。// TODO: 18/08/2017 下面代码在处理这三种情况时处理不好，还有bug。待改进
 * 正向前缀：-------                 -------------       ---------
 * 逆向前缀：-------------           ---------           ---------
 * 设定前缀树节点数据模型
 * [val,nextNodes,position]，其中position同时兼顾判断是否是单词的判断，以及判断单词在原始数组的第几个位置。当为-1，则不是单词，当大于-1，则表示位置
 * 2. 算法范式：
 * 3. 算法：
 * 实施步骤
 * （1）建立两个正向和逆向前缀树
 * （2）遍历单词，执行建模部分提到的策略
 * 注意：若搜索到单词正好匹配，则判断是否是同一个单词，若是则跳过。position可以发挥作用
 *
 * 4. 数据结构：
 * 要判断是否回文，最简单的方法就是收尾两个指针，同时往中间收缩，比对是否相等。这等价于队列的收尾操作
 * 前缀树节点增加标识位，标识这个单词在数组的第几个位置
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/08/2017
 * @see
 * @since cgs-leetcode on  18/08/2017
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {

        Node forwardNode = buildTrie(words);
        Node reverseNode = buildReversTrie(words);
        List<List<Integer>> solutions = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            searchWord(reverseNode, forwardNode, words[i], i, solutions);
            if(words[i] == null || words[i].equals("")) {
                // 对于空串，补偿前面的单词
                for(int j = 0; j < i; j++) {
                    if (isPalindrome(words[j], 0, words[j].length() - 1)) {
                        List<Integer> solution = new ArrayList<>();
                        solution.add(j);
                        solution.add(i);
                        solutions.add(solution);
                    }
                }
            }
        }
        return solutions;
    }


    Node buildTrie(String[] dict) {
        Node root = new Node('$', -1);
        for (int j = 0; j < dict.length; j++) {
            Node point = root;
            for (int i = 0; i < dict[j].length(); i++) {
                Node nextNode = point.nextNodes[dict[j].charAt(i) - 'a'];
                if (nextNode == null) {
                    nextNode = new Node(dict[j].charAt(i));
                    // 对象数组，这里不要忘记把数组的值给指定了
                    point.nextNodes[dict[j].charAt(i) - 'a'] = nextNode;
                }
                if (i == dict[j].length() - 1) {
                    nextNode.position = j;
                }
                point = nextNode;
            }
        }
        return root;
    }

    Node buildReversTrie(String[] dict) {
        Node root = new Node('$', -1);
        for (int j = 0; j < dict.length; j++) {
            Node point = root;
            for (int i = dict[j].length() - 1; i >=0; i--) {
                Node nextNode = point.nextNodes[dict[j].charAt(i) - 'a'];
                if (nextNode == null) {
                    nextNode = new Node(dict[j].charAt(i));
                    // 对象数组，这里不要忘记把数组的值给指定了
                    point.nextNodes[dict[j].charAt(i) - 'a'] = nextNode;
                }
                if (i == 0) {
                    nextNode.position = j;
                }
                point = nextNode;
            }
        }
        return root;
    }

    void searchWord(Node reverseRoot, Node forwardRoot, String word, int position, List<List<Integer>> solutions) {

        int i;
        for (i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
//            i = i;
            // 因为构建的树，保证正向前缀树一定存在
            // 对于前缀单词大于后缀单词的情况处理
            if (reverseRoot.nextNodes[index] == null) {
                if (reverseRoot.position > -1 && isPalindrome(word, i, word.length() - 1)) {
                    List<Integer> legal = new ArrayList<>();
                    legal.add(position);
                    legal.add(reverseRoot.position);
                    solutions.add(legal);
                }
                return;
            }
            if (i == word.length() - 1) {
                if (reverseRoot.nextNodes[index].position > -1 && reverseRoot.nextNodes[index].position != forwardRoot.nextNodes[index].position) {
                    List<Integer> legal = new ArrayList<>();
                    legal.add(position);
                    legal.add(reverseRoot.nextNodes[index].position);
                    solutions.add(legal);
                }
            }

            if (reverseRoot.nextNodes[index].val != forwardRoot.nextNodes[index].val) {
                return;
            }

            reverseRoot = reverseRoot.nextNodes[index];
            forwardRoot = forwardRoot.nextNodes[index];

        }

        // 对于前缀单词大于后缀单词的情况处理
//        if (loop <= word.length()) {
//            if(reverseRoot.position > -1 && reverseRoot.position != forwardRoot.position && isPalindrome(word, loop, word.length() - 1)) {
//                List<Integer> legal = new ArrayList<>();
//                legal.add(position);
//                legal.add(reverseRoot.position);
//                solutions.add(legal);
//            }
//        }

        if(i >= word.length()) {
            // 对于剩下的逆向前缀树遍历遍历是否还存在以该逆向前缀为前缀的单词
            Map<String, Integer> subWordMap = new HashMap<>();
            searchSubWord(reverseRoot, subWordMap, new LinkedList());
            for (Map.Entry<String, Integer> entry : subWordMap.entrySet()) {
                if (isPalindrome(entry.getKey(), 0, entry.getKey().length() - 1)) {
                    List<Integer> legal = new ArrayList<>();
                    legal.add(position);
                    legal.add(entry.getValue());
                    solutions.add(legal);
                }
            }
        }

    }

    // 搜索子字符 dfs
    void searchSubWord(Node root, Map<String, Integer> subWordMap, LinkedList<Character> subChars) {
        if (root == null) {
            return;
        }
        for(Node node: root.nextNodes) {
            if(node != null) {
                subChars.add(node.val);
                if (node.position > -1) {
                    subWordMap.put(getSubWord(subChars), node.position);
                }
                searchSubWord(node, subWordMap, subChars);
                subChars.removeLast();
            }
        }
    }

    String getSubWord(LinkedList<Character> subChars) {
        StringBuilder sb = new StringBuilder();
        for(Character val: subChars) {
            sb.append(val);
        }
        return sb.toString();
    }

    // 判断单词的某个部分是否是回文
    boolean isPalindrome(String word, int s, int e) {
        if(s > e) {
            return false;
        }
        for(; s < e; s++, e--) {
            if(word.charAt(s) != word.charAt(e)) {
                return false;
            }
        }
        return true;
    }



    class Node {
        char val;
        Node[] nextNodes;
        int position;

        Node(char val) {
            this.val = val;
            this.nextNodes = new Node[26];
            position = -1;
        }
        Node(char val, int position) {
            this.val = val;
            this.nextNodes = new Node[26];
            this.position = position;
        }

    }

    public static void main(String[] args) {
//        String[] words = {"abcd","dcba","lls","s","sssll"};
//        String[] words = {"s","lls","lllls"};
//        String[] words = {"a",""};
//        String[] words = {"a","abc","aba", ""};
        String[] words = {"a","b","c","ab","ac","aa"};
        List<List<Integer>> lists = new PalindromePairs().palindromePairs(words);
        System.out.println(lists);
    }
}
