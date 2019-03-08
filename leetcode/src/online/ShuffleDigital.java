package online;


public class ShuffleDigital {

    public int solution(int A) {
        // write your code in Java SE 8

        String sourceStr = Integer.toString(A);
        char[] charSolutions = new char[sourceStr.length()];
        int solutionIndex = 0;
        for (int start = 0, end = sourceStr.length() - 1;; start++, end--) {
            if (solutionIndex >= charSolutions.length) {
                break;
            }
            charSolutions[solutionIndex++] = sourceStr.charAt(start);
            if (solutionIndex >= charSolutions.length) {
                break;
            }
            charSolutions[solutionIndex++] = sourceStr.charAt(end);
        }
        return Integer.valueOf(new String(charSolutions));
    }

    public static void main(String[] args) {
        int A = 1;

        ShuffleDigital shuffleDigital = new ShuffleDigital();

        int solution = shuffleDigital.solution(A);
        System.out.println(solution);
    }

}
