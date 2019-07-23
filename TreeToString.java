/*
 * https://leetcode.com/problems/construct-string-from-binary-tree/
 * leetcode 606
 */
import java.util.*;


public class TreeToString {
    
    // recursion
    // Time: O(n)
    // Space: O(n)
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.key + "";
        }
        if (root.right == null) {
            return root.key + "(" + tree2str(root.left) + ")";
        }
        return root.key + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")"; 
        
    }
    
    // iterative
    // Time:
    // Space:
    public String tree2strItr(TreeNode root) {
        if (root == null) return "";
        StringBuilder s = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Set<TreeNode> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if (visited.add(curr)) {
                s.append("(" + curr.key);
                if (curr.left == null && curr.right != null) {
                    s.append("()");
                } 
                
                if (curr.right != null) {
                    stack.push(curr.right);
                } 
                
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            } else {
                stack.pop();
                s.append(")");
            }
        }
        return s.substring(1, s.length()-1);
    }
    
    public static void main(String[] args) {
        // construct a tree for test
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        //root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        
        // test1
        TreeToString t = new TreeToString();
        String s1 = t.tree2str(root);
        for (char c : s1.toCharArray()) {
            System.out.print(c);
        }
        
        System.out.println();
        
        // test2
        String s2 = t.tree2strItr(root);
        for (char c : s2.toCharArray()) {
            System.out.print(c);
        }
        
    }

}
