/**
 * 223. Rectangle Area
 * 一直以来我检查rectangle overlap的方法就是边界比较 a最大的x是否 > b的, 诸如此类, 比较起来比较复杂
 * 但是这里突然发现有一个非常优雅的方法 可以推定最小的边界 这样一下就能得到overlapping area
*/
class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int total = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        int right = ax2 < bx2 ? ax2 : bx2,
            left = ax1 > bx1 ? ax1 : bx1,
            top = ay2 < by2 ? ay2 : by2,
            bottom = ay1 > by1 ? ay1 : by1;
        int ox = right - left, oy = top - bottom;
        return ox > 0 && oy > 0 ? total - ox * oy : total;
    }
};