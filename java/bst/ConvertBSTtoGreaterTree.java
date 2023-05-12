package bst;

public class ConvertBSTtoGreaterTree {
    /* 这道题的思路在于 当前节点的新值 应该等同于右子树的sum 或者右子树加上父node(自己是左node的情况)
     * 也就是说 如果我们能够反向中序遍历 这个sum的值就是可以累积上来的
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        traverse(root);
        return root;
    }
    private void traverse(TreeNode root) {
        if(root == null) return;
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
