package stack;

public class BasicCalculator {
    /* 一开始用stack的思路是正确的 但是我一开始的方法有一点复杂 用一个char stack
     * 什么都往里面放 如果是数字 并且peek是符合 就运算然后放入新的数字 close括号就把前面的数字和括号拿下来 把新的数字推上去 同时检查之前是不是运算符
     * 但是这种方法有个问题 就是比如我们有234242423这么一个数字 不断的拿下来放上去新的 但是int是4 bytes char是2 bytes range搭不上
     * 所以我想必然要把int 分开一个stack储存 但是怎么再去和符号做联系呢 如果两个stack就没办法知道计算顺序了
     * 正确的思路是直接用符号把数字算好 比如-a就把a搞成负数 再放到stack里 四个符号都同样处理 
     * 运算符解决了 括号怎么办呢 可以用递归 碰到(就递归 )返回 先把括号里的全部算了
     */
    private int start;
    public int calculate(String s) {
        start = 0;
        return recursion(s);
    }
    private int recursion(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int num = 0;
        for(;start < s.length();start++) {
            char c = s.charAt(start);
            if(c == ' ') continue;
            if(c == ')') break;
            switch(c) {
                case '(':
                    start++;
                    num = recursion(s);
                    break;
                case '+':
                    stack.push(num * sign);
                    num = 0;
                    sign = 1;
                    break;
                case '-':
                    stack.push(num * sign);
                    num = 0;
                    sign = -1;
                    break;
                default:
                    num = num * 10 + (c - '0');
            }
        }
        num = num * sign;
        while(!stack.empty()) num += stack.pop();  
        // System.out.println(num);
        return num;
    }
}
