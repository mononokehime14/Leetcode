package array;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        /* 太巧了 那天cc OA的第二题 二分查找峰值 很像patience sort的那道题
         * 我用了preMin和postMax 试图O(N)解决 但是失败了
         * 这个算法刚刚好用在这个题目里 一遍计算前面的乘积 一遍计算后面的乘积 然后将其相乘
         * 我宣布 由于其三次loop原数组的特点 我将这种算法命名为三叉戟算法
         */
        int n = nums.length;
        int[] pre = new int[n];
        int[] post = new int[n];
        pre[0] = 1;
        for(int i = 1;i < n;i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }
        post[n-1] = 1;
        for(int i = n - 2;i >= 0;i--) {
            post[i] = post[i + 1] * nums[i + 1];
        }
        int[] output = new int[n];
        for(int i = 0;i < n;i++) {
            output[i] = pre[i] * post[i];
        }
        return output;
    }
}
