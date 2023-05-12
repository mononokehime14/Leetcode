package greedy;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {
    /* 56
     * 这道题并非贪心 放到这里只是为了和其他interval得题目放在一起
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> intervals2 = new ArrayList<>();
        for(int i = 0;i < intervals.length - 1;i++){
            if(intervals[i+1][0] <= intervals[i][1]){
                int right = Math.max(intervals[i][1], intervals[i+1][1]);
                intervals[i+1][0] = intervals[i][0];
                intervals[i+1][1] = right;
            }else{
                intervals2.add(intervals[i]);
            }  
            if(i == intervals.length - 2){
                intervals2.add(intervals[i+1]);
            }   
        }
        return intervals2.toArray(new int[intervals2.size()][]);
    }
}
