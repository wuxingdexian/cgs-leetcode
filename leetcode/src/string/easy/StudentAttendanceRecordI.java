package string.easy;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/student-attendance-record-i/description/" />
 * 551. Student Attendance Record I
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:

 'A' : Absent.
 'L' : Late.
 'P' : Present.
 A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

 You need to return whether the student could be rewarded according to his attendance record.

 Example 1:
 Input: "PPALLP"
 Output: True
 Example 2:
 Input: "PPALLL"
 Output: False
 * <p>
 * 0. 本质：
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
 * @author changgan on 31/08/2017
 * @see
 * @since cgs-leetcode on  31/08/2017
 */
public class StudentAttendanceRecordI {
    public boolean checkRecord(String s) {
        int absent = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A') {
                absent++;
                if(absent > 1) {
                    return false;
                }
            } else if(s.charAt(i) == 'L') {
                if(i + 1 < s.length() && s.charAt(i + 1)=='L' && i + 2 < s.length() && s.charAt(i + 2)=='L') {
                    return false;
                }
            }
        }
        return true;
    }
}
