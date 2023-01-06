package graph;

public class MinCostToConnectAllPoints {
    /* 1584
     * 这是用Kruskal算法做的 所谓Minimum Spanning Tree其实就是一个edge的组 这个组能够在图中形成一个树 
     * 并且权重合最小 Kruskal就是将edge按照权重排序 从小到大一个一个加入group 如果要加入的两点已经能够connect 则不加入以避免环
     * 结合那道Graph Valid Tree的题目 我们就可以发现可以用Union Find做 我觉得DFS检测环似乎适合于静态的图 像这样我们不断加入edge的情况 可能union find实现起来更加简单一些
     * 注意这里一开始想不明白怎么存每个点 实际上我们除了计算曼哈顿距离 其余的情况并不需要知道x和y 故此可以用point在points中的index来代表这个点
     * 
     * Kruskal复杂度分析
     * 假设一幅图的节点个数为V，边的条数为E，首先需要O(E)的空间装所有边，而且 Union-Find 算法也需要O(V)的空间，所以 Kruskal 算法总的空间复杂度就是O(V + E)。
     * 时间复杂度主要耗费在排序，需要O(ElogE)的时间，Union-Find 算法所有操作的复杂度都是O(1)，套一个 for 循环也不过是O(E)，所以总的时间复杂度为O(ElogE)。
     * 如果我们用DFS进行环检测 时间复杂度应该是E * V
     * 
     * Prim算法实现请看MinCostToConnectAllPointsPrim.java
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UF uf = new UF(n);
        Queue<int[]> edges = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        for(int i = 0;i < n;i++){
            for(int j = i + 1;j < n;j++) {
                edges.add(new int[]{i, j, 
                manhattanDistance(points[i][0], points[i][1], points[j][0], points[j][1])});
            }
        }
        int count = 0;
        while(!edges.isEmpty()) {
            int[] edge = edges.poll();
            if(uf.connected(edge[0], edge[1])) {
                continue;
            }
            count += edge[2];
            uf.union(edge[0], edge[1]);
        }
        return count;
    }
    private int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    class UF {
        private int count;
        private int[] parent;
    
        public UF(int size) {
            parent = new int[size];
            count = size;
            for(int i = 0;i < size;i++) parent[i] = i;
        }
        
        private int find(int current) {
            if(parent[current] != current) { // not root
                parent[current] = find(parent[current]);
            }
            return parent[current];
        }
        
        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if(pa == pb) return;
            parent[pb] = pa;
            count--;
        }
        
        public boolean connected(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            return pa == pb;
        }
        
        public int getCount() { return this.count; }
    }
}
