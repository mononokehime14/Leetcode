package array;

public class SmallestRangeII {
    /* 910
     * 古怪 我还是没有完全理解 sort之后 nums[i] + k vs nums[i+1] - k是比nums[i] - k vs nums[i+1] + k更好的
     * !!!
     */
    Arrays.sort(nums);
    int n = nums.length, answer = nums[n-1] - nums[0];
    for(int i = 0;i < n - 1;i++) {
        int low = Math.min(nums[0] + k, nums[i+1] - k);
        int high = Math.max(nums[n-1] - k, nums[i] + k);
        answer = Math.min(answer, high - low);
    }
    return answer;
}
