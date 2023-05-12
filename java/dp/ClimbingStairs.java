public class ClimbingStairs {
    public int climbStairs(int n) {
        /* 用两个prev做了空间优化 和斐波那契数列是一摸一样的 */
        if(n == 1) return 1;
        int prev = 2;
        int prevPrev = 1;
        for(int i = 2;i < n;i++) {
            int current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }
        return prev;
    }
}
