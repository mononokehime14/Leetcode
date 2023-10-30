/**
 * 461. Hamming Distance
 * Easy题, 基础的对XOR的理解, XOR得出不一样的bits, 数有多少1即可
*/
class Solution {
public:
    int hammingDistance(int x, int y) {
        int diff = x ^ y, answer = 0;
        while(diff) {
            answer += (diff & 1);
            diff >>= 1;
        }
        return answer;
    }
};