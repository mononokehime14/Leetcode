package math;

import java.util.Arrays;

public class DivisorAndMultiplier {
    /*
     * Given an array of length n, for each index i from 1 to n, count the number of indices j (j != i), 
     * such that either arr[j] is a divisor of arr[i] or arr[j] is a multiple of arr[i]
     * 
     * 这道题和之前高效计算prime的思路是一样的 与其每一个都进行一次计算（用了max长度的freq count之后 每一次跳着count还是没有本质上的提升）
     * 不如直接从1开始把每个值先全部算好 这种思路非常适合 有多个query 重复同一种算法的时候
     */
    public int[] countDivisorsMultiples(int[] arr) {
        int MAX = Arrays.stream(A).max().getAsInt();
        int[] freq = new int[MAX + 1];
        for (int i: arr) freq[i]++;
        int[] res = new int[MAX + 1];
        for (int i = 1; i <= MAX; ++i) {
            for (int j = i; j <= MAX; j += i) {
                if (i == j) {
                    res[i] += (freq[j] - 1);
                }
                else {
                    res[i] += freq[j];
                    res[j] += freq[i];
                }
            }
        }
        int n = arr.length;
        int[] output = new int[n];
        for(int i = 0;i < n;i++) output[i] = res[arr[i]];
        return output;
        // int max = Arrays.stream(arr).max().getAsInt();
        // int n = arr.length;
        // int[] output = new int[n];
        // int[] count = new int[max+1];
        // for(int a: arr) count[a]++;
        // for(int i = 0;i < n;i++) {
        //     int temp = 0;
        //     for(int i = a + a;i <= max;i+=a) {
        //         temp += count[i];
        //     }
        //     temp += count[1];
        //     output[i] = temp;
        // }
        // return output;
    }
}
