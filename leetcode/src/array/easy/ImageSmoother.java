package array.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/image-smoother/description/" />
 * 661. Image Smoother
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

 Example 1:
 Input:
 [[1,1,1],
 [1,0,1],
 [1,1,1]]
 Output:
 [[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
 Explanation:
 For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 Note:
 The value in the given matrix is in the range of [0, 255].
 The length and width of the given matrix are in the range of [1, 150].
 * <p>
 * 0. 本质：function
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * （1）find nums around it
 * （2）calculate
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class ImageSmoother {
//
//    public int[][] imageSmoother(int[][] M) {
//        int[][] result = new int[M.length]][M[0].length];
//        for(int r = 0; r < M.length; r++) {
//            for(int c = 0; c < M[0].length; c++) {
//                result[r][c] = getFloor(M, r, c);
//            }
//        }
//        return result;
//    }
//
//    int getFloor(int[][] M, int r, int c) {
//        int counter = 0;
//        int sum = 0;
//        if(r == 0 && c == 0) {
//            counter = 1 +( M.length > 1? 1: 0)+ (M[0].length > 1? 1: 0) + (M.length > 1 && M[0].length > 1? 1: 0);
//            sum = M[r][c] +( M.length > 1? M[r+1][c]: 0)/*down*/+ (M[0].length > 1? M[r][c+1]: 0)/*right*/ + (M.length > 1 && M[0].length > 1? M[r+1][c+1]: 0)/*right down*/;
//        } else if(r == 0 && c == M[0].length - 1) {
//            counter = 1 +( M.length > 1? 1: 0)+ (M[0].length > 1? 1: 0) + (M.length > 1 && M[0].length > 1? 1: 0);
//            sum = M[r][c] +( M.length > 1? M[r+1][c]: 0)/*down*/+ (M[0].length > 1? M[r][c-1]: 0)/*right*/ + (M.length > 1 && M[0].length > 1? M[r+1][c+1]: 0)/*right down*/;
//        } else if(r == M.length - 1 && c == 0) {
//
//        } else if(r == M.length - 1 && c == M[0].length - 1) {
//
//        }
//
//        else if(r == 0 && c > 0 && c < M[0].length - 1) {
//
//        } else if(r == M.length - 1 && c > 0 && c < M[0].length - 1) {
//
//        } else if(r > 0 && r < M.length - 1 && c == 0) {
//
//        } else if(r > 0 && r < M.length - 1 && c == M[0].length - 1) {
//
//        }
//
//        else {
//
//        }
//    }
}
