import java.util.Stack;
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        /*
         * 不要试图用recursive/backtrack，形式并不规整，并不是opt左边是数字，更左边就是第一个数；
         * 或者左边是opt第一个数就是第一个。这种方法无法解决["4","-2","/","2","-3","-","-"]
         * 无从判断哪里是哪里。故此，必用stack方法，注意第一个opt左边必然是它operation的两个数
         * 我们可从左之后，遇到opt pop前面的两个，运算，然后push
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
