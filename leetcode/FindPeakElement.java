public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int s = 0, n = nums.length-1;//不然size2就不对了
        if(n == 1){
            return 0;
        }
        int mid = (s + n) / 2;
        while(s < n){
            if(nums[mid] < nums[mid+1]){
                s = mid + 1;//这种情况mid一定不是peak
                mid = (s + n) / 2;
            }else if(nums[mid] > nums[mid + 1]){
                n = mid;
                mid = (s + n) / 2;
            }
        }
        return mid;
    }
}
