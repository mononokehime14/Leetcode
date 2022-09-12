package bfs;
import java.util.*;
public class OpenTheLock {
    /* 基础的BFS,需要发现这是一个最短距离问题. 这种问题和DP问题的区别就在于找到最优解, DP不是找到最优解的算法
     * 而是更加适合最值或者是否可行类的题目.
     * 优化: 类似双指针, 可以双向BFS!从起点和终点一起开始有交集的时候就是找到路径
     * 这里必然成功的原因一个是因为是一层一层递进的, 中途有解的话必然相遇
     * 其次就是要轮换, q1轮流做, 这样两个都在演进
     * 为什么不需要用queue了呢, 因为下一层的信息只在下一次对面的判断的时候有用,不是一直都要保持的
     */
    public int openLock(String[] deadends, String target) {
        // Set<String> deadends1 = new HashSet<String>();
        // for(int i = 0;i < deadends.length;i++) deadends1.add(deadends[i]);
        // Queue<String> p = new LinkedList<String>();
        // Set<String> visited = new HashSet<String>();
        // p.add("0000");
        // visited.add("0000");
        // int step = 0;
        // while(!p.isEmpty()) {
        //     int sz = p.size();
        //     for(int i = 0;i < sz;i++) {
        //         String current = p.poll();
        //         if(deadends1.contains(current)) continue;
        //         if(current.equals(target)) return step;
        //         char[] ch = current.toCharArray();
        //         for(int j = 0;j < 4;j++) {
        //             ch = current.toCharArray();
        //             if(ch[j] == '9') ch[j] = '0';
        //             else ch[j] += 1;
        //             String after = new String(ch);
        //             if(!visited.contains(after)){
        //                 p.add(after);
        //                 visited.add(after);
        //             } 
        //         }
        //         for(int j = 0;j < 4;j++) {
        //             ch = current.toCharArray();
        //             if(ch[j] == '0') ch[j] = '9';
        //             else ch[j] -= 1;
        //             String after = new String(ch);
        //             if(!visited.contains(after)){
        //                 p.add(after);
        //                 visited.add(after);  
        //             } 
        //         }
        //     }
        //     step++;
        // }
        // return -1;
        Set<String> deadends1 = new HashSet<String>();
        for(String s : deadends) deadends1.add(s);
        Set<String> visited = new HashSet<String>();
        Set<String> q1 = new HashSet<String>();
        Set<String> q2 = new HashSet<String>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        while(!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<String>();
            for(String current : q1) {
                if(deadends1.contains(current)) continue;
                if(q2.contains(current)) return step; // intersect
                visited.add(current);
                char[] ch = current.toCharArray();
                for(int j = 0;j < 4;j++) {
                    ch = current.toCharArray();
                    if(ch[j] == '9') ch[j] = '0';
                    else ch[j] += 1;
                    String after = new String(ch);
                    //System.out.println(after);
                    if(!visited.contains(after)){
                        temp.add(after);
                    } 
                }
                for(int j = 0;j < 4;j++) {
                    ch = current.toCharArray();
                    if(ch[j] == '0') ch[j] = '9';
                    else ch[j] -= 1;
                    String after = new String(ch);
                    //System.out.println(after);
                    if(!visited.contains(after)){
                        temp.add(after);
                    } 
                }
            }
            q1 = q2;
            q2 = temp;
            step++;
        }
        return -1;
    }
}
