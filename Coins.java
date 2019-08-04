/*
 * DFS coins change and factor combinations
 */

import java.util.*;

public class Coins {

    /*
     * Money change
     */
    public List<List<Integer>> change(int money, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsChange(res, list, coins, money, 0);
        return res;
    }

    private void dfsChange(List<List<Integer>> res, List<Integer> list, int[] coins, int money,
                    int index) {
        if (index == coins.length) {
            if (money == 0) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }

        for (int i = 0; i * coins[index] <= money; i++) {
            list.add(i);
            dfsChange(res, list, coins, money - i * coins[index], index + 1);
            list.remove(list.size() - 1);
        }
    }


    /*
     * Factor combinations leetcode 254
     */
    public List<List<Integer>> factors(int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfsFactors(res, list, target, 2);
        return res;
    }

    private void dfsFactors(List<List<Integer>> res, List<Integer> list, int target, int index) {
        if (target <= 1 && list.size() > 1) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = index; i <= target; i++) {
            if (target % i == 0) {
                list.add(i);
                dfsFactors(res, list, target / i, i);
                list.remove(list.size() - 1);
            }
        }

    }

    public static void main(String[] args) {

        Coins c = new Coins();

        List<List<Integer>> test1 = c.change(25, new int[] {5, 2});
        for (List<Integer> list : test1) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        System.out.println("=============");

        List<List<Integer>> test2 = c.factors(12);
        for (List<Integer> list : test2) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }

}
