package graph.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/keys-and-rooms/description/" />
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.

 Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

 Initially, all the rooms start locked (except for room 0).

 You can walk back and forth between rooms freely.

 Return true if and only if you can enter every room.

 Example 1:

 Input: [[1],[2],[3],[]]
 Output: true
 Explanation:
 We start in room 0, and pick up key 1.
 We then go to room 1, and pick up key 2.
 We then go to room 2, and pick up key 3.
 We then go to room 3.  Since we were able to go to every room, we return true.
 Example 2:

 Input: [[1,3],[3,0,1],[2],[0]]
 Output: false
 Explanation: We can't enter the room with number 2.
 * <p>
 * 0. 本质：序列
 * 1. 建模：图，
 * 如果使用DFS，则可以不用列举出其adjacency list；类似先序遍历、中序遍历、后序遍历，注意添加遍历时访问过的点，避免重复死循环访问，此时的遍历即为树的遍历
 * 如果使用BFS，需要列举出其adjacency list；
 *
 * 2. 算法范式：回溯法-》DFS； 或迭代-》BFS
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/10/2018
 * @see
 * @since cgs-leetcode on  15/10/2018
 */
public class KeysAndRooms {


    private boolean success = false;
    private Set<Integer> visitedSet = new HashSet<>();
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms == null || rooms.size() == 0) {
            return success;
        }

        visitedSet.add(0);
        preTraverse(rooms, rooms.get(0));
        return success;
    }

    private void preTraverse(List<List<Integer>> rooms, List<Integer> keyToRooms) {
        if(success) {
            return;
        }

        if(visitedSet.size() == rooms.size()) {
            success = true;
        }

        if(keyToRooms == null || keyToRooms.size() == 0) {
            return;
        }
        for(Integer room: keyToRooms) {
            if(visitedSet.contains(room)) {
                continue;
            }
            // 进入room
            visitedSet.add(room);
            preTraverse(rooms, rooms.get(room));
        }
    }

}
