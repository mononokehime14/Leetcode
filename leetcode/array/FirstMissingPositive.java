package array;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        /** 41
         * 这道题非常难和已知的算法搭上关系 和之前某种利用相应index上的数字*-1来记录状态的思路相似
         * 主要是因为memory要求O(1) 同时要求O(N)时间复杂度 这个思路是可以考虑的
         * 但是要利用这个思路 还差一步思路 那就是first missing positive的取值必然在1到n+1之间
         * 这是为什么呢 最小值是1很好理解 最大值为什么是n+1 因为最大的情况就是阶梯上升 没有任何空间给missing number
         * 这样的话 1 2 3 4 5... 最大的missing number就是n+1了
         * 用这个思路 我们把超出范围的转化成n+1 然后迭代 把每个出现的数字 在相应的index上 把index上的数字变负数来表示这个数字出现过
         * 这样最后我们只要寻找还没变负的index 没有则必然是n+1 因为我们跳过了它(array index的rangemax原因)
         */
        int n = nums.length;
        for(int i = 0;i < n;i++) {
            if(nums[i] <= 0 || nums[i] > n) nums[i] = n + 1;
        }
        for(int i = 0;i < n;i++) {
            int temp = Math.abs(nums[i]);
            if(temp == n + 1 || nums[temp-1] < 0) continue;
            nums[temp-1] /= -1;
        }
        for(int i = 0;i < n;i++) {
            if(nums[i] > 0) return i + 1;
        }
        return n + 1;
    }
}
