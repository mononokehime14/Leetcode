public class SumRootToLeafNumbers {
    /* 129
     * 单纯的root到leaf遍历而已
     */
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        traverse(root, 0);
        return sum;
    }
    private void traverse(TreeNode root, int current) {
        if(root == null) return;
        current = current * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum += current;
            return;
        }
        traverse(root.left, current);
        traverse(root.right, current);
    }
}
