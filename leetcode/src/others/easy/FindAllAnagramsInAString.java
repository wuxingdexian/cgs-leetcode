package others.easy;

import com.sun.xml.internal.bind.v2.TODO;
import sort.easy.ValidAnagram;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/description/" />
 * 438. Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * 1. 建模：
 * set+function
 * 设s和p分别对应题设的s和t，length(t)表示t的长度，sum(t)为计算t中所有字符相加的和
 * 集合set：
 * domain1：对于s，从0开始，分别截取连续长度为length(t)的子串sub_s(i)，集合取值范围无穷，对于确定的字符串，取值会确定
 * domain2：对于t，计算该字符串的sum(t)
 * codmain：所有连续长度为length(t)的字符sum，无穷
 * sum(t)，集合取值范围无穷，对于确定的t，有一个唯一的sum
 * 函数function：由s中从i开始的连续长度为length(t)的子串sub_s(i)，分别计算sum(sub_s(i))
 *
 * 上面就将s和t分别转换为domain1和domain2，domain2只映射到codomain的一个值，因为domain2只有一个字符串t。
 * domain1映射到codomain，得到大于等于1个值。
 *
 * 注意：这个function不是单射，因为对于ac和bb两个字符串有相同的sum。
 *
 * // TODO: 22/08/2017 明天看下别人如何使用滑动窗口的办法来解决这题 https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/，
 * 参考上面链接，根据别人的代码整理的模型 《Sliding Window algorithm template to solve all the Leetcode substring search problem.》
 * domain：从0开始，分别截取连续长度为length(t)的子串sub_s(i)
 * codomain：t的所有字符构建的有序对ordered-pair，T(i) = (t.charAt(i), 1)，i={1,2,...,t.length}
 * function：s中长度为length(t)的子串sub_s(i)，每个字符和t构建的codomain中的字符映射，并且数量相等。 // FIXME: 23/08/2017 注意，这个函数不是单射
 * 如：aabbc 和bab
 * sub_s(1)=abb，两个b和下面有序对的b映射，；两个b数量相等和有序对b的value相等。 注意：不是单射函数
 * ordered paired:(b,2), (a,1)
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）计算t的和sum(t)
 * （2）预处理s，得到连续长度为length(t)的子串的和。// TODO: 22/08/2017  这一步将降低字符串的很多复杂度. 同时这里可以简单的dynamic programming应用
 * （3）遍历s预处理后的子串和，找到和sum(t)相同的值；单独遍历这个子串和t是否变位词
 * （4）对于两个子串比较是否为变位词，则有很多办法了  参考{@link ValidAnagram}
 *
 * TODO
 * 根据别人的代码整理的模型 《Sliding Window algorithm template to solve all the Leetcode substring search problem.》
 * （1）遍历t，对于每个字符，构建其中有序对，使用hashMap存储。
 * （2）设定有序对个数counter=hashMap的元素个数
 * （3）设定head、tail两个指针，head指向的字符s.charAt(i)，i={1,2,...,s.length}，
 *      1）如果和有序对T(j)的key相等，则T(j)的value-1，同时counter减1；
 *      2）head前进1位；
 * （4）当counter等于0的时候，
 *      1）判断length(head-tail)是否等于length(t)，若相等，则保存tail的位置信息。
 *      2）tail前进1位，判断tail当前指向的字符，通过function，是否找到hashMap中有序对T(j)，若找到，则有序对value+1，同时counter+1；
 *      3）执行上述两个步骤，直到counter不等于0
 * （5）执行上面步骤（3）和（4）直到结束
 * FIXME: 23/08/2017 通过上述对别人代码的细节进行建模，可以看出
 * FIXME（1）这个滑动窗口不是严格意义的窗口，两个指针head和tail的区域使得窗口长度变动。
 * FIXME（2）函数不是单射，原作者使用“ 1）length(head-tail)是否等于length(t)；2）counter是否等于0；”两个来辅助条件来判断结论是否成立。 速度很快 good job
 *
 * 算法3 2018.8.28
 * 和算法1类似，本质上也是函数映射关系。
 * 本次直接初始化目标串的Map，<character, count>键值对，滑动窗口后对character的count进行加减，当所有character的count都为0，则找到一个。
 * 为了记录哪些character已经是count为0。
 * （1）队列：进行滑动窗口控制
 * （2）利用set数据结构：虽然可以在for训练外使用int变量，但是控制起来有点麻烦，需要考虑相同字符是否已经被操作过，简单方便。
 *
 * 如果都是ascii码，可以使用int数组做map，增加一个计数器，可以提高性能
 * 举例 s:"bbbca", p:"abc"
 *          a  b  c
 * 初始化    1  1  1
 * b        1  0  1
 * b        1 -1  1
 * b        1 -2  1
 * c        1 -1  0   窗口为3，第一个b 出队
 * a        0  0  0   窗口为3，第二个b 出队，滑动窗口所有元素为0，找到
 *
 * 4. 数据结构：
 * 有序对ordered-pair(t.charAt(i), 1)，使用hashMap存储
 * 5. 改进：
 * 6. 启发：从set+function角度出来，这个更底层的思维方式和模式不错。 从{@link ValidAnagram}这个题目引发出来的感悟
 * 7. jdk知识：
 * java1.8开始新增了default限制符，能在接口中默认方法的实现http://blog.csdn.net/wwwsssaaaddd/article/details/24213525
 * java 1.8 开始新增了这个函数，不错！{@link Map#getOrDefault(Object, Object)}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/08/2017
 * @see
 * @since cgs-leetcode on  22/08/2017
 */
public class FindAllAnagramsInAString {

    /**
     * 这个滑动窗口感觉更合理，对于ASCII码，要追求性能，可以用int[]数组做map，增加一个计数器
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams3(String s, String p) {
        if (null == p || p.length() == 0 || null == s || s.length() < p.length()) {
            return new ArrayList<>();
        }

        Map<Character, Integer> char2countMap = new HashMap<>();
        for (char c: p.toCharArray()) {
            Integer count = char2countMap.getOrDefault(c, 0);
            count++;
            char2countMap.put(c, count);
        }

        List<Integer> solution = new ArrayList<>();
        // sliding windows
        LinkedList<Character> windowList = new LinkedList<>();
        Set<Character> zeroCharSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            // 维护滑动窗口内的字符和Map的映射关系
            if (windowList.size() >= p.length()) {
                Character character = windowList.removeFirst();
                if (char2countMap.containsKey(character)) {
                    int count = char2countMap.get(character);
                    count++;
                    char2countMap.put(character, count);
                    if (count == 0) {
                        zeroCharSet.add(character);
                    } else {
                        zeroCharSet.remove(character);
                    }
                }
            }
            windowList.addLast(s.charAt(i));

            if (char2countMap.containsKey(s.charAt(i))) {
                Integer count = char2countMap.get(s.charAt(i));
                count--;
                char2countMap.put(s.charAt(i), count);
                if (count == 0) {
                    zeroCharSet.add(s.charAt(i));
                } else {
                    zeroCharSet.remove(s.charAt(i));
                }
            }

            if (zeroCharSet.size() == char2countMap.size()) {
                solution.add(i - p.length() + 1);
            }
        }
        return solution;
    }


    // 根据上面模型2编码 感觉还有有点绕，不太好理解
    public List<Integer> findAnagrams2(String s, String p) {

        Map<Character, Integer> charHashMap = getHashMap(p);
        int head, tail = 0, counter = charHashMap.size();
        List<Integer> solutions = new ArrayList<>();
        for (head = 0; head < s.length(); head++) {
            if(null != charHashMap.get(s.charAt(head))) {
                charHashMap.put(s.charAt(head), charHashMap.get(s.charAt(head)) - 1);
                if (charHashMap.get(s.charAt(head)) == 0) {
                    // 这个条件判断至关重要
                    counter--;
                }
            }
            while (counter == 0 && tail <= head) {
                if(head - tail + 1 == p.length()) {
                    solutions.add(tail);
                }

                if(null != charHashMap.get(s.charAt(tail))) {
                    charHashMap.put(s.charAt(tail), charHashMap.get(s.charAt(tail)) + 1);
                    if (charHashMap.get(s.charAt(tail)) > 0) {
                        // 这个条件判断至关重要
                        counter++;
                    }
                }
                tail++;
            }
        }
        return solutions;
    }

    Map<Character, Integer> getHashMap(String p) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            hashMap.put(p.charAt(i), hashMap.getOrDefault(p.charAt(i), 0) + 1);
        }
        return hashMap;
    }



    /*
    // 加法function有bug，如 "op" "by"
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null || s.length() == 0 || s.length() < p.length()) {
            return new ArrayList();
        }

        // 26个数组，分别和字母对应
        int[] primes = { 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        int pSum = 0;
        for(int j = 0; j < p.length(); j++) {
            pSum += primes[p.charAt(j) - 'a'];
        }

        int[] sSum = new int[s.length() - p.length() + 1];

        for(int j = 0; j < p.length(); j++) {
            sSum[0] += primes[s.charAt(j) - 'a'];
        }
        List<Integer> solutions = new ArrayList();
        if(sSum[0] == pSum) {
            solutions.add(0);
        }
        for(int i = 1; i < sSum.length; i++) {
            sSum[i] = sSum[i - 1] + primes[s.charAt(p.length() - 1 + i) - 'a'] - primes[s.charAt(i - 1) - 'a'];
            if(sSum[i] == pSum) {
                solutions.add(i);
            }
        }
        return solutions;
    }
    */

    /**
     * 这是根据set+function启示设计的算法，accepted后，300ms，有点慢。// TODO: 23/08/2017 看下别人是如何做的
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null || s.length() == 0 || s.length() < p.length()) {
            return new ArrayList();
        }

        int[] sum = new int[s.length() - p.length() + 1];
        int sump = 0;
        for(int j = 0; j < p.length(); j++) {
            sump += p.charAt(j);
        }
        for(int j = 0; j < p.length(); j++) {
            sum[0] += s.charAt(j);
        }

        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + s.charAt(p.length() - 1 + i) - s.charAt(i - 1);
        }

        List<Integer> solutions = new ArrayList();
        for(int i = 0; i < sum.length; i++) {
            if(sum[i] == sump && isAnagrams(s, i, i + p.length(), p)) {
                solutions.add(i);
            }
        }
        return solutions;
    }

    // 这也是set+function的角度建模得来，单射的function，完美
    boolean isAnagrams(String s, int startOfs, int endOfs, String p) {
        boolean[] flag = new boolean[26];
        for(int i = startOfs; i < endOfs; i++) {
            flag[s.charAt(i) - 'a'] = !flag[s.charAt(i) - 'a'];
        }
        for(int i = 0; i < p.length(); i++) {
            flag[p.charAt(i) - 'a'] = !flag[p.charAt(i) - 'a'];
        }
        for(int i = 0; i < flag.length; i++) {
            if(flag[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        List<Integer> anagrams = new FindAllAnagramsInAString().findAnagrams3("cbaebabacdbcabcab", "abc");
        List<Integer> anagrams = new FindAllAnagramsInAString().findAnagrams3("aaaabaaaa", "aaaa");
//        List<Integer> anagrams = new FindAllAnagramsInAString().findAnagrams3("bbbca", "abc");
        System.out.println(anagrams);
    }
}
