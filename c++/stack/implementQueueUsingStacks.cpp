using namespace std;
/**
 * 232. Implement Queue using Stacks
 * 用两个stack倒一下 push正常push 拿的时候倒到out stack里 这样顺序就反过来了
 * 后面接着push先放在in里 直到out拿空则再次翻转顺序
*/
class MyQueue {
private:
    stack<int> in, out;
public:
    MyQueue() {
        
    }
    
    void push(int x) {
        in.push(x);
    }
    
    int pop() {
        in_and_out();
        int top = out.top();
        out.pop();
        return top;
    }

    void in_and_out(){
        if(out.empty()) {
            while(!in.empty()) {
                int x = in.top();
                in.pop();
                out.push(x);
            }
        }
    }
    
    int peek() {
        in_and_out();
        return out.top();
    }
    
    bool empty() {
        return in.empty() && out.empty();
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */