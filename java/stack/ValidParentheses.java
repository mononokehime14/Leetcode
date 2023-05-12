package stack;

public class ValidParentheses {
    /* 对于一种括号的检查情况来说太简单了 我们只要加减一个count count不能为负数 最后必须为0就可以了
     * 但是对于多种括号呢 一个count无法应对了 为了动态记录三种括号的信息 我们可以使用一个stack
     * 只要是前半部分就push到栈里 后半部分检查是否和栈top匹配
     * 这里有一个细节就是要转化成另一半才能进行匹配比较
     */
    public boolean isValid(String s) {
        if(s.length() <= 1) return false;
        Stack<Character> st = new Stack<>();
        for(int i = 0;i < s.length();i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                st.push(s.charAt(i));
            }else{
                if(!st.empty() && match(st.pop()) == s.charAt(i)){  
                }else{
                    return false;
                }
            }
        }
        return st.empty();
    }
    private char match(char s) {
        if(s == '(') return ')';
        if(s == '[') return ']';
        return '}';
    }
}
