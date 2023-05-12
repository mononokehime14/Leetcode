public class EvaluateBooleanBinaryTree {
    /* 2331
     * Easy题 简单的分而治之 或者说遍历也可以
     */
    public boolean evaluateTree(TreeNode root) {
        return traverse(root);
    }
    private boolean traverse(TreeNode root) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return root.val == 1;
        boolean left = traverse(root.left);
        boolean right = traverse(root.right);
        return root.val == 2 ? (left || right) : (left && right);
    }
}
