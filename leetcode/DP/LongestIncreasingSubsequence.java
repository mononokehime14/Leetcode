import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        /*
         * DP Bottom up, 注意最大值不是累加的，而是留在mem中的
         * 一开始想到的表达式: DP[i] = max(DP[j]+1 if nums[j] < nums[i], j = i-1 ... 0)
         * 这个表达式只能囊括最后一个数的最大sequence, 最大值可能出现在前面, 所以正向loop每一个
         * 都做如此表达式
         */
        // int nlength = nums.length;
        // if(nlength == 1) return 1;
        // int[] mem = new int[nlength];
        // mem[0] = 1;
        // mem[1] = nums[1] > nums[0] ? 2 : 1;
        // for(int i = 2;i < nlength;i++){
        //     mem[i] = 1;
        //     for(int j = 0;j < i;j++){
        //         if(nums[j] < nums[i]){
        //             mem[i] = Math.max(mem[i], mem[j] + 1);
        //         }
        //     }
        // }
        // return Arrays.stream(mem).max().getAsInt();

        /* Patience Sorting在这个问题上将世界无敌
         * 从左至右选, 比牌堆top小的才能加入成为新top, 比现有牌堆top大开新的牌堆, first fit
         * 牌堆的数量就是LIS, 因为起码第一堆的某一张加上后面牌堆的第一张就是一个LIS, 第一堆如果只有一张
         * 那这样它将是最小值, 那么连在一起仍然成立, 如果是最大值那么LIS必然也只有1
         * 仅仅如此是N**2, 无法做到nlogn, 注意牌堆top是有序的, 可以使用南北朝算法搜索
         */
        int[] top = new int[nums.length];
        top[0] = nums[0];
        int number_piles = 1;
        for(int i = 1;i < nums.length;i++){
            int left = 0;
            int right = number_piles;
            while(left < right){
                int mid = left + (right - left) / 2;
                if(top[mid] >= nums[i]){
                    right = mid;
                }else if(top[mid] < nums[i]){
                    left = mid + 1;
                }
            }
            if(left == number_piles || top[right] < nums[i]){
                top[number_piles] = nums[i];
                number_piles++;
            }else{
                top[right] = nums[i];
            }
        }
        return number_piles;
    }
}
