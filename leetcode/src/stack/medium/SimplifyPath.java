package stack.medium;

import java.util.Iterator;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 题目感觉不是很说明白前提条件。比如"/..."这样的极端条件，题目给出的时输出"/..."
 * 7. jdk知识：
 * （1）{@link StringBuilder}的长度方法是{@link StringBuilder#length()}
 * （2）{@link String#split(String)}会把空字符串带出来
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/08/2018
 * @see
 * @since cgs-leetcode on  22/08/2018
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (null == path || path.length() == 0) {
            return path;
        }

        Stack<String> stack = new Stack<>();
        String[] subNodes = path.split("/");
        for (String s : subNodes) {
            if (".".equals(s)) {
                continue;
            } else if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if ("".equals(s)) {
                continue;
            } else {
                stack.push(s);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            stringBuilder.append("/");
        }
        return stringBuilder.length() > 1? stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString(): stringBuilder.toString();
    }

    public static void main(String[] args) {
        String test = "/home/";
        String s = new SimplifyPath().simplifyPath(test);
        System.out.println(s);

    }

}
