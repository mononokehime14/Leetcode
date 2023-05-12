package array;

public class NumberOfUnequalTripletsInArray {
    /* 2475
     * 由于要求i < j < k 所以 暴力解的
     */
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i = 0;i < n;i++) {
            for(int j = i+1;j < n;j++) {
                if(nums[j] == nums[i]) continue;
                for(int k = j+1;k < n;k++) {
                    if(nums[i] != nums[j] && nums[j] != nums[k] && nums[k] != nums[i]) count++;
                }
            }
        }
        return count;
    }
}
