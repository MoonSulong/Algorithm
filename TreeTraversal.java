import java.util.*;

/**
 * pre-, in-, post-order in iterative and recursive methods   
 * 
 * level-order with BFS method
 * 
 * @author Sulong
 *
 */

public class TreeTraversal {

    // preOrder Recursion and Iteration
    public List<TreeNode> preOrderRec(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        preRec(root, res);
        return res;
    }

    private void preRec(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        res.add(root);
        preRec(root.left, res);
        preRec(root.right, res);
    }

    public List<TreeNode> preOrderItr(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
    
    public List<TreeNode> levelOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            res.add(node);
            if (node.left != null) {
                q.offer(node.left);
            }
            
            if (node.right != null) {
                q.offer(node.right);
            }           
        }
        return res;
    }

    // inOrder recursion and iteration
    public List<TreeNode> inOrderRec(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        inRec(root, res);
        return res;
    }

    private void inRec(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        inRec(root.left, res);
        res.add(root);
        inRec(root.right, res);
    }

    public List<TreeNode> inOrderItr(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node);
                node = node.right;
            }
        }
        return res;
    }
    
    // postOrder Recursion and Iteration
    public List<TreeNode> postOrderRec(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        postRec(root, res);
        return res;
    }

    private void postRec(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        postRec(root.left, res);
        postRec(root.right, res);
        res.add(root);
    }
    
    public List<TreeNode> postOrderItr(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if (prev == null || curr == prev.right || curr == prev.left) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    res.add(curr);
                }
            } else if (prev == curr.right || prev == curr.left && curr.right == null) {
                stack.pop();
                res.add(curr);
            } else {
                stack.push(curr.right);
            }
            prev = curr;       
        }
        return res;
    }

    public static void print(List<TreeNode> test) {
        for (TreeNode node : test) {
            System.out.print(node.key + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // construct a tree for test
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        List<TreeNode> test = new ArrayList<>();
        TreeTraversal t = new TreeTraversal();

        // Test for preOrder recursion
        test = t.preOrderRec(root);
        System.out.print("preOrder: ");
        print(test);

        // Test for preOrder iteration
        test = t.preOrderItr(root);
        System.out.print("preOrder: ");
        print(test);


        // Test for inOrder recursion
        test = t.inOrderRec(root);
        System.out.print("inOrder: ");
        print(test);

        // Test for inOrder iteration
        test = t.inOrderItr(root);
        System.out.print("inOrder: ");
        print(test);
        
        // Test for inOrder recursion
        test = t.postOrderRec(root);
        System.out.print("postOrder: ");
        print(test);

        // Test for inOrder iteration
        test = t.postOrderItr(root);
        System.out.print("postOrder: ");
        print(test);
        
        // Test for levelOrder iteration
        test = t.levelOrder(root);
        System.out.print("levelOrder: ");
        print(test);
    }
}
