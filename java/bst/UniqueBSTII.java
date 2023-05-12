package bst;

public class UniqueBSTII {
    /* 95
     * 这道题和Unique BST的思路是一样的只不过是要输出所有组合而已
     * 注意这里对于left > right 和 left == right的分开处理
     */
    private List<TreeNode>[][] memo;
    public List<TreeNode> generateTrees(int n) {
        memo = new ArrayList[n+1][n+1];
        return find(1, n);
    }
    private List<TreeNode> find(int left, int right) {
        if(left == right) return Collections.singletonList(new TreeNode(left));
        if(left > right) return Collections.singletonList(null);
        if(memo[left][right] != null) return memo[left][right];
        List<TreeNode> answer = new ArrayList<>();
        for(int i = left;i <= right;i++) {
            List<TreeNode> leftList = find(left, i - 1);
            List<TreeNode> rightList = find(i + 1, right);
            for(int j = 0;j < leftList.size();j++) {
                for(int k = 0;k < rightList.size();k++) {
                    answer.add(new TreeNode(i, leftList.get(j), rightList.get(k)));
                }
            }
        }
        memo[left][right] = answer;
        return answer;
    }
}
