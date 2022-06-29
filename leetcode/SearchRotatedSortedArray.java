public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if(target == nums[0]){
            return 0;
        }else if(n == 1){
            return -1;
        }
        int left = 0;
        int right = n - 1;
        while(left <  right){
            int mid = (left + right) / 2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        int offset = left;
        left = 0;
        right = n - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int _mid = (mid + offset) % n;
            if(nums[_mid] == target){
                return _mid;
            }else if(nums[_mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
