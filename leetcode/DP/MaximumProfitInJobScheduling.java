public class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        /* 对于比较复杂的DP 应该首先考虑状态怎么选择 这里按照时间来选择是不合适的 因为在一个特定的时间 该时间之前的结果还不是最优的
         * 因为有的之前开始的job还没结束但是实际上profit更高
         * 这里应该沿用背包问题的状态选择思路 我们是不是要加入当前的job
         * 那么基于这个思路 我们再思考状态转移方程 对于当前的job
         * DP[i] = Max(
         *  DP[i-1]也就是不加入,
         *  profit[i] + DP[x] x是在i开始前结束的第一个job 这里这个x一定要是最优解 不然就无法DP累加了 所以因此我们需要根据end time sort
         * )
         * 如何找到start time前的第一个job呢 我们需要binary search
         * 这里我觉得可以学习一下答案的bs方法 感觉比我之前的模版要好理解的多
         * 另外一个方法是TreeMap 比较有意思 利用了floorEntry能够找到比key小的第一个key value pair
         * 但是实际上速度变慢了 可能对于这道题的testcase TreeMap的红黑树不如直接bs
         */
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i = 0;i < n;i++)  {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        // TreeMap<Integer, Integer> dp = new TreeMap<>();
        // dp.put(0, 0);
        // for(int i = 0;i < n;i++) {
        //     int cur = jobs[i][2] + dp.floorEntry(jobs[i][0]).getValue();
        //     dp.put(jobs[i][1], Math.max(dp.lastEntry().getValue(), cur));
        // }
        // return dp.lastEntry().getValue();
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        for(int i = 1;i < n;i++) {
            int cur = jobs[i][2];
            int prev = binarySearch(jobs, i);
            if(prev != -1) {
                cur += dp[prev];
            }
            dp[i] = Math.max(cur, dp[i-1]);
        }
        return dp[n - 1];
    }
    private int binarySearch(int[][] jobs, int r) {
        int left = 0, right = r - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(jobs[mid][1] <= jobs[r][0]){
                if(jobs[mid + 1][1] <= jobs[r][0]) {
                    left = mid + 1;
                }else{
                    return mid;
                }
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }
}