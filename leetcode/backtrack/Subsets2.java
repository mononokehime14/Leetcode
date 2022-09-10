package backtrack;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Subsets2 {
    /*
     * 思路先是和subset一致, 有一处不同就是数字有重复的情况, 这样我们就发现比如candidate里有4剩下414画树
     * [4]
     * [44], [41], [44]
     * [441] [444] [414]  这里441和414就重复了
     * 能不能用hashmap解决呢, 很可惜hashmap不是万能的,这种情况转成string也测不出来
     * 能不能在选择的时候保持一个hashmap去掉重复的数字呢, 不能解决问题因为上述反例就是加入4和1之后后面出事情的
     * 方法是sort nums, 然后如果做选择的时候发现相邻的数字相同就跳过
     */
    List<List<Integer>> output;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        output = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), nums, 0);
        return output;
    }
    private void backtrack(List<Integer> candidate, int[] nums, int start) {
        output.add(new ArrayList<>(candidate));
        for(int i = start;i < nums.length;i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            candidate.add(nums[i]);
            backtrack(candidate, nums, i+1);
            candidate.remove(candidate.size() - 1);
        }
    }
}
