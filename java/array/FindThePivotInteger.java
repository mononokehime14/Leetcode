package array;

public class FindThePivotInteger {
    /* 2485
     * Presum方法 自称三叉戟算法 没什么特殊的 easy题
     */
    public int pivotInteger(int n) {
        int[] preSum = new int[n+1];
        for(int i = 1;i <= n;i++) preSum[i] = preSum[i-1] + i;
        int[] postSum = new int[n+1];
        postSum[n] = n;
        for(int i = n - 1;i >= 1;i--) postSum[i] = postSum[i+1] + i;
        for(int i = 1;i <= n;i++) {
            if(preSum[i] == postSum[i]) return i;
        }
        return -1;
    }
}
