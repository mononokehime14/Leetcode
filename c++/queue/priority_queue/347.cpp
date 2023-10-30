/**
 * 347. Top K Frequent Elements
 * 这道题naive思路还是很好想的, 无非就是count, 然后维持一个size为k的最大堆
 * 世宇TT第一轮碰到的题目额外要求了大数据, 那可能就是用字典树代替hashmap
*/
class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> count;
        for(int n : nums) count[n]++;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq; //最小堆
        for(auto & [key , value] : count) {
            pq.emplace(value, key); // emplace is similar to push, but without unnecessary copy
            if(pq.size() > k) {
                pq.pop();
            }
        }
        vector<int> answer(k);
        for(int i = k - 1;i >= 0;--i) {
            answer[i] = pq.top().second;
            pq.pop();
        }
        return answer;
    }
};