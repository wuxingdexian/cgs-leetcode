package others.hard;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/" />
 * 30. Substring with Concatenation of All Words
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].
 (order does not matter).

 *
 * <p>
 * 0. 本质：
 * words进行：combinatorics-》generation-》permutation
 * 子串：序列
 * 1. 建模：
 * 如果对words进行排列，那么就有n!种可能，复杂度为n!，应该反过来搞
 *
 * 对words预处理，建模，得到key-value pairs：words_p(word, count)，key：word，value：count
 *
 * 对处理过程的s建模，从头开始，每次截取和word相同长度的资产，得到key-value pairs：sub_P(word, index[]), key：word，value：index[]
 *
 * 2. 算法范式：
 *
 * 3. 算法：
 * （1）设置index_pointer指针，初始为null
 * （2）从头开始遍历的s，截取和words中word相同长度的单词；比对这个单词word是否存在words_p中是否存在
 *      1）若存在，则将这个word加入到sub_P，记录下首字符的位置；同时words_p中对应的word的count减1；同时index_pointer指向word的起始位置0；
 *      2）若不存在，则执行下一步；
 * （3）检查words_p中所有的word的count都变为0，
 *      1）若是则将index_pointer加入到solution；同时index_pointer指向的s中的子串单词在words_p中+1；同时index_pointer偏移一个单词长度，指向下一个单词；
 *      2）若不是，则继续检查s的下一个单词word；
 * （4）若这个单词存在words_p中且count大于0，则将这个word加入到sub_P，同时words_p中对应的word的count减1；
 * （5）若这个单词存在words_p中且count等于0，则将index_pointer指向sub_P中这个word的第一个index，然后将小于这个index的sub_P中的其他单词删除，并将被删除单词对应的words_p恢复为初始值；
 * （6）若这个单词在words_p中不存在，则清空sub_P，并将words_p恢复为初始值；
 * （7）如此执行上述操作，直到最后。
 *
 * 4. 数据结构：
 * hashMap
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/10/2017
 * @see
 * @since cgs-leetcode on  11/10/2017
 */
public class SubstringWithConcatenationOfAllWords {
    // TODO: 19/10/2017 实现
}
