package interview;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MathworksVOPreparation {
    // Actual question
    // intern A complete K tasks, get corresponding reward for each task; intern B complete 剩下的
    // 求最大的reward 实质上就是求最大reward的一种sub sequence
    // DP是可以解的 太可惜了 我离最后的解法只差了base case 我把正确的base case设置comment掉了 
    // 面试的时候脑子一团乱糟糟的 根本没办法静下心来思考 我感觉我缺乏一套系统的推定DP的方法 不然在那种紧张慌乱的情况下
    // 感觉很难debug DP 这种题debug可能我不适合只用2d dp矩阵 有点抽象 我反而应该用原题的例子 对于一个element 怎么算两种状态
    // base case应该考虑2d矩阵的边边 感觉已经做出来了这个base case 后面也改对了状态转移 太可惜了
    public int maxReward(int k, List<Integer> reward1, List<Integer> reward2){
        int n = reward1.size();
        int[][] dp = new int[n+1][k+1];
        for(int i = 1;i <= n;i++) {
            dp[i][0] += dp[i-1][0] + reward2.get(i-1);
        }
        for(int i = 1;i <= n;i++) {
            for(int j = 1;j <= k;j++) {
                dp[i][j] = Math.max(dp[i-1][j] + reward2.get(i-1), dp[i-1][j-1] + reward1.get(i-1));
            }
        }
        return dp[n][k];
    }
    // Given square, a maxSum, find maximum width, so that all sub-square with this width, has all its element sum less than maxSum
    public int largestSubgrid(int[][] grid, int maxSum) {
        int n = grid.length;
        int[][] preSum = new int[n][n];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                int currentSum = grid[i][j];
                if(i - 1 >= 0) currentSum += preSum[i-1][j];
                if(j - 1 >= 0) currentSum += preSum[i][j-1];
                if(i - 1 >= 0 && j - 1 >= 0) currentSum -= preSum[i-1][j-1];
                preSum[i][j] = currentSum;
            }
        }
        for(int width = n;width > 0;width--) {
            boolean flag = true;
            for(int i = width-1;i < n && flag;i++) {
                for(int j = width-1;j < n && flag;j++) {
                    int currentSum = preSum[i][j];
                    if(j - width >= 0) currentSum -= preSum[i][j-width];
                    if(i - width >= 0) currentSum -= preSum[i-width][j];
                    if(i - width >= 0 && j - width >= 0) currentSum += preSum[i-width][j-width];
                    if(maxSum < currentSum) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) return width;
        }
        return 0;
    }
    // red, green, blue light, each with range (start, end), all three combine become white, count white range
    public int countWhite(int n, int[][] lights) {
        int m = lights.length;
        int[][] preDiff = new int[n+1][3];
        for(int[] light: lights) {
            preDiff[light[1]][light[0]-1]++;
            if(light[2]+1 <= n) preDiff[light[2]+1][light[0]-1]--;
        }
        int[] temp = new int[3];
        int count = 0;
        for(int[] position : preDiff) {
            temp[0] += position[0];
            temp[1] += position[1];
            temp[2] += position[2];
            if(temp[0] > 0 && temp[1] > 0 && temp[2] > 0) {
                count++;
            }
        }
        return count;
    }
    // find the maximum possible value of the smallest vulnerability in the final array after performing exactly k upgrades
    public int findLargestMin(int[] servers, int k) {
        int n = servers.length;
        int min = Arrays.stream(servers).min().getAsInt();
        int sum = Arrays.stream(servers).sum();
        if(min * n <= sum - k) return min;
        k -= (sum - min * n); // must be positive
        return min - (int)Math.ceil(k / n);

    }
    // count sub arrays that have elements >= min and <= max
    public int countSubArrayInRange(int[] arr, int min, int max) {
        int count = 0, answer = 0, n = arr.length;
        for(int i : arr) {
            if(i > max || i < min) {
                if(count > 0) {
                    answer += (count - 1 + 1) * (count - 1) / 2;
                }
                count = 0;
            }else{
                count++;
            }
        }
        if(count > 0) {
            answer += (count - 1 + 1) * (count - 1) / 2;
        }
        return answer;
    }
    // cost of combining two number = sum of two, given array, combine until last one remain, minimal cost
    // the number of combination is fixed, so we need to avoid number of times we use large number to combine -> start form small, greedly
    public int costOfCombine(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : arr) pq.offer(i);
        int cost = 0;
        while(pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            cost += sum;
            pq.offer(sum);
        }
        return cost;
    }
}
