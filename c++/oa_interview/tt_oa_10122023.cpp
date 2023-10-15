/**
 * 连做三周OA, 第一周借电脑被坑了, 然后题目有问题reinvite, 第二周做的OA系统没记录, 第三周再做
 * So TikTok, fuck you!
*/ 

/**
 * Count all subarrays that match this condition: first ^ end = sum of XOR rest
 * first ^ end = sum of XOR rest -> sum of the entire array = 0
 * Count all subarrays that XOR = 0 = Count all subarrays sum to x, 类two-sum, 用hashmap
 * 这里要注意, window size只考虑>=3, 这个trick要用delay hashmap update来完成
*/
#include <vector>
#include <unordered_map>
using namespace std;
int getChannelRating(vector<int>& views){
    int n = views.size();
    vector<int> prefixes(n, 0);
    prefixes[0] = views[0];
    for(int i = 1;i < n;++i) prefixes[i] = prefixes[i-1] ^ views[i]; 
    
    unordered_map<int, int> memo;
    int answer = 0;
    for(int i = 2;i < n;++i) {
        int part_b = prefixes[i] ^ 0;
        if(memo.find(part_b) != memo.end()) {
            answer += memo[part_b];
        }
        if(prefixes[i] == 0) {
            answer += 1;
        }
        memo[prefixes[i-2]]++;  // delay update
    }
    return answer;
}

/**
 * 对于每一个size 1 <= k <= N的滑动窗口, 如果一个数字在每一个窗口都出现, 就是common integer
 * 找出每一个size的最小common integer, 找不到就-1
 * 思路是记录数字出现的最大间距, 至少需要该间距的window size, 就能保证出现在每一个窗口里
*/
vector<int> miniCommonInteger(vector<int>& data) {
    int n = data.size();
    vector<int> answer(n, -1);
    vector<int> max_distances(n+1, -1);
    vector<int> last_occur(n+1, -1);
    for(int i = 0;i < n;++i) {
        int current = data[i];
        if(last_occur[current] == -1) {
            max_distances[current] = i + 1;
        }else{
            max_distances[current] = max(max_distances[current], i - last_occur[current]);
        }
        last_occur[current] = i;
    }
    for(int i = 1;i <= n;++i) {
        if(max_distances[i] != -1) {
            max_distances[i] = max(max_distances[i], n - last_occur[i]);
            if(answer[max_distances[i]-1] == -1) {
                answer[max_distances[i]-1] = i; // update minimum common integer
            }
        }
    }

    // 修正
    for(int i = 1;i < n;++i) {
        if(answer[i] == -1 && answer[i-1] != -1) {
            answer[i] = answer[i-1];
        }else if(answer[i] != -1 && answer[i-1] != -1) {
            answer[i] = min(answer[i], answer[i-1]);
        }
    }

    return answer;
}

