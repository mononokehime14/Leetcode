package graph;

public class CheapestFlightsWithinKStops {
    /* 787
     * 这道题自然可以用Dijkstra做 不过尝试用了DP 用回溯加备忘录的做法 但是怎么也debug不对 主要是memo
     * 如果安排从src开始 最短路径就是src的next中 subproblem最小的 加上成本 这样用current带K的memo key是不对的 计算出来的不是最优解
     * 正确答案是反着来的 从dest开始 subproblem是dest的from 加上edge成本 这样似乎就能得到子问题的最优解
     * 除此之外和答案的代码结构几乎一摸一样
     * commented是Dijkstra版本
     */
    private int[][] memo;
    private List<int[]>[] graph;
    private int src, dst;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        memo = new int[n][k+2];
        for(int i = 0;i < n;i++) Arrays.fill(memo[i], -2);
        graph = buildGraph(flights, n);
        this.src = src;
        this.dst = dst;
        return backtrack(dst, k+1); // 自身可以不用算一个 不过memo还是得+2 K是从1开始而不是从0开始的
    }
    private int backtrack(int current, int k) {
        if(current == this.src) return 0;
        if(k == 0) return -1;
        if(memo[current][k] != -2) return memo[current][k];
        // System.out.println("now " + current + " " + k);
        int answer = Integer.MAX_VALUE;
        for(int[] from : graph[current]) {
            int nextCost = backtrack(from[0], k-1);
            if(nextCost == -1) continue;
            answer = Math.min(answer, nextCost + from[1]);
        }
        if(answer == Integer.MAX_VALUE) {
            memo[current][k] = -1;
            return -1;
        }
        memo[current][k] = answer;
        // System.out.println("cun: " + state + " " + answer);
        return answer;
    }
    private List<int[]>[] buildGraph(int[][] nums, int n) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<int[]>();
        for(int[] i : nums) {
            graph[i[1]].add(new int[]{i[0], i[2]}); // 注意是反着来的
        }
        return graph;
    }
    // public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    //     List<int[]>[] graph = new LinkedList[n];
    //     for (int i = 0; i < n; i++) {
    //         graph[i] = new LinkedList<>();
    //     }
    //     for (int[] edge : flights) {
    //         int from = edge[0];
    //         int to = edge[1];
    //         int price = edge[2];
    //         graph[from].add(new int[]{to, price});
    //     }
    
    //     // 启动 dijkstra 算法
    //     // 计算以 src 为起点在 k 次中转到达 dst 的最短路径
    //     K++;
    //     return dijkstra(graph, src, K, dst);
    // }
    
    // class State {
    //     // 图节点的 id
    //     int id;
    //     // 从 src 节点到当前节点的花费
    //     int costFromSrc;
    //     // 从 src 节点到当前节点经过的节点个数
    //     int nodeNumFromSrc;
    
    //     State(int id, int costFromSrc, int nodeNumFromSrc) {
    //         this.id = id;
    //         this.costFromSrc = costFromSrc;
    //         this.nodeNumFromSrc = nodeNumFromSrc;
    //     }
    // }
    
    // // 输入一个起点 src，计算从 src 到其他节点的最短距离
    // int dijkstra(List<int[]>[] graph, int src, int k, int dst) {
    //     // 定义：从起点 src 到达节点 i 的最短路径权重为 distTo[i]
    //     int[] distTo = new int[graph.length];
    //     // 定义：从起点 src 到达节点 i 的最小权重路径至少要经过 nodeNumTo[i] 个节点
    //     int[] nodeNumTo = new int[graph.length];
    //     Arrays.fill(distTo, Integer.MAX_VALUE);
    //     Arrays.fill(nodeNumTo, Integer.MAX_VALUE);
    //     // base case
    //     distTo[src] = 0;
    //     nodeNumTo[src] = 0;
    
    //     // 优先级队列，costFromSrc 较小的排在前面
    //     Queue<State> pq = new PriorityQueue<>((a, b) -> {
    //         return a.costFromSrc - b.costFromSrc;
    //     });
    //     // 从起点 src 开始进行 BFS
    //     pq.offer(new State(src, 0, 0));
    
    //     while (!pq.isEmpty()) {
    //         State curState = pq.poll();
    //         int curNodeID = curState.id;
    //         int costFromSrc = curState.costFromSrc;
    //         int curNodeNumFromSrc = curState.nodeNumFromSrc;
            
    //         if (curNodeID == dst) {
    //             // 找到最短路径
    //             return costFromSrc;
    //         }
    //         if (curNodeNumFromSrc == k) {
    //             // 中转次数耗尽
    //             continue;
    //         }
    
    //         // 将 curNode 的相邻节点装入队列
    //         for (int[] neighbor : graph[curNodeID]) {
    //             int nextNodeID = neighbor[0];
    //             int costToNextNode = costFromSrc + neighbor[1];
    //             // 中转次数消耗 1
    //             int nextNodeNumFromSrc = curNodeNumFromSrc + 1;
    
    //             // 更新 dp table
    //             if (distTo[nextNodeID] > costToNextNode) {
    //                 distTo[nextNodeID] = costToNextNode;
    //                 nodeNumTo[nextNodeID] = nextNodeNumFromSrc;
    //             }
    //             // 剪枝，如果中转次数更多，花费还更大，那必然不会是最短路径
    //             if (costToNextNode > distTo[nextNodeID]
    //                 && nextNodeNumFromSrc > nodeNumTo[nextNodeID]) {
    //                 continue;
    //             }
                
    //             pq.offer(new State(nextNodeID, costToNextNode, nextNodeNumFromSrc));
    //         }
    //     }
    //     return -1;
    // }
}
