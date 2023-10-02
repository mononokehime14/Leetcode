/**
 * 168. Excel Sheet Column Title
 * 比较简单 modular就行了 这里注意一开始觉得是-= 26 但是看了一下例子 仍然是/=
 * 因为每一个当前的digit都代表26 * 右边的组合的新组合 组合数量是递乘
*/
class Solution {
public:
    string convertToTitle(int columnNumber) {
        string answer;
        while(columnNumber > 0) {
            --columnNumber;
            answer = (char)('A' + columnNumber % 26) + answer;
            columnNumber /= 26;
        }
        return answer;
    }
};