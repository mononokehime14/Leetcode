#include <vector>
#include <stack>
using namespace std;

class finalPricesWithDiscount {
public:
    /**
     * 1475. Final Prices With a Special Discount in a Shop
     * 这道题一开始想不出来, 想用前缀数组prefix sum, 但是实际上右边最小的并不一定就是第一个小于当前数字的, 这个方法行不通
     * 实际上是要用单调栈, 从右往左遍历, 所有比当前price大的全部滚了, 因为往前也不需要他们了, 他们肯定不如当前的price位置前+小; 然后把当前数字push上去
     * 这个和next greater实际上是一个思路. 每个数字入栈出栈一次, 所以是O(n)
    */
    vector<int> finalPrices(vector<int>& prices) {
        int n = prices.size();
        vector<int> discount(n, 0);
        stack<int> st;
        for(int i = n - 1;i >= 0;i--) {
            while(!st.empty() && st.top() > prices[i]) st.pop();
            int price_copy = prices[i];
            if(!st.empty()) price_copy -= st.top();
            st.push(prices[i]);
            prices[i] = price_copy;
        }
        return prices;
    }
};