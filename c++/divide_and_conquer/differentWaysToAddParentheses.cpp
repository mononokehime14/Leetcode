#include <vector>
using namespace std;
/**
 * 241. Different Ways to Add Parentheses
 * C++版本的答案直接抄录的 这里主要是发现原来的Java解有一些些改进的地方
 * 总体的思路还是分而治之 时间复杂度可能不是2 ** n, 而是2**logn 因为层高是logn 这个和不带dp的斐波那契数列是不一样的
 * 然后是memorization, 应该是有重叠子问题的, 自上而下就是memorization, 自下而上就是dp
 * memorization实际效果不大的原因是因为n被限制 实际上带memorization的时间复杂度应该在n平方 因为每个组合只算一次
*/
vector<int> diffWaysToCompute(string input) {
    vector<int> ways;
    for (int i = 0; i < input.length(); i++) {
       char c = input[i];
        if (c == ’+’ || c == ’-’ || c == ’*’) {
            vector<int> left = diffWaysToCompute(input.substr(0, i));
            vector<int> right = diffWaysToCompute(input.substr(i + 1));
            for (const int & l: left) {
                for (const int & r: right) {
                    switch (c) {
                        case ’+’: ways.push_back(l + r); break;
                        case ’-’: ways.push_back(l - r); break;
                        case ’*’: ways.push_back(l * r); break;
                    }    
                }
            }
        }
    }
    if (ways.empty()) ways.push_back(stoi(input));
    return ways;
}