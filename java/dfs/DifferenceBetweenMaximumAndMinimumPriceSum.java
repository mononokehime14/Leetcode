package dfs;

public class DifferenceBetweenMaximumAndMinimumPriceSum {
    /* 2538
     * 周赛题 这道题首先一个很好发现的点是minimum就是node price本身 我们需要找到的每个node为起点到其他点的最大path
     * 很显然 dfs一遍 每个node dfs这是n平方 我的考虑是参考二叉树的diameter那道题 在dfs一个点的同时 动态更新一个runtime max
     * 可能横跨当前点 也可能不 我现在还是觉得这个算法可能是对的 但是实现起来比较麻烦 要同时记录path的起点 不是很好更新
     * 
     * 答案是沿用每个node dfs的思路 用类似备忘录的方法 记录每个点经过相邻点的最大的path 其中最大的自然就是这个点到任意一点的最大path
     * 这个基础的dfs很好理解 注意parent的使用 避免了走回头路 用visited也可以 其实空间使用是一样的 但是在树(edge n - 1)中 只要避免走回parent就可以了
     * 我们把邻接表扩充一下 除了记录邻居 还记录经过邻居的最大path 这样当我们对每个node进行dfs的时候 就可以重复利用前面算过的了
     * 其实这个算法在全连接图中 哦等下 这是树 不可能是全连接 所以这个算法正是O(N) 双100%
     */
    private List<long[]>[] tree;
    public long maxOutput(int n, int[][] edges, int[] price) {
        tree = buildTree(edges, n);
        long answer = 0L;
        for(int i = 0;i < n;i++) {
            answer = Math.max(traverse(i, price, -1) - price[i], answer);
        }
        return answer;
    }
    private long traverse(int root, int[] price, int parent) {
        long max = 0L;
        for(long[] next : tree[root]) {
            if(next[0] == parent) continue;
            if(next[1] == 0) { // 没算过
                long nextMax = traverse((int)next[0], price, root);
                next[1] = nextMax;
            }
            max = Math.max(max, next[1]);
        }
        return max + price[root];
    }
    // private List<Integer>[] tree;
    // private long[] runtimeMax;
    // public long maxOutput(int n, int[][] edges, int[] price) {
    //     tree = buildTree(edges, n);
    //     runtimeMax = new long[]{0, 0};
    //     boolean[] visited = new boolean[n];
    //     long[] first = traverse(0, price, visited);
    //     return Math.max(runtimeMax[0] - runtimeMax[1], first[0] - Math.min(first[1], price[0]));
    // }
    // private long[] traverse(int root, int[] price, boolean[] visited) {
    //     visited[root] = true;
    //     int currentCost = price[root];
    //     long[] largest = new long[]{currentCost, currentCost};
    //     long[] secondLargest = new long[]{currentCost, currentCost};
    //     for(int next : tree[root]) {
    //         if(visited[next]) continue;
    //         long[] nextPrice = traverse(root, price, visited); 
    //         if(nextPrice[0] - nextPrice[1] > largest[1] - largest[0]) {
    //             largest[0] = nextPrice[0];
    //             largest[1] = nextPrice[1];
    //         }else if(nextPrice[0] - nextPrice[1] > secondLargest[0] - secondLargest[1]) {
    //             secondLargest[0] = nextPrice[0];
    //             secondLargest[1] = nextPrice[1];
    //         }
    //     }
    //     largest[0] += price[root];
    //     if(secondLargest[0] + largest[0] - Math.min(secondLargest[1], largest[1]) > runtimeMax[0] - runtimeMax[1]) {
    //         runtimeMax[0] = secondLargest[0] + largest[0];
    //         runtimeMax[1] = Math.min(secondLargest[1], largest[1]);
    //     }
    //     return largest;
    // }
    private List<long[]>[] buildTree(int[][] edges, int n) {
        List<long[]>[] tree = new ArrayList[n];
        for(int i = 0;i < n;i++) tree[i] = new ArrayList<>();
        for(int[] e : edges) {
            tree[e[0]].add(new long[]{e[1], 0L});
            tree[e[1]].add(new long[]{e[0], 0L});
        }
        return tree;
    }
}
