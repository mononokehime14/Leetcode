package oa;
public class minyiLinkedin2022OA {
    // public static void main(String[] args) {
    //     int[] nums1 = new int[]{4,2,10,5,9};
    //     int[] nums2 = new int[]{2,3,2,9,7,6,2};
    //     int[] nums3 = new int[]{1,2,3,4,5,6};
    //     int[] nums4 = new int[]{2,3,2,9,7,6,2,1};
    //     int[] nums5 = new int[]{2,3,2,9,7,6,2,1,6};
    //     int[] nums6 = new int[]{2,3,2,9,7,6,1,6};
    //     System.out.println(subsegmentSort(nums1)); // 2
    //     System.out.println(subsegmentSort(nums2)); // 2
    //     System.out.println(subsegmentSort(nums3)); // 6
    //     System.out.println(subsegmentSort(nums4)); // 1
    //     System.out.println(subsegmentSort(nums5)); // 1
    //     System.out.println(subsegmentSort(nums6)); // 1
    // }
    // public String breakAPalindrome(String a) {

    // }
    public static int subsegmentSort(int[] arr) {
        /**
         * 思路比较类似patience sorting
         */
        int pointer = 0;
        int n = arr.length;
        int[] max = new int[n];
        max[0] = arr[0];
        for(int i = 0;i < n;i++) {
            int j = binarySearch(max, arr[j], right);
            if(j != 1) {
                
            }
        }
        // int n = arr.length;
        // int[] preMax = new int[n];
        // int[] postMin = new int[n];
        // preMax[0] = Integer.MIN_VALUE;
        // for(int i = 0;i < n - 1;i++) {
        //     preMax[i + 1] = arr[i] > preMax[i] ? arr[i] : preMax[i];
        // }
        // postMin[n-1] = Integer.MAX_VALUE;
        // for(int i = n-1;i > 0;i--) {
        //     postMin[i - 1] = arr[i] < postMin[i] ? arr[i] : postMin[i]; 
        // }
        // int count = 1;
        // for(int i = 0;i < n;i++) {
        //     if(arr[i] > preMax[i] && arr[i] < postMin[i]) count++;
        // }
        // return count;
        // return traverse(arr, 0, arr.length - 1);
    }
    private static int binarySearch(int[] nums, int targetm int right) {
        int left = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        if(left == nums.length) return -1;
        return right;
    }
    // private static int traverse(int[] arr, int left, int right) {
    //     if(left > right) return 0;
    //     int cur = arr[left];
    //     int i;
    //     for(i = right;i > left;i--) {
    //         if(arr[i] < cur) {
    //             break;
    //         }
    //     }
    //     if(right == i) return 1; // no more segment
    //     System.out.println(left + " " + right + " " + i);
    //     return traverse(arr, left, i) + traverse(arr, i+1, right);
    // }
}
