package graph;

public class PathWithMaximumProbability {
    /* 1514
     * 思路和path with minimum effort是一样的 就是Dijkstra
     * 有一些些细节 比如图是双向构建的 pq是最大堆
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Queue<Node> pq = new PriorityQueue<Node>((a, b) -> Double.compare(b.disFromStart, a.disFromStart));
        pq.offer(new Node(start, 1.0));
        double[] distance = new double[n];
        Arrays.fill(distance, 0.0);
        distance[start] = 1.0; //注意不能写成distance[0]
        List<Node>[] graph = buildGraph(edges, n, succProb);
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.index == end) return current.disFromStart; // early end
            for(Node neighbor: graph[current.index]) {
                double newProb = neighbor.disFromStart * current.disFromStart;
                if(newProb > distance[neighbor.index]) {
                    distance[neighbor.index] = newProb;
                    pq.offer(new Node(neighbor.index, newProb));
                }
            }
        }
        return distance[end];
    }
    private List<Node>[] buildGraph(int[][] edges, int n, double[] succProb) {
        List<Node>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<Node>();
        for(int i = 0;i < edges.length;i++) {
            //重复使用了Node结构 正好是一个int index一个double probability 注意这里的disFromStart单纯意味着link probability
            graph[edges[i][0]].add(new Node(edges[i][1], succProb[i]));
            graph[edges[i][1]].add(new Node(edges[i][0], succProb[i])); // 双向构建
        }
        return graph;
    }
}
class Node {
    public int index;
    public double disFromStart;
    public Node(int i, double d) {
        index = i;
        disFromStart = d;
    }
}
