package dfs;

public class PathSumII {
    /* 113
     * 是承接112的 output path就好了 dfs output path实在是再适合不过了 这里注意track的加入和撤销
     * 一个比较好的思考切入点是最后两层 leaf层两个traverse call完了之后 自己是在最后一个的 所以判断撤销是用removeLast
     */
    private List<List<Integer>> answer;
    private int targetSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        answer = new LinkedList<>();
        traverse(root, 0, new LinkedList<Integer>());
        return answer;
    }
    private void traverse(TreeNode root, int sum, LinkedList<Integer> track) {
        if(root == null) return;
        sum += root.val;
        track.addLast(root.val);
        if(root.left == null && root.right == null) {
            if(sum == targetSum) {
                answer.add(new LinkedList<>(track));
            }
            track.removeLast();
            return;
        }
        traverse(root.left, sum, track);
        traverse(root.right, sum, track);
        track.removeLast();
    }
}
