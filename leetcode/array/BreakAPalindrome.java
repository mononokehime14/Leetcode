package array;

public class BreakAPalindrome {
    public String breakPalindrome(String p) {
        /**
         * 思路想不到 一开始是用了palindrome的套路 找到中间点 然后背向双指针
         * 但是这样非常麻烦 这个思路很取巧 只需要看前一半 从左到右 只要能翻就直接return 必然是最小值
         * 左边一半找不到 就直接把右边最后一个改成b 这必然是最小的
         * 因为 aabaa -> aabab比aabba要小
         */
        int n = p.length();
        if(n == 1) return "";
        char[] pl = p.toCharArray();
        for(int i = 0;i < n/2;i++) {
            if(pl[i] > 'a') {
                pl[i] = 'a';
                break;
            }else if(i == n/2 - 1){
                pl[n-1] = 'b';
            }
        }
        return new String(pl);
    }
}
