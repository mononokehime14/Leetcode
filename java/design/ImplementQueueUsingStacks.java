package design;

public class ImplementQueueUsingStacks {
    /* 232
     * 把s1的数字导到s2里 这样s2当中 顺序就反过来了 变成了FIFO 也就是queue的顺序
     * s2的肯定要先用完/pop完 因为顺序在前 这时候push先push到s1里 一会儿s2用完了 再把s1的刚加的导过去
     */
    private Stack<Integer> s1, s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    public int peek() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    public boolean empty() {
        return s1.size() == 0 && s2.size() == 0;
    }
}
