package math;

public class PreimageSizeOfFactorialZerosFunction {
    /* 这道题是进阶版的trailing zeros
     * 我本来的想法是按照原思路 应该可以通过k代表需要多少个5因子 推导出range
     * 但是这个想法似乎太简单了 我还没有想清楚为什么这个方法不正确
     * 正解是通过南北朝算法确定range 每次call trailing zeros 推导左边和右边的边界
     * 为什么right要选择Long.MAX_VALUE呢 这是因为我们不方便直接知道什么数字给了最大的K 10^9
     * 我们知道什么数字会大过这个max k Long.MAX_VALUE就可以 这样南北朝就是左闭右开 需要left < right停止
     * 有因为这个特性 我们return的时候 如果是左侧边界left就可以了因为left == right的时候就退出了
     * 右侧边界同理就要left - 1因为right并不是我们想要的值
     */
    public int preimageSizeFZF(int k) {
        // if(k > 0 && k % 5 == 0) return 0;
        // return (k+1) * 5 - k * 5;
        return (int)(rightBS(k) - leftBS(k) + 1);
    }
    private long leftBS(int k) {
        long left = 0, right = Long.MAX_VALUE;
        while(left < right) {
            long mid = left + (right - left) / 2;
            if(trailingZeros(mid) < k) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }
    private long rightBS(int k) {
        long left = 0, right = Long.MAX_VALUE;
        while(left < right) {
            long mid = left + (right - left) / 2;
            if(trailingZeros(mid) <= k) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left - 1;
    }
    private long trailingZeros(long n) {
        long sum = 0;
        while(n > 0){
            n /= 5;
            sum += n;
        }
        return sum;
    } 
}
