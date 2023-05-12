package binarysearch;
public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        /*
         * 这道题目的细节非常非常复杂 我认为可能是BS中最难的一类了
         * 首先思路是找到pivot 分组 然后在相应的组里再次BS
         * 寻找pivot的第一个关键是和最后一个数字相比 第二个关键是我想要找什么 我想要找第二组的开头 那么 <=的时候
         * 就有可能mid是正确答案 所以right = mid 一开始左闭右闭 按理来说应该 left <= right 不过这道题目了好像不是要找固定的数字
         * left = right的时候就可以推断拿到正确的pivot了 因为left = right所以随便哪一个是pivot都可以
         * 第二部分只是左闭右闭寻找数字 只需要注意题目问的不是sort之后的数组里的index 所以不用offset也可以
         */
        int n = nums.length, left = 0, right = n - 1;
        if(target == nums[0]){
            return 0;
        }else if(n == 1){
            return -1;
        }
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[n - 1]) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        //System.out.println(left + " " + right);
        int pivot = right;
        if(target > nums[n - 1]) {
            left = 0;
            right = pivot - 1;
        }else{
            left = pivot;
            right = n - 1;
        }
        //System.out.println(left + " " + right);
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
        // int n = nums.length;
        // if(target == nums[0]){
        //     return 0;
        // }else if(n == 1){
        //     return -1;
        // }
        // int left = 0;
        // int right = n - 1;
        // while(left <  right){
        //     int mid = (left + right) / 2;
        //     if(nums[mid] > nums[right]){
        //         left = mid + 1;
        //     }else{
        //         right = mid;
        //     }
        // }
        // int offset = left;
        // left = 0;
        // right = n - 1;
        // while(left <= right){
        //     int mid = (left + right) / 2;
        //     int _mid = (mid + offset) % n;
        //     if(nums[_mid] == target){
        //         return _mid;
        //     }else if(nums[_mid] > target){
        //         right = mid - 1;
        //     }else{
        //         left = mid + 1;
        //     }
        // }
        // return -1;
    }
}
