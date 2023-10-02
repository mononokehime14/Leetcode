/**
 * 260. Single Number III
 * 我们知道single number的检查XOR操作 a ^ a = 0, 0 ^ b = b
 * 现在有两个single number, 那么对数组的XOR将得到两个数字的XOR 而这实际上代表两者bits上的difference
 * 然后我们对这个diff做取最右边1的操作 这样我们之后能够区分两个数字
 * 这个获取最右边的操作利用了-x = ~x + 1, 这样我们会发现除了最右边的1 右边和左边都是清成0的
 * 如此一来 x&(-x)就只有最右边的1存在
 * 现在我们再xor一遍 这一次我们可以区分两个数字了 而每个数字分别在它的那一组里XOR 最后分别得到结果
 * 本质上 这是分化了数列 然后再次利用XOR的方法得到解
*/
class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long diff = 0;
        for(int i : nums) diff ^= i;
        diff = diff & (-diff);
        int a = 0, b = 0;
        for(int i : nums) {
            if(i & diff) {
                a ^= i;
            }else{
                b ^= i;
            }
        }
        return vector<int>{a, b};
    }
};