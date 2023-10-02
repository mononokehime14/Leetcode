/**
 * 274. H-Index
 * 这里我的第一反应是二分搜索 因为我们知道h的最大值可能是citations的数量n
 * 最小值可能是0 那么好像可以二分搜索 但是这里的问题在于 没有一个比较快的判断一个candidate
 * 是否正确的方法 对于一个h 好像只能loop citations来检查 那这使得复杂度为nlogn
 * 正确的答案是使用桶排序 桶排序就是把值分到桶里(比如0-10, 10-20) 然后桶内自己排序
 * 严格意义来讲 这和radix sort一样并非O(N) 并不stable 取决于桶内有多少element 是否平均分配
 * anyway在这里 我们只要反过来循环buckets 当h的数量大于对应的citation即可return
 * 这里把>n的放到n的桶中不会出现问题 这是因为h的最大值就是n 不可能有n+1的papers被cite n+1次
*/
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size(); // h max n, min 0
        if(!n) return 0;
        vector<int> buckets(n+1, 0); // 桶排序
        for(int i : citations) {
            if(i > n) buckets[n]++; //超过n的放在一个桶里
            else buckets[i]++;
        }
        int papers = 0;
        for(int i = n;i >= 0;--i) {
            papers += buckets[i];
            if(papers >= i) return i;
        }
        return 0;
    }
};