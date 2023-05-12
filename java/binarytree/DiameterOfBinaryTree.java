package binarytree;

public class DiameterOfBinaryTree {
    /* 这道题和maximum depth不同的地方在于, 最大直径可能不经过root, 而存在于某个
     * node左边连到其右边的局部网络中, 所以除了max(left + right) + 1这种递推之外
     * 还要额外保持一个runtime max记录pass该node的这种连接情况的最大值
     * 本质还是动规思路的二叉树postorder traverse, 由于其题目的特殊性要注意细节
     */
    int runtime_max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        return Math.max(traverse(root), runtime_max);
    }
    private int traverse(TreeNode root) {
        if(root == null) return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        if(root.left == null && root.right == null){
            return 0;
        }
        if(root.left != null && root.right != null){
            runtime_max = Math.max(runtime_max, left + right + 2);
        }
        return 1 + Math.max(left, right);
    }
}
