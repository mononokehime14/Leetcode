package graph;

public class PathWithMinimumEffort {
    /* 1631
     * 大型电视连续剧 回溯的诱惑 为什么要用回溯呢？一开始感觉是和LongestIncreasingPathInMatrix这道题有点像
     * 但是一上手就发现 首先不能visited之后就不visit 因为第一次到达的路径不一定就是最好的 所以只能用track防止一个path里走回头路
     * 整个算法是遍历所有选择 应该是4 ** N
     * 然后就在考虑是否可以用DP 可惜这道题和那道increasing path是不一样的 和visited是一个道理 访问到dp有记录的地方 并不能直接用dp的值 因为不是最优子解
     * 所以这道题应该用dijkstra 
     * 之前一直被搞懂 为什么要一个array和node都记录fromStart最短距离 现在感觉是为了pq服务的 每次更新从起点的最短距离的时候都不一定是最终答案
     * 有可能之后还要改 那么改动或者提取pq中存的node是很不方便的 所以array里也存 更新的时候array可以直接更新
     * pq里塞一个新node 由于relax的时候必然最短距离更短 新的node会排在前面 后面就很有必要用
     * if(current.fromStart > distance[current.x][current.y]) continue; 来过掉那些outdated nodes
     * 在这里我们用了提前退出 好像不加这个检查也过了 原因还没想明白
     */
    public int minimumEffortPath(int[][] heights) {
        Queue<Node> pq = new PriorityQueue<>((a, b) -> (a.fromStart - b.fromStart));
        int m = heights.length, n = heights[0].length;
        int[][] distance = new int[m][n];
        for(int[] i: distance) Arrays.fill(i, Integer.MAX_VALUE);
        distance[0][0] = 0;
        pq.offer(new Node(0, 0, 0));
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.x == m - 1 && current.y == n - 1) return current.fromStart; // 重要优化 提升速度到90+ 不要计算全源路径 提前结束
            // if(current.fromStart > distance[current.x][current.y]) continue;
            if(current.x + 1 < m) {
                int newCost = Math.max(Math.abs(heights[current.x +1][current.y] - heights[current.x][current.y]), current.fromStart);
                if(newCost < distance[current.x +1][current.y]) {
                    distance[current.x + 1][current.y] = newCost;
                    pq.offer(new Node(current.x + 1, current.y, newCost));
                } 
            } 
            if(current.x - 1 >= 0) {
                int newCost = Math.max(Math.abs(heights[current.x - 1][current.y] - heights[current.x][current.y]), current.fromStart);
                if(newCost < distance[current.x - 1][current.y]) {
                    distance[current.x - 1][current.y] = newCost;
                    pq.offer(new Node(current.x - 1, current.y, newCost));
                } 
            } 
            if(current.y + 1 < n) {
                int newCost = Math.max(Math.abs(heights[current.x][current.y+1] - heights[current.x][current.y]), current.fromStart);
                if(newCost < distance[current.x][current.y+1]) {
                    distance[current.x][current.y+1] = newCost;
                    pq.offer(new Node(current.x, current.y+1, newCost));
                } 
            } 
            if(current.y - 1 >= 0) {
                int newCost = Math.max(Math.abs(heights[current.x][current.y-1] - heights[current.x][current.y]), current.fromStart);
                if(newCost < distance[current.x][current.y-1]) {
                    distance[current.x][current.y-1] = newCost;
                    pq.offer(new Node(current.x, current.y-1, newCost));
                } 
            } 
        }
        return distance[m-1][n-1];
    }
    // private int gmin;
    // public int minimumEffortPath(int[][] heights) {
    //     gmin = Integer.MAX_VALUE;
    //     int m = heights.length, n = heights[0].length;
    //     boolean[][] track = new boolean[m][n];
    //     dfs(heights, track, 0, 0, m, n, 0, heights[0][0]);
    //     return gmin
    // }
    // private void dfs(int[][] heights, boolean[][] track, int x, int y, int m, int n, int cost, int prev) {
    //     if(x < 0 || x >= m || y < 0 || y >= n || track[x][y]) return;
    //     cost = Math.max(Math.abs(prev - heights[x][y]), cost);
    //     if(x == m - 1 && y == n -1) {
    //         gmin = Math.min(gmin, cost);
    //         return;
    //     }
    //     track[x][y] = true;
    //     dfs(heights, track, x + 1, y, m, n, cost, heights[x][y]);
    //     dfs(heights, track, x - 1, y, m, n, cost, heights[x][y]);
    //     dfs(heights, track, x, y + 1, m, n, cost, heights[x][y]);
    //     dfs(heights, track, x, y - 1, m, n, cost, heights[x][y]);
    //     track[x][y] = false;
    // }
}
class Node {
    public int x;
    public int y;
    public int fromStart;
    public Node(int xx, int yy, int ss) {
        x = xx;
        y = yy;
        fromStart = ss;
    }
}
