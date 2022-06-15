import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Subsets {
    List<List<Integer>> mem;
    public List<List<Integer>> subsets(int[] nums) {
        mem = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        Arrays.sort(nums);
        mem.add(new ArrayList<>());
        int n = nums.length;
        generate(candidate, nums, 0, n);
        return mem;
    }
    private void generate(List<Integer> candidate, int[] nums, int position, int n){
        if(position >= n){
            return; 
        }
        for(int i = position;i < n;i++){
            candidate.add(nums[i]);
            generate(candidate, nums, i + 1, n);
            mem.add(new ArrayList<>(candidate));
            candidate.remove(candidate.size() - 1);
        }
    }
}
