package greedy;

public class MinimumNumberOfArrowsToBurstBallons {
    /* 452
     * 这道题的思路和NonOverlappingIntervals是一样的 这里突然明白了这种贪心思路的原理
     * 按照end排序之后 end最早的这个气球 必定需要一只箭 因为没有别的气球在其前面结束 要想射中这个气球必然要出一箭
     * 这一箭要能射中尽可能多的气球 也就是所有和这个气球overlap的别的气球
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1])); // a[1] - b[1] will cause integer overflow
        int end = points[0][1];
        int count = 1; // at least one
        for(int i = 1;i < points.length;i++) {
            if(points[i][0] > end) { // no overlap, new end
                end = points[i][1];
                count++;
            }
        }
        return count;
    }
}
