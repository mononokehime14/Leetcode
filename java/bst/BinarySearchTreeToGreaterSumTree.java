package bst;

public class BinarySearchTreeToGreaterSumTree {
    /* 1038
     * 这就相当于二分搜索树的前缀和数组
     * 在二分搜索树中 右边子树是肯定大于自己的 在左边的node中 还要考虑parent node的问题
     */
    public TreeNode bstToGst(TreeNode root) {
        calculateSum(root, 0);
        return root;
    }
    private int calculateSum(TreeNode root, int parent) {
        if(root == null) return 0;
        int rightSum = calculateSum(root.right, parent); // 考虑左边的node的右边子树 parent应该继承下来 不应该是0
        int leftSum = calculateSum(root.left, rightSum + root.val + parent);
        int sum = rightSum + leftSum + root.val; // 单纯的sum
        root.val = rightSum + root.val + parent; // 更改root val
        return sum;
    }
}
