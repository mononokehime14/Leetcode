package backtrackdfs;
import java.util.*;
public class CombinationSum {
    /* 注意题目条件是没有重复的元素但是可以重复使用
     * 我想的办法是while loop加在for loop里面
     * 但是实际上有更聪明的, 可以直接不加index就好了
     * 补上一个补丁, 因为只用sum == target是会造成无限循环的, 可能加过了然后一直加自己
     * 所以要加上sum > target return 结束回溯
     */
    List<List<Integer>> output;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        output = new LinkedList<>();
        backtrack(new LinkedList<Integer>(), candidates, target, 0, 0);
        return output;
    }
    private void backtrack(LinkedList<Integer> track, int[] candidates, int target, int sum, int index){
        if(sum == target){
            output.add(new LinkedList<>(track));
            return;
        }
        if(sum > target) {
            return;
        }
        for(int i = index;i < candidates.length;i++) {
            sum+=candidates[i];
            track.add(candidates[i]);
            backtrack(track, candidates, target, sum, i);
            track.removeLast();
            sum-=candidates[i];
        }
    }
}
