package sorting;

public class ReversePairs {
    /* 493
     * 这道题同样是利用merge sort的merge phase 首先是意识到 暴力解法是n平方 而条件限制是10的五次方 故此要用nlogn的方法
     * 而思考如何利用merge phase的关键 在于思考如何在两个有序的子数组中 寻求我们的答案 并在双指针n的时间内完成
     * 在这道题中 只要左边的数字大于2 * 右边的一个数字 一个reverse pair就出现了 这样我们可以左指针从最左边开始 右指针从中间开始
     * 右指针能够满足条件则扩展 而左边的数字是要全部试一遍的 放心 一次遍历就可以了 因为左边的数字只会越来越大 右边的指针能够满足当前的左边数字 就能满足接下来的左边数字
     */
    private int[] temp;
    private int count = 0;
    public int reversePairs(int[] nums) {
        int n = nums.length;
        this.temp = new int[n];
        mergeSort(nums, 0, n - 1);
        return this.count;
    }
    private void mergeSort(int[] nums, int left, int right) {
        if(left == right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, right, mid);
    }
    private void merge(int[] nums, int left, int right, int mid) {
        for(int i = left;i <= right;i++){
            temp[i] = nums[i];
        }
        int end = mid + 1;
        for(int i = left;i <= mid;i++) {
            while(end <= right && (long)nums[i] > (long)2 * nums[end]) { // prevent integer overflow
                end++;
            }
            this.count += end - mid - 1;
            // System.out.println(left + " " + right + " " + count);
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
