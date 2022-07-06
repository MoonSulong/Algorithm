import java.util.*;

public class Permutation {
    public static List<String> permutations(String input) {
        List<String> res = new ArrayList<>();
        char[] array = input.toCharArray();
        dfsPermutate(0, array, res);
        return res;
    }

    /*
     * 0 abc bac cba
     * 1 abc acb bac bca cba cab
     * 2 abc acb bac bca cba cab
     */
    private static void dfsPermutate(int idx, char[] array, List<String> res) {
        if (idx == array.length) {
            res.add(new String(array));
            return;
        }
        for (int i = idx; i < array.length; i++) {
            swap(array, i, idx);
            dfsPermutate(idx + 1, array, res);
            swap(array, i, idx);
        }
    }

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        // Test
        List<String> res = permutations("abc");

        for (String s : res) {
            System.out.println(s);
        }
    }
}
