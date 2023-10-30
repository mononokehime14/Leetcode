/**
 * 638. Shopping Offers
 * 思路是很好想的, 选择一个special, 然后递归下去. 带个备忘录的状态也很好想, 事实证明将多个状态转化成
 * string的实现方式应该是合理的. 这里有一个base case的细节, base case并非只是need全部为0, 而是没有任何一个special能apply了
 * 也就是说, 剩下的要单买. 这个情况就出现在总需求金额少于任何一个special offer的价格的时候.
*/
class Solution {
public:
    unordered_map<string, int> memo;
    int shoppingOffers(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
        return backtrack(price, special, needs);
    }

    int backtrack(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
        string need_s = "";
        for(int n : needs) need_s += to_string(n) + " ";
        if(memo.find(need_s) != memo.end()) return memo[need_s];

        int total_price = 0, n = needs.size();
        for(int i = 0;i < n;++i) total_price += needs[i] * price[i];

        for(int i = 0;i < special.size();++i) {
            int special_price = special[i][n];
            if(special_price < total_price) {
                bool valid = true;
                for(int j = 0;j < n;++j) {
                    needs[j] -= special[i][j];
                    if(needs[j] < 0) {
                        //复原
                        for(int k = 0;k <= j;k++) needs[k] += special[i][k];
                        valid = false;
                        break;
                    }
                }
                if(!valid) continue;
                int candidate = backtrack(price, special, needs) + special_price;
                total_price = min(total_price, candidate);

                // backtrack
                for(int j = 0;j < n;++j) needs[j] += special[i][j];
            }
        }
        memo[need_s] = total_price;
        return total_price;
    }
};