package slidingwindow;

public class CountTheNumberOfGoodSubarrays {
    /* 2537
     * 周赛题 subarray所以自然想到了滑动窗口 这里先是正常的滑动窗口 右边扩张加入数字 如果满足条件则把左边收缩
     * 这里注意如果发现一个sub array满足条件 那么它的右边再怎么加都同样满足 所以一次要加n - right
     * 然后就是参考了MinimumWindowSubstring这道题 每次检查window中的subarray是否满足条件时 如果遍历一遍hashmap
     * 那如果全部的数字都不一样 不就是n平方吗 所以good pairs的数量不能每次都数一遍 而是要随着加入/移除数字直接更新好 更新O(1)
     * 可以发现的规律是5个数字组合是4 + 3 + 2 + 1 去掉一个就是3 + 2 + 1 所以good pairs的数量减去了(n - 1) 加入的话自然就是 +n
     */
    HashMap<Integer, Integer> count = new HashMap<>();
        int left = 0, right = 0, n = nums.length, current = 0;
        long answer = 0;
        while(right < n) {
            int r = nums[right];
            if(count.containsKey(r)) {
                int old = count.get(r);
                current += old;
                // current -= ((old - 1) * old) / 2;
                // current += ((old + 1) * old) / 2;
                count.put(r, old + 1);
            }else{
                count.put(r, 1);
            }
            while(current >= k) {
                // System.out.println(left + " " + right);
                answer += (n - right);
                int l = nums[left];
                int old = count.get(l);
                if(old == 1) {
                    count.remove(l);
                }else{
                    current = current - old + 1;
                    // current -= ((old - 1) * old) / 2;
                    // if(old > 2) {
                    //     current += ((old - 2) * (old - 1)) / 2;
                    // }
                    count.put(l, old - 1);
                }
                left++;
            }
            right++;
        }
        return answer;
    }
    // private int calculateWindow(HashMap<Integer, Integer> count) {
    //     int sum = 0;
    //     for (Map.Entry<Integer, Integer> i : count.entrySet()) {
    //         if(i.getValue() > 1) {
    //             sum += (i.getValue() * (i.getValue() - 1)) / 2;
    //         }
    //     }
    //     return sum;
    // }
}
