package string.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/judge-route-circle/description/" />
 * 657. Judge Route Circle
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/08/2017
 * @see
 * @since cgs-leetcode on  19/08/2017
 */
public class JudgeRouteCircle {
    public boolean judgeCircle(String moves) {
        int leftNum = 0;
        int rightNum = 0;
        int upNum = 0;
        int downNum = 0;
        char[] movements = moves.toCharArray();
        for(int i = 0; i < movements.length; i++) {

            if(movements[i] == 'R') {
                rightNum++;
            } else if(movements[i] == 'U') {
                upNum++;
            } else if(movements[i] == 'L') {
                leftNum++;
            } else if(movements[i] == 'D') {
                downNum++;
            }
        }
        return leftNum == rightNum && upNum == downNum;
    }
}
