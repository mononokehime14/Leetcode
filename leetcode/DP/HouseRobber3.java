public class HouseRobber3 {
    /* 337
     * 这道题的分类讨论情况有一点不一样 如果我们抢root 则left right都不能抢
     * 如果我们不抢root 并不意味着我们就一定要抢left right 而是left right可抢可不抢 要选择受益最大的
     * 这样 我们就保持抢和不抢两个变量
     */
    public int rob(TreeNode root) {
        int[] result = traverse(root);
        return Math.max(result[0], result[1]);
    }
    private int[] traverse(TreeNode root) {
        if(root == null) return new int[]{0, 0};
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int no = Math.max(left[1], left[0]) + Math.max(right[0], right[1]);
        int yes = left[0] + right[0] + root.val;
        return new int[]{no, yes};
    }
}
