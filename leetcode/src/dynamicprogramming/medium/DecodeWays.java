package dynamicprogramming.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/decode-ways/description/" />
 * 91. Decode Ways
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.
 * <p>
 * 1. 建模：可以动态编程建模，也可以决策树建模
 * （1）recurrence relation
 * 设的P(b)为数字字符和前面一个字符比较时出现可能组合，如三个相邻的数字：a,b,c。
 * P(b) = 0，如果b=0 且a=0或a>2
 * P(b) = 1，如果b=0 且a=1
 * P(b) = 1，如果b=0 且a=2
 * P(b) = 1，如果b!=0 且a=0
 * P(b) = 2，如果b!=0 且a=1，这是ab结合或a,b分开
 * P(b) = 2，如果b>0 且 b<7 且a=2
 *
 * 设sum(b)为截止到数字字符b时总共的可能性结果数。如三个相邻的数字：x,a,b,c。假设不存在P(b) = 0，如果b=0 且a=0或a>2的情况
 * sum(b) = 0，如果b=0 且a=0或a>2
 * sum(b) = sum(x)，如果b=0 且a=1
 * sum(b) = sum(x)，如果b=0 且a=2
 * sum(b) = sum(a)，如果b!=0 且a=0
 * sum(b) = sum(a) + sum(x)，如果b!=0 且a=1，此时，ab结合得sum(x)，或a,b分开得sum(a)
 * sum(b) = sum(a) + sum(x)，如果b>=0 且 b<7 且a=2，此时ab结合得sum(x)，或a,b分开得sum(a)
 * sum(b) = sum(a)，如果b!=0 且a>2
 *
 * （2）decision tree（// FIXME: 11/08/2017 决策树太慢，递归导致性能太差）
 * 有序对(combination[], wordchar[])，wordchar 是给定数字字符串的字符数组，combination是对应可能结果
 * 下面给出一次决策树的判断规则：
 * 对于一个字符，若为1到9，则直接取；对于字符，若为1，则同时取下一个字符；对于字符，若为2，则判断下一个字符是否小于等于6大于等于0，是则同时取。
 * 举例：
 *          (_,_,_...), (1,2,6)
 * (1,_,_...), (2,6) | (1,2,_...), (1,2,6)
 * ...
 *
 * 2. 算法范式：dynamic programming、或 backtracking
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：recurrence relation一下子可能很难找到答案，但是通过观察前后关系，总结性质，可能很快得出答案。比如 {@link OutOfBoundaryPaths}
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if(null == s || s.length() == 0) {
            return 0;
        }
        return generateByBackTracking(s.toCharArray(), 0);
    }

    private int generateByDynamicProgramming(char[] numbers) {
        if(numbers.length == 1) {
            return numbers[0] == '0'? 0: 1;
        }

        int sum = 0;
        int sum0 = 1;
        int sum1 = numbers[0] == '0'? 0:1;

        for(int i = 1; i < numbers.length; i++) {
            if(numbers[i] == '0') {
                if(numbers[i - 1] == '0' || numbers[i - 1] > '2') {
                    return 0;
                } else if(numbers[i - 1] == '1') {
                    sum = sum0;
                } else if(numbers[i - 1] == '2') {
                    sum = sum0;
                }
            } else {
                if(numbers[i - 1] == '0') {
                    sum = sum1;
                } else if(numbers[i - 1] == '1') {
                    sum = sum0 + sum1;
                } else if(numbers[i - 1] == '2') {
                    if(numbers[i] >= '0' && numbers[i] < '7') {
                        sum = sum0 + sum1;
                    } else {
                        sum = sum1;
                    }

                } else {
                    sum = sum1;
                }
            }
            sum0 = sum1;
            sum1 = sum;

        }
        return sum;

    }

    private int generateByBackTracking(char[] numbers, int index) {
        if(numbers.length == index) {
            return 1;
        }
        int sum = 0;
//        for(int i = index; i < numbers.length; i++) {

            if(numbers[index] >= '1' && numbers[index] <= '9') {
                sum += generateByBackTracking(numbers, index + 1);
            }

            if(index + 1 < numbers.length && numbers[index] == '1') {
                sum += generateByBackTracking(numbers, index + 2);
            }
            if(index + 1 < numbers.length && numbers[index] == '2' && numbers[index + 1] >= '0' && numbers[index + 1] <= '6') {
                sum += generateByBackTracking(numbers, index + 2);
            }
//        }
        return sum;
    }

    public static void main(String[] args) {
        int i = new DecodeWays().numDecodings("7541387519572282368613553811323167125532172369624572591562685959575371877973171856836975137559677665");
        System.out.println(i);
    }
}
