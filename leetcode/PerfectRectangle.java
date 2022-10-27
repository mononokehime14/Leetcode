public class PerfectRectangle {
    /* 391
     * 这是一道比较奇怪又困难的题 思路非常难想 感觉很复杂 又不属于寻常的算法题类型
     * 要检查一些小矩形能够刚好组成一个大矩形 可以从两个方面来判断 首先是面积 其次是边界
     * 面积很好理解 面积之和是否等同于最后设想中的大矩形 大矩形由最小的左下角和最大的右上角组成 这里可以检查重叠的问题
     * 剩下的问题是不规整的矩形怎么办 这里有一个检测方法 就是检测顶点 正常来讲 矩形有四个顶点 而偶数个的矩形拼接在一起 如果某个顶点
     * 是相同的 那么最终这个顶点会消失 而奇数个则会产生/保留这个顶点 那么按照这个原理 我们可以检测distinct顶点数 消弭偶数的 最终剩下4个顶点就ok
     * 到这里还有细节 如果部分矩形是重叠的 那么他们的顶点消掉了但实际上却还在 所以还有一层检测 就是检查最后剩下的四个顶点 一定要和我们求出来的左下角和右上角match
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int areaSum = 0, blx = Integer.MAX_VALUE, bly = Integer.MAX_VALUE;
        int urx = Integer.MIN_VALUE, ury = Integer.MIN_VALUE;
        HashSet<String> mem = new HashSet<>();
        for(int[] i: rectangles) {
            blx = Math.min(blx, i[0]);
            bly = Math.min(bly, i[1]);
            urx = Math.max(urx, i[2]);
            ury = Math.max(ury, i[3]);
            areaSum += (i[2] - i[0]) * (i[3] - i[1]);
            String temp = i[0] + "," + i[1];
            if(mem.contains(temp)) mem.remove(temp);
            else mem.add(temp);
            temp = i[0] + "," + i[3];
            if(mem.contains(temp)) mem.remove(temp);
            else mem.add(temp);
            temp = i[2] + "," + i[1];
            if(mem.contains(temp)) mem.remove(temp);
            else mem.add(temp);
            temp = i[2] + "," + i[3];
            if(mem.contains(temp)) mem.remove(temp);
            else mem.add(temp);
        }
        if(areaSum != (urx - blx) * (ury - bly)) return false;
        if(mem.size() != 4) return false;
        String temp = urx + "," + ury;
        if(!mem.contains(temp)) return false;
        temp = urx + "," + bly;
        if(!mem.contains(temp)) return false;
        temp = blx + "," + bly;
        if(!mem.contains(temp)) return false;
        temp = blx + "," + ury;
        if(!mem.contains(temp)) return false;
        return true;
    }
}
