public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int output[] = {-1, -1};
        if(nums.length == 0){
            return output;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while(left < right){
            mid = (left + right) / 2;
            if(target >  nums[mid]){
                left = mid + 1;
            }else if(target < nums[mid]){
                right = mid - 1;
            }else{
                right = mid;
            }
        }
        if(nums[left] != target){
            return output;
        }
        output[0] = left;
        //this part cause O(N) worst case, we can surely substitute with another binary search.
        while(left < nums.length && nums[left] == target){
            left++;
        }
        output[1] = left - 1;
        return output;
    }
}
