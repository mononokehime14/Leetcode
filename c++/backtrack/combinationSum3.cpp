/**
 * 216. Combination Sum III
 * 和combination的套路是一样的 combination要使用start 画出树就知道 这会防止重复 1 2 3和2 1 3不可以同时出现
 * 这里只是单纯的停止的时候判断一下sum有没有加到就行了
*/
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> answer;
        vector<int> track;
        backtrack(answer, track, 1, k, n);
        return answer;
    }
    void backtrack(vector<vector<int>>& answer, vector<int>& track, int start, int k, int n) {
        if(n < 0) return; // earyly stop, 1 - 9都是正数 加超了不可能回来
        if(track.size() == k) {
            if(n == 0) answer.push_back(track); // 草这里忘记return了但是好像没事
        }
        for(int i = start;i <= 9;++i) {
            track.push_back(i);
            backtrack(answer, track, i+1, k, n-i);
            track.pop_back();
        }
    }
};