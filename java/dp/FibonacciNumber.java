public class FibonacciNumber {
    public int fib(int n) {
        /* 简单DP不做空间优化无法达到双百, 观察我们只用到了前两个数字! 
         * 但是这个空间优化只是理论上的orz leetcode上空间占用反而上升了
        */
        if(n == 0 || n == 1) return n;
        // int[] dp = new int[n + 1];
        int i_1 = 1;
        int i_2 = 0;
        for(int i = 2;i < n + 1;i++){
            int ic = i_1 + i_2;
            i_2 = i_1;
            i_1 = ic;
        }
        return i_1;
    }
}
