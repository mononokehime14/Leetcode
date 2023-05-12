package dfs;

public class MostProfitablePathInATree {
    /* 2467
     * 难道说 我是天才？这道题题干巨吓人 复杂的不知道怎么下口 经过本皇细致的思考 我发现B的路线是肯定只有一条
     * 因为edge的数量是n-1 也就是说这是树 是不可能有第二条路到达0的 那么B的路径就确定了 B的路径一旦确定 我们在dfsA的时候
     * 就能够知道B在当前的node上 是第几个step 这样我们就知道门是不是开过 reward是不是拿走 
     * 那么 这道题就好做了 a在dfs所有的点 到leaf是比较income就可以了
     */
    private List<Integer>[] graph;
    private List<Integer> bTrack;
    private boolean bEnd = false;
    private int answer = Integer.MIN_VALUE;
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        int[][] newAmount = new int[n][2];
        for(int i = 0;i < n;i++) {
            newAmount[i][0] = amount[i];
            newAmount[i][1] = -1;   
        }
        graph = buildGraph(edges, n); 
        bTrack = new LinkedList<>(); 
        traverseB(new LinkedList<Integer>(), -1, bob);
        for(int i = 0;i < bTrack.size();i++) {
            newAmount[bTrack.get(i)][1] = i;
        }
        traverseA(newAmount, -1, 0, 0, 0);
        return answer;
    }
    private void traverseB(LinkedList<Integer> track, int parent, int current) {
        if(bEnd) return;
        track.addLast(current);
        if(current == 0) {
            bTrack = new ArrayList<>(track);
            bEnd = true;
            return;
        }
        for(int next : graph[current]) {
            if(next == parent) continue;
            traverseB(track, current, next);
        }
        track.removeLast();
    }
    private void traverseA(int[][] newAmount, int parent, int current, int income, int step) {
        if(newAmount[current][1] != -1) {
            if(newAmount[current][1] == step) { // a and b reach here at the same time
                income += newAmount[current][0] / 2;
            }else if(newAmount[current][1] > step) { // b will reach here later, a will take the cost/reward
                income += newAmount[current][0];
            }
            // b reached here before, a gets nothing
        }else{ // b will not come here
            income += newAmount[current][0];
        }
        if(graph[current].size() == 1 && current != 0) { //leaf
            answer = Math.max(answer, income);
            return;
        }
        for(int next : graph[current]) {
            if(next == parent) continue;
            traverseA(newAmount, current, next, income, step + 1);
        }
    }
    private List<Integer>[] buildGraph(int[][] edges, int n) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0;i < n;i++) graph[i] = new ArrayList<>();
        for(int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        return graph;
    }
}
