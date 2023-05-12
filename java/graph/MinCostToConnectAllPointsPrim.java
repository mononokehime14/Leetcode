package graph;

public class MinCostToConnectAllPointsPrim {
    /* 1584
     * Prim版本 Prim是基于一种切分定理 如果一刀切断数个edge把一棵树切成两个子树 这几个被切断的edge中 最小cost的那一个必然是minimum spanning tree里的
     * 证明就是最小生成树首先要是树 要能连接所有nodes 两个子树必然要相连 而使他们相连的最短边 必然就出现在minimum spanning tree中
     * 故此 算法就可以从一个node开始 node也算一个子树 切开node和剩下的树 找出最短cutting edge
     * 找出最短的横切边可以用priority queue实现 切开的时候把所有当前node的edge加入pq 然后拿最短edge 把联通的点加入当前的minimum spanning tree中
     * 切两个node和剩下的树 这里切开这两个点和剩下的树碰到的横切边是cumulative的 注意不能加入有环的edge
     * 时间复杂度 加入pq是logE E是edge number 一共E个操作 ElogE 是等同于Kruskal的
     * 空间复杂度也是V + E E来自pq V来自visited 等同于Kruskal
     */
    public int minCostConnectPoints(int[][] points) {
        List<int[]>[] graph = buildGraph(points);
        return new Prim(graph).getWeightSum();
    }
    private List<int[]>[] buildGraph(int[][] points) {
        int n = points.length;
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<int[]>();
        for(int i = 0;i < n;i++){
            for(int j = i + 1;j < n;j++) {
                int cost = manhattanDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
                graph[i].add(new int[]{i, j, cost});
                graph[j].add(new int[]{j, i, cost});
            }
        }
        return graph;
    }
    class Prim {
        // 核心数据结构，存储「横切边」的优先级队列
        private PriorityQueue<int[]> pq;
        // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
        private boolean[] inMST;
        // 记录最小生成树的权重和
        private int weightSum = 0;
        // graph 是用邻接表表示的一幅图，
        // graph[s] 记录节点 s 所有相邻的边，
        // 三元组 int[]{from, to, weight} 表示一条边
        private List<int[]>[] graph;

        public Prim(List<int[]>[] graph) {
            this.pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            this.graph = graph;
            int n = graph.length;
            this.inMST = new boolean[n];
            this.inMST[0] = true;
            cut(0);
            while(!pq.isEmpty()) {
                int[] edge = pq.poll();
                if(this.inMST[edge[1]]) continue;
                this.weightSum += edge[2];
                this.inMST[edge[1]] = true;
                cut(edge[1]);
            }
        }

        private void cut(int node) {
            for(int[] edge : this.graph[node]) {
                if(this.inMST[edge[1]]) continue;
                this.pq.offer(edge);
            }
        }

        public int getWeightSum() { return this.weightSum; }

        public boolean allConnected() {
            for(boolean i : this.inMST) {
                if(!i) return false;
            }
            return true;
        }
    }
    private int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
