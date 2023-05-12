package graph;

public class CourseSchedule2 {
    /* 基础思路和CourseSchedule一模一样 但是有一点不一样 我们需要记录成功的track
     * 单纯的环检测算法不能够完成这个目的 因为没有return什么 没有更新什么 只是测试有没有再遇到track里的值
     * 所以我们需要一个list来记录成功的track 但是为什么加入list是后序呢
     * 这是拓扑排序的特性 设想如果是前序 那么 A _左_ _右_ depends on A的课程自然在A的后面 但是A depends的课程呢 没办法到A的前面去了
     * 而后序 则保证 _左_ _右_ A 可以想象为A depends on 的课程必然在前面 注意这里箭头的意涵改了 A->B是A depends on B
     * 所以 我们可以更改build graph的时候的 可以更改箭头的方向 然后后序加入 如果build graph不改 后面反转过来也是一样的
     */
    List<Integer> postorder;
    boolean[] visited;
    boolean[] track;
    boolean answer;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        postorder = new LinkedList<Integer>();
        visited = new boolean[numCourses];
        track = new boolean[numCourses]; // boolean array initialized to false
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        for(int i = 0;i < numCourses;i++) {
            traverse(i, graph); // 别写错成traverse(0, graph)
        }
        if(answer){
            return new int[]{};
        }
        Collections.reverse(postorder);
        int[] result = new int[numCourses];
        for(int i = 0;i < numCourses;i++){
            result[i] = postorder.get(i);
        }
        return result;
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
        postorder.add(current);
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
