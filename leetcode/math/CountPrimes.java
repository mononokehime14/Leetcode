package math;

public class CountPrimes {
    /* 算法名字叫做埃拉托斯特尼筛选法 是一种O(NloglogN)高效寻找prime的方法
     * 方法的原理在于如果2是prime 则2的所有倍数都不是prime
     * 故此可以递推3就必然是prime 因为前面没有数字的倍数是3 这里去掉了0和1
     * 递推全部 然后再loop寻找就可以了
     */
    public int countPrimes(int n) {
        boolean[] nums = new boolean[n];
        Arrays.fill(nums, true);
        for(int i = 2;i * i < n;i++) {
            if(nums[i]){
                for(int j = 2 * i;j < n; j+=i){
                    nums[j] = false;
                }
            }
        }
        int sum = 0;
        for(int i = 2;i < n;i++) { // 0 and 1 are not prime number
            sum += nums[i] ? 1 : 0;
        }
        return sum;
    }
}
