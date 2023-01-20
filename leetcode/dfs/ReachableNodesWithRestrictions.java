package dfs;

public class ReachableNodesWithRestrictions {
    /* 2368
     * 普通的dfs 这类n nodes n-1的undirected graph 由于一条边两个node都有记录 traverse的时候要注意visited
     * 由于是树的原因 只需要带一个parent 保证不回到parent就可以了
     */
    private int answer = 0;
    private List<Integer>[] tree;
    private HashSet<Integer> r;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        tree = buildTree(edges, n);
        r = new HashSet<>();
        for(int i : restricted) r.add(i);
        traverse(0, -1);
        return answer;
    }
    private void traverse(int root, int parent) {
        if(r.contains(root)) return;
        answer++;
        for(int next : tree[root]) {
            if(next == parent) continue;
            traverse(next, root);
        }
    }
    private List<Integer>[] buildTree(int[][] edges, int n) {
        List<Integer>[] tree = new ArrayList[n];
        for(int i = 0;i < n;i++) tree[i] = new ArrayList<>();
        for(int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        return tree;
    }
}
