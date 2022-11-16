package backtrackdfs;

import java.util.*;

public class CombinationSum2 {
    /* 思路首先从combination开始, 注意这个和dp(求出是否可能sum成target)问题的区别
     * 选择就是loop candidates看当前的数字要不要加入
     * 结束回溯的条件是我们加到了target, 或者我们无法在选择的时候加入任何数字
     * 注意不能重复, 所以要使用index避免再加入前面的数字, 这里发现不用再用visited的了
     * 因为是一个意思, index就已经避免了加入前面的数字了
     * 这是不够的, 因为candidates里的数字有可能重复, 所以要用和subsets2一样的方法, sort加去除相邻重复
     */
    List<List<Integer>> output;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        output = new ArrayList<>();
        Arrays.sort(candidates); // sort
        backtrack(new ArrayList<Integer>(), candidates, target, 0, 0);
        return output;
    }
    private void backtrack(List<Integer> track, int[] candidates, int target, int sum, int start){
        if(sum == target){
            output.add(new ArrayList<Integer>(track));
            return;
        }
        for(int i = start;i < candidates.length;i++) {
            if(candidates[i] + sum > target) continue;
            if(i > start && candidates[i] == candidates[i-1]) continue; // skip same number
            sum += candidates[i];
            track.add(candidates[i]);
            backtrack(track, candidates, target, sum, i+1);
            track.remove(track.size() - 1);
            sum -= candidates[i];
        }
    }
}
