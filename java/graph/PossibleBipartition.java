package graph;

public class PossibleBipartition {
    /* 和判断二分图几乎一模一样 区别只在于一些细节
     * 比如给的data不是adjacent list or adjacent matrix
     * 只是一个dislike list 我们需要转化成标准的adjacent list 注意这是undirected 两边都要更新
     * 还有就是index从1开始 为了方便起见 我们也从1开始 memory的构建都多加一个
     */
    boolean[] visited;
    boolean[] colors;
    boolean answer = true;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n + 1];
        colors = new boolean[n + 1];
        List<Integer>[] graph = buildGraph(dislikes, n);
        for(int i = 1;i < n + 1;i++) {
            if(!visited[i]) traverse(graph, i);
        }
        return answer;
    }
    private void traverse(List<Integer>[] graph, int current) {
        if(!answer) return;
        visited[current] = true;
        for(int i: graph[current]) {
            if(!visited[i]){
                colors[i] = !colors[current];
                traverse(graph, i);
            }else{
                if(colors[i] == colors[current]){
                    // not bipartite
                    answer = false;
                    return;
                }
            }
            
        }
    }
    private List<Integer>[] buildGraph(int[][] dislikes, int n) {
        List<Integer>[] graph = new LinkedList[n + 1];
        for(int i = 1;i < n + 1;i++) {
            graph[i] = new LinkedList<>();
        }
        for(int[] pair: dislikes) {
            graph[pair[0]].add(pair[1]);
            graph[pair[1]].add(pair[0]);
        }
        return graph;
    }
}
