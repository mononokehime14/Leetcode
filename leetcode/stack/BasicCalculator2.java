package stack;

public class BasicCalculator2 {
    /* 和1一样的思路 多了乘除之后 我们sign就不能是1或者-1了 要用char 后面switch判断
     * 这里要清楚 碰到一个符号解决的上一个符号的运算
     * 还有一个细节就是最后可能不碰到下一个符号就结束了 这样就要最后再把残余解决一下
     */
    private int start;
    public int calculate(String s) {
        start = 0;
        return recursion(s);
    }
    private int recursion(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for(;start < s.length();start++) {
            char c = s.charAt(start);
            if(c == ' ') continue;
            if(c == ')') break;
            if(c == '(') {
                start++;
                num = recursion(s);
            }else if(c == '+' || c == '-' || c == '*' ||c == '/') {
                switch(sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(num * -1);
                        break;
                    case '*':
                        int newI = num * stack.pop();
                        System.out.println(newI);
                        stack.push(newI);
                        break;
                    case '/':
                        int newII = stack.pop() / num;
                        System.out.println(newII);
                        stack.push(newII);
                        break;
                }
                num = 0;
                sign = c;
            }else{
              num = num * 10 + (c - '0');  
            }     
        }
        if(num != 0) {
            switch(sign) {
                case '+':
                    break;
                case '-':
                    num = -1 * num;
                    break;
                case '*':
                    num = num * stack.pop();
                    break;
                case '/':
                    num = stack.pop() / num;
                    break;
            }
        }
        while(!stack.empty()) num += stack.pop();  
        // System.out.println(num);
        return num;
    }
}
