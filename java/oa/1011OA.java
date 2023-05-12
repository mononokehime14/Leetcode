package oa;

import java.util.*;

public class 1011OA {
    /* minyi OA */
    public static int[] supplySolution(String[][] logs) {
        HashMap<String, Map<Integer, Integer>> prices = new HashMap<>();
        List<Integer> output = new LinkedList<>();
        for(int i = 0;i < logs.length;i++) {
            String[] s = logs[i];
            switch(s[0]) {
                case "supply":
                    if(!prices.containsKey(s[1])) prices.put(s[1], new TreeMap<>());
                    TreeMap<Integer,Integer> temp = prices.get(s[1]);
                    int price = Integer.parseInt(s[3]);
                    int number = Integer.parseInt(s[2]);
                    temp.put(price, temp.getOrDefault(price, 0) + number);
                    prices.put(s[1], temp);
                    break;
                case "sell":
                    TreeMap<Integer,Integer> temp = prices.get(s[1]);
                    int price = temp.firstKey();
                    int number = Integer.parseInt(s[2]);
                    int revenue = temp.get(price) > number ? number * price : 0;
                    while(temp.get(price) <= number) {
                        number -= temp.get(price);
                        revenue += temp.get(price) * price;
                        temp.remove(price);
                        price = temp.firstKey();
                    }
                    ouput.add(revenue);
                    break;
                case "return":
                    if(!prices.containsKey(s[1])) prices.put(s[1], new TreeMap<>());
                    TreeMap<Integer,Integer> temp = prices.get(s[1]);
                    int oldPrice = Integer.parseInt(s[3]);
                    int newPrice = Integer.parseInt(s[4]);
                    int number = Integer.parseInt(s[2]);
                    temp.put(newPrice, temp.getOrDefault(newPrice, 0) + number);
                    break;
            }
        }
        return output.toArray();
    }

    /* Yiyang OA */
    public static String[] subStrings(String s) {
        Set<Character> vowel = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        String minS = null, maxS = null;
        int n = s.length();
        for(int i = 0;i < n;i++) {
            if(!vowel.contains(s.charAt(i))) continue;
            for(int j = i + 1;j < n;j++) {
                if(vowel.contains(s.charAt(j))) continue;
                String current = s.substring(i, j);
                if(minS == null || current.compareTo(minS) < 0) {
                    minS = current;
                }
                if(maxS == null || current.compareTo(maxS) > 0) {
                    maxS = current;
                }
            }
        }
        return new String[]{minS, maxS};
    }

    public String[] missingWords(String[] t, String[] s) {
        //t is subsequence
        int left = 0, right = 0;
        List<String> output = LinkedList<String>();
        while(left < t.length) {
            while(!t[left].equals(s[right])) {
                output.add(s[right]);
                right++;
            }
            left++;
            right++;
        }
        while(right < s.length) output.add(s[right++]);
        return output.toArray();
    }

    /* CC OA */
    public int[] vowel(String[] strArr, String[] queries) {
        int preSum = new int[strArr.length + 1];
        int output = new int[queries.length];
        for(int i = 1;i <= strArr;i++) {
            String current = strArr[i-1];
            if(isVowel(current.charAt(0)) || isVowel(current.charAt(current.length() -  1))) {
                preSum[i] = preSum[i-1] + 1;
            }else{
                preSum[i] = preSum[i-1]; 
            }
        }
        for(int i = 0;i < queries.length;i++) {
            String s = queries[i];
            int x = Integer.parseInt(s.split("\\-")[0]);
            int y = Integer.parseInt(s.split("\\-")[1]);
            output[i] = preSum[x] - preSum[y];
        }
    }
    private boolean isVowel(char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        return false;
    }
}
