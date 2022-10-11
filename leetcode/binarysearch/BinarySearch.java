package binarysearch;
public class BinarySearch {
    public int search(int[] nums, int target) {
        int mid = nums.length / 2;
        int n = nums.length;
        int s = 0;
        while(n > s){
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                n = mid;
                mid = (n + s) / 2;
            }else{
                s = mid + 1;
                mid = (n + s) / 2;
            }
        }
        return -1;
    }
}
