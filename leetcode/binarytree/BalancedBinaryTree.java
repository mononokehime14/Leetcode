public class BalancedBinaryTree {
    /*
     * 快速想出来的分而治之思路 只要比较左右子树的高度就可以了
     * 这能达到99%的速度 但是8%的空间 我觉得可能是out的使用
     * 实际上 我们可以通过return -1来表示不平衡 然后各个子树再往上传
     */
    private boolean out;
    public boolean isBalanced(TreeNode root) {
        out = true;
        traverse(root);
        return out;
    }
    private int traverse(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = traverse(root.left);
        int rightHeight = traverse(root.right);
        if(Math.abs(leftHeight - rightHeight) > 1) out = false;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
