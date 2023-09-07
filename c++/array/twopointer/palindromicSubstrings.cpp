/**
 * 647. Palindromic Substrings
 * 使用了背向双指针 在每一个点扩散出去 就能统计出所有的回文子字符串 注意这里奇偶的处理
 * 是自己想出来的 还挺得意的
*/
using namespace std;
class Solution {
public:
    int countSubstrings(string s) {
        int answer = 0, size = s.length();
        for(int i = 0;i < size;++i) {
            answer += extend(s, i, i, size); // odd
            answer += extend(s, i, i+1, size); // even
        }
        return answer;
    }

    int extend(string s, int left, int right, int size) {
        int count = 0;
        while(left >= 0 && right < size && s[left--] == s[right++]){
            count++;
        }
        return count;
    }
};