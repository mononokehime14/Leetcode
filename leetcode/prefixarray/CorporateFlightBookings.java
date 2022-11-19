package prefixarray;

public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        /* 差分数组 可以理解成 数组不记录每个位置具体的数值 而是记录某种台阶
         * 比如start -> end我要在中间填一样的数字 那么可以想象成从start上升到end + 1下降的一个阶梯
         * 只由start和end+1负责记录这个阶梯 我们最后loop的时候在这一个叠加上一个就能获得整个数组的全部数值
         */ 
        int[] output = new int[n];
        for(int[] i: bookings) {
            int start = Math.min(i[0], i[1]) - 1;
            int end = Math.max(i[0], i[1]) - 1;
            output[start] += i[2];
            if(end + 1 < n) output[end + 1] -= i[2];
        }
        for(int i = 1;i < n;i++) {
            output[i] += output[i-1];
        }
        return output;
    }
}
