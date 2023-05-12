package array;

public class IsSubsequence {
    /* 392
     * 草傻逼了 还以为这道题是DP 感觉很类似编辑距离问题 但是实际上并不是 只需要很简单的双指针就行了
     * 事实证明 两个string不一定会用到DP 但是却大概率会用到双指针
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        char[] sl = s.toCharArray();
        char[] tl = t.toCharArray();
        int left = 0, right = 0;
        while(right < m && left < n) {
            if(sl[left] == tl[right]) {
                left++;
                right++;
            }else{
                right++;
            }
        }
        if(left == n) return true;
        return false;
    }
}
