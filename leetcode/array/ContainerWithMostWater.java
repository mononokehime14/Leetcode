package array;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        /* 这道题的思路和接雨水很像 但是区别在于不用计算全部的面积 而是要找到一个最大的面积
         * 这样的话 其实没有比较记录峰值 只要当前的值比较就可以了
         * 然后是重中之重的双指针技巧 指针向内收缩 则除非内部的height高与当前的解 不然是不可能产生更大的面积的
         * 为什么左边比右边低要移动左边呢 因为移动左边才可能产生更大的面积 移动右边左边不动 整个面积仍然受限于左边!
         */
        int n = height.length;
        int left = 0, right = n - 1, area = 0;
        while(left < right) {
            int currentArea = (right - left) * Math.min(height[right], height[left]);
            area = Math.max(currentArea, area);
            if(height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return area;
    }
}
