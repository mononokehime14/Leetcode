public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    /* 2458
     * 很难 我直接用了暴力解法 每次query寻找node顺便计算高度 TLE了 正确答案的第一个思路就是对于这种多个query的题目
     * 很多情况下是要提前把答案算好的
     * 这道题正确的思路是用dfs扫两遍 第一遍计算出每个node left和right subtree的height 注意height和depth的区别
     * height是自下而上 depth是自上而下 第二遍就是计算对于每个node 如果删除自己 maxHeight是多少
     * 第一遍dfs并不难理解 重点在于第二遍 我认为核心在于上一层要计算这一层的node如果删除的maxHeight
     * 比如root的左边如果删掉 那么maxHeight就是右子树的高度+1 这里还有一个问题 就是一个node删掉之后 max可能存在于和这个node同一层的
     * 其他node上 而不仅仅是右边 也就是说 max存在两种情况 第一种是当前parent的另一个node的height 第二种则是不经过parent的 别的部分的max Height
     * 所以traverseB在一开始 root是root.left的时候 curMax设置成了右子树的height 在继续递归中 仍要考虑/不断更新这个curMax
     */
    HashMap<Integer, Integer> h, r, l;
    public int[] treeQueries(TreeNode root, int[] queries) {
        h = new HashMap<>();
        r = new HashMap<>();
        l = new HashMap<>();
        traverseA(root);
        traverseB(root.left, r.get(root.val), 1);
        traverseB(root.right, l.get(root.val), 1);
        int m = queries.length;
        int[] answer = new int[m];
        for(int i = 0;i < m;i++) {
            answer[i] = h.get(queries[i]);
        }
        return answer;
    }
    private int traverseA(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = traverseA(root.left);
        int rightHeight = traverseA(root.right);
        r.put(root.val, rightHeight);
        l.put(root.val, leftHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    private void traverseB(TreeNode root, int curMax, int depth) {
        if(root == null) return;
        h.put(root.val, curMax);
        traverseB(root.left, Math.max(curMax, r.get(root.val) + depth), depth+1);
        traverseB(root.right, Math.max(curMax, l.get(root.val) + depth), depth+1);
    }
    // private int q;
    // public int[] treeQueries(TreeNode root, int[] queries) {
    //     int n = queries.length;
    //     int[] answer = new int[n];
    //     for(int i = 0;i < n;i++) {
    //         q = queries[i];
    //         int height = Math.max(traverse(root) - 1, 0);
    //         answer[i] = height;
    //     }
    //     return answer;
    // }
    // private int traverse(TreeNode root) {
    //     if(root == null) return 0;
    //     if(root.val == q) {
    //         root = null;
    //         return 0;
    //     }
    //     return 1 + Math.max(traverse(root.left), traverse(root.right));
    // }
}
