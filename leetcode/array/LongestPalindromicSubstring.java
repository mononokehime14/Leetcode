package array;

public class LongestPalindromicSubstring {
    /* 一开始想这道题的思路在于dp 我们判断两边是否相同 然后在看中间是否是palindrome
     * 但是这个方法的一个问题是在递推过程中 无法确定中间的最长回文 是不是两边连在一起 换句话说 极值不能累积获得
     * 所以更好想的方法就是从中点扩散双制作 来判断从这个中点开始最长的回文 然后尝试每一个中点 就尝试了所有的情况
     */
    public String longestPalindrome(String s) {
        String res = "";
        for(int i = 0;i < s.length();i++) {
            String odd = palindrome(s, i-1, i+1);
            String even = palindrome(s, i, i+1);
            res = res.length() > odd.length() ? res : odd;
            res = res.length() > even.length() ? res : even;
        }
        return res;
    }
    private String palindrome(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left+1, right); //越界或者不等才会停止循环 所以不能算进去
    }
}
