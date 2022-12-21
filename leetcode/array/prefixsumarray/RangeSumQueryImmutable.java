package array.prefixsumarray;

public class RangeSumQueryImmutable {
    class NumArray {
        /* 303
         * Easy, just preSum
         */
        private int[] preSum;
        public NumArray(int[] nums) {
            int n = nums.length;
            preSum = new int[n];
            preSum[0] = nums[0];
            for(int i = 1;i < n;i++) {
                preSum[i] = preSum[i-1] + nums[i];
            }
        }
        
        public int sumRange(int left, int right) {
            return left - 1 >= 0 ? preSum[right] - preSum[left - 1] : preSum[right];
        }
    }
    
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
}
