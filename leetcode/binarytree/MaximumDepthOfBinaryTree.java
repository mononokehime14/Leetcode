package binarytree;

public class MaximumDepthOfBinaryTree {
    /* 既然要求maximum depth, 这里的思路是divide and conquer, 某种动态规划思路, postorder traverse 
     * 这种做法的速度不如回溯
    */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int step = 1; // root is depth 1
        step += Math.max(maxDepth(root.left), maxDepth(root.right));
        return step;
    }
    /* 也可以用正统的回溯思路, DFS/preorder到一条track的极限, 然后return更新max */
    // 这种做法速度极佳, 100
    int res = 0;
    int depth = 0;
    int maxDepth(TreeNode root) {
        traverse(root);
        return res; 
    }
    void traverse(TreeNode root) {
        if (root == null) {
            res = Math.max(res, depth);
            return; 
        }
        depth++; 
        traverse(root.left); 
        traverse(root.right);
        depth--;
    }
}
