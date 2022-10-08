package slidingwindow;

public class FindAllAnagramsInAString {
    /*
     * 这道题同样很难debug 我真的是fuck sliding window 我用的方法是和Permutation in String那道题目一样的
     * 由于题目假设char只是英文字符 所以可以用26长的array代替hashmap
     * 思路就是保持一个长度为n的window并滑动 超出这个长度的是不可能是正确答案的
     */
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        int[] window = new int[26];
        List<Integer> output = new LinkedList<>();
        if(m < n) return output;
        for(int i = 0;i < n;i++) {
            window[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']--;
        }
        if(allZero(window)) output.add(0);
        for(int i = n;i < m;i++) {
            window[s.charAt(i) - 'a']--;
            window[s.charAt(i - n) - 'a']++;
            if(allZero(window)) output.add(i - n + 1);
        }
        return output;
    }
    private boolean allZero(int[] nums) {
        for(int i: nums) {
            if(i != 0) return false;
        }
        return true;
    }
}
