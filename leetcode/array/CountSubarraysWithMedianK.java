package array;

public class CountSubarraysWithMedianK {
    /* 2488
     * 我的本来想法是用merge sort 在sort的过程中我们就能发现是不是median是k 但是这个方法的问题是 merge sort会把数组分成等长的两部分
     * 这样就漏掉了其他长度的正确解 如果要每一个长度都试一下 层高就不是logN了 而是N了 这样复杂的就是logN
     * 答案是很多巧妙的技巧的合体
     * 首先第一个关键是O(N)时间复杂度快速判断median是不是k 小于k的计作-1 等于计作0 大于计作1 全部加起来1或者0 那么符合
     * 有了这个思路 下一个关键就是怎样快速检查所有的subarray 这里的一个重要观察是 median是k的数组里 必须有k 也就是说 所有的答案都是从k的index延伸出去的
     * 那么这里我们就可以用类似two sum unsorted版本的思想 index往右计算所有可能的subarray sum 接着往左算sum 每次检查map里右边有咩有和当前sum拼成0或者1的
     * 还有一个关键的assumption 如果数组里有多个k的话 这个解法就会忽略掉后面的index 但是正好题目里有这个假设 数组里的值都是distinct的
     */
    public int countSubarrays(int[] nums, int k) {
        int idx = 0, n = nums.length;
        for(int i = 0;i < n;i++) {
            if(nums[i] == k) {
                idx = i;
                break;
            }
        }
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = idx;i < n;i++) {
            sum += nums[i] > k ? 1 : nums[i] == k ? 0 : -1;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        sum = 0;
        int count = 0;
        for(int i = idx;i >= 0;i--) {
            sum += nums[i] > k ? 1 : nums[i] == k ? 0 : -1;
            count += map.getOrDefault(0 - sum, 0);
            count += map.getOrDefault(1 - sum, 0);
        }
        return count;
    }
    // private int count;
    // private int[] temp;
    // public int countSubarrays(int[] nums, int k) {
    //     int n = nums.length;
    //     temp = new int[n];
    //     mergeSort(nums, 0, n-1, k);
    //     return count;
    // }
    // private void mergeSort(int[] src, int left, int right, int k) {
    //     if(left == right) {
    //         if(src[left] == k) count++;
    //         return;
    //     }
    //     int mid = left + (right - left) / 2;
    //     mergeSort(src, left, mid, k);
    //     mergeSort(src, mid + 1, right, k);
    //     merge(src, left, right, mid, k);
    // }
    // private void merge(int[] src, int left, int right, int mid, int k) {
    //     int l = left, r = mid + 1, index = left;
    //     while(l <= mid && r <= right) {
    //         if(src[l] <= src[r]) {
    //             temp[index] = src[l];
    //             l++;
    //         }else{
    //             temp[index] = src[r];
    //             r++;
    //         }
    //         index++;
    //     }
    //     while(l <= mid) {
    //         temp[index] = src[l];
    //         l++; 
    //         index++;
    //     }
    //     while(r <= right) {
    //         temp[index] = src[r];
    //         r++; 
    //         index++;
    //     }
    //     for(int i = left;i <= right;i++) {
    //         src[i] = temp[i];
    //     }
    //     if(src[mid] == k) count++;
    // }
}
