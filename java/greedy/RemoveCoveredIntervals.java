package greedy;

public class RemoveCoveredIntervals {
    /* 1288
     * 参照Non overlapping interval的思路做出来的 严格来说 也可以算作一种贪心策略
     * 但是最重要的是认识到要用start升序 用end降序 然后比较end end不如的必然被cover
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if(a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });
        int count = 1, end = intervals[0][1], n = intervals.length;
        for(int i = 1;i < n;i++) {
            if(intervals[i][1] > end) {
                end = intervals[i][1];
                count++;
            }
        }
        return count;
    }
}
