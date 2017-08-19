package trie.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/replace-words/description/" />
 * 648. Replace Words
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

 Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

 You need to output the sentence after the replacement.

 Example 1:
 Input: dict = ["cat", "bat", "rat"]
 sentence = "the cattle was rattled by the battery"
 Output: "the cat was rat by the bat"
 Note:
 The input will only have lower-case letters.
 1 <= dict words number <= 1000
 1 <= sentence words number <= 1000
 1 <= root length <= 100
 1 <= sentence words length <= 1000
 * <p>
 * 1. 建模：
 * 步骤：
 * （1）为单词建立前缀树trie，结构为Node为(val,nextNodes[],isWord)，
 * val保存当前字符的值，nextNodes保存下一层字符，isWord判断截止目前这个字符串是否为单词
 * nextNodes直接初始26长度数组
 * （2）单词搜索
 * val-'a'直接方便定位到nextNodes的位置
 * （3）单词替换
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * java创建对象数组，要额外给数组元素new对象，然后赋值，不要忘记了。下面<code>point.nextNodes[word.charAt(i) - 'a'] = nextNode;</code>如果不添加，则最后返回的{@link Node#nextNodes}都是空
 * 这里为了简便，将Node的成员变量设定默认访问级别，在面试或实际开发过程要封装 get 和set
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/08/2017
 * @see
 * @since cgs-leetcode on  18/08/2017
 */
public class ReplaceWords {
    public String replaceWords(List<String> dict, String sentence) {

        Node root = buildTrie(dict);
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length - 1; i++) {
            sb.append(searchShortestWord(words[i], root));
            sb.append(" ");
        }
        sb.append(searchShortestWord(words[words.length - 1], root));
        return sb.toString();
    }

    Node buildTrie(List<String> dict) {
        Node root = new Node('$', false);
        for(String word: dict) {
            Node point = root;
            for(int i = 0; i < word.length(); i++) {
                Node nextNode = point.nextNodes[word.charAt(i) - 'a'];
                if(nextNode == null) {
                    nextNode = new Node(word.charAt(i), i == word.length() - 1);
                    // 对象数组，这里不要忘记把数组的值给指定了
                    point.nextNodes[word.charAt(i) - 'a'] = nextNode;
                }
                if(i == word.length() - 1) {
                    nextNode.isWord = true;
                }
                point = nextNode;
            }
        }
        return root;
    }

    String searchShortestWord(String word, Node root) {
        char[] vals = new char[word.length()];
        for(int i = 0; i < word.length(); i++) {
            Node nextNode = root.nextNodes[word.charAt(i) - 'a'];
            if(nextNode == null) {
                return word;
            }
            vals[i] = word.charAt(i);
            if(nextNode.isWord) {
                return new String(vals, 0, i + 1);
            }
            root = nextNode;
        }
//        bug，放在这里，要是上面word遍历完就是没找到，则i已经等于word.length()，这里再加1则数组越界.将功能提到for里面
//        return new String(vals, 0, i + 1);
        return word;
    }

    class Node{
        char val;
        Node[] nextNodes;
        boolean isWord;

        public Node(char val, boolean isWord) {
            this.val = val;
            this.nextNodes = new Node[26];
            this.isWord = isWord;
        }
    }

    public static void main(String[] args) {

        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");
        String s = new ReplaceWords().replaceWords(dict, "the cattle was rattled by the battery");
        System.out.println(s);
    }

}
