public class CapacityToShipPackagesWithinDays {
    /* 1011
     * 非常经典的二分查找使用案例 在snowflake的OA准备的时候就见过这种思路
     * 高层来讲 就是不是用二分查找查找一个数列什么的 而是用二分查找试答案
     * 这种思路适用于min max的情况知道 比如这里capacity必然大于等于任何单个的element 又同时不超过全部的和
     * 同时 需要能够承受nlogn的时间成本 同时 检查一个答案是否正确要在O(N)
     * 
     * 一开始我是想用random那道题的prefix sum + 二分查找 但是这种思路错了 那道题只是查找一次 如果你全部的区间都搜出来
     * 那不是还不如linear过一遍吗 最大的问题在于range是算出来的 很多情况下会错 不是一个正确的想法
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int sum = Arrays.stream(weights).sum();
        int right = sum;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(!isValid(weights, mid, days)) {
                left = mid + 1;
            }else{
                //System.out.println(mid + " can");
                right = mid - 1;
            }
        }
        return left > sum ? -1 : left;
        // int n = weights.length;
        // int[] preSum = new int[n+1];
        // for(int i = 1;i <= n;i++) {
        //     preSum[i] = preSum[i-1] + weights[i-1];
        // }
        // int range = preSum[n] / days;
        // int start = 0, end = 0;
        // int max = 0;
        // while(days > 0) {
        //     if(days == 1) {
        //         max = Math.max(preSum[n] - preSum[start], max);
        //     }else{
        //         end += range;   
        //         System.out.println("end:"+ end + ", from " + (start + 1));
        //         int newRight = bsFirstGreater(preSum, start+1, end);
        //         max = Math.max(preSum[newRight] - preSum[start], max);
        //         start = newRight;
        //     }
        //     days--;
        // }
        // return max;
    }
    private boolean isValid(int[] nums, int maxCap, int days) {
        int sum = 0, tempDays = 1;
        for(int i : nums) {
            if(sum + i <= maxCap) {
                sum += i;
            }else{
                sum = i;
                tempDays++;
            }
        }
        return tempDays <= days;
    }
    // private int bsFirstGreater(int[] nums, int start, int target) {
    //     int left = start, right = nums.length - 1;
    //     while(left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if(nums[mid] < target) {
    //             left = mid + 1;
    //         }else{
    //             right = mid - 1;
    //         }
    //     }
    //     return left;
    // }
}
