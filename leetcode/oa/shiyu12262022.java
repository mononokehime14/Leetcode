package oa;

public class shiyu12262022 {
    /*
     * 给一个图 图中的edge一开始是双向的 但是我们只能选择一边用 问最好的情况下 有多少unreachable nodes 就是有多少没有incoming edge的
     * List<Integer> tree_from = Arrays.asList(1,2,4,5,4);
        List<Integer> tree_to = Arrays.asList(2,3,5,6,6);
        System.out.println(countMinimumUnreachableWarehouses(6, tree_from, tree_to)); -> 1
        tree_from = Arrays.asList(1,2);
        tree_to = Arrays.asList(2,1);
        System.out.println(countMinimumUnreachableWarehouses(3, tree_from, tree_to)); -> 1
        tree_from = Arrays.asList(1,2,3,3,4);
        tree_to = Arrays.asList(2,3,1,4,3);
        System.out.println(countMinimumUnreachableWarehouses(4, tree_from, tree_to)); -> 0
     */
    public static int countMinimumUnreachableWarehouses(int warehouseNodes, List<Integer> warehouseFrom, List<Integer> warehouseTo) {
        List<Integer>[] graph = buildGraph(warehouseNodes, warehouseFrom, warehouseTo);
        boolean[] visited = new boolean[warehouseNodes];
        int count = 0;
        for(int i = 0;i < warehouseNodes;i++) {
            if(visited[i]) continue;
            boolean temp = traverse(i, graph, visited, new boolean[warehouseNodes]);
            if(!temp) count++;
        }
        return count;
    }
    public static boolean traverse(int current, List<Integer>[] graph, boolean[] visited, boolean[] track){
        if(track[current]){ //环
            System.out.println("huan:");
            for(boolean i : track) System.out.print(i + " ");
            System.out.println();
            return true;
        }
        if(visited[current]){ //如果visit过了
            return false;
        }
        visited[current] = true; //后面不用撤回 DFS不回来了
        track[current] = true;
        boolean answer = false;
        for(int next: graph[current]){
            graph[next].remove(Integer.valueOf(current));
            boolean temp = traverse(next, graph, visited, track);
            if(temp) answer = true;
        }
        track[current] = false; //要撤回 别的track是完全不一样的
        return answer;
    } 
    public static List<Integer>[] buildGraph(int n, List<Integer> from, List<Integer> to) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<Integer>();
        for(int i = 0;i < from.size();i++) {
            graph[from.get(i)-1].add(to.get(i)-1);
            graph[to.get(i)-1].add(from.get(i)-1);
        }
        return graph;
    }
}
