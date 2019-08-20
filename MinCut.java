/*
 * DP: minimum cut palindrome
 * 
 * cut[i] -> the fewest cut for a string with length = i + 1
 * 
 * [j, i] from j to i is palindrome need two conditions
 * 1. array[i] = array[j] 
 * 2. [j+1, i-1] is palindrome
 */
public class MinCut {
    
    public static int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] array = s.toCharArray();
        int n = array.length;
        int[] cut = new int[n];
        boolean[][] isP = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            cut[i] = i;
            for (int j = 0; j <= i; j++) {
                if (array[i] == array[j] && (i -j < 2 || isP[j+1][i-1])) {
                    isP[j][i] = true;
                    if (j == 0) {
                        cut[i] = 0;
                    } else {
                        cut[i] = Math.min(cut[i], cut[j-1] + 1);
                    }
                }
            }
        }
        return cut[n-1];
    }

    public static void main(String[] args) {
        // Test answer 1 & 5
        String s1 = "aaabababab";
        System.out.println(minCut(s1));
        String s2 = "abcdea";
        System.out.print(minCut(s2));

    }

}
