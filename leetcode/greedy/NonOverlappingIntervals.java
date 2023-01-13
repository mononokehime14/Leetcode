package greedy;

public class NonOverlappingIntervals {
    /* 435
     * 这道题的贪心不能用overlap最多的区间 或者时间最长的区间 是可以举出反例的
     * 正确的贪心思路是挑选end最早的 然后删除所有和该区间overlap的 重复 如此选出来的是最后不overlap的区间
     * 推了几个例子确实是这样 而且这道题目是要区间数量最大化 而不是区间总时间最大 所以这个贪心可能是合理的
     * 在实现上 我们可以sort 然后删除 = 跳过
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int end = intervals[0][1];
        int count = 1; // at least one
        int n = intervals.length;
        for(int i = 1;i < n;i++) {
            if(intervals[i][0] >= end) { // no overlap, new end
                end = intervals[i][1];
                count++;
            }
        }
        return n - count; // 要求的remove的intervals数量
    }
}
