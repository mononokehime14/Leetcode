/**
 * 436. Find Right Interval
 * 我本来想用bucket sort, 就是把left interval用list buckets记录
 * 这样对于一个right interval, 直接去找list里的next就行. 但是发现不是很好实现, right interval
 * 不存到list里就实现不了O(1)的如此操作, 要是存到list里, 还得判断next是left interval还是right interval 这就也不算严格的O(1)
 * 时间复杂度不好判断了
 * 正确答案是left interval普通sort 不用buckets sort
 * 然后right interval去二分查找就行了 first greater
*/
class Solution {
public:
    vector<int> findRightInterval(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<pair<int, int>> left_intervals(n);
        for(int i = 0;i < n;++i) left_intervals[i] = make_pair(intervals[i][0], i);
        sort(left_intervals.begin(), left_intervals.end(), [](pair<int, int> & a, pair<int, int> & b) {
            return a.first < b.first;
        });
        vector<int> answer(n, -1);
        for(int i = 0;i < n;++i) {
            int target = intervals[i][1];
            int left = 0, right = n - 1;
            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(left_intervals[mid].first < target) {
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            if(left != n) answer[i] = left_intervals[left].second;
        }
        return answer;
    }
};