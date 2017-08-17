package math.easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/count-primes/description/" />
 * 204. Count Primes
 * Description:

 Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * 1. 建模：
 * 定理：合数能分解为素数乘积，并且肯定有一个小于等于其平方根的质数为其因子。
 * （1）模型1
 * 法则：include-exclude法则，设定p(i)为被素数i整除的性质，sum(n, p(i))为整数n范围内被素数i整除的整数个数；
 * 则小于n范围内的素数个数为：sum(n, !p(2)!p(3)..!p(i)) = n-(sum(n, p(2)) + sum(n, p(3)) + ..) + (sum(n, p(2&&3)) + sum(n, (2&&5)) + ...) - ... + ...)
 * （2）模型2-方法简单，使用这个模型
 * 找到小于等于其平方根内的所有素数，然后遍历n内的所有整数，找出不被任何这些素数整除的数，最后统计个数
 *
 * 设定有序对(n，primes[])，primes为最大素数大于n的平方根的素数集合。如果primes的最大素数小于n的平方根，则递归该过程获取
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：除法效率太低，应该反过来使用乘法。
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class CountPrimes {

    // ----------------------这个方法太挫-----------------------
    /*
    public int countPrimes(int n) {
        if(n < 3) {
            return 0;
        }

        boolean[] flag = new boolean[n + 1];
        // 默认都是素数
        Arrays.fill(flag, true);
        List<Integer> primes = new ArrayList();
        primes.add(2);
        int sqrt = (int) Math.sqrt(n) + 1;
        getPrimes((int) Math.sqrt(n) + 1, primes, flag);
        // special 2
        int sum = primes.size();
        for(int i = sqrt; i < n; i++) {
            if (flag[i]) {
                for (Integer prime : primes) {
                    if (i % prime == 0) {
                        flag[i] = false;
                        break;
                    }
                }
                if (flag[i]) {
                    sum++;
//                    primes.add(i);
                }
            }
        }
        return sum;
    }

    // 获取小于n的所有素数
    void getPrimes(int n, List<Integer> primes, boolean[] flag) {
        if(primes.get(primes.size() - 1) < n) {
            getPrimes((int)Math.sqrt(n), primes, flag);
        }
        for(int i = primes.get(primes.size() - 1) + 1; i < n; i++) {
            if(flag[i]) {
                for(Integer prime: primes) {
                    if(i % prime == 0) {
                        // 被整除 不是素数
                        flag[i] = !flag[i];
                        break;
                    }
                }
                if(flag[i]) {
                    primes.add(i);
                }
            }
        }
    }
    */
    // ----------------------这个方法太挫-----------------------


    public int countPrimes(int n) {
        boolean[] flag = new boolean[n];
        int count = 0;
        // 从2开始，2是最小的素数.
        int sqrt = (int) Math.sqrt(n);
        for(int i = 2; i < n; i++) {
            if(!flag[i]) {
                count++;
                if(i <= sqrt) {
                    // 因为“合数能分解为素数乘积，并且肯定有一个小于等于其平方根的质数为其因子。”
                    // 所以上面取i * i >= n时，这里内嵌for已经i>sqrt后的所有合数都已经找完
                    for (int j = 2; i * j < n; j++) {
                        flag[i * j] = true;
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int i = new CountPrimes().countPrimes(20);
        System.out.println(i);
    }
}
