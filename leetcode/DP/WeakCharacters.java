import java.util.Arrays;
import java.util.Comparator;

class WeakCharacters {
    public int numberOfWeakCharacters(int[][] properties) {
        /* 整体思路基于一点: 只要找到了properties中的max, 拿max和剩下的比较就可以了 因为题目规定任何一个character大于当前的 当前的就算weak
         * 那么loop一遍不是O(N)吗为何还要sort呢, 因为loop一遍得到的max, max_a不一定max_d, 并非真正的max
         * 所以descending order, 第二个col ascending, keep max updated for 2nd col
         * 这样可以排除第一个col相同的那些character被算进去(前面的一定比自己小所以自己不可能被算成weak)
         * 和前面的max比较就可以了
        */
        Arrays.sort(properties, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
            }
        });
        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < properties.length - 1;i++){
            /* First one cannot be weak, as least has 1st col maximum */
            if(properties[i][1] < max){
                count++;
            }else if(properties[i][1] > max){
                max = properties[i][1];
            }
        }
        return count;
    }
}