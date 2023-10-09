/**
 * 373. Find K Pairs with Smallest Sums
 * 这道题一开始能想到的思路是双指针比较 然后分类讨论 如果nums[i+1]+nums2[j] < nums1[i] + nums2[j+1]
 * 那就i前进 但是这个是有问题的 因为一个指针到头了以后 k还没到的话 还要从头再考虑那些被跳过的 那这个就不好操作了
 * C++书上使用的方法是对于每一个nums1的值, 有一个相当于nums2的指针, 每次扫一遍nums1找出最小的组合
 * 然后相应的那个值的nums2上的指针前进一下, 但是这个会TLE
 * 所以这里只能使用PQ 将一开始的想法稍微优化一下 与其我们指针跳过, 不如两种可能都赛到PQ里
*/
class Solution {
public:
    vector<vector<int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        vector<vector<int>> ret;
        auto comp = [&nums1, &nums2](const pair<int, int>  &a, const pair<int, int> &b) {
            return nums1[a.first] + nums2[a.second] > nums1[b.first] + nums2[b.second];
        };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> pq(comp);
        pq.push({0, 0});
        while(pq.size() && k--) {
            auto idx = pq.top();
            pq.pop();
            int i = idx.first, j = idx.second;
            ret.push_back(vector<int>({nums1[i], nums2[j]}));
            if(i == 0 && j+1 < nums2.size())pq.push({i, j+1});
            if(i+1 < nums1.size())pq.push({i+1, j});
        }
        return ret;
        // int m = nums1.size(), n = nums2.size(), first = 0;
        // vector<int> second(m, 0);
        // vector<vector<int>> answer;
        // k = min(k, m * n);
        // while(k > 0) {
        //     int index = 0;
        //     long local_min = LONG_MAX;
        //     for(int i = 0;i < m;++i) {
        //         if(second[i] < n && nums1[i] + nums2[second[i]] < local_min) {
        //             index = i;
        //             local_min = nums1[i] + nums2[second[i]];
        //         }
        //     }
        //     answer.push_back(vector<int>{nums1[index], nums2[second[index]]});
        //     second[index]++;
        //     --k;
        // }
        // return answer;
    }
};