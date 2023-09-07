using namespace std;
/**
 * 696. Count Binary Substrings
 * 想不到的easy题 我第一开始想套用palindrome substring的背向双指针方法 但是这样是会TLE的 最坏复杂度应该是n平方
 * 然后我又在想dp 但是dp似乎并没有子问题存在 知道ij中间的binary substrings数量并不能帮助我们计算ij
 * 答案是考虑以i结尾的binary substring 如果i是1 前面有x个连续的1 那么合法的substring只有一个就是前面也连续有x+1个0
 * 那么我们就one pass计算consecutive 1 or 0, 数字变化的时候换
*/
class Solution {
public:
    int countBinarySubstrings(string s) {
        int prev = 0, cur = 1, answer = 0;
        for(int i = 1;i < s.length();i++) {
            if(s[i] == s[i-1]) { // cur累积
                ++cur;
            }else{ // 数字变换 新cur重开 之前cur变为prev
                prev = cur;
                cur = 1;
            }
            if(prev >= cur) ++answer;
        }
        return answer;
    }
};