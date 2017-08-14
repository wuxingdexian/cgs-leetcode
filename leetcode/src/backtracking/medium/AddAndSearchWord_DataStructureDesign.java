package backtracking.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/add-and-search-word-data-structure-design/description/" />
 * 211. Add and Search Word - Data structure design
 * Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 For example:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 Note:
 You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * 1. 建模：决策树
 * （1）模型1
 * 建立两个单词匹配的有序对(searchWord[], dictionaryWord[])，
 * 首先必须满足searchWord.length == dictionaryWord.length
 *                                                              (searchWord[], dictionaryWord[])
 *                              (searchWord[0] == '.' || searchWord[0] == dictionaryWord[0])  |    (searchWord[0] != dictionaryWord[])
 *                              (searchWord[1] == '.' || searchWord[1] == dictionaryWord[1]) |   (searchWord[1] != dictionaryWord[1])
 * 满二叉树，但是根的左树不断深入
 *
 * （2）由于上面的模型太耗费时间，需要设计单词存储的结构
 * 设计一棵决策树来保存单词
 * 树根为*，叶子节点为$，表示遍历下来得到完整的单词。如保存单词aa,aab,abb,abc，那这颗树大致的形状就是下面这样的。
 *                                                  *
 *                  a       |    b      |      c      |      d   |...
 *             a     |   b
 *          $  |  b    b | c
 *                $    $   $
 *
 * 再设计单词查找的决策树，设有序对((_,_,...),(subWordTreeList_i), i)，第一个括号内表示待查找单词的字符数组，第二个表示对应单词的第i个字符对应的单词树的深度i的子树，第三个i表示到达单词的第i个字符
 *  举例：查找单词“.b”
 *                  ((.,b), subWordTreeList_0, 0)   ,0表示第1个
 *  ((.,b), subWordTreeList_0_0, 2) | ((.,b), subWordTreeList_0_1, 2) |
 *  判断条件：若i小于word.length 并且 subWordTreeList_i==null，则返回false；若i等于word.length，并且subWordTreeList_i包含'$'的节点，则返回TRUE；
 * 2. 算法范式：backtracking
 * 3. 算法：遍历所有的单词，只要有相同的就返回。一次遍历执行的单词匹配有上面决策树执行
 * 4. 数据结构：（1）模型1，使用List<String>来保存单词，太耗时；（2）模型2建立树结构，注意List的效率比数组低，所有树结构改List为数组
 * 5. 改进：（1）模型1，第一次没有建立单词树，速度很慢，通不过；（2）建立了单词树，和搜索决策树。其中单词树的数据结构改用数组来存储
 * 6. 启发：这种遍历多个，找到是否有其中一个为真，返回值又是布尔值的。那么在递归拿到返回值时判断结果，如果为true则返回true，避免继续往后走，影响结果。如
 * <code>
 *     if (subWordTrees[i] != null && compare(searchedWords, subWordTrees[i].getSubWordTrees(), step + 1)) {
 *          return true;
 *      }
 * </code>
 * 7. jdk知识：new一个基本类型的数组，会自动给其分配内存，但是new一个对象，则不会为其自动分配空间，如<code>this.subWordTrees = new WordTree[27];</code>。
 * 所有需要先new，用时要判断NPE
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 10/08/2017
 * @see
 * @since DiscreteMathematics on  10/08/2017
 */
public class AddAndSearchWord_DataStructureDesign {

    /** Initialize your data structure here. */
    private WordTree wordTree;
    public AddAndSearchWord_DataStructureDesign() {
        wordTree = new WordTree('*');

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (null == word) {
            return;
        }
        WordTree tmpWordTree = wordTree;
        for (int i = 0; i < word.length(); i++) {
            WordTree subWordTree = tmpWordTree.getSubWordTrees()[word.charAt(i) - 'a'];
            if(null != subWordTree) {
                tmpWordTree = subWordTree;
                continue;
            } else {
                subWordTree = new WordTree(word.charAt(i));
                tmpWordTree.getSubWordTrees()[word.charAt(i) - 'a'] = subWordTree;
                tmpWordTree = subWordTree;
            }
        }
        tmpWordTree.getSubWordTrees()[26] = (new WordTree('$'));
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {

        if (null == word) {
            return false;
        }
        return compare(word.toCharArray(), wordTree.getSubWordTrees(), 0);
    }

    public boolean compare(char[] searchedWords, WordTree[] subWordTrees, int step) {

        if(searchedWords.length == step) {
            if(null != subWordTrees[26] && subWordTrees[26].getLetter() == '$') {
                return true;
            } else {
                return false;
            }
        }

        if (searchedWords[step] == '.') {
            for (int i = 0; i < 26; i++) {
                if (subWordTrees[i] != null && compare(searchedWords, subWordTrees[i].getSubWordTrees(), step + 1)) {
                    return true;
                }
            }
        } else if(null != subWordTrees[searchedWords[step] - 'a'] && searchedWords[step] == subWordTrees[searchedWords[step] - 'a'].getLetter()){
            if (compare(searchedWords, subWordTrees[searchedWords[step] - 'a'].getSubWordTrees(), step + 1)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        AddAndSearchWord_DataStructureDesign words = new AddAndSearchWord_DataStructureDesign();

        words.addWord("a");
        words.addWord("ab");
//        words.addWord("aac");

        boolean aa = words.search(".ddd");
        System.out.println(aa);


    }

    class WordTree {
        private char letter;
        private WordTree[] subWordTrees;

        WordTree(char letter) {
            this.letter = letter;
            // subWordTrees[26] 第27号元素用来表明这个单词是否结束，给该元素赋值时，需要new对象
            this.subWordTrees = new WordTree[27];
        }

        public char getLetter() {
            return letter;
        }

        public void setLetter(char node) {
            this.letter = node;
        }

        public WordTree[] getSubWordTrees() {
            return subWordTrees;
        }

        public void setSubWordTrees(WordTree[] subWordTrees) {
            this.subWordTrees = subWordTrees;
        }
    }


    //---------------------------------------------------------------------------------------------------------------------------------
    /*下面是第一次写的很耗费性能单词程序*/

//    /** Initialize your data structure here. */
//    List<String> dictionary;
//    public WordDictionary() {
//        // 使用List，则元素有重复的情况
//        dictionary = new ArrayList();
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        if(null != word) {
//            dictionary.add(word);
//        }
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        if(null == word) {
//            return false;
//        }
//        for(String dictionaryWord: dictionary) {
//            if(dictionaryWord.length() == word.length()) {
//                if(compair(word.toCharArray(), dictionaryWord.toCharArray(), 0)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean compair(char[] searchedWordChars, char[] dictionaryWordChars, int step) {
//        if(step == searchedWordChars.length) {
//            return true;
//        }
//
//        for(int i = step; i < searchedWordChars.length; i++) {
//            if(searchedWordChars[i] == '.' || searchedWordChars[i] == dictionaryWordChars[i]) {
//                return compair(searchedWordChars, dictionaryWordChars, step + 1);
//            } else {
//                return false;
//            }
//        }
//        return false;
//    }
    //---------------------------------------------------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------------------------------------------------
//    这是第二次写的，能通过，但是还有点慢
//    /** Initialize your data structure here. */
//    private WordTree wordTree;
//    public WordDictionary() {
//        wordTree = new WordTree('*', new ArrayList<>());
//
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        if (null == word) {
//            return;
//        }
//        WordTree tmpWordTree = wordTree;
//        for (int i = 0; i < word.length(); i++) {
//            WordTree subWordTree = getSubWordTree(tmpWordTree.getSubWordTreeList(), word.charAt(i));
//            if(null != subWordTree) {
//                tmpWordTree = subWordTree;
//                continue;
//            } else {
//                subWordTree = new WordTree(word.charAt(i), new ArrayList<>());
//                tmpWordTree.getSubWordTreeList().add(subWordTree);
//                tmpWordTree = subWordTree;
//            }
//        }
//        tmpWordTree.getSubWordTreeList().add(new WordTree('$', null));
//    }
//
//    private WordTree getSubWordTree(List<WordTree> subWordTreeList, char letter) {
//        for (WordTree subWordTree: subWordTreeList) {
//            if (subWordTree.getLetter() == letter) {
//                return subWordTree;
//            }
//        }
//        return null;
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//
//        if (null == word) {
//            return false;
//        }
//        WordTree tmpWordTree = wordTree;
//
//        return compare(word.toCharArray(), wordTree.getSubWordTreeList(), 0);
//    }
//
//    public boolean compare(char[] searchedWords, List<WordTree> subWordTreeList, int step) {
//        if (null == subWordTreeList) {
//            return false;
//        }
//        if(searchedWords.length == step) {
//            if(null != getSubWordTree(subWordTreeList, '$')) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        for (WordTree wordTree: subWordTreeList) {
//            if (searchedWords[step] == '.' || wordTree.getLetter() == searchedWords[step]) {
//                if (compare(searchedWords, wordTree.getSubWordTreeList(), step + 1)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    class WordTree {
//        private char letter;
//        private List<WordTree> subWordTreeList;
//
//        WordTree(char letter, List<WordTree> subWordTreeList) {
//            this.letter = letter;
//            this.subWordTreeList = subWordTreeList;
//        }
//
//        public char getLetter() {
//            return letter;
//        }
//
//        public void setLetter(char node) {
//            this.letter = node;
//        }
//
//        public List<WordTree> getSubWordTreeList() {
//            return subWordTreeList;
//        }
//
//        public void setSubWordTreeList(List<WordTree> subWordTreeList) {
//            this.subWordTreeList = subWordTreeList;
//        }
//    }
    //---------------------------------------------------------------------------------------------------------------------------------
}
