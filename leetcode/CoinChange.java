public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount + 1]; // base case 在amount 0的时候
        for(int i = 0;i < amount + 1;i++){
            mem[i] = -1;
        }
        mem[0] = 0;
        for(int i = 0;i < amount + 1;i++){
            if(mem[i]==-1){
                continue;
            }
            for(int j = 0;j < coins.length;j++){
                if(coins[j] + i <= amount && coins[j] + i > 0){ //这里9也要check，因为有[1,2147483647]，2的testcase
                    if(mem[coins[j] + i] == -1 || mem[i] + 1 < mem[coins[j] + i]){
                        mem[coins[j] + i] = mem[i] + 1;
                    }
                }
            }
        }
        return mem[amount];
    }
}
