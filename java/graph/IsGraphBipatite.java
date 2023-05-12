package graph;

public class IsGraphBipatite {
    /* 整体思路和图DFS非常相似 但是有一些细节要注意
     * 第一个是如果判断二分图呢 就是要一条边的两个node不能是同一个set 也就是同一个颜色
     * 那么 我们就需要一个额外的colors数组记录node的颜色 并且比较相邻的两个node的颜色
     * 这里就要注意 我们需要node相邻的node信息 甚至要更新他们的颜色
     * 那么正常的图DFS 是node by node 没有这种信息的 所以这一次 我们在traverse递归前 要对相邻node做更新
     * 不用记录路径 visited就可以了 停止条件就是visited或者相邻node都visited过了
     * 这里注意一个小细节 一开始的for循环我们的目的是因为有可能有互相独立的sub图 我们要把他们全部检查到
     * 这里如果是visited那么必然属于已经被检查过的sub 我们就可以跳过了 一个小优化
     */
    boolean[] visited;
    boolean[] colors;
    boolean answer = true;
    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        colors = new boolean[graph.length];
        for(int i = 0;i < graph.length;i++) {
            if(!visited[i]) traverse(graph, i);
        }
        return answer;
    }
    private void traverse(int[][] graph, int current) {
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
}
