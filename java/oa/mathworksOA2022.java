package oa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class mathworksOA2022 {
    // Sum as you go, Jump Game
    public long sumAsYouGo(int[] path, int maxLength) {
        int n = path.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = path[0];
        for(int i = 1;i < n;i++) {
            int max = -1;
            for(int j = Math.max(0, i - maxLength);j < i;j++) {
                max = Math.max(max, dp[j]);
            }
            dp[i] = max == -1 ? -1 : max + dp[i];
        }
        return dp[n-1];
    }
    // Binary Tree Search
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) {
            return root;
        }else if(root.val < val) {
            return searchBST(root.right, val);
        }else{
            return searchBST(root.left, val);
        }
    }
    // Group Division
    public int groupDivision(int[] weights, int maxRange) {
        int count = 1, n = weights.length;
        int start = weights[0];
        Arrays.sort(weights);
        for(int i = 0;i < n;i++) {
            if(weight[i] - start > maxRange) {
                start = weights[i];
                count++;
            }
        }
        return count;
    }
    // Fun Anagrams
    public int funAnagrams(List<String> words, List<String> phases) {
        HashMap<String, Integer> anagrams = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        for(String word : words) {
            String newWord = reorganize(word);
            map.put(word, newWord);
            anagrams.put(newWord, anagrams.getOrDefault(newWord, -1) + 1);
        }
        int answer = 0;
        for(String phase : phases) {
            int localAnswer = 0;
            for(String word : phase.split(" ")) {
                if(!map.containsKey(word)) continue;
                String newWord = map.get(word);
                if(anagrams.containsKey(newWord)) {
                    localAnswer = localAnswer == 0 ? anagrams.get(newWord) : localAnswer * anagrams.get(newWord);
                }
            }
            answer += localAnswer;
        }
        return answer;
    }
    public String reorganize(String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i : count) sb.append(i);
        return sb.toString();
    }
    //Box the items, 完全背包
    public int boxTheItems(int total, int k) {
        int[] dp = new int[total+1];
        // initialize here?
        for(int i = 1;i <= k;i++) {
            for(int j = 1;j <= total;j++) {
                if(j - i >= 0) dp[j] = dp[j-i] + dp[j];
            }
        }
        return dp[total];
    }
    // Collecting Pebbles, 01背包
    public int collectingPebbles(List<Integer> bucketSizes, int numOfPebbles) {
        int[] dp = new int[numOfPebbles+1];
        int n = Math.max(numOfPebbles, bucketSizes.size());
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for(int bucket : bucketSizes) {
            for(int j = 1;j <= numOfPebbles;j++) {
                if(j - bucket >= 0) {
                    dp[j] = Math.min(dp[j-bucket] + 1, dp[j]);
                }
            }
        }
        return dp[numOfPebbles] == n + 1 ? -1 : dp[numOfPebbles];
    }
    // schedule K processes
    public int scheduleKProcesses(int[] capacities, int processes) {
        int n = capacities.length;
        int max_ability = Arrays.stream(capacities).max().getAsInt();
        // Stores frequency of each element
        int tmp[] = new int[max_ability + 1];
        // Stores minimum time required
        // to schedule all process
        int count = 0;
        // Count frequencies of elements
        for (int i = 0; i < n; i++) {
            tmp[capacities[i]]++;
        }
        // Find the minimum time
        for (int i = max_ability;i >= 0; i--) {
            if (tmp[i] != 0) {
                if (tmp[i] * i < K) {
                    // Decrease the value of K
                    K -= (i * tmp[i]);
                    // Increment tmp[i/2]
                    tmp[i / 2] += tmp[i];
                    // Increment the count
                    count += tmp[i];
                    // Return count, if all process are scheduled
                    if (K <= 0) return count;
                }else {
                    // Increment count
                    if (K % i != 0) {
                        count += (K / i) + 1;
                    }
                    else {
                        count += (K / i);
                    }
                    // Return the count
                    return count;
                }
            }
        }
        // If it is not possible to
        // schedule all process
        return -1;
    }
    //Maze, leetcode the Maze
    public boolean maze2D(String[] gird, int maxTime) {
        int m = gird.length, n = gird[0].length();
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{ {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
        Queue<int[]> q = new linkedlist<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        int level = 0;
        while(!q.isEmpty() && level <= maxTime) {
            int size = q.size();
            for(int i = 0;i < size;i++) {
                int[] current = q.poll();
                if(current[0] = m - 1 && current[1] == n - 1) return true;
                for(int[] dir : dirs) {
                    int newX = current[0] + dir[0];
                    int newY = current[1] + dir[1];
                    // move Furthest straight-line distance?
                    // while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    //     x += dir[0];
                    //     y += dir[1];
                    // }
                    if(gird[newX].charAt(newY) != '$' && !visited[newX][newY]) {
                        q.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            level++;
        }
    }
    // counting cars
    public int[] countingCars(int[] numCars, int[] hourStartQ) {
        int n = numCars.length, m = hourStartQ.length;
        int[] postAnswer = new int[n];
        postAnswer[n-1] = numCars[n-1];
        int count = 1, currentMax = numCars[n-1];
        for(int i = n - 2;i >= 0;i--) {
            if(numCars[i] > currentMax) {
                count = 1;
                currentMax = numCars[i];
            }else if(numCars[i] == currentMax) {
                count++;
            }
            postAnswer[i] = count;
        }
        int[] answer = new int[m];
        for(int i = 0;i < m;i++) {
            answer[i] = postAnswer[hourStartQ[i]-1];
        }
        return answer;
    }
}
