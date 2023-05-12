package dfs;

public class AmountOfTimeForBinaryTreeToBeInfected {
    /* 2385
     * 一遍dfs找到node的parent left和right 相当于构建邻接表
     * 第二遍dfs从start开始到底 保持visited
     * 第二遍也可以用bfs 也是很自然的 一层层往下就可以了
     */
    private HashSet<Integer> visited;
    private HashMap<Integer, List<Integer>> tree;
    private int answer = 0;
    public int amountOfTime(TreeNode root, int start) {
        visited = new HashSet<>();
        tree = new HashMap<>();
        traverseA(root, null);
        traverseB(start, 0);
        return answer;
    }
    private void traverseA(TreeNode root, TreeNode parrent) {
        if(root == null) return;
        tree.putIfAbsent(root.val, new ArrayList<Integer>());
        if(parrent != null) tree.get(root.val).add(parrent.val);
        if(root.left != null) {
            tree.get(root.val).add(root.left.val);
            traverseA(root.left, root);
        }
        if(root.right != null) {
            tree.get(root.val).add(root.right.val);
            traverseA(root.right, root);
        }
    }
    private void traverseB(int root, int height) {
        if(!tree.containsKey(root) || visited.contains(root)) return;
        answer = Math.max(answer, height);
        visited.add(root);
        for(int next : tree.get(root)) {
            traverseB(next, height + 1);
        }
    }
}
