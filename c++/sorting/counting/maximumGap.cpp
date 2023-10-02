/**
 * 164. Maximum Gap
 * 朴实无华的需要sort一遍 然后找出maximum gap 没有别的trick
 * 这里如何sort是一个关键 需要linear sort 那么只能是counting类型的
 * 但是这里虽然肯定是正整数 但是数值可能很大 那么这时候可以用radix sort 原理是从最右边开始 按照每一个digit排序
 * 如此一直到所有digits(看最大值得到) 我们就得到了完整的排序 而在每一次pass中
 * 由于digits range一定是0-10 可以用counting sort得到每一个digit的appearance 然后使用prefix sum得到其在这一个pass排完序之后的数列里的位置
 * 这个和PP里的exclusive scan是同理的
 * 然后再填入intermediate array 但是这一步填入的时候为什么需要倒序 我还是并不是很明白
 * 
 * radix sort是O(N * k) 其中k是最大digit数量 可以看出如果数字非常大或者差异很大 那么其实效率并不高
 * space是O(N + K) k用于couting, N用于intermediate
*/
int maximumGap(vector<int>& nums) {
        radix_sort(nums);
        int answer = 0;
        for(int i = 1;i < nums.size();++i) answer = max(answer, nums[i] - nums[i-1]);
        return answer;
    }

    void radix_sort(vector<int>& nums) {
        int n = nums.size();
        if(n < 2) return;
        int max_val = *max_element(nums.begin(), nums.end());
        int radix = 10, exp = 1;
        vector<int> intermediate(n);
        while(max_val / exp > 0) {
            vector<int> counting(radix, 0);
            for(int i = 0;i < n;++i) counting[(nums[i] / exp) % 10]++;
            for(int i = 1;i < radix;++i) counting[i] += counting[i-1];
            for(int i = n-1;i >= 0;--i) {
                --counting[(nums[i] / exp) % 10]; //比如digit 0有一个 那么我们应该先减1去index 0
                intermediate[counting[(nums[i] / exp) % 10]] = nums[i];//这一步会覆盖 所以需要intermediate vector
            }
            for(int i = 0;i < n;++i) nums[i] = intermediate[i];
            exp *= 10;
        }
        // for(int i = 0;i < n;++i) cout << nums[i] << " ";
        // cout << endl;
    }