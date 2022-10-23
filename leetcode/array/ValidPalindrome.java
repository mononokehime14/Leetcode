package array;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        /* 简单的回文检查 双指针就可以了 题目比较坑 还有一些不是字母或者数字的符号什么的 需要跳过 */
        int left = 0, right = s.length() - 1;
        s = s.toLowerCase();
        while(left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if((l - 'a' > 25 || l - 'a' < 0) && (l - '0' > 9 || l - '0' < 0)) {
                left++;
                continue;
            }else if((r - 'a' > 25 || r - 'a' < 0) && (r - '0' > 9 || r - '0' < 0)) {
                right--;
                continue;
            }else{
                if(l != r) return false;
                left++;
                right--;
            }
        }
        return true;
    }
}
