#include <vector>
using namespace std;
/**
 * 605 Can Place Flowers
 * 确实是可以用贪心的思路, 如果前面后面都是0自己也是0, 就可以种
 * 但事实上我的方法是扫一遍, 看interval有多少个0, 这里的分类讨论还比较复杂, 两边都是1, 一边是1, 全0有不同的算法
 * 好处在于这是不用更改数组的, 理论速度是一样的, 但是实际速度上确实不如贪心算法
*/
class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int size = flowerbed.size();
        for(int i = 0;i < size;i++) {
            if(flowerbed[i] == 0) {
                if((i == 0 || flowerbed[i-1] == 0) && (i == size - 1 || flowerbed[i+1] == 0)) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n <= 0;
    }
    // bool canPlaceFlowers(vector<int>& flowerbed, int n) {
    //     int zero_count = 0;
    //     bool first = true;
    //     for(auto i : flowerbed) {
    //         if(i == 0) {
    //             zero_count++;
    //         }else{
    //             if(first) {
    //                 n -= zero_count / 2;
    //                 first = false;
    //             }else{
    //                 n -= zero_count % 2 == 0 ? zero_count / 2 - 1 : zero_count / 2;
    //             }
    //             zero_count = 0;
    //         }
    //     }
    //     if(first) {
    //         if(zero_count > 0){
    //             n -= zero_count % 2 == 0 ? zero_count / 2 : zero_count / 2 + 1;
    //         }
    //     }else{
    //         n -= zero_count / 2;
    //     }
    //     return n <= 0;
    // }
};