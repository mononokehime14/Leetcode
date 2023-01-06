package sorting;

public class CountOfRangeSum {
    /* 327
     * 这道题的暴力解法当然是n平方的 但是很明显length是10的5次方需要用至少nlogn的方法
     * 在思考利用merge sort的过程中 我始终想不通 merge sort的层高虽然是log n 但是并不能经手所有的区间
     * 正确答案是merge sort 前缀和数组！我们利用两半都已经有序的前提 对于左边的每一个element 我们在右边寻找合适的区间右边界 数出数量
     * 一个很明显的疑惑是 顺序是乱掉的 presum的顺序不再是一开始的样子 但是在这里 顺序并不重要 我们只是在寻找一个合适的左边界和右边界 只要之间的合符合我们要求就可以了
     * 左边界在右边的情况 在递归下层已经试过了 
     */
    private long[] temp;
    private int lower;
    private int upper;
    private int sum;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.sum = 0;
        this.upper = upper;
        this.lower = lower;
        int n = nums.length;
        this.temp = new long[n+1];
        long[] preSum = new long[n+1];
        /*
         * 这里preSum必须是n+1 第一个要是0 如果按照以前n的做法的话 最大的range也只能exclude第一个element 因为后面是
         * preSum[i] - preSum[j] 是如果preSum[0] = nums[0]就被exclude出去了
         */
        for(int i = 1;i <= n;i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        mergeSort(preSum, 0, n);
        return this.sum;
    }
    private void mergeSort(long[] nums, int left, int right) {
        if(left == right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid+1, right);
        merge(nums, left, right, mid);
    }
    private void merge(long[] nums, int left, int right, int mid) {
        for(int i = left;i <= right;i++) {
            temp[i] = nums[i];
        }
        int start = mid + 1, end = mid + 1;
        for(int i = left;i <= mid;i++) {
            while(start <= right && nums[start] - nums[i] < this.lower) {
                start++;
            }
            while(end <= right && nums[end] - nums[i] <= this.upper) {
                end++;
            }
            this.sum += end - start; // end is not an answer, so no need to end - start + 1
        }
        int l = left, r = mid + 1;
        for(int i = left;i <= right;i++) {
            if(l == mid + 1) {
                nums[i] = temp[r++];
            }else if(r == right + 1) {
                nums[i] = temp[l++];
            }else if(temp[l] > temp[r]) {
                nums[i] = temp[r++];
            }else{
                nums[i] = temp[l++];
            }
        }
    }
}
