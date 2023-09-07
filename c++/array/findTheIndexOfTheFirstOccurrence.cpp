/**
 * 28. Find the Index of the First Occurrence in a String
 * 提了一个M*N的方法滚了 这道题实际上要用KMP算法进行字符串比较 可以达到M+N的复杂度
*/
class Solution {
public:
    int strStr(string haystack, string needle) {
        int m = haystack.length(), n = needle.size();
        for(int i = 0;i < m;++i){
            int i_copy = i;
            bool valid = true;
            for(int j = 0;j < n;++j){
                if(haystack[i_copy++] != needle[j]) {
                    valid = false;
                    break;
                }
            }
            if(valid) return i;
        }
        return -1;
    }
};