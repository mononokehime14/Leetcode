public class ScrambleString {
    /* 87
     * 要反思一下昨天的状态 好像人实在是太累了 根本思考不了 我还是得保证自己7-8小时的睡眠 即使睡不着
     * 这道题我是有两个想法的 首先是分而治之 当前的string可以分成两半 考虑换和不换的情况
     * 其次 一个string能够算是和s2相等 可以检查词频 出现的字母一样的话 就确保能够换过去
     * 但是这两个想法如何结合起来 答案是分而治之之后 不用去计算substring的词频是否符合 可以递归下去
     * a b两个char的string假设能够match 情况就是s2要么是a b要么是b a 再往上 a b c能否match 成功的情况也必然是有一种分法
     * 检查substring递归到最底层是一样的 也就是说 base case能够算出来是否match 而不需要计算词频 第二个想法是不必要的
     * 这里dp用的是dfs带备忘录 时间复杂度是n平方 但是击败百分比很低 可能用迭代的dp更快？
     */
    private HashMap<String, Boolean> memo;
    public boolean isScramble(String s1, String s2) {
        memo = new HashMap<>();
        if(s1.length() != s2.length()) return false;
        if(s1.length() == 0) return false;
        return recurse(s1, s2);
    }
    private boolean recurse(String s1, String s2) {
        String state = s1 + "," + s2;
        if(memo.containsKey(state)) return memo.get(state);
        if(s1.equals(s2)) {
            memo.put(state, true);
            return true;
        }
        int n = s1.length();
        if(s2.length() != n || n < 1) {
            memo.put(state, false);
            return false;
        }
        boolean result = false;
        for(int i = 1;i < n;i++) {
            boolean noswap = recurse(s1.substring(0,i), s2.substring(0,i)) && recurse(s1.substring(i,n), s2.substring(i,n));
            boolean swap = recurse(s1.substring(0,i), s2.substring(n - i, n)) && recurse(s1.substring(i,n), s2.substring(0, n - i));
            if(swap || noswap) {
                result = true;
                break;
            }
        }
        memo.put(state, result);
        return result;
    }
}
