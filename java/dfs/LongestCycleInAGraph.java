package dfs;

public class LongestCycleInAGraph {
    /* 2360
     * 自己做出来的 看答案发现不用递归 由于只有一个outgoing edge 所以for迭代就可以了
     * 然后自己用的track本来是int[n][2] 第一个维度记录是否visit 第二个记录step 但是这样会TLE
     * 换了HashMap之后不知道为什么就过了 别的答案用for时间复杂度不会快还是O(N) 但是空间可能省去了递归stack的成本
     * 思路就是单纯的环检测 由于maximum并不是要求含有环的path 而是环本身的长度
     * 而一个node最多只有一个outgoing edge 这样的话 一个有环的path 是不可能再形成另一个环的
     * 那么答案就是找出所有的环 并且比较长度 所以可以参考环检测的算法 一个global visited 一个local track
     * track咬住成为环 visited过了不去 visited还保证我们能迭代到每一个independent的环
     */
    private boolean[] visited;
    private int answer = -1;
    private int[] edges;
    public int longestCycle(int[] edges) {
        this.edges = edges;
        int n = edges.length;
        visited = new boolean[n];
        for(int i = 0;i < n;i++) {
            if(!visited[i]) traverse(i, new HashMap<Integer, Integer>(), 0);
        }
        return answer;
    }
    private void traverse(int root, HashMap<Integer, Integer> track, int step) {
        if(track.containsKey(root)) {
            answer = Math.max(step - track.get(root), answer);
            return;
        }
        if(visited[root]) return;
        visited[root] = true;
        track.put(root, step);
        if(edges[root] != -1) traverse(edges[root], track, step + 1);
    }
}
