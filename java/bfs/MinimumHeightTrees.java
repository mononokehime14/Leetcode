package bfs;

public class MinimumHeightTrees {
    /*
     * 310
     * 这道题的思路其实非常非常难想 最后我感觉是类似反向BFS
     * 我们从最外面一层开始遍历 每次寻找leaves 这样往上一层一层推 就能找到minimum height的trees
     * 这里的停止条件非常有讲究 我用了自己的理解 我认为停止条件是经历所有的edge 或者剩下一个
     * 经历所有则最终那个node就是root 剩下一个是左右两边不平衡的情况 有两种可能的root 左边重还是右边重height一样
     * 这时候剩下的两个root必然是相连的
     * 换言之 其实这道题目的答案其实最多只有两个root
     * 反向BFS的思路太巧妙了
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Collections.singletonList(0);
        Queue<Integer> leaves = new LinkedList<Integer>();
        // build graph
        List<Set<Integer>> al = new ArrayList<>();
        for(int i = 0;i < n;i++) al.add(new HashSet<>());
        for(int[] i: edges) {
            al.get(i[0]).add(i[1]);
            al.get(i[1]).add(i[0]);
        }
        // initialize outmost leaves
        for(int i = 0;i < n;i++) {
            if(al.get(i).size() == 1) leaves.add(i);
        }
        // BFS
        int edgeC = n -1;
        while(edgeC > 1) {
            int size = leaves.size();
            edgeC -= size;
            for(int i = 0;i < size;i++) {
                int current = leaves.poll();
                int parent = al.get(current).iterator().next(); // first item in set
                if(al.get(parent).size() == 2) leaves.add(parent);
                al.get(parent).remove(current);
            }
        }
        return (List) leaves;
    }
}
