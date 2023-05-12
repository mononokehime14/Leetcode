package binarytree;

public class InvertBinaryTree {
    /* 基本思路是互换left right, 但是注意到parent互换必须要等到children换完
     * 所以这是postorder traverse
     */
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }
    private void traverse(TreeNode root) {
        if(root == null) return;
        traverse(root.left);
        traverse(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
