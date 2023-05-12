package math;

public class SuperUglyNumber {
    /* 313
     * 和丑数2的题目相比 丑数不再是质因数只有2 3 5 而是只有primes数组中的质数
     * 那么 套用之前的思路 我们就不是动态挑选3个链表 而是挑选k个链表
     * 一切思路不变 但是借鉴了Merge K Sorted List的方法用PriorityQueue
     * 注意这里PQ的data 除了我们要比较的值 这里是丑数 之外 还要有下一跳的地址 这里就是uglyNum中的index
     * 额外的 我们要保存这条链表代表的prime 以方便后面更新计算
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] uglyNums = new int[n+1];
        for(int i : primes) {
            pq.offer(new int[]{1, 1, i});
        }
        int p = 1;
        while(p <= n) {
            int[] current = pq.poll();
            if(uglyNums[p-1] != current[0]) {
                uglyNums[p++] = current[0];
            }
            int[] newPair = new int[]{uglyNums[current[1]] * current[2], current[1] + 1, current[2]};
            pq.offer(newPair);
        }
        return uglyNums[n];
    }
}
