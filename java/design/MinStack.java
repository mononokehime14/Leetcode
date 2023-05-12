package design;

public class MinStack {
    /* 一开始想到的是 用原本的stack 然后再用一个相应的stack记录min
     * 但是可以自己implement stack 用自己的node结构 这样就可以用一个stack记录了
     * 注意head的使用 和doubly linked list不同 不是不会碰到的虚拟head 而是不断更新的 stack塞进去的新东西就串在head后面
     */
    private Node head;
    public MinStack() {
        head = new Node(-1, Integer.MAX_VALUE, null);
    }
    
    public void push(int val) {
        head = new Node(val, Math.min(val, head.min), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
    private class Node {
        int val;
        int min;
        Node next;
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
