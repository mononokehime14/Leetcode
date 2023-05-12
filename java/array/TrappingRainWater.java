package array;

public class TrappingRainWater {
    public int trap(int[] height) {
        /* 一开始的想法是动态更新峰值leftMax 当右侧的峰值出现的时候 两个峰值取小的那一个 然后计算峰值之间的所有数
         * 但是这个想法的问题在于 如何判断峰值 如果height下跌峰值出现 那么height一直跌怎么办 如果再比较前次记录的leftMax
         * 大于等于才出现第二个峰值 那么就处理不了第二个峰值比第一个低的情况
         * 这里只能对于每一个值 往左边找第一个峰值 往右边找第一个峰值 我们可以用三叉戟算法
         * 
         * 三叉戟算法的空间复杂度是O(N) 可以用双指针优化成O(1)
         * 左右指针往中间走 分别更新各自的峰值 如果左边的峰值小 则当前数值 必然会产生峰值 - height的水
         */
        int n = height.length, sum = 0;
        int left = 0, right = n - 1, leftMax = 0, rightMax = 0;
        // 可以从零开始应该一开始max是等于height自己的 必然不会积水
        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax) {
                sum += leftMax - height[left];
                left++;
            }else{
                sum += rightMax - height[right];
                right--;
            }
        }
        return sum;
        // int n = height.length, sum = 0;
        // int[] preMax = new int[n];
        // int[] postMax = new int[n];
        // preMax[0] = height[0];
        // for(int i = 1;i < n;i++) preMax[i] = Math.max(preMax[i-1], height[i]);
        // postMax[n-1] = height[n-1];
        // for(int i = n - 2;i >= 0;i--) postMax[i] = Math.max(postMax[i+1], height[i]);
        // for(int i = 1;i < n-1;i++) { //注意把边界去掉
        //     sum += Math.min(preMax[i], postMax[i]) - height[i];
        // }
        // return sum;
    }
}
