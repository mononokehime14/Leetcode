/**
 * 888. Fair Candy Swap
 * 虽然是easy题, 但是自己做出来还是很开心, 思路还挺天才的
 * 天之道, 损有余而补不足 sumA - a + b = sumB - b + a
 * sumA - 2a = sumB - 2b
 * a = (sumA - sumB + 2b) / 2
 * 所以我们用hashset记录一边就可以了
*/
class Solution {
public:
    vector<int> fairCandySwap(vector<int>& aliceSizes, vector<int>& bobSizes) {
        unordered_set<int> count;
        int sumA = 0, sumB = 0;
        for(int i : aliceSizes) {
            count.insert(i);
            sumA += i;
        }
        for(int i : bobSizes) sumB += i;
        for(int i : bobSizes) {
            int a = sumA - sumB + 2 * i;
            if(a % 2 == 0 && count.find(a/2) != count.end()) return vector<int>{a/2, i};
        }
        return vector<int>{-1, -1}; // not possible
    }
};