package stack;
public class MinimumAddToMakeParenthesesValid {
    /* 921
     * 太蠢了 这道题我本来想要BFS解的 每次选择是每个空位置插正反(
     * 但是这个题目暴力穷举是会超时的
     * 所以应该用更简单的思路 我们知道对于一种括号的统计计算 保持一个count往往可以解决问题 这里也是一样
     * count加减 如果成负 说明我们除了加入一个新的括号别无他法使其valid 故此直接input++
     * 实际上 这里的balance可以理解为stack size
     */
    public int minAddToMakeValid(String s) {
        if(s.length() == 0) return 0;
        int input = 0;
        int balance = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') {
                balance++;
            }else{
                balance--;
                if(balance < 0) {
                    input++;
                    balance = 0;
                }
            }
            
        }
        return input + balance;
    }
    // public int minAddToMakeValid(String s) {
    //     Queue<String> q = new LinkedList<String>();
    //     Set<String> visited = new HashSet<String>();
    //     q.add(s);
    //     visited.add(s);
    //     int step = 0;
    //     while(!q.isEmpty()) {
    //         int sz = q.size(); 
    //         int slen = q.peek().length();
    //         for(int i = 0;i < sz;i++) {
    //             String current = q.poll();
    //             for(int j = 0;j < slen;j++) {
    //                 String ns = current.substring(0, j) + "(" + current.substring(j, slen);
    //                 if(!visited.contains(ns)){
    //                     q.add(ns);
    //                     visited.add(ns);
    //                 }
    //                 ns = current.substring(0, j) + ")" + current.substring(j, slen);
    //                 if(!visited.contains(ns)){
    //                     q.add(ns);
    //                     visited.add(ns);
    //                 }
    //             }
    //         }
    //         step++;
    //     }
    //     return step;
    // }
}
