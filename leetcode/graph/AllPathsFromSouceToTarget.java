package graph;

import java.util.*;

public class AllPathsFromSouceToTarget {
    /* 797
     * 这道题看起来就是DFS回溯的思路 但是到这道题开始 发现了DFS在图题目中和回溯的一些细节不同
     * 在回溯算法中 我们在递归前后做选择和撤销选择 这些都是for循环里面 我们做选择意思是在track中加入该选择
     * 这样我们就会发现 当前的选择呢？ 当前的选择在之前call的时候加好了 那root呢 root是空不重要
     * 但是在DFS图遍历中 root是重要的 所以我们对顺序做了一些调整 进入循环前加入当前的node
     * 然后进入循环选择 结束循环之后撤销当前的node 其实也就是说当前的call只管当前的node
     * 
     * 这里为什么不用visited是因为题目说明了图是无环的
     */
    List<List<Integer>> output;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        output = new LinkedList<>();
        traverse(new LinkedList<Integer>(), graph, graph.length - 1, 0);
        return output;
    }
    private void traverse(LinkedList<Integer> track, int[][] graph, int end, int current) {
        if(current == end) {
            track.add(current);
            output.add(new LinkedList<Integer>(track));
            track.removeLast();
            return;
        }
        track.add(current);
        // 如果需要visited 这里加入
        for(int i: graph[current]) {
            //选择
            traverse(track, graph, end, i);
        }
        track.removeLast();
    }
}
