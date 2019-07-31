import java.util.*;

public class NQueens {
    
    public static List<List<String>> NQs(int n) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, n);
        return res;
    }
    
    private static void dfs(List<List<String>> res, List<Integer> list, int n) {
        List<String> sol = new ArrayList<>();
        if (list.size() == n) {
            sol = formate(list, sol, n);
            res.add(new ArrayList<String>(sol));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(list, i)) {
                list.add(i);
                dfs(res, list, n);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private static boolean isValid(List<Integer> list, int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == col || Math.abs(list.get(i) - col) == list.size() - i) {
                return false;
            }
        }
        return true;
    }
    
    private static List<String> formate (List<Integer> list, List<String> sol, int n) {
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (list.get(i) == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }                        
            }
            sol.add(sb.toString());
        }
        return sol;
    }
    

    public static void main(String[] args) {
        // Test
        List<List<String>> res = NQs(4);
        for (List<String> sol: res) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

}
