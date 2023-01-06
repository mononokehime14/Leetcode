package design;

public class RandomPickWithBlacklist {
    /* 710
     * 这道题的思路在于 要把黑名单里的数字 映射到一个正常的数字那里 这样 我们就能获得常数时间的pick
     * 一开始的思路是把黑名单的数字全部换到数组后面 这样每次pick只要在前面random就行了 但是问题是没办法保持那么长的数组
     * 在第66个testcase时就用了最长的n 导致memory limit超出
     * 所以只能用map 把一个黑名单映射到一个不是黑名单的正常数字那里
     */
    private HashMap<Integer, Integer> blackToNormal;
    private int size;
    private Random rand = new Random();
    public Solution(int n, int[] blacklist) {
        blackToNormal = new HashMap<>();
        size = n - blacklist.length;
        for(int i : blacklist) blackToNormal.put(i, i);
        int last = n - 1;
        for(int i : blacklist) {
            if(i >= size) continue;
            while(blackToNormal.containsKey(last)) last--;
            blackToNormal.put(i, last);
            last--;
        }
    }
    
    public int pick() {
        int rd = rand.nextInt(size); // [0, size -1]
        if(blackToNormal.containsKey(rd)) return blackToNormal.get(rd);
        return rd;
    }
}
