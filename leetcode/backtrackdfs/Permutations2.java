package backtrackdfs;

import java.util.*;
public class Permutations2 {
    /* 基本思路应该沿用permutation, 注意这题的区别在于数组中有duplicates
     * 根据subsets2, 我们可以类似的适用sort加相邻去除重复的方法
     * 但是这样会有一个潜在的问题, 那就是如果112, 2用了, 用了一个1, 第三个1
     * 我就用不出去了. 所以如果相邻重复前一个是used, 这说明是我自己用的, 我用第二个也
     * 不会产生重复问题;反之如果前一个unused, 则说明前面的tree用过了, 我如果还用第二个就会造成树重复了
     */
    List<List<Integer>> output;
    public List<List<Integer>> permuteUnique(int[] nums) {
        output = new ArrayList<>();
        int visited = 0;
        Arrays.sort(nums);
        backtrack(new ArrayList<Integer>(), nums, visited);
        return output;
    }
    private void backtrack(List<Integer> candidate, int[] nums, int visited) {
        if(candidate.size() == nums.length) {
            output.add(new ArrayList<Integer>(candidate));
            return;
        }
        for(int i = 0;i < nums.length;i++) {
            if(((visited >> i) & 1) == 1) continue;
            if(i > 0 && nums[i] == nums[i-1] && (((visited >> (i-1)) & 1) == 0)) continue;
            visited = visited | (1 << i);
            candidate.add(nums[i]);
            backtrack(candidate, nums, visited);
            candidate.remove(candidate.size() - 1);
            visited = visited ^ (1 << i);
        }
    }
}
