import java.util.HashMap;
public class HappyNumber {
    public boolean isHappy(int n) {
        /*
         * 思路在于检查cycle, 如果陷入cycle则无法达到目标
         * 问题在于是否有可能无限增长呢?并不，因为受到最大值的限制
         * 比如三位数最大999, 进入243, 之后最高也不会超过三位; 四位数9999一开始就会变成324
         * 之后也不会超过999了. 所以不会出现无限增长. 所有超出243的数字之后都不会再回来. 
         * 
         * Native Implementation of cycle detection, O(logn) && O(logn):
         */
        // HashMap<Integer, Integer> mem = new HashMap<>();
        // while(n != 1){
        //     mem.put(n, 1);
        //     n = next(n);
        //     if(mem.containsKey(n)) return false;
        // }
        // return true;

        /* Floyd's Cycle-Finding Algo, 龟兔赛跑 
         * 节省了空间, 用不到hashmap了
        */
        int slow = n;
        int fast = next(n); //先跑一次
        while(fast != 1 && slow != fast){
            slow = next(slow); // stride 1
            fast = next(next(fast)); // stride 2
        }
        return fast == 1;
    }
    private int next(int n){
        int sum = 0;
        while(n != 0){
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }
}
