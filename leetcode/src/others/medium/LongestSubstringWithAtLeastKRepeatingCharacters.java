package others.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/" />
 * 395. Longest Substring with At Least K Repeating Characters
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

 Example 1:

 Input:
 s = "aaabb", k = 3

 Output:
 3

 The longest substring is "aaa", as 'a' is repeated 3 times.
 Example 2:

 Input:
 s = "ababbc", k = 2

 Output:
 5

 The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * <p>
 * 0. 本质：关系、序列-》子序列（集合-》子集合）
 * 1. 建模：
 * 一个字符存在很多个位置，1：N
 * 2. 算法范式：
 * 3. 算法：
 * 算法一：利用关系的角度来求解
 * （1）找出每个字符对应的位置有多少
 * （2）将所有字符位置个数大于等于k的找出来；
 * （3）查看这些字符是否能连续起来，保证中间无空挡
 * TODO 难点在步骤（3），既要判断下标连续，还要判断连续下标内的字符是否都符合个数大于k。
 * TODO 最难的是连续下标范围内如果不符合，那么需要尝试不断减少下标范围进行二次判断。此时应该优先选择递归而非迭代的方式进行。
 *
 * 算法二：阉割版分治法，只有divided-and-conquer，数据预处理，弱化combine TODO 从LeetCode运行分析看，比纯粹的divided-and-conquer高效
 * （1）找出每个字符对应的位置有多少
 * （2）将字符数小于k的位置找出来，这些位置将字符串隔开，得到很多小格；
 * （3）针对不同小格继续（2）的步骤，知道不能再隔开；
 * （4）最后得到结果
 * 这里预处理后的子问题求解和{@link NumberOfIslands}一个路数。都是弱化了非重叠子问题时分治法的combine步骤
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/09/2017
 * @see
 * @since cgs-leetcode on  14/09/2017
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    // 算法二：阉割版分治法，只有divided-and-conquer，数据预处理，弱化combine
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() < k) {
            return 0;
        }

        char[] chars = s.toCharArray();

        return dfs(chars, 0 , chars.length, k);

    }

    // 返回最大的长度
    int dfs(char[] chars, int s, int e, int k) {
        if(e - s < k) {
            return 0;
        }
        int[] counter = new int[26];
        for(int i = s; i < e; i++) {
            counter[chars[i] - 'a']++;
        }

        // tmp to get maxLength
        int maxLength = getFullLength(chars, s, e, k, counter);
        if(maxLength > 0) {
            return maxLength;
        }

        // 隔开数组,dfs
        for(int i = s; i < e; i++) {
            int tmpPoint = i, nextPoint = i;
            while (nextPoint < e && counter[chars[nextPoint] - 'a'] >= k) {
                nextPoint++;
            }
            maxLength = Math.max(dfs(chars, tmpPoint, nextPoint, k), maxLength);
            i = nextPoint;
        }
        return maxLength;
    }

    // if the chars from s to e all are legal, then return e-s
    int getFullLength(char[] chars, int s, int e, int k, int[] counter) {
        int maxLength = 0;
        for(int i = s; i < e; i++) {
            if(counter[chars[i] - 'a'] >= k) {
                maxLength++;
            } else {
                maxLength = 0;
                break;
            }
        }
        return maxLength;
    }


    // 算法一：利用关系的角度来求解
    public int longestSubstringUsingRelation(String s, int k) {
        if(s == null || s.length() < k) {
            return 0;
        }

        return dfsUsingRelation(s.toCharArray(), 0, s.length(), k);

    }

    // FIXME 还有bug，需要调试
    public int dfsUsingRelation(char[] chars, int s, int e, int k) {
        if(e - s < k) {
            return 0;
        }

        // get num of character >= k
        List<Integer>[] cache = new ArrayList[26];
        for(int i = s; i < e; i++) {
            cache[chars[i] - 'a'] = cache[chars[i] - 'a'] == null? new ArrayList(): cache[chars[i] - 'a'];
            cache[chars[i] - 'a'].add(i);
        }

        // get consecutive indexes
        List<Integer> positions = new ArrayList();
        for(int i = 0; i < 26; i++) {
            if(cache[i] != null && cache[i].size() >= k) {
                positions.addAll(cache[i]);
            }
        }
        Collections.sort(positions);

        int maxLength = 0;
        for(int i = 0; i < positions.size();) {
            int tmp = i, j = i + 1;
            while(j < positions.size() && positions.get(tmp) + 1 == positions.get(j)) {
                tmp++;
                j++;
            }
            if (check(chars, k, positions, i, j)) {
                maxLength = j - i;
            } else {
                maxLength = Math.max(dfsUsingRelation(chars, i, j, k), maxLength);
            }
            i = j;
        }
        return maxLength;
    }

    boolean check(char[] chars,int k, List<Integer> positions, int s, int e) {
        if (e - s < k) {
            return false;
        }

        int[] map = new int[26];
        for(int i = s; i < e; i++) {
            map[chars[positions.get(i)] - 'a']++;
        }
        for(int i = s; i < e; i++) {
            if(map[chars[positions.get(i)] - 'a'] < k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        int aaabb = new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("baaabbc", 3);
        int aaabb = new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstringUsingRelation("zzzzzzzzzzaaaaaaaaabbbbbbbbhbhbhbhbhbhbhicbcbcibcbccccccccccbbbbbbbbaaaaaaaaafffaahhhhhiaahiiiiiiiiifeeeeeeeeee", 10);
        System.out.println(aaabb);
    }
}
