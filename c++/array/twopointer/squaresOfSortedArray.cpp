#include <vector>
#include <cmath>
using namespace std;

class squaresOfSortedArray {
public:
    /**
     * 977. Squares of a Sorted Array 
     * 双指针, 只要找到第一个不为负数的位置, 一个指针向左, 一个向右, 就像merge sorted array一样简单了.
     * 这里有一个C++的知识, 一开始output我用了reserve, 但是实际上reserve只allocation了memory, 没有initialized
     * 故此size也不会受到影响, 极度怀疑leetcode的checker是用size循环检查的, size会=0
     * 可以用resize, 就会影响size, 这是一个会initialize的方法. 当然, 最后直接用initialize的方法就好了.
     * */
    vector<int> sortedSquares(vector<int>& nums) {
        int left = 0, index = 0, n = nums.size(), right = n;
        vector<int> output(n);
        for(int i = 0;i < n;i++) {
            if(nums[i] >= 0) {
                right = i; 
                break;
            }
        }
        left = right - 1;
        while(left >= 0 && right < n) {
            int square1 = pow(nums[left], 2);
            int square2 = pow(nums[right], 2);
            if(square1 <= square2) {
                output[index++] = square1;
                left--;
            }else{
                output[index++] = square2;
                right++;
            }
        }
        while(left >= 0) {
            output[index++] = pow(nums[left--], 2);
        }
        while(right < n) {
            output[index++] = pow(nums[right++], 2);
        }
        return output;
    }
};