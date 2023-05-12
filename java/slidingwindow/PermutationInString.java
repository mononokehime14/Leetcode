package slidingwindow;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        /**
         * 这道题很难debug 如果用hashmap 入window出window的模版的话 非常不方便
         * 如果保持窗口固定 那就非常好implement了 直接i先跑m 然后从m开始只保持m 的window
         * 剩下的就只是入window和出window之后 怎么更新window的状态了 在这道题目里可以用26的array代替map
         */
        int n = s2.length(), m = s1.length();
        if(m > n) return false;
        int[] window = new int[26];
        for(int i = 0;i < m;i++) {
            window[s1.charAt(i) - 'a']++;
            window[s2.charAt(i) - 'a']--;
        }
        if(allZero(window)) return true;
        for(int i = m;i < n;i++) {
            window[s2.charAt(i) - 'a']--;
            window[s2.charAt(i - m) - 'a']++;
            if(allZero(window)) return true;
        }
        return false;
    }
    private boolean allZero(int[] nums) {
        for(int i: nums) {
            if(i != 0) return false;
        }
        return true;
    }
}
