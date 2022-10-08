package stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        /*
         * 这种题目好像都是用Stack 括号 运算符
         * 数字就push 运算符就把栈里的两个数字拿下来 运算 然后push新的结果上去
         * 是否可以把这种题目的思路归类成动态更新某个状态 就可以尝试用stack
         */
        int b;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < tokens.length;i++){
            switch(tokens[i]){
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    b = stack.pop();
                    stack.push(stack.pop() - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    b = stack.pop();
                    stack.push((int) (stack.pop() / b));
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }
}
