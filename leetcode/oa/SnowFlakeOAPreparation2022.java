package oa;

public class SnowFlakeOAPreparation2022 {
    public static int getMinCost(List<Integer> cost, List<Integer> time) {
        HashMap<Integer, Integer> mem = new HashMap<>();
        return backtrack(cost, time,mem, 0, cost.size());
    }
    private static int backtrack(List<Integer> cost, List<Integer> time, HashMap<Integer, Integer> mem, int index, int n) {
        if(index >= n) return 0;
        if(mem.containsKey(index)) return mem.get(index);
        int currentCost = cost.get(index); // current task scheduled on paid server
        int min = Integer.MAX_VALUE;
        for(int i = index + 1;i <= index + time.get(index) + 1;i++) { 
            int nextCost = backtrack(cost, time, mem, i, n);
            min = Math.min(min, nextCost + currentCost);
        }
        mem.put(index, min);
        return min;
    }
}
