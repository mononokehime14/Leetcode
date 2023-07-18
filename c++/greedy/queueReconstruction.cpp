#include <vector>
#include <algorithm>
using namespace std;
/**
 * 406 Queue Reconstruction by Height
 * 这道题我一开始想的贪心思路是按照前面有多少>=自己的人从小排序, 然后平局则height小的在前面, 这样不会实际上影响后面的
 * 我一开始觉得合理, 如果7,0和8,1在一起, 这是不可能的, 因为8比7大的情况下, 8是不可能在7前面的
 * 所以我们只要贪心的把>=自己的人这个维度需要的少的人先排好, 那么似乎可以保证排序正确
 * 但是事实上, 这可能是有点问题, 而且主要是给后面的循环带来了很大的麻烦, 每一个都要检查>=的数量后插入
 * 正确答案是按照height从高到低排序, 平局则>=需求小的在前面, 这很合理因为7,0和7,1就应该是7,1在后面
 * 而按照height排序的好处就在于插入不用考虑任何事情, 比如7,0 7,1现在插入6,1 插在1的位置就可以了因为前面插入的必定都是>=的
 * 6,2也完全没问题 而7,0 7,1的正确性完全不受影响 因为6是比他们矮的
 * 这就是贪心的策略的立足点, 先排高的, 局部的order就会变成全局的order
*/
class Solution {
public:
    vector<vector<int>> reconstructQueue(vector<vector<int>>& people) {
        sort(people.begin(), people.end(), [](vector<int> &a, vector<int> &b){
            if(a[0] == b[0]) return a[1] < b[1];
            return a[0] > b[0];
        });
        vector<vector<int>> answer;
        for(const auto& p : people) {
            answer.insert(answer.begin() + p[1], p);
        }
        return answer;
    }
};