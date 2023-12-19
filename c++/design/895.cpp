/**
 * 895. Maximum Frequency Stack
 * 这道题从buckets开始思考是正确的, 毕竟是count frequency, 然后又需要动态更新
 * 不过这道题有一个特性, 就是stack, 特别是freq tie的时候按照stack顺序pop
 * 比如5 7 5 7 5, pop两次, 先拿5, 然后拿7
 * 那么buckets会变得很复杂, 5 freq--之后, 要从3 frequency的bucket里全部抽出来, 加入frequency 2
 * 的bucket当中, 而且还要保证那里保留了5 7 5 7的stack 顺序
 * 答案就是一边数freq, 一边直接放到相应的buckets里, 也就是说, 三个5分别放到1 2 3的freq当中
 * 可以立即为保存到当时的freq代表的bucket里面
 * 这样 最高freq的pop掉之后, 自然就会看下来, 然后又能完整的保留不同element的stack顺序, 非常精妙
*/
class FreqStack {
public:
    unordered_map<int, int> freq;
    unordered_map<int, stack<int>> buckets;
    int max_freq = 0;
    FreqStack() {
        
    }
    
    void push(int val) {
        freq[val]++;
        int f = freq[val];
        if(buckets.find(f) == buckets.end()) buckets[f] = stack<int>();
        buckets[f].push(val);
        max_freq = max(max_freq, f);
    }
    
    int pop() {
        int answer = buckets[max_freq].top();
        buckets[max_freq].pop();
        while(buckets.find(max_freq) != buckets.end() && buckets[max_freq].empty()) max_freq--;
        freq[answer]--;
        return answer;
    }
};