/**
 * 275. H-Index II
 * 非常妙 之前1的时候 我就在想二分查找了 但是苦于没有快速检查h是否正确的方法
 * 这里直接citations是排好序的 这样我们知道h index最高n 最低0 开始二分查找
 * 对于一个mid 我们考虑n-mid 这以为着当前和后面有多少paper 然后citations[mid]可以和这个数量相比
 * 假设citation是6 数量是5 这就是说有5个papers至少是6 citations 这当然是满足条件的
 * 如果citation多 这证明可能是正确解 这时候我们right = mid - 1 为什么呢 因为可以尝试更大的h-index
 * 也就是更大的n-mid
 * 如果citation少 不是正确解 left = mid + 1 这样按照我们的经验 结果就取决于left了
*/
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size(), left = 0, right = n - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(citations[mid] == n - mid) {
                return n - mid;
            }else if(citations[mid] < n - mid) { // not possible region
                left = mid + 1;
            }else{
                right = mid - 1;
            }
            // cout << citations[mid] << " " << left << " " << right << endl;
        }
        return n - left; //甚至不用检查left == n 因为这样就证明是0 没有合法的
    }
};