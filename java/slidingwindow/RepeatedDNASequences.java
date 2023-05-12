package slidingwindow;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        /*
         * 此滑动窗口之术也.滑动窗口和loop+储存的不同,在于其保持一个common的部分.每次滑动, 可
         * 只对common部分作出改动,比如本题,只要移调最高位填上最低位即可,然后检查是否hashset中已有.
         * 注意output也保存在hashset里的原因是答案不能有duplicate.
         */
        HashSet<String> output = new HashSet<>();
        HashSet<Integer> mem = new HashSet<>();
        int[] si = new int[s.length()];
        for(int i = 0;i < si.length;i++){
            switch(s.charAt(i)){
                case 'A':
                    si[i]= 0;
                    break;
                case 'C':
                    si[i] = 1;
                    break;
                case 'G':
                    si[i] = 2;
                    break;
                case 'T':
                    si[i] = 3;
                    break;
            }
        }
        int common = 0;
        int pop = (int) Math.pow(4, 9);
        if(si.length >= 10){
            for(int i = 0;i < 10;i++){
                common = common * 4 + si[i];
            }
            mem.add(common);
        }
        for(int r = 10;r < si.length;r++){
            common = (common - pop * si[r-10]) * 4 + si[r];
            if(mem.contains(common)){
                output.add(s.substring(r-9, r+1));
            }else{
                mem.add(common);
            }
        }
        return new LinkedList<>(output);
    }
}
