package bfs;

public class MinimumFuelCostToReportToCapital {
    /* 2477
     * 和Minimum Depth Binary Tree非常相像 反向BFS 从leaves开始向内
     * 细节上 我终于明白为什么之前那道题的答案要用set 是因为之后remove的时候会方便
     * 然后queue里我存了index parent和passengers number
     * 这里困扰了一下的是java怎么round up 要注意ceil里必须是一个double先 所以要先乘1.0 然后ceil完转成int
     * 我用了额外的数组记录passenger的数量
     * 太坑了 没看到题目是long 周赛的时候卡在了最后三个hidden test case 实际上改成long就过了
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        // using set of integer, to facilitate remove later
        Set<Integer>[] graph = buildGraph(roads);
        Queue<int[]> q = new LinkedList<>();
        int n = roads.length + 1;
        // use an additional array to record pasengers number, probably there is a better way
        int[] pasengers = new int[n];
        Arrays.fill(pasengers, 1);
        // add leaves as first level
        for(int i = 1;i < n;i++) {
            if(graph[i].size() == 1) q.add(new int[]{i, graph[i].iterator().next(), 1}); // index of city, parent, passenger number
        }
        int edgeCount = 0;
        long count = 0; // answer
        while(edgeCount < n - 1) {
            int size = q.size();
            edgeCount += size;
            for(int i = 0;i < size;i++) {
                int[] current = q.poll();
                count += (int)Math.ceil(current[2] * 1.0 / seats); // java round up int
                pasengers[current[1]] += current[2];
                if(current[1] != 0) {
                    graph[current[1]].remove(current[0]);
                    if(graph[current[1]].size() == 1) { // only if parent is also leaf after remove, then we add to queue
                        q.add(new int[]{current[1], graph[current[1]].iterator().next(), pasengers[current[1]]});
                    }
                }
            }
        }
        return count;
    }
    private Set<Integer>[] buildGraph(int[][] roads) {
        int n = roads.length + 1;
        Set<Integer>[] graph = new HashSet[n];
        for(int i = 0;i < n;i++) graph[i] = new HashSet<Integer>();
        for(int[] r: roads) {
            graph[r[0]].add(r[1]);
            graph[r[1]].add(r[0]);
        }
        return graph;
    }
}
