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
    // private void quicksort(int[][] nums, int left, int right){
    //     if(left >= right){
    //         return;
    //     }
    //     //partition
    //     int[] s = nums[left];
    //     int[] n = nums[right];
    //     int pivot = (left + right) / 2;
    //     int[] m = nums[pivot];
    //     if((s[0] > m[0] && s[0] <= n[0]) || (s[0] >= n[0] && s[0] < m[0])){
    //         pivot = left;
    //         m = s;
    //     }else if((n[0] > m[0] && n[0] <= s[0]) || (n[0] < m[0] && n[0] >= s[0])){
    //         pivot = right;
    //         m = n;
    //     }
    //     //System.out.println("pivot " + left + " " + right + " " + m);
    //     //swap pivot to the last
    //     swap(nums, pivot, right);
    //     int i = left - 1, j = right;
    //     while(true){
    //         while(i < j && nums[++i][0] < m[0]){}
    //         while(j > i && nums[--j][0] > m[0]){}
    //         if(i < j){
    //             swap(nums, i, j);
    //         }else{
    //             break;
    //         }
    //     }
    //     //swap back
    //     swap(nums, i, right);
    //     //recusion
    //     quicksort(nums, left, i - 1);
    //     quicksort(nums, i + 1, right);
    // }
    // private void swap(int[][] nums, int a, int b){
    //     int[] temp = nums[a];
    //     nums[a] = nums[b];
    //     nums[b] = temp;
    // }
}
