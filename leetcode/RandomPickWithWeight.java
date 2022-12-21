public class RandomPickWithWeight {
    /* 528
     * 一开始我的想法是 weight相当于有多少区间吗 我们可以相应扩充 我的考虑是用所有weight的sum 1-sum 大区间 然后随机
     * 有几个细节没有考虑到 就是之后怎么从这个大区间中进行搜索 我的想法是linear 但是实际上 这里可以用prefix sum array + binary search
     * 维持一个累积的weight sum的prefix sum array 其必然有序 这样我们只需要搜出刚好比random摇出来的数字大的那个preSum element 拿到其index就可以了
     */
    private int[] preSum;
    private int n;
    private Random rand = new Random();
    public Solution(int[] w) {
        n = w.length;
        preSum = new int[n];
        preSum[0] = w[0];
        for(int i = 1;i < n;i++) {
            preSum[i] = preSum[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int rd = rand.nextInt(preSum[n - 1]) + 1;
        return bsFirstGreater(rd);
    }
    private int bsFirstGreater(int rd) {
        int left = 0, right = n - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(preSum[mid] < rd) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
