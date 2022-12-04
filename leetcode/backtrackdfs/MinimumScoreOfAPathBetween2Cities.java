package backtrackdfs;

public class MinimumScoreOfAPathBetween2Cities {
    /* 2492
     * 周赛题 第一次碰到可以在图中返回已经去过的点的 有点懵 后来发现 既然只是需要path中最小的部分
     * 允许重复访问其实是等同于1去任何能够去到的地方的cost是1能到的所有edge中最低的cost
     * 这样只需要dfs 然后找出最低的cost 题目甚至保证了1必然能到n
     */
    private int globalMin;
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = buildGraph(roads, n);
        globalMin = 10001;
        dfs(graph, new boolean[n], 0);
        return globalMin;
    }
    private void dfs(List<int[]>[] graph, boolean[] visited, int i) {
        if(visited[i]) return;
        visited[i] = true;
        // track[i] = true;
        for(int[] next : graph[i]) {
            globalMin = Math.min(next[1], globalMin);
            dfs(graph, visited, next[0]);
        }
        // track[i] = false;
    }
    private List<int[]>[] buildGraph(int[][] roads, int n) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<int[]>();
        for(int[] r: roads) {
            graph[r[0]-1].add(new int[]{r[1]-1, r[2]});
            graph[r[1]-1].add(new int[]{r[0]-1, r[2]});
        }
        return graph;
    }
}
