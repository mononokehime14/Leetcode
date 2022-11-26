public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /* 4
         * 这道题一开始直接暴力解过的 就是merge 一次拿一个一直到拿到中点的 但是这样子的时间复杂度是O(Min(M, N))
         * 题目提示说O(log(M+N)) 而且给的两个数组都是排好序的 当时就想这估计是某种二分查找的使用 但是没有想到方法
         * 答案是二分查找一个数组中 属于合并后前半部分的那一部分 比如1 2 3 4, 1 2 3 二分查找第二个数组里一个sub array的长度
         * 这个subarray就是合起来之后中位数前的那一部分 剩下的部分在另一个数组里 长度可以通过减去当前的那一部分得到 比如
         * 一共size 9
         * 1 2 3 4 5
         * 1 2 3 4
         * 属于前半部分的是第一个数组的 1 2 和第二个数组的 1 2 3 注意这里中位数被算进前半部分了 另外这里3可能用的是另一个数组的
         * 判断这个分法是否正确 就是用第一个数组的2 <= 第二个数组的4 第二个数组的3 <= 第一个数组的3 这样我们保持长度确定的情况下 一定能组成正确的前半部分
         * 所以我们将二分查找较小的那个数组中 这一部分的长度
         */
        int m = nums1.length, n = nums2.length;
        if(m > n) return findMedianSortedArrays(nums2, nums1);
        if(m == 0) {
            return n % 2 == 0 ? (nums2[(n-1)/2] + nums2[n/2]) / 2.0 : 1.0 * nums2[n/2];
        }else if(n == 0) {
            return m % 2 == 0 ? (nums1[(m-1)/2] + nums1[m/2]) / 2.0 : 1.0 * nums1[m/2];
        }
        int total = m + n;
        int partition = total / 2;
        int left = 0, right = m;
        while(true) {
            int indexOne = left + (right - left) / 2; // possible length, later will -1 when accessing the last element in the left sub array
            int indexTwo = partition - indexOne; 
            // System.out.println(indexOne + " " + indexTwo);
            int element10 = indexOne <= 0 ? Integer.MIN_VALUE : nums1[indexOne-1]; // ensure it passes check
            int element11 = indexOne < m ? nums1[indexOne] :  Integer.MAX_VALUE;
            int element20 = indexTwo <= 0 ? Integer.MIN_VALUE : nums2[indexTwo-1];
            int element21 = indexTwo < n ? nums2[indexTwo] :  Integer.MAX_VALUE;
            // System.out.println(element10 + " " + element21 + " " + element20 + " " + element11);
            if(element10 <= element21 && element20 <= element11) {
                // System.out.println("enter?");
                if(total % 2 == 0) {
                    return (Math.max(element10, element20) + Math.min(element11, element21)) / 2.0;
                }
                return Math.min(element11, element21);
            }else if(element10 > element21) {
                right = indexOne - 1;
            }else{
                left = indexOne + 1;
            }
        }
        // should be guaranteed to find median

        // int m = nums1.length, n = nums2.length;
        // if(m == 0) {
        //     return n % 2 == 0 ? (nums2[(n-1)/2] + nums2[n/2]) / 2.0 : 1.0 * nums2[n/2];
        // }else if(n == 0) {
        //     return m % 2 == 0 ? (nums1[(m-1)/2] + nums1[m/2]) / 2.0 : 1.0 * nums1[m/2];
        // }
        // int one = 0, two = 0, i = 0, end = (m + n) % 2 == 0 ? (m + n - 1) / 2 : (m + n) / 2;
        // double mid = 0.0;
        // while(i <= end) {
        //     if(one < m && two < n) {
        //         if(nums1[one] <= nums2[two]) {
        //             mid = nums1[one++];
        //         }else{
        //             mid = nums2[two++];
        //         }
        //     }else if(one < m) {
        //         mid = nums1[one++];
        //     }else if(two < n) {
        //         mid = nums2[two++]; 
        //     }
        //     // System.out.println(mid);
        //     i++;
        // }
        // if((m + n) % 2 == 0) {
        //     double mid2 = -1;
        //     if(one < m && two < n) {
        //         if(nums1[one] <= nums2[two]) {
        //             mid2 = nums1[one++];
        //         }else{
        //             mid2 = nums2[two++];
        //         }
        //     }else if(one < m) {
        //         mid2 = nums1[one++];
        //     }else if(two < n) {
        //         mid2 = nums2[two++]; 
        //     }
        //     // System.out.println(mid2);
        //     mid = (mid + mid2) / 2.0;
        // }
        // return mid;
    }
}
