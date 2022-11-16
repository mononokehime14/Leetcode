package backtrackdfs;
import java.util.Arrays;
import java.util.HashMap;
public class PartitionKEqualSumSubsets {
    /*
    * 第一步的思考过程是为什么不能用DP?我们在分成两部分的时候转化成了01背包问题解出来了, 但是分成k份咋搞呢
    * 先dp出一种组合, 然后在剩下的?这样前面的选择实际上会影响后面的分组的正确性, 只能全部可能的组合+后续的DP都试一下
    * 这样的话, 选择就是选择不同的能产生group sum的group, 然后pass剩下的给递归. 这样做只能说太慢了.
    * 如果我们想到, 每个数字都要去一个组, 我们linear loop, 每次选择当前数字进入不同的组然后交给递归, nums结束回溯
    * 结束, 或者当前数字加不进任何一个组(group sum都超了)结束回溯. 这是一个好思路, 但是惨遭time limit exceed
    * 
    * oops, 实际上最开始的思路可能是对的, 每一个group先对nums进行选择加入或者不加入, loop nums, 无法再加入并且
    * sum没有到要求(结束回溯), 不然就在其选择的条件下继续递归, group sum加到要求了就下一个group, 直到group全部结束(结束回溯)
    * 这个方法为什么会快呢? 前一个方法无疑是K^N, 
    * 这里注意我们可以用hashmap记录一个group装满后的组合情况, 防止重复.
    * 同时注意我们用bit operation代替visited, 这对k n小于32的情况非常适用, 我猜测是靠这个过的time limit
    */
    // int[] groups;
    // public boolean canPartitionKSubsets(int[] nums, int k) {
    //     int sum = Arrays.(nums).sum();
    //     if(sum % k != 0) return false;
    //     sum /= k;
    //     groups = new int[k];
    //     groups[0] = nums[0];
    //     return backtrack(groups, 1, nums, k, sum);
    // }
    // private boolean backtrack(int[] groups, int index, int[] nums, int k, int sum){
    //     if(index == nums.length) {
    //         boolean flag = true;
    //         for(int i = 0;i < k;i++){
    //             if(groups[i] != sum){
    //                 flag = false;
    //                 break;
    //             }
    //         }
    //         return flag;
    //     }
        
    //     for(int i = 0;i < k;i++) {
    //         if(groups[i] + nums[index] > sum) continue;
    //         groups[i] += nums[index];
    //         boolean result = backtrack(groups, index + 1, nums, k, sum);
    //         groups[i] -= nums[index];
    //         if(result) return result;
    //     }
    //     return false;
    // }
    HashMap<Integer, Boolean> mem;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) return false;
        sum /= k;
        // Java boolean array initialize value: false
        int visited = 0;
        mem = new HashMap<>();
        return backtrack(0, nums, visited, 0, k, sum, 0);
    }
    private boolean backtrack(int index, int[] nums, int visited, int group_sum, int k, int sum, int start){
        if(index == k) return true;
        if(group_sum == sum){ // 一个满足条件的partition
            boolean result = backtrack(index + 1, nums, visited, 0, k, sum, 0);
            mem.put(visited, result);
        }
        if(mem.containsKey(visited)) return mem.get(visited);
        for(int i = start;i < nums.length;i++){
            if(((visited >> i) & 1) == 1 || nums[i] + group_sum > sum) continue;
            visited = visited | (0x1 << i);
            group_sum += nums[i];
            boolean result = backtrack(index, nums, visited, group_sum, k, sum, i+1);
            group_sum -= nums[i];
            visited = visited ^ (0x1 << i);
            if(result) return true;
        }
        return false;
    }
}
