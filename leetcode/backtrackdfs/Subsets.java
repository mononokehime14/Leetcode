package backtrackdfs;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Subsets {
    /*
     * subset和permutation的区别就在于不能有重复, 根据手推的时候的方法, 应该考虑先加一个数, 然后
     * 递归传递, 这时候防止重复要用到visited hashmap剪吗?其实不用, 注意它的问题其实和K partition是很不同的
     * 我们只要像K partition那样用start记录我们在nums中loop的位置, 我们就保证了2开头的subset不会再加入1,
     * 这样我们的去重复已经完成了, 而不像k partitions还要考虑不同的groups位置同样的combination这种
     */
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
