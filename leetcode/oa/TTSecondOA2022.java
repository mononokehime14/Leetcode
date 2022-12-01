package oa;
import java.util.Arrays;

public class TTSecondOA2022 {
    /*
     * 前两个选择题：一个NoSQL相较于普通数据库的优势，一个问Linux process state
     * 第一个coding：假如只通过把element移动到array末尾的操作来进行排序，问输入数组之后需要这样操作多少次才能让数组变有序
     * 第二个：LC 依旧拔 题目废话很多，直接按lc原题做即可
     * 第三个：纯纯的01背包问题，直接套进来，看清weight和value具体是哪个就行，不需要改动
     * 值得一提的是这次给的stdin似乎有问题，我试了python和cpp，前两个coding都会在读输入的时候就会报错。具体原因是给的stdin代码只能处理空格隔开的多个参数，前两个题的输入都只有一个参数，是逗号隔开的数组‍‍‌‍‍‌‍‌‌‌‌‍‌‍‍‍‍‍‌‍。把输入改了之后所有test cases都能过。
     */

    // Trick-Or-Treats, LC198 House Robber
    public int houseRobber(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int prev = Math.max(nums[0], nums[1]), prevPrev = nums[0];
        for(int i = 2;i < n;i++) {
            int current = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = current;
        }
        return prev;
    }

    // Reordering from shortest to tallest, minimum number of move-to-back moves to sort an array ascending
    // 在这一个符合排序完后的位置的值和下一个符合排序完位置后的值之间的所有值 必然大于下一个值 大于这一个值 大于所有之前的值 都必然move
    // 而这些要move的值 是可以在右边形成一个升序的 只要我们从最小的move到最大的 因为它们全部大于前面正确序列的最大值
    // 相当于我们只要找出sorted版本中 从前面开始数 不用move到后面的 剩下的必然要动 而且可以只动一次形成升序
    public int minimumMoveBack(int[] nums) {
        int[] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        int n = nums.length;
        int answer = n, i = 0, j = 0;
        for(int i = 0;i < n;i++) {
            if(nums[i] == numsCopy[j]) {
                answer--;
                j++; //注意J是不必要走完的
            }
        }
    }
    // Strategy for TikTok Advertisement, CoinChange, 01背包
    public int highestUsers(int[][] M, int N) {
        int m = M.length;
        int[] dp = new int[N+1];
        // base case 0, no need initialization
        for(int i = 0;i <= m;i++) {
            for(int j = 0;j <= N;j++) {
                if(j - M[i][1] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - M[i][1]]);
                }
            }
        }
        return dp[N];
        // int m = M.length;
        // int[][] dp = new int[M+1][N+1];
        // // base case 0, no need initialization
        // for(int i = 0;i <= m;i++) {
        //     for(int j = 0;j <= N;j++) {
        //         int use = j - M[i][1] >= 0 ? dp[i][j - M[i][1]] : 0;
        //         dp[i][j] = Math.max(dp[i-1][j], use);
        //     }
        // }
        // return dp[m][N];
    }

    /*
     * Linux process status: Running/Runnable (TASK_RUNNING), Uninterruptible Sleep, Interruptible Sleep, Stopped, Zombie
     * Stopped(TASK_STOPPED)就是513学过的SIGSTOP/SIGCONT Zombie (EXIT_ZOMBIE) 就是child结束了send SIGCHILD signal需要parent用wait/waitpid reap
     * 两个sleep是说在wait state (TASK_UNINTERRUPTABLE, TASK_INTERRUPTABLE)
     * TASK_TRACED则是debugger在trace某个process
     * 
     * Higher scalability
     * Specialized Query Operations
     * Frustration with the restrictiveness of relational schemas
     */
}