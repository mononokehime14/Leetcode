public class BinaryTreePostorderTraversal {
    /*
     * Postorder还能忘掉是什么吗 臭小子 还不抽自己两个大嘴巴子
     */
    private List<Integer> postorder;
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder = new ArrayList<>();
        traverse(root);
        return postorder;
    }
    private void traverse(TreeNode root) {
        if(root == null) return;
        traverse(root.left);
        traverse(root.right);
        postorder.add(root.val);
    }
}
