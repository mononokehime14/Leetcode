package sorting;

public class SortAnArray {
    /*
     * 由于想要最小的memory quick sort是合适的 当然从Big O的角度来说 Merge Sort和Quick Sort同样都是O(N)
     * 但是平均情况下quick sort是logN的空间复杂度 由于递归
     * 但是第18个test case怎样都过不了 所以看了别的答案的模版 感觉这个置换可能要简单一些 可以考虑就这样用这个模版
     * 用一个for循环类似快慢指针的方法 将小于的放到第二个开始 这样自然pivot就可以更前半部分的最后一个换回来
     * 这个模版的swap次数可能要比原先的多 但是比较好debug是真的
     * 
     * Merge Sort的算法模版也附录在下
     */
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length < 2) return nums;
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
    private void quickSort(int[] nums, int lo, int hi){
        if (lo >= hi) return;
        int q = lo + (int)(Math.random() * (hi - lo + 1));
        swap(nums, lo, q);
        int index = lo + 1;
        for (int i = lo + 1; i <= hi; i++){
            if (nums[i] < nums[lo]){
                swap(nums, i, index++);
            }
        }
        swap(nums, lo, --index);
        quickSort(nums, lo, index - 1);
        quickSort(nums, index + 1, hi);
    }
    // private void quickSort(int[] nums, int left, int right) {
    //     if(left >= right) return;
    //     int randomIndex = left + (int)Math.random()*(right - left + 1); // max and min are inclusive, so range is max - min + 1
    //     swap(nums, randomIndex, right); // swap pivot with the rightmost
    //     int l = left, r = right-1;
    //     while(true) {
    //         while(l < right && nums[l] < nums[right]){
    //             l++;
    //         }
    //         while(r >= left && nums[r] >= nums[right]){
    //             r--;
    //         }
    //         if(l < r) {
    //             swap(nums, l, r);
    //         }else{
    //             break;
    //         }
    //     }
    //     swap(nums, l, right);
    //     quickSort(nums, left, l-1);
    //     quickSort(nums, l+1, right);
    // } 
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /*
     * Merge Sort
     */
    // 用于辅助合并有序数组
    private static int[] temp;

    public static void sort(int[] nums) {
        // 先给辅助数组开辟内存空间
        temp = new int[nums.length];
        // 排序整个数组（原地修改）
        sort(nums, 0, nums.length - 1);
    }

    // 定义：将子数组 nums[lo..hi] 进行排序
    private static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            // 单个元素不用排序
            return;
        }
        // 这样写是为了防止溢出，效果等同于 (hi + lo) / 2
        int mid = lo + (hi - lo) / 2;
        // 先对左半部分数组 nums[lo..mid] 排序
        sort(nums, lo, mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        sort(nums, mid + 1, hi);
        // 将两部分有序数组合并成一个有序数组
        merge(nums, lo, mid, hi);
    }

    // 将 nums[lo..mid] 和 nums[mid+1..hi] 这两个有序数组合并成一个有序数组
    private static void merge(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入 nums
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
