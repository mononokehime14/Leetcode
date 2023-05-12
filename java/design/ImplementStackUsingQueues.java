package design;

public class ImplementStackUsingQueues {
    /* 225
     * 用了一个暗影卫队 暴力解出来的 (commented) top和pop的时候 导到另一个queue里面 所以要不断的判断当前的queue是哪一个
     * 更好的办法是把导的部分放在push里 一开始就把最上面那个放到最前面 这样pop和top都不用循环了 而要完成这个甚至不用暗影卫队 自己加poll出来的就可以了
     * 实际上这就是在push的时候 实现反向的queue
     */
    private Queue<Integer> q;
    public MyStack() {
        q = new LinkedList<>();
    }
    
    public void push(int x) {
        q.add(x);
        int size = q.size();
        for(int i = 1;i < size;i++) {
            q.add(q.remove());
        }
    }
    
    public int pop() {
        return q.remove();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.size() == 0;
    }
    // private Queue<Integer> q1;
    // private Queue<Integer> q2;
    // public MyStack() {
    //     q1 = new LinkedList<>();
    //     q2 = new LinkedList<>();
    // }
    
    // public void push(int x) {
    //     if(q1.size() != 0) {
    //         q1.add(x);
    //     }else{
    //         q2.add(x);
    //     }
    // }
    
    // public int pop() {
    //     if(q1.size() != 0){
    //         while(q1.size() > 1) {
    //             q2.add(q1.poll());
    //         }
    //         return q1.poll();
    //     }else{
    //         while(q2.size() > 1) {
    //             q1.add(q2.poll());
    //         }
    //         return q2.poll();
    //     }
    // }
    
    // public int top() {
    //     if(q1.size() != 0){
    //         while(q1.size() > 1) {
    //             q2.add(q1.poll());
    //         }
    //         int answer = q1.poll();
    //         q2.add(answer);
    //         return answer;
    //     }else{
    //         while(q2.size() > 1) {
    //             q1.add(q2.poll());
    //         }
    //         int answer = q2.poll();
    //         q1.add(answer);
    //         return answer;
    //     }
    // }
    
    // public boolean empty() {
    //     return q1.size() == 0 && q2.size() == 0;
    // }
}
