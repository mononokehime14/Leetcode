package oa;

import java.util.Arrays;

public class PayPalOA2022Preparation {
    // Positive Prefix 

    // Minute Dilemma, leetcode 1010 原题
    public int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer, Integer> s = new HashMap<>();
        int count = 0;
        for(int i: time) {
            int m = i % 60, temp = (60 - m) % 60;
            if(s.containsKey(temp)) {
                count += s.get(temp);     
            }
            if(s.containsKey(m)) {
                s.put(m, s.get(m) + 1); 
            }else{
                s.put(m, 1);
            }
        }
        return count;
    }
    // Closest Numbers LeetCode 1200原题
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for(int i = 1;i < arr.length;i++) {
            min = Math.min(Math.abs(arr[i] - arr[i-1]), min);
        }
        for(int i = 1;i < arr.length;i++) {
            if(Math.abs(arr[i] - arr[i-1]) == min) {
                answer.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }
        return answer;
    }

    //First unique character
    public int firstUniqueCharacter(String s) {
        int[] counting = new int[26];
        char[] clist = s.toCharArray();
        for(char c: clist) {
            counting[c - 'a']++;
        }
        for(int i = 0;i < clist.length;i++) {
            if(counting[clist[i] - 'a'] ==  1) return i+1;
        }
        return -1;
    }
    //Postive Prefixes
    public static int positivePrefix(Integer[] arr) {
        Arrays.sort(arr, Collections.reverseOrder()); // we cannot sort primitive using comparator
        for(int i: arr) System.out.println(i);
        long sum = 0;
        int count = 0;
        for(int i: arr) {
            sum += i;
            if(sum < 0) break;
            count++;
        }
        return count;
    }
    // Vowels
    public int[] vowels(String[] strArr, String[] queries) {
        int n = strArr.length, m = queries.length;
        int[] preSum = new int[n + 1];
        int[] answer = new int[m];
        for(int i = 1;i < n + 1;i++) {
            preSum[i] = check(strArr[i-1]) + preSum[i-1];
        }
        for(int i = 0;i < m;i++) {
            int left = Integer.parseInt(queries[i].split("-")[0]);
            int right = Integer.parseInt(queries[i].split("-")[1]);
            answer[i] = preSum[right] - preSum[left - 1];
        }
        return answer;
    }
    private int check(String s) {
        char c = s.charAt(0), c2 = s.charAt(s.length() - 1);
        if((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c =='u') && (c2 == 'a' || c2 == 'e' || c2 == 'i' || c2 == 'o' || c2 =='u')) return 1;
        return 0;
    }
}
