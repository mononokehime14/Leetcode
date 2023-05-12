package array;

public class 4Sum2 {
    /** 454
     * 关键在于 用另一种2sum的方法 然后嵌套 这样O(N ** 2)
     * 如果用双指针的2sum方法嵌套 将是一个O(N**4) 而且我他妈的写的有bug 没办法解决最后两个array 两边loop就重复count 一遍就漏正确解的问题
     * 
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: nums3) {
            for(int j: nums4) {
                map.put(i + j, map.getOrDefault(i+j, 0) + 1);
            }
        }
        int count = 0;
        for(int i: nums1) {
            for(int j: nums2) {
                count += map.getOrDefault(0-i-j, 0);
            }
        }
        return count;
        // Arrays.sort(nums1);
        // Arrays.sort(nums2);
        // Arrays.sort(nums3);
        // Arrays.sort(nums4);
        // int count = 0;
        // int n = nums1.length;
        // for(int i = 0;i < n;i++) {
        //     // if(i > 0 && nums1[i] == nums1[i-1]) continue;
        //     for(int j = 0;j < n;j++) {
        //         // if(j > 0 && nums2[j] == nums2[j-1]) continue;
        //         int left = 0, right = n - 1;
        //         while(left <= right) {
        //             long temp = nums3[left] + nums4[right];
        //             temp += nums2[j];
        //             temp += nums1[i];
        //             if(temp > 0) {
        //                 right--;
        //                 // while(left < right && nums4[right] == nums4[right+1]) right--;
        //             }else if(temp < 0) {
        //                 left++;
        //                 // while(left < right && nums3[left] == nums3[left-1]) left++;
        //             }else{
        //                 count++;
        //                 left++;
        //                 right--;
        //                 // while(left < right && nums3[left] == nums3[left-1]) left++;
        //                 // while(left < right && nums4[right] == nums4[right+1]) right--;
        //             }
        //         }
        //         left = 0;
        //         right = n - 1;
        //         while(left <= right) {
        //             long temp = nums4[left] + nums3[right];
        //             temp += nums2[j];
        //             temp += nums1[i];
        //             if(temp > 0) {
        //                 right--;
        //                 // while(left < right && nums4[right] == nums4[right+1]) right--;
        //             }else if(temp < 0) {
        //                 left++;
        //                 while(left < right && nums3[left] == nums3[left-1]) left++;
        //             }else{
        //                 count++;
        //                 left++;
        //                 right--;
        //                 // while(left < right && nums3[left] == nums3[left-1]) left++;
        //                 // while(left < right && nums4[right] == nums4[right+1]) right--;
        //             }
        //         }
        //     }
        // }
        // return count;
    }
}
