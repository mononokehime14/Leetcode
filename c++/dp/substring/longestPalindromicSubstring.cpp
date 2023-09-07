#include <vector>
using namespace std;
/**
 * 5. Longest Palindromic Substring
 * 这道题的基础DP可以参照palindromic subsequence的思路 subsequence是dp[i][j]是从i到j的最长子序列
 * 但是substring里没有必要 要累加状态的话 dp[i][j]可以是从i到j是否是回文 最后再扫一遍就能得到最大值
 * 至于迭代方向 我们就要看状态转移 dp[i+1][j-1] 如此则必然从下至上 从左至右 base case对角线 同时不能让i > j
 * 
 * O(N)的方法叫做Manacher算法 这个算法首先预处理空档加入字符 这样所有的回文都是奇数长度 必然分类讨论
 * 然后一维dp dp[i]代表以i为中心的最长回文的半径(i前面多少个elements) 另外维护一个最远的有边界r
 * 然后分类讨论 如果i >= r 没什么好说的我们只能扩展指针找回文
 * 如果i < r 可能i的回文完全囊括在r所代表的回文(中心c)里 这样的话我们根据r和c计算一个mirror
 * 也可能i的回文从r内开始但是超过r 如此我们从r-i开始 还需要做扩展指针找回文
 * 这样两种情况下 我们就可以先得到i的回文必然是min(r-i, dp[mirror]) 
 * 利用了动态规划 尽可能的减少了扩展指针运算的复杂度
 * 我们在不断维护r的情况下 应该不需要对r内再做指针扩展 故此整体时间复杂度是O(N)
*/
class Solution {
public:
    string longestPalindrome(string s) {
        string new_s = preProcess(s);
        return manacher(new_s, s);
    }
    
    string preProcess(string s){
        int len = s.length();
        string new_s = "|";
        for(const char c : s) {
            new_s = new_s + c + "|";
        }
        return new_s;
    }

    string manacher(string s, string original_s){
        int size = s.length(), max_p = 1, r = 0, c = 0, max_p_center = 0;
        vector<int> dp(size, 0);
        for(int i = 1;i < size;i++) {
            // 找出 i 为中心的回文串半径 p[i]
            int local = 0;
            if (i < r) {
                // 目标回文串的中心在 r 左边时
                // 最大化吸收已有信息
                
                int mir = c - (i - c); // j 是 i 关于 c 对称的位置
                // p[i] 至少为下面二者最小值, 分别代表完全囊括(镜像)和cross(之后还需要扩展)
                local = min(dp[mir], r - i);
            } 

            // 左右进行扩展，探测剩余长度
            int left = i - local - 1;
            int right = i + local + 1;

            while (left >= 0 && right < size && s[left] == s[right]) {
                left--;
                right++;
                local++;
            }
            // 更新 c 和 r
            if (i + local > r) {
                r = i + local;
                c = i;
            }
            // 更新 最大值
            if (max_p < local) {
                max_p = local;
                max_p_center = i;
            }
            dp[i] = local;
        }
        return original_s.substr((max_p_center-max_p)/2, max_p);
    }

    string longestPalindrome_n2DP(string s) {
        int m = s.size();
        vector<vector<bool>> dp(m, vector<bool>(m, false));
        for(int i = 0;i < m;++i) dp[i][i] = true;
        int max_str = 1;
        string answer = s.substr(0, 1);
        for(int i = m-2;i >= 0;--i) {
            for(int j = i+1;j < m;++j) {
                if(i+1 > j-1) { // 2 char
                    dp[i][j] = s[i] == s[j];
                }else{
                    dp[i][j] = s[i] == s[j] && dp[i+1][j-1];
                }
                if(dp[i][j] && j - i + 1 > max_str) {
                    max_str = j - i + 1;
                    answer = s.substr(i, max_str);
                }
            }
        }
        return answer;
    }
};