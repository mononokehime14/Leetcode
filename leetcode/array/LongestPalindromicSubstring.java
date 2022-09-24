package array;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        char[] cs = s.toCharArray();
        int left = 0, right = cs.length;
        if(right % 2 == 0) {
            left = right / 2 - 1;
            right /= 2;
        }else{
            left = right / 2 - 1;
            right = right / 2 + 1;
        }
        while(left >= 0 && right < cs.length) {
            if(cs[left] != cs[right])
        }
    }
}
