package backtrackdfs;

public class CanIWin {
    /* 这道题的题干很有迷惑性 感觉好像是能找到一个optimal的玩法 但是实际上 我们还是只能靠暴力穷举所有的选择
     * 所以 一个可行的答案就是回溯 这里回溯必然要带备忘录 为什么呢 因为visited的情况是可能重复的 不同的开始可能在某一个时刻有同样的visited
     * 所以我们需要记录visited pattern对应的boolean 这就类似partitionKEqualSum的道理了
     * 其余的就是普通回溯 停止条件是desireTotal <= 0 这样自然赢了
     */
    boolean[] visited;
    HashMap<Integer, Boolean> mem;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;
        visited = new boolean[maxChoosableInteger];
        mem = new HashMap<>();
        return backtrack(desiredTotal);
    }
    private boolean backtrack(int desiredTotal) {
        if(desiredTotal <= 0) return false; // lose 第一次进入的时候不会触发
        int visitedB = format(visited);
        // System.out.println(visitedB);
        if(mem.containsKey(visitedB)) return mem.get(visitedB); 
        for(int i = 0;i < visited.length;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            boolean nextRound = backtrack(desiredTotal - i - 1);
            if(!nextRound) { // next player surely lose
                mem.put(visitedB, true);
                visited[i] = false;
                return true;
            }
            visited[i] = false;
        }
        mem.put(visitedB, false);
        return false;
    }
    private int format(boolean[] visited) {
        int answer = 0;
        for(boolean v: visited) {
            answer = answer * 10;
            answer += v ? 1 : 0;
        }
        return answer;
        // int num = 0;
        // for(boolean b: visited){
        //     num <<= 1;
        //     if(b) num |= 1;
        // }
        // return num;
    }
}
