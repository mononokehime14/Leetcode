public class SameTree {
    /* 100
     * 检查两个二叉树是否结构和val相同 分而治之就可以了
     */
    private boolean answer = true;
    public boolean isSameTree(TreeNode p, TreeNode q) {
        traverse(p, q);
        return answer;
    }
    private void traverse(TreeNode a, TreeNode b) {
        if(!answer) return;
        if(a == null && b == null) return;
        if((a == null && b != null) || (a != null && b == null) || a.val != b.val) {
            answer = false;
            return;
        }
        traverse(a.left, b.left);
        traverse(a.right, b.right);
    }
}
