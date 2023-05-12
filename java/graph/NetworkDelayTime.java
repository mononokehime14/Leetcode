package graph;

public class NetworkDelayTime {
    /* 743
     * 标准的Dijkstra算法 这里要注意算法实现的一些细节 我们大概率要使用一个node 记录有可能更新的最短路径
     * 同时再额外保持一个array 记录起点到各个点的最短距离
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = buildGraph(times, n);
        Queue<Node> pq = new PriorityQueue<>((a, b) -> (a.disFromK - b.disFromK)); // min heap
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k-1] = 0;
        pq.offer(new Node(k-1, 0, -1));
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.disFromK > distance[current.index]) continue;
            for(int[] neighbor: graph[current.index]) {
                int newDis = current.disFromK + neighbor[1];
                if(newDis < distance[neighbor[0]]) {
                    distance[neighbor[0]] = newDis;
                    pq.offer(new Node(neighbor[0], newDis, current.index));
                }
            }
        }
        int max = Arrays.stream(distance).max().getAsInt();
        return max == Integer.MAX_VALUE ?  -1 : max;
    }
    private List<int[]>[] buildGraph(int[][] times, int n) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<int[]>();
        for(int[] t: times) {
            graph[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }
        return graph;
    }
}
class Node {
    public int index;
    public int disFromK;
    public int prev;
    public Node(int i, int d, int p) {
        index = i;
        disFromK = d;
        prev = p;
    }
}