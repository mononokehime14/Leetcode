#include <vector>
using namespace std;
/**
 * 769. Max Chunks To Make Sorted
 * 一开始我觉得可以记录max max更新的时候说明我们可以分割一次 但是这个思路是错的
 * 这只能记录新的上升sequence 但是如果其中出现比前面小的数字 那么前面的分割实际上就是不对的
 * 我们注意到range是给定的 又需要分割好的ranges递增 故此分割出来的range实际上值都是确定的 
 * 比如第一个分割是8个数字 必然就是0-7 如此一来 我们可以保持一个max 然后去和index比较 到位了就是能分割
*/
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
       int answer = 0, max_a = -1, size = arr.size();
       for(int i = 0;i < size;i++) {
           max_a = max(max_a, arr[i]);
           if(max_a == i) answer++;
       }
       return answer; 
    }
};