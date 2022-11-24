package oa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SnowFlakeOAPreparation2022 {
    // Task Scheduling 回溯/dfs 备忘录
    public static int getMinCost(List<Integer> cost, List<Integer> time) {
        int n = cost.size();
        Map<Integer, Integer>[] memo = new Map[n];
        for(int i = 0;i < n;i++) memo[i] = new HashMap<>();
        return backtrack(cost, time, 0, 0, n, memo);
    }
    private static int backtrack(List<Integer> cost, List<Integer> time, int index, int freetime, int n, Map<Integer,Integer>[] memo) {
        if(index == n) return freetime >= 0 ? 0 :  Integer.MAX_VALUE / 10; // /10 avoid integer overflow
        if(freetime >= n - index) return 0; // the optimal plan now is use free server all the way
        if(memo[index].containsKey(freetime)) return memo[index].get(freetime);
        int one = backtrack(cost, time, index + 1, freetime - 1, n, memo); // put on free server
        int two = cost.get(index) + backtrack(cost, time, index + 1, freetime + time.get(index), n, memo); // put on paid server
        one = Math.min(one, two);
        memo[index].put(freetime, one);
        return one;
    }

    // Cross the Threshold
    public int getMaxBarrier(List<Integer> initialEnergy, long th) {
        int left = 1, right = Collections.max(initialEnergy);
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(!isValid(initialEnergy, th, mid)) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
    private static boolean isValid(List<Integer> ini, long th, int b) {
        long sum;
        for(int i : ini) sum += Math.max(0, i - b);
        return sum >= th;
    }

    // String formation
    private int M = (int) 1e9 + 7
    public int numWays(List<String> words, String target) {
        int w = words.get(0).length(), t = target.length();
        int[][] board = new int[m][26];
        int[][] dp = new int[m][26];
        for(int[] d : dp) Arrays.fill(d, -1);
        for(String word: words) {
            for(int i = 0;i < w;i++) {
                board[i][word.charAt(i) - 'a']++;
            }
        }
        return traverse(board, target, 0, 0, w, t);
    }
    private int traverse(int[][] board, String target, int index, int cindex, int w, int t, int[][] dp) {
        if(cindex == t) return 1;
        if(index == n) return 0;
        char c = target.charAt(cindex);
        if(dp[index][c - 'a'] != -1) return dp[index][c - 'a'];
        int sum = 0;
        for(int i = index;i < w;i++) {
            if(board[i][c - 'a'] > 0) {
                sum += board[i][c - 'a'] * traverse(board, target, i+1, cindex+1, w, t);
                sum %= M;
            }
        }
        dp[index][c - 'a'] = sum;
        return sum;
    }

    // !!!Perfect Pairs
    /*
    def find_small(lists,index):
        l = 0
        r = len(lists) - 1
        while(l<=r):
            mid = (l + r)//2
            if lists[mid] == index:
                return mid
            if lists[mid] > index:
                r = mid - 1
            else:
                l = mid + 1
        if r==-1:
            return -1
        else:
            return r

    def find_large(lists,index):
        l = 0
        r = len(lists) - 1
        while(l<=r):
            mid = (l + r)//2
            if lists[mid] == index:
                return mid
            if lists[mid] < index:
                l = mid + 1
            else:
                r = mid - 1
        if l==len(lists):
            return -1
        else:
            return l

    def solution(numbers):
        res = 0
        numbers.sort()
        zero = find_large(numbers,0)
        neg = numbers[:zero]
        pos = numbers[zero:]
        temp = 0
        for i in range(len(neg)):
            l = find_large(numbers,neg[i]*2)
            r = find_small(numbers,neg[i]/2)
            if l != -1 and r!= -1:
                temp += (r-l+1)-1
        res += temp//2
        
        temp = 0
        for i in range(len(pos)):
            l = find_large(numbers,pos[i]/2)
            r = find_small(numbers,pos[i]*2)
            if l != -1 and r!= -1:
                temp += (r-l+1)-1
        res += temp//2
        
        temp = 0
        for i in range(len(pos)):
            used = -pos[i]
            l = find_large(numbers,used*2)
            r = find_small(numbers,used/2)
            if r != -1 and l != -1:
                temp += r - l +1
        res += temp
        return res
    */
     public static int getPerfectPairsCount(int[] numbers){
        int count = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            if(numbers[i] == 0) {
                count += n - 1;
            }else if(numbers[i] > 0) {
                int leftBound = bsFirstGreater(nums, 0);
                int rightBound = bsFirstSmaller(nums, 2 * numbers[i]);
                count += rightBound - leftBound + 1;
            }else{
                int leftBound = bsFirstGreater(nums, 0);
                int rightBound = bsFirstSmaller(nums, -1 * numbers[i]);
                count += rightBound - leftBound + 1;
            }
        }
        return count;
    }
    private int bsFirstGreater(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) {
                left = mid + 1; 
            }else {
                right = mid - 1;
            }
        }
        return left == nums.length ? -1 : left; 
    }
    private int bsFirstSmaller(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) { 
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return right < 0 ? -1 : right;
    }

    // Maximize Array Value
    public maximumArrayValue(int[] nums) {
        int left = 0, right = Arrays.stream(nums).max().getAsInt();
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(!maxValid(nums, mid)) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
    
    private boolean maxValid(int[] nums, int max) {
        int buffer;
        for(int i: nums) {
            if(i <= max) {
                // smaller than max, we can thus have some buffer
                buffer += max - i;
            }else if(i - max <= buffer) {
                // higher than max, 但是可以通过操作消到max之下 转移到左边 并且左边不回因此超出max 
                buffer -= (i - max);
            }else{
                // ji
                return false;
            }
        }
        return true;
    }
    /* 
     def canAchieveMax(a, m):
        avail = 0
        for el in a:
            if el <= m:
                # we have (m - el) extra space to transfer
                # from elements to the right of this one
                avail += m - el
            elif (el - avail) <= m:
                # this element exceeds our maximum value,
                # but we can transfer as much as we need
                # to elements to the left
                avail -= el - m
            else:
                # this element exceeds our maximum value
                # and if we were to transfer it to any
                # number of elements to the left, they
                # would exceed it instead. 
                # we definitely cannnot achieve this max
                return False

        return True

    def minimizeMax(a):
        i, j = 0, max(a)
        while i < j - 1:
            m = (i + j) // 2

            if canAchieveMax(a, m):
                # we can achieve a max of m, so no need to
                # try to achieve a greater max. let's see if we 
                # can achieve a lower, more optimal max
                j = m
            else:
                # we can't achieve m or any max below m, so try
                # to find a greater max that we can achieve
                i = m + 1

        # we can either achieve i or j, try i first since it's smaller
        return i if canAchieveMax(a, i) else j
    */

    // Path to a goal
    // https://leetcode.com/discuss/interview-question/800573/path-&#8205;&#8204;&#8204;&#8204;&#8204;&#8205;&#8204;&#8204;&#8204;&#8205;&#8204;&#8204;&#8204;&#8205;&#8204;&#8205;&#8205;&#8205;&#8204;&#8204;to-a-goal-coding-question-asked-at-gameskraft
    static int MOD = 1000000007;
    public static int path2Goal(String s2, int n, int x, int y) {
        // Creating array of string length
        char[] s = s2.toCharArray();
        
        int[] prevSame = new int[s.length];
        int idxL = -1;
        int idxR = -1;
        for (int i = 0; i < prevSame.length; i++) {
            if (s[i] == 'l') {
                prevSame[i] = idxL;
                idxL = i;
            } else {
                prevSame[i] = idxR;
                idxR = i;
            }
        }
    
        // dp[i][j] is number of distinct subsequnces of length i to end up at j
        long[][] dp = new long[s.length+1][n+1];
        dp[0][x] = 1;
        for (int i = 1; i <= s.length; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i-1][j];
                if (s[i-1] == 'l') {
                    if (j+1 <= n) dp[i][j] += dp[i-1][j+1];
                    if (j+1 <= n && prevSame[i-1] >= 0) dp[i][j] -= dp[prevSame[i-1]+1-1][j+1];
                } else {
                    if (j-1 >= 0) dp[i][j] += dp[i-1][j-1];
                    if (j-1 >= 0 && prevSame[i-1] >= 0) dp[i][j] -= dp[prevSame[i-1]+1-1][j-1];
                }
                dp[i][j] = (dp[i][j] + MOD) % MOD;
            }
        }
        return (int) dp[s.length][y];
    }

    // String Pattern / Number of distinct words of size n with at most k contiguous vowels
    // https://www.geeksforgeeks.org/number-of-distinct-words-of-size-n-with-at-most-k-contiguous-vowels/
    public int kvowelwords(int N, int K) {
        int i, j;
        int MOD = 1000000007;
        // Array dp to store number of ways
        int[][] dp = new int[N + 1][K + 1] ;
        int sum = 1;
        for(i = 1; i <= N; i++)
        {
            // dp[i][0] = (dp[i-1][0]+dp[i-1][1]..dp[i-1][k])*21
            dp[i][0] = sum * 21;
            dp[i][0] %= MOD;
    
            // Now setting sum to be dp[i][0]
            sum = dp[i][0];
    
            for(j = 1; j <= K; j++)
            { 
                // If j>i, no ways are possible to create
                // a string with length i and vowel j
                if (j > i){
                    dp[i][j] = 0;
                }else if (j == i){
                    // If j = i all the character should
                    // be vowel
                    dp[i][j] = power(5, i, MOD);
                }else{
                    // dp[i][j] relation with dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1] * 5;
                }
                dp[i][j] %= MOD;
                // Adding dp[i][j] in the sum
                sum += dp[i][j];
                sum %= MOD;
            }
        }
        return sum;
    }
    private int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
    
        if (x == 0)
            return 0;
    
        while (y > 0)
        {
            if ((y & 1) != 0)
                res = (res * x) % p;
                
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }
    // Sorted Arrangement
    public long minimumOperations(List<Integer> numbers) {
        if (numbers.size() == 1) return 1;
        List<Integer> list = new LinkedList<>();
        long sum = 0;
        for(int n : numbers) {
            sum += insert(curList, n);
        }
        return sum;
    }
    private int insert(LinkedList<Integer> numbers, int target) {
        int n = numbers.size();
        int left = firstSmallerBS(numbers, target);
        int right = firstGreaterBS(numbers, target);
        if(left == -1) {
            numbers.addFirst(target);
            return 1;
        }else if(right == -1){
            numbers.addLast(target);
            return 1;
        }else if(left < (n - right)) {
            numbers.add(left, target);
            return 2 * left + 1;
        }else{
            numbers.add(right, target);
            return 2 * (n - right) + 1;
        }
    }
    private int firstSmallerBS(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums.get(mid) >= target) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right < 0 ? -1 : right;
    }
    private int firstGreaterBS(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums.get(mid) <= target) {
                left = mid + 1;
            }else{
                right = mid - 1; 
            }
        }
        return left == nums.size() ? -1 : left;
    }

    // Split the array
    public static int countWays(List<Integer> nums) {
        int mod = 1000000007;
        int sz = nums.size();
        // have an new int[] sum that contains the sum of the nums
        // sum[i] = nums[0] + ... + nums[i];
        long[] sum = new long[sz];
        sum[0] = nums.get(0);
        for (int i = 1; i < sz; i++){
            sum[i] = (sum[i - 1] + nums.get(i));
        }
        
        // i: the boundary between A1 and A2
        // j: the left-most boundary between A2 and A3
        // k: the right-most boundary between A2 and A3
        int res = 0;
        for (int i = 0, k = 0; i < sz - 2; i++){
            // num[j] > S1 + S2 --> continue 
            int j = i + 1;
            if (nums.get(j) > sum[i] + sum[sz - 1] - sum[j]) continue;
            // S2              <= S1     + S3 
            // sum[k] - sum[i] <= sum[i] + sum[sz - 1] - sum[k]
            // 2 * sum[j] <= 2 * sum[i] + sum[sz - 1]
            while (k <= j || (k < sz - 1 && 2 * sum[k] <= 2* sum[i] + sum[sz - 1])){
                k++;
            }
            res = (res + k - j) % mod;
            if (res < 0) res += mod;
        }
        return res;
    }

    // Shrinking Number Line
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, answer = nums[n-1] - nums[0];
        for(int i = 0;i < n - 1;i++) {
            int low = Math.min(nums[0] + k, nums[i+1] - k);
            int high = Math.max(nums[n-1] - k, nums[i] + k);
            answer = Math.min(answer, high - low);
        }
        return answer;
    }

    //Vowel Substring
    // sliding window
    public static int vowelSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(char c: s.toCharArray()) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c =='u') {
                sb.append(c);
            }else{
                if(sb.length() > 0) {
                    count += countSubstring(sb.toString());
                }
                sb.setLength(0);
            }
        }
        if(sb.length() > 0) {
            count += countSubstring(sb.toString());
        }
        return count;
    }
    public static int countSubstring(String s) {
        // System.out.println(s);
        char[] sc = s.toCharArray();
        int left = 0, right = 0, n = sc.length;
        int a = 0, e = 0, i = 0, o = 0, u = 0, count = 0;
        while(right < n) {
            switch(sc[right]) {
                case 'a':
                    a++;
                    break;
                case 'e':
                    e++;
                    break;
                case 'i':
                    i++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'u':
                    u++;
                    break;
                default:
                    a = 0;
                    e = 0;
                    i = 0;
                    o = 0;
                    u = 0;
                    left = right + 1;
            }
            while(a > 0 && e > 0 && i > 0 && o > 0 && u > 0) {
                count += n - right;
                // System.out.println(a + " " + e + " " + i +" "+o+" "+u);
                switch(sc[left]) {
                    case 'a':
                        a--;
                        break;
                    case 'e':
                        e--;
                        break;
                    case 'i':
                        i--;
                        break;
                    case 'o':
                        o--;
                        break;
                    case 'u':
                        u--;
                        break;
                }
                left++;
            }
            right++;
        }
        return count;
    }
}
