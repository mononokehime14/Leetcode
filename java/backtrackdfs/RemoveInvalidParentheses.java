package backtrackdfs;

public class RemoveInvalidParentheses {
    /* 301
     * 这道题的解法挺好想的 我觉得我有点畏惧hard题 如果顺着思路想一想的话 也许能够直接做出来 毕竟看答案也只是确认了答案不过是如此的思路
     * 我们先算出来有多少括号需要删 然后再backtrack 每次删掉一个括号 或者不删 两种选择 然后移动index 这样的话 时间复杂度应该是2 ** N, N是string length
     */
    private Set<String> output;
    public List<String> removeInvalidParentheses(String s) {
        int rc = 0, count = 0;
        for(int i = 0;i < s.length();i++) {
            char c = s.charAt(i);
            if(c == '(') {
                count++;
            }else if(c == ')') {
                count--;
                if(count < 0) {
                    rc++;
                    count++;
                }
            }
        }
        int lc = count;
        if(lc == 0 && rc == 0) return Collections.singletonList(s);
        // System.out.println(lc + " " + rc);
        output = new HashSet<>();
        char[] sc = s.toCharArray();
        backtrack(sc, lc, rc, 0);
        return new ArrayList<String>(output);
    }
    private void backtrack(char[] s, int lc, int rc, int start) {
       if(lc == 0 && rc == 0) {
           // System.out.println(new String(s));
           if(isValid(s)) {
               StringBuilder sb = new StringBuilder();
               for(char c: s) {
                   if(c != '*') sb.append(c);
               }
               output.add(sb.toString());
               return;
           }
       }
        if(start >= s.length) return;
       // remove
       if(lc > 0 && s[start] == '(') {
           s[start] = '*';
           backtrack(s, lc - 1, rc, start + 1);
           s[start] = '(';
       }else if(rc > 0 && s[start] == ')') {
           s[start] = '*';
           backtrack(s, lc, rc - 1, start + 1);
           s[start] = ')';
       }
       // dont remove
        backtrack(s, lc, rc, start + 1);
    }
    private boolean isValid(char[] s) {
        int count = 0;
        for(char c: s) {
            if(c == '(') {
                count++;
            }else if(c == ')') {
                count--;
                if(count < 0) return false;
            }
        }
        return count == 0;
    }
}
