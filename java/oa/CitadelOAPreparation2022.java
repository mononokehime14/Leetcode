package oa;

import java.util.Arrays;
import java.util.HashMap;

public class CitadelOAPreparation2022 {
    // Global Maximum
    // Function to find the maximum of all minimum difference of pairs possible among the subsequence
    public int globalMaximum(int A[], int n, int B) {
        // Sort the Array
        Arrays.sort(A);
        // Stores the boundaries
        // of the search space
        int s = 0;
        int e = A[n - 1] - A[0];
        // Store the answer
        int ans = 0;
        // Binary Search
        while (s <= e) {
            int mid = (s + e) / 2;
            // If subsequence can be formed
            // with min diff mid and size B
            if (can_place(A, n, B, mid)) {
                ans = mid;
                // Right half
                s = mid + 1;
            }else {
                // Left half
                e = mid - 1;
            }
        }
        return ans;
    }
    /* 对于每一个想要找到的mid 尝试大小为n的sequence 看看能不能sequence的min == mind */
    private boolean can_place(int A[], int n, int B, int mid) {
        int count = 1;
        int last_position = A[0];
    
        // If a subsequence of size B with min diff = mid is possible， return true else false
        for(int i = 1; i < n; i++)
        {
            if (A[i] - last_position >= mid) {
                last_position = A[i];
                count++;
                if (count == B){
                    return true;
                }
            }
        }
        return false;
    }

    //Throttling Gateway
    public int throttlingGateway(int[] requestTime) {
        int n = requestTime.length;
        HashMap<Integer, Integer> mem = new HashMap<>();
        int answer = 0;
        for(int i: requestTime) {
            if(mem.containsKey(i) && mem.getOrDefault(i, 0) >=  3) {
                answer++;
                continue;
            }
            if(!mem.containsKey(i)) mem.put(i, 0);
            mem.put(i, mem.get(i) + 1);
            int tenCound = 0
            for(int j = Math.min(i - 10, 0);j <= i;j++) {
                tenCound += mem.getOrDefault(j, 0);
            }
            if(tenCound > 20) {
                answer++;
                mem.put(i, mem.get(i) - 1);
                continue;
            }
            int miniteCound = 0;
            for(int j = Math.min(i - 60, 0);j <= i;j++) {
                miniteCound += mem.getOrDefault(j, 0);
            }
            if(tenCound > 60) {
                answer++;
                mem.put(i, mem.get(i) - 1);
            }
        }
        return answer;
    }

    // Matrix Summation
    public static int[][] matrixSummation(int[][] after) {
        int m = after.length, n = after[0].length;
        int[][] before = new int[m][n];
        for(int i = m - 1;i > 0;i--) {
            for(int j = n - 1;j > 0;j--) {
                before[i][j] = after[i][j] - (after[i-1][j] + after[i][j-1] - after[i-1][j-1]);
            }
        }
        for(int i = m - 1;i > 0;i--) {
            before[i][0] = after[i][0] - after[i-1][0];
        }
        for(int i = n - 1;i > 0;i--) {
            before[0][i] = after[0][i] - after[0][i-1];
        }
        before[0][0] = after[0][0];
        return before;
    }

    // Visiting Cities
    public int[] visitingCities(int[] red, int[] blue, int blueCost) {
        int n = red.length;
        int[] output = new int[n + 1];
        int[][] dp = new int[n+1][n+1];
        for(int i = 0;i < red.length;i++) {
            dp[i+1][0] = output[i] + red[i];
            dp[i+1][1] = Math.min(dp[i][1], dp[i][0] + blueCost) + blue[i];
            System.out.println(dp[i+1][0] + " " + dp[i+1][1]);
            output[i+1] = Math.min(dp[i+1][0], dp[i+1][1]);
            if(dp[i+1][0] > dp[i+1][1]) dp[i+1][0] = Integer.MAX_VALUE;
            if(dp[i+1][0] < dp[i+1][1]) dp[i+1][1] = Integer.MAX_VALUE;
        }
        return output;
    }

    // Prime Factor Visitation
    public int[] primeFactorVisitation(int[] state, int[] nums) {
        // calculate primes
        int max = Arrays.stream(nums).max().getAsInt();
        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for(int i = 2;i * i < max + 1;i++) {
            if(primes[i]){
                for(int j = 2 * i;j < max + 1; j+=i){
                    primes[j] = false;
                }
            }
        }
        // for(boolean i: primes) System.out.print(i + " ");
        int n = state.length;
        int[] count = new int[n];
        for(int i: nums) {
            countPrimeFactors(count, primes, i, n);
        }
        for(int i = 0;i < n;i++) {
            if(count[i] == 0) continue;
            if(count[i] % 2 == 1) state[i] = 1 - state[i];
        }
        return state;
    }
    private void countPrimeFactors(int[] count, boolean[] primes, int target, int n) {
        System.out.print(target+": ");
        for(int i = 2;i <= target;i++) {
            if(!primes[i]) continue;
            boolean first = true;
            while(target % i == 0) {
                System.out.print(i + " ");
                target /= i;
                if(first) {
                    for(int k = i-1;k < n;k += i) count[k]++;
                    first = false;
                }
            }
        }
        System.out.print("\n");
    }
    // Sprint Training
    public int getMostVisited(int n, int[] sprints) {
        int[] count = new int[n + 2]; //这里size + 2首先是因为--要在end后面 正好sprints里的数字也比index大一 顺手就+2了
        for(int i = 1;i < sprints.size();i++) {
            int start = Math.min(sprints.get(i), sprints.get(i-1));
            int end = Math.max(sprints.get(i), sprints.get(i-1));
            count[start]++;
            count[end + 1]--;
        }
        int step = 0;
        for(int i = 0;i < n;i++) {
            step += count[i];
            count[i] = step;
        }
        int max = 0, maxIndex = 0;
        for(int i = 0;i < n;i++) {
            if(count[i] > max) {
                max = count[i];
                maxIndex = i;
            }
        }
        return maxIndex + 1;   
    }
}
