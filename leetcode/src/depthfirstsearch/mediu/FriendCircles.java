package depthfirstsearch.mediu;

import bitmanipulation.easy.FindTheDifference;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/friend-circles/description/" />
 * 547. Friend Circles
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

 Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

 Example 1:
 Input:
 [[1,1,0],
 [1,1,0],
 [0,0,1]]
 Output: 2
 Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 The 2nd student himself is in a friend circle. So return 2.
 Example 2:
 Input:
 [[1,1,0],
 [1,1,1],
 [0,1,1]]
 Output: 1
 Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 Note:
 N is in range [1,200].
 M[i][i] = 1 for all students.
 If M[i][j] = 1, then M[j][i] = 1.
 * <p>
 * 1. 建模：
 * 图
 * 记住，有传递性，则如下面结构，只有一个环，而非两个。
 *                    0
 *                    |
 *                    1
 *                  /   \
 *                 2    3
 *
 * 2. 算法范式：
 * 3. 算法：图的深度遍历
 *
 * ------错误分析------
 * 从0到n，若该点没走过，则逐渐深度遍历：首先记录下到过的每个点，然后从该点i和其相连的大于i并没有走过的点，直到后续没有点可以走，此时得到一个环；
 * 然后依次执行上述步骤。
 * ------------------
 *
 * FIXME: 15/08/2017 上面得分析有错误，因为传递性，应该是只要是点i能到达得点，都算在同一个环内。也即深度遍历，标记已经走过的点，知道不能再遍历。然后再剩下未被标记的点开始遍历。
 *
 * 4. 数据结构：辅助map结构，记录已经走过的点
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        int num = 0;
        Map<Integer, Integer> passedFriends = new HashMap<>();
        for(int i = 0; i < M.length; i++) {
            if(passedFriends.get(i) == null) {
                dfs(M, i, passedFriends);
                num++;
            }
        }
        return num;
    }

    void dfs(int[][] M, int start, Map<Integer, Integer> passedFriends) {

        for(int i = 0; i < M[start].length; i++) {
            if(M[start][i] == 1 ) {
                if(passedFriends.get(i) == null) {
                    passedFriends.put(i, 1);
                    dfs(M, i, passedFriends);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        int circleNum = new FriendCircles().findCircleNum(M);
        System.out.println(circleNum);
    }
}
