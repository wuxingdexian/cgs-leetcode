package bitmanipulation.easy;

/**
 * <p>
 * 背景描述：
 * 190. Reverse Bits
 * Reverse bits of a given 32 bits unsigned integer.

 For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

 Follow up:
 If this function is called many times, how would you optimize it?
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：读一下Hacker's Delight 这本书，应该有启发
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/08/2017
 * @see
 * @since DiscreteMathematics on  08/08/2017
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int num = 0;
        for(int i = 0 ;i < 32; i++) {
            if(((1 << i) & n) != 0) {
                num |= (1 << (31 - i));
            }
        }
        return num;
    }
}
