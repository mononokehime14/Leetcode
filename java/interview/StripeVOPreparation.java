package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import javax.management.Query;

/**
 * For Stripe Intern VO 2022 笑死 明天就要面试了 甚至今天还有tt面试 fuck tt
 */
public class StripeVOPreparation {
    /** Mutual Rank
     * 给一个user map，比如 {a: [c,d], c: [a]}，key是不同的user，value是其他user的ranking
     * 然后实现hasMutualRank(a)，如果a的第一rank user恰好也把a作为第一rank，那么return true
     */
    public boolean hasMutualRank(HashMap<String, List<String>> map, String a) {
        String firstRank = map.get(a).get(0);
        //检查是否有key 是否value为空list 注意空list应该用isempty检测而不是null
        if(map.containsKey(firstRank) && !map.get(firstRank).isEmpty() && map.get(firstRank).get(0).equals(a)) return true;
        return false;
    }

    /**
     * 在第一题的基础上改动，rank作为变量, hasMutualRank(a, rank)。第一题就相当于hasMutualRank(a, 0)
     */
    public boolean hasMutualRank2(HashMap<String, List<String>> map, String a, int rank) {
        if(!map.containsKey(a) || map.get(a).isEmpty() || mep.get(a).size() <= rank) return false;
        String pair = map.get(a).get(rank);
        if(!map.containsKey(pair) || map.get(pair).isEmpty() || mep.get(pair).size() <= rank) return false;
        if(map.get(pair).get(rank).equals(a)) return true;
        return false;
    }

    /**
     * 如果rank有浮动，rank和rank - 1都可以算作hasMutualRank，输出所有有mutual rank的user pair
     * 或者理解为每个人的option list位置是可以swap的， 就是相邻两个option 可以换位置， 但是只是一次机会
     */
    public void hasMutualRank3(HashMap<String, List<String>> map, String a, int rank, int range) {
        if(!map.containsKey(a)) return;
        int aRange = Math.min(rank + range + 1, map.get(a).size());
        for(int i = rank;i < aRange;i++) {
            String pair = map.get(a).get(i);
            if(!map.containsKey(pair)) continue;
            int bRange = Math.min(rank + range + 1, map.get(pair).size());
            for(int j = rank;j < bRange;j++) {
                if(map.get(pair).get(j).equals(a)) { // find pair
                    System.out.println(a + " " + pair);
                }
            }
        }
    }

    /** HTTP Header
     * Part 1
     */
    public List<String> parseAcceptLanguage(String header, List<String> supported) {
        HashSet<String> mem = new HashSet<>(supported); // if given Set, this is not needed
        LinkedList<String> answer = new LinkedList<>();
        for(String s: header.trim().split(",")) {
            if(mem.contains(s)) answer.addLast(s);
        }
        return answer;
    }
    /** 
     * Accept-Language headers will often also include a language tag that is not region-specific - 
     * for example, a tag of "en" means "any variant of English". Extend your function to support these language tags by letting them match all specific variants of the language.  
     * Examples:  parse_accept_language("en", ["en-US", "fr-CA", "fr-FR"]) returns: ["en-US"]  
     */
    public List<String> parseAcceptLanguage2(String tag, List<String> supported) {
        LinkedList<String> answer = new LinkedList<>();
        for(String s: supported) {
            if(s.split("-")[0].equals(tag)) answer.add(s);
        }
    }

    public static long getMaximumEvenSum(List<Integer> val) {
        /**
         * Fuck DP, DP你个脑袋 先加所有正数 如果结果是偶数可以直接return 不是偶数则
         * either subtract a positive odd number from it, or add a negative odd. 
         */
        // Find sum of positive numbers
        int pos_sum = 0;
        for (int i = 0; i < n; ++i)
            if (arr[i] > 0)
                pos_sum += arr[i];
 
        // If sum is even, it is our
        // answer
        if (pos_sum % 2 == 0)
            return pos_sum;
 
        // Traverse the array to find the
        // maximum sum by adding a
        // positive odd or subtracting a
        // negative odd
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            if (arr[i] % 2 != 0) {
                if (arr[i] > 0)
                    ans = ans>(pos_sum - arr[i]) ?
                          ans:(pos_sum - arr[i]);
                else
                    ans = ans>(pos_sum + arr[i]) ?
                          ans:(pos_sum + arr[i]);
            }
        }  
 
        return ans;
        // int n = val.size();
        // long[][] dp = new long[n+1][2];
        // for(int i = 1;i < n;i++) {
        //     int current = val.get(i);
        //     if(current%2==0) {
        //         dp[i][0] = dp[i-1][0] + current;
        //         dp[i][1] = dp[i-1][1] + current;
        //     }else{
        //         dp[i][0] = dp[i-1][1] + current;
        //         dp[i][1] = dp[i-1][0] + current;
        //     }
        // }
        // return dp[n][0];
    }

    public static long getMaximumEvenSum(List<Integer> val) {
        int n = val.size();
        long[][] dp = new long[n][2];
        int first = val.get(0);
        dp[0][0] = first % 2 == 0 ? first : 0;
        dp[0][1] = first % 2 == 0 ? 0 : first;
        for(int i = 1;i < n;i++) {
            int current = val.get(i);
            if(current%2==0) {
                dp[i][0] = Math.max(dp[i-1][0] + current, dp[i-1][0]);
                dp[i][1] = Math.max(dp[i-1][1] + current, dp[i-1][1]);
            }else{
                dp[i][0] = Math.max(dp[i-1][1] + current, dp[i-1][0]);
                dp[i][1] = Math.max(dp[i-1][0] + current, dp[i-1][1]);
            }
            // System.out.println(dp[i][0] + " " + dp[i][1]);
        }
        return dp[n-1][0];
    }
}
