package graph;

public class GraphValidTree {
    /* 261
     * 这道题是prime的原因做不了了 记录了答案 题目是要确定图能不能是一棵树 换言之就是检查图里是否有环
     * 树是图的subset 是一种有向无环图 正好有n-1 edge 我认为这是说得通的 一个图要保证全部相连又没有环 只能有n-1个edge
     * 而要检查图里是否有环 之前知道的做法是用dfs 检查track会不会咬自己 但是可以用union find 原理是两个node union的时候 如果都已经在group里面了
     * 那么就会产生环
     */
    boolean validTree(int n, int[][] edges) {
        // 初始化 0...n-1 共 n 个节点
        UF uf = new UF(n);
        // 遍历所有边，将组成边的两个节点进行连接
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 若两个节点已经在同一连通分量中，会产生环
            if (uf.connected(u, v)) {
                return false;
            }
            // 这条边不会产生环，可以是树的一部分
            uf.union(u, v);
        }
        // 要保证最后只形成了一棵树，即只有一个连通分量
        return uf.count() == 1;
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
