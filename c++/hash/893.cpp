/**
 * 893. Groups of Special-Equivalent Strings
 * 非常巧妙的一个方法, 对于两个words, 怎么才能知道他们可以transfer呢
 * 这实际上很类似共同祖先或者是Union Find并查集的某种思路, 对于a和b,
 * 奇偶单拎出来, 加上分隔符拼在一起. 
 * 因为每次swap只会奇数char之间或者偶数之间, 奇数位置上的char肯定不会变到偶数上
 * 如果a和b能够置换, 奇数位置上的这些字母的集合肯定是一样的, 偶数位置同理
*/
class Solution {
public:
    int numSpecialEquivGroups(vector<string>& words) {
        unordered_set<string> hash;
        for (auto &s: words) {
            string l, r;
            for (int i = 0; i < s.length(); ++i) {
                l += s[i];
                if (i < s.length()) r += s[++i];
            }
            sort(l.begin(), l.end());
            sort(r.begin(), r.end());
            l += '#' + r;
            hash.insert(l);
        }
        return hash.size();
    }
};