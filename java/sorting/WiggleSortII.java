package sorting;

public class WiggleSortII {
    /*
     * debug不出来 放在那里先过了 其实本来的方法也没有太理解 只能理解想法 不能理解具体的算法
     * 这道题如果不考虑O(N)的时间复杂度和O(1)的空间复杂度的话其实挺简单的 就是找出中位数 然后把中位数下面的数字放在偶数index上
     * 上面的放在奇数位置上 找出中位数的时间复杂度可以降到O(N)如果用quick select 但是这样数组就不是排好序的了 如果不用额外的空间成本怎么操作呢
     * 好像算法的思路是大的排在前面 小的排在后面 然后前面的大的去奇数位置 后面小的去偶数位置 这就是通过 (1 + 2 * index) % (n | 1)的方法
     * 比如6个数字 0 -> 1, 1 -> 3, 2 -> 5, 3 -> 0, 4 -> 2, 5 -> 4, 所以我的大概想法是先排成大的前面小的后面 然后再执行这个对应swap
     * 答案的方法是可以直接一次迭代就完成的 不甚理解 而且我quick select那一步就有问题 有时候对有时候不对 完全debug不出来为什么 我觉得是不是需要重新深度研究一下快排？
     */
    public void wiggleSort(int[] nums) {
        // int n = nums.length;
        // int mid = findKthLargest(nums, (n-1)/2);
        // int left = 0, right = n - 1, i=0;
        // while(i <= right) {
        //     if (nums[newIndex(i,n)] > mid) {
        //         swap(nums, newIndex(left++,n), newIndex(i++,n));
        //     }
        //     else if (nums[newIndex(i,n)] < mid) {
        //         swap(nums, newIndex(right--,n), newIndex(i,n));
        //     }
        //     else {
        //         i++;
        //     }
        // }
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        int mid = (n - 1) / 2;
        // System.out.println(mid + " " + copy[mid]);
        int right = n - 1;
        for(int i = 0;i < n;i++) {
            if(i % 2 == 0) {
                nums[i] = copy[mid];
                mid--;
            }else{
                nums[i] = copy[right];
                right--;
            }
        }
    }
    
    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
    
    private int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        if(nums.length == 1) {
            return nums[0];
        }
        return quickselect(nums, 0, n-1, k);
    }
    
    private int quickselect(int[] nums, int left, int right, int k) {
        int randomIndex = (int) (left + (Math.random()*(right - left)));
        swap(nums, randomIndex, right);
        int l = left, r = right-1;
        while(true) {
            while(l < r && nums[l] <= nums[right]) {
                l++;
            }
            while(l < r && nums[r] > nums[right]) {
                r--;
            }
            if(l < r){
                swap(nums, l, r);
            }else{
                break;
            }
        }
        swap(nums, l, right);
        if(k == l){
            return nums[k];
        }else if(k > l) {
            return quickselect(nums, l+1, right, k);
        }else{
            return quickselect(nums, left, l-1, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
