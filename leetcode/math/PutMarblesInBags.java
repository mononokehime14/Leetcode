package math;

public class PutMarblesInBags {
    /* 2551
     * 差一点点做出来 我能够想到答案是pow(2, n) - 2 （实际上 应该是-4 这里是题目的问题 没有考虑同时向对方互换的情况）
     * 但是n有很大的case 所以不能用java的pow 而必须要用自己的 所以我使用了之前pow那道题的代码 有点问题 没调试成功
     * 正好pp第一个作业也有快速pow的算法 这里就用了pp的 实际上就是把exponent拆分成1 2 4 8 ...的组合 注意M的取模
     * 有一个细节 result - 2是有可能变成负数的 加上M再取模 不会影响结果 可以防止负数
     */
    private long M = (long) 1e9 + 7;
    public int monkeyMove(int n) {
        return (int) myPow(2, n);
    }
    private long myPow(int x, int n) {
        long result = 1, xpower = x;
        while(n > 0) {
            if(n % 2 == 1) { // 这个bit上有1
                result = result * xpower % M;
            }
            xpower = xpower * xpower % M;
            n >>= 1;
        }
        return (result - 2 + M) % M; //这可以防止 res - 2变成负的
    }
}
