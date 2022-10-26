package array;

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        /* 这道题思路是正确的 在细节上卡了一下
         * 首先是数数 如果最后是偶数 必然可以分列两旁 如果是奇数 一开始觉得只有一个最大的count能放在中间
         * 但是没有想到可以把奇数-1的偶数部分分列两旁 最后有一个奇数剩下的1放在中间 这样就对了
         * 这里有一个技巧 在于a比A要后面 'a' - 'A'是32 所以理论上 要存lower + higher一个32 + 26 = 58的array就够了
         * 也可以直接counting[c] 直接将其转化成int
         */
        int[] counting = new int[64];
        for(char c: s.toCharArray()) {
            counting[c - 'A']++;
        }
        int result = s.length();
        boolean hasOdd = false;
        for(int i: counting) {
            if(i % 2 == 1) {
                result--;
                hasOdd = true;
            }
        }
        return hasOdd ? result + 1 : result;
    }
}
