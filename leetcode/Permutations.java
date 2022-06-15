import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
public class Permutations {
    List<List<Integer>> mem;
    public List<List<Integer>> permute(int[] nums) {
        mem = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        int n = nums.length;
        int visited[] = new int[n];
        for(int i = 0;i < n;i++){
            visited[i] = 0;
        }
        generate(candidate, nums, visited, n);
        return mem;
    }
    private void generate(List<Integer> candidate, int[] nums, int[] visited, int n){
        if(candidate.size() == n){
            mem.add(new ArrayList<>(candidate));
            return; 
        }
        for(int i = 0;i < n;i++){
            if(visited[i] == 1) continue;
            visited[i] = 1;
            candidate.add(nums[i]);
            generate(candidate, nums, visited, n);
            candidate.remove(candidate.size() - 1);
            visited[i] = 0;
        }
    }
}
