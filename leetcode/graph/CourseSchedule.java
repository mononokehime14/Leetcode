package graph;

public class CourseSchedule {
    /* 首先这道题的思路是检查图循环 如果有循环那么就无法完成 只是这道题有非常多的细节
     * 第一个细节在于不是从一个root开始 而是要for loop全部的点 每个都当root开始
     * 这样操作的原因是点不一定都是相连的 如果只从一个点开始 就会漏掉落单的点
     * 第二个细节在于不能用visited来判断循环 这是因为我们如果碰到了一个已经visited过的点
     * 并不代表这就是循环 只能说我们之前visit过而已 但是如果我们保持一个track track里面的点
     * 代表prerequisite关系的连接 那么如果再遇到同样的点循环就出现了 类比贪吃蛇的路径和蛇本身
     * 这也是为什么visited不用撤销 它不是回溯用的 而是DFS用的 之后必然不会再回来了
     * 第三个细节是用来提速的 我们使用给的int[][]构建了adjacent list 获得了加速
     */
    boolean[] visited;
    boolean[] track;
    boolean answer;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        track = new boolean[numCourses]; // boolean array initialized to false
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        for(int i = 0;i < numCourses;i++) {
            traverse(i, graph);
        }
        return !answer;
    }
    private void traverse(int current, List<Integer>[] graph){
        if(track[current]){ //环
            answer = true;
            return;
        }
        if(visited[current] || answer){ //如果visit过了或者answer已经是true了
            return;
        }
        visited[current] = true; //后面不用撤回 DFS不回来了
        track[current] = true;
        for(int next: graph[current]){
            traverse(next, graph);
        }
        track[current] = false; //要撤回 别的track是完全不一样的
    }
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }
}
