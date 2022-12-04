package math;

public class LargestNumber {
    /* 179
     * 毕竟还是给我搞出来了 而且和后来看的答案是一样的思路 应该是最优解
     * 只要认识到需要按照从左到右位置上的数字从大到小排序就行了
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        boolean allzero = true;
        for(int i : nums) {
            if(i != 0) {
                allzero = false;
                break;
            }
        }
        if(allzero) return "0";
        String[] numsS = new String[n];
        for(int i = 0;i < n;i++) numsS[i] = String.valueOf(nums[i]);
        Arrays.sort(numsS, new Comparator<String>(){
            public int compare(String a, String b) {
                int n = a.length(), m = b.length(), index = 0;
                String ab = a + b;
                String ba = b + a;
                while(index < n + m) { // 其实后面的这个比较可以用ab.compareTo(ba)
                    if(ab.charAt(index) > ba.charAt(index)) {
                        return -1;
                    }else if(ab.charAt(index) < ba.charAt(index)) {
                        return 1;
                    }
                    index++;
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s: numsS) sb.append(s);
        return sb.toString();
    }
}
