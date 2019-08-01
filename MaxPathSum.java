import java.util.*;

public class MaxPathSum {
    /*
     * Maximum Path Sum Binary Tree I 
     * from one leaf node to another leaf node
     */
    public int maxPathSumL2L(TreeNode root) {
       int[] max = new int[] {Integer.MIN_VALUE}; 
       if (root == null) return max[0];
       leaf2leaf(root, max);
       return max[0];
    }
    
    // bottom up
    private int leaf2leaf(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = leaf2leaf(root.left, max);
        int right = leaf2leaf(root.right, max);
        if (root.left != null && root.right != null) {
            // update global max at non-leaf node
            max[0] = Math.max(max[0], left + right + root.key);
        }
        if (root.left == null) return root.key + right;
        if (root.right == null) return root.key + left;
        return Math.max(left, right) + root.key;
    }
    
    /*
     * Maximum Path Sum Binary Tree II
     * from any node to any node
     */
    public int maxPathSumN2N(TreeNode root) {
        int[] max = new int[] {Integer.MIN_VALUE};
        if (root == null) return max[0];
        node2node(root, max);
        return max[0];
    }
    
    // bottom up
    private int node2node(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = node2node(root.left, max);
        int right = node2node(root.right, max);
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        max[0] = Math.max(max[0], left + right + root.key);
        return Math.max(left, right) + root.key;
    }
    
    /*
     * Maximum Path Sum Binary Tree III
     * single sub-path from root to leaf
     */
    
    public int maxPathSumSubPath(TreeNode root) {
        int[] max = new int[] {Integer.MIN_VALUE};
        subPathSum(root, max, 0);
        return max[0];
    }
    
    // top down
    private void subPathSum(TreeNode root, int[] max, int prefixSum) {
        if (root == null) return;
        // prefixSum prev -> curr
        prefixSum = Math.max(root.key, prefixSum + root.key);
        max[0] = Math.max(prefixSum, max[0]);
        subPathSum(root.left, max, prefixSum);
        subPathSum(root.right, max, prefixSum);
    }
    /*
     * Maximum Path Sum Binary Tree IIII
     * sigle path for root to leaf
     */
    public int maxPathSumL2R(TreeNode root) {
        int[] max = new int[] {Integer.MIN_VALUE};
        leaf2root(root, max, 0);
        return max[0];
    }
    
    // top down
    private void leaf2root(TreeNode root, int[] max, int prefixSum) {
        if (root == null) return;
        // prefixSum root -> curr 
        prefixSum += root.key;
        max[0] = Math.max(prefixSum, max[0]);
        leaf2root(root.left, max, prefixSum);
        leaf2root(root.right, max, prefixSum);
    }
    
    /*
     * Maximum Path Sum Target
     * Any node in sub-path from root to leaf
     */
    
    public boolean maxPathSumT(TreeNode root, int target) {
        if (root == null) return false;
        Set<Integer> sumSet = new HashSet<>();
        sumSet.add(0);
        return subPathSumT(root, target, 0, sumSet);
    }
    
    private boolean subPathSumT(TreeNode root, int target, int sum, Set<Integer> sumSet) {
        sum += root.key;
        boolean remove = sumSet.add(sum);
        if (sumSet.contains(sum - target)) return true;
        if (root.left != null && subPathSumT(root.left, target, sum, sumSet)) {
            return true;
        }
        if (root.right != null && subPathSumT(root.right, target, sum, sumSet)) {
            return true;
        }
        if (remove) {
            sumSet.remove(sum);
        }
        return false;
    }
    
    /*
     * Maximum Path Sum Target
     * from root to leaf
     */
    
    public boolean maxPathSumTR2L(TreeNode root, int target) {
        if (root == null) return false;
        return leaf2rootTar(root, target, 0);
    }
    
    private boolean leaf2rootTar(TreeNode root, int target, int sum) {
        sum += root.key;
        if (root.left == null && root.right == null) {
            return sum == target;
        }
        if (root.left != null && leaf2rootTar(root.left, target, sum)) {
            return true;
        }
        if (root.right != null && leaf2rootTar(root.right, target, sum)) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
     // construct a tree for test
        TreeNode root = new TreeNode(-4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        //root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(-3);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(-1);
        root.right.right = new TreeNode(7);
        
        MaxPathSum sol = new MaxPathSum();
        int leaf2leaf = sol.maxPathSumL2L(root);
        System.out.println(leaf2leaf);
        
        int node2node = sol.maxPathSumN2N(root);
        System.out.println(node2node);
        
        int subpathSum = sol.maxPathSumSubPath(root);
        System.out.println(subpathSum);
        
        int leaf2root = sol.maxPathSumL2R(root);
        System.out.println(leaf2root);
        
        boolean subPathContains = sol.maxPathSumT(root, 13);
        System.out.println(subPathContains);
        
        boolean leaf2rootContains = sol.maxPathSumTR2L(root, 13);
        System.out.println(leaf2rootContains);
    }
}
