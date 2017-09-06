package others.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/powx-n/description/" />
 * Pow(x, n)
 * Implement pow(x, n).
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：TODO shit 整数int极值问题 还要好好处理下
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class PowXN {
    public double myPow(double x, int n) {
        double result = 1;
        if(n == 0) {
            return 1;
        }
        if(n > 0) {
            for(int i = 0; i < n; i++) {
                result *= x;
            }
        } else {
            for(int i = 0; i > n; i--) {
                result *= 1 /x;
            }
        }


        return result;
    }


    // TODO shit 整数int极值为题
    public double myPow1(double x, int n) {
        return pow(x, (long)n);
    }

    double pow(double x, long n) {
        if(n == 0) {
            return 1;
        }
        if(n < 0) {
            x = 1 / x;
            n = 0 - n;
        }
        return n % 2 == 0? pow(x * x, n >> 1): x * pow(x*x, n>>1);
    }

    public static void main(String[] args) {
        double v = new PowXN().myPow1(2.00000, -2147483648);
        System.out.println(v);
    }
}
