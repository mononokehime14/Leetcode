package backtrack;
import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /* 注意重复问题的检查不是用start index因为题目要求回去找, 但是又不能重复找已经加入的数字
     * 所以需要visited. 又注意size<=6所以可以用bit operation优化. 速度提升至77%
     */
    List<List<Integer>> mem;
    public List<List<Integer>> permute(int[] nums) {
        mem = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        int n = nums.length;
        int visited = 0;
        generate(candidate, nums, visited, n);
        return mem;
    }
    private void generate(List<Integer> candidate, int[] nums, int visited, int n){
        if(candidate.size() == n){
            mem.add(new ArrayList<>(candidate));
            return; 
        }
        for(int i = 0;i < n;i++){
            if(((visited >> i) & 1) == 1) continue;
            visited = visited | (1 << i);
            candidate.add(nums[i]);
            generate(candidate, nums, visited, n);
            candidate.remove(candidate.size() - 1);
            visited = visited ^ (1 << i);
        }
    }
}
