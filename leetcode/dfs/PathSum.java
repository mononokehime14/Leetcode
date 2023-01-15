package dfs;
public class PathSum {
    /* 112
     * 比较简单的DFS 到底比较sum 这里错了一次因为没理解leaf的定义 不是null就是leaf
     * 而是left right同为null的时候才算是leaf
     */
    private boolean answer = false;
    private int targetSum;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        traverse(root, 0);
        return answer;
    }
    private void traverse(TreeNode root, int sum) {
        if(root == null) return;
        sum += root.val;
        if(root.left == null && root.right == null) {
            if(sum == targetSum) {
                answer = true;
            }
            return;
        }
        traverse(root.left, sum);
        traverse(root.right, sum);
    }
}
