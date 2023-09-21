/**
 * 6. Zigzag Conversion
 * 找出offset跳转的规律就行 分三种情况讨论
*/
class Solution {
public:
    string convert(string s, int numRows) {
        int size = s.length();
        if(numRows == 1) return s;
        int interval = (numRows - 1) * 2;
        string answer;
        //first row
        for(int i = 0;i < size;i+=interval) {
            answer += s[i];
        }
        //middle rows
        for(int row = 1;row < numRows - 1;++row) {
            for(int i = row, j = 0;i < size;j+=interval, i=j-i) {
                answer += s[i];
            }
        }
        // final row
        if(numRows > 1) {
            for(int i = numRows - 1;i < size;i+=interval) {
                answer += s[i];
            }  
        }
        return answer;
    }
};