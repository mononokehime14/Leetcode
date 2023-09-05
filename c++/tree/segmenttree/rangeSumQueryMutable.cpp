#include <vector>
using namespace std;
/**
 * 307. Range Sum Query - Mutable
 * 这里学习了线段树的技巧 代码可以用于线段树的模版
 * 首先我们发现这道题和前面的前缀数组的区别就是update 需要动态更改一段区间
 * 那么我首先的反应就是差分数组 但是差分数组动态更新一段区间是很快 但是query的时候 就需要从头扫一遍 因为台阶都是累积的
 * 这对于答案只需要一个final的没问题 但是这道题目中每次query都从头扫一遍 复杂度就爆炸了
 * 
 * 线段树原理很简单 一个数组可以有n!个interval 线段树用平衡二叉树把区间记录下来 比如[0...9]左右child分别为[0...4][5...9]
 * 依次往下. n的奇偶可能导致leaf不在同一层 (线段树不是完全二叉树, 最后一层可能不全, 和堆是一样的), 但是leaf一定都在最后两层里面
 * 这样 我们就完全套用堆的C++实现 使用vector 当前pivot是i则left child 2*i+1, right child 2*i+2, parent (i-1)/2
 * 根据二叉树节点数量的计算方法 第一层2**0, 第二层2**1, 最后一层2**(h-1), h为层高, 那么把他们都加起来就相当于binary表达式里全部填1
 * 值就是2**h - 1 我们可以大概估计需要的节点的上限是2n - 4n (取决于leaf是一层搞定还是两层)
 * build query和update的逻辑是相同的 tree[index]代表该节点的区间的前缀和 left right index则理解为区间 merge的逻辑可以根据题目更改
*/
class NumArray {
public:
    vector<int> tree;
    vector<int> src;
    int size;
    NumArray(vector<int>& nums) {
        size = nums.size();
        src = nums;
        nums[0] = 10000;
        cout << src[0] << endl; // an experiment that C++ vector assignment does deep copy
        tree.resize(4 * size);
        buildSegmentTree(0, 0, size - 1);
    }

    void buildSegmentTree(int treeIndex, int left, int right) {
        if(left == right) {
            tree[treeIndex] = src[left];
            return;
        }
        int leftChild = treeIndex * 2 + 1;
        int rightChild = treeIndex * 2 + 2;
        int m = left + (right - left) / 2;
        buildSegmentTree(leftChild, left, m);
        buildSegmentTree(rightChild, m+1, right);
        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }
    
    void update(int index, int val) {
        src[index] = val;
        updateSegmentTree(0, 0, size-1, index, val);
    }

    void updateSegmentTree(int treeIndex, int left, int right, int index, int value) {
        if(left == right) {
            tree[treeIndex] = value;
            return;
        }
        int leftChild = treeIndex * 2 + 1;
        int rightChild = treeIndex * 2 + 2;
        int m = left + (right - left) / 2;
        if(index <= m) { // left side needs update
            updateSegmentTree(leftChild, left, m, index, value);
        }else{
            updateSegmentTree(rightChild, m+1, right, index, value);
        }
        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }
    
    int sumRange(int left, int right) {
        return querySegmentTree(0, 0, size-1, left, right);
    }

    int querySegmentTree(int treeIndex, int left, int right, int query_left, int query_right) {
        if(query_left == left && query_right == right) { // correct interval
            return tree[treeIndex];
        }
        int leftChild = treeIndex * 2 + 1;
        int rightChild = treeIndex * 2 + 2;
        int m = left + (right - left) / 2;
        if(query_right <= m) { // only left
            return querySegmentTree(leftChild, left, m, query_left, query_right);
        }else if(query_left > m) { // only right
            return querySegmentTree(rightChild, m+1, right, query_left, query_right);
        }else{ // both, need merge
            return querySegmentTree(leftChild, left, m, query_left, m) +
                querySegmentTree(rightChild, m+1, right, m+1, query_right);
        }
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */