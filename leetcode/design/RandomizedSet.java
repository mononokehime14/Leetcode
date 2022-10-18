package design;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    HashMap<Integer, Integer> mem;
    ArrayList<Integer> index;
    int count = 0;
    Random rdm;

    public RandomizedSet() {
        mem = new HashMap<Integer, Integer>();
        index = new ArrayList<Integer>();
        rdm = new Random();
    }
    
    public boolean insert(int val) {
        if(mem.containsKey(val)) return false;
        mem.put(val, count);
        count++;
        index.add(val);
        return true;
    }
    /*
     * 如何remove中获得O(1)time complexity? 利用ArrayList remove last index
     * constant time的特性,除了remove last index都是O(N)
     * 如果我不知道要remove的数字的index那么remove就有search cost, 一旦我知道, 便可以和最后一个index
     * swap来达到constant time的效果
     * 这里还利用了ArrayList get()是O(1)这使得swap可以在Constant time完成 额外注意这是破坏顺序的 换言之顺序在这个结构里不重要
     * 为什么get()是O(1) 而LinkedList就是O(N) 因为ArrayList backed with Array access其内在array是O(1)的 只要计算地址就可以了
     */
    public boolean remove(int val) {
        if(!mem.containsKey(val)) return false;
        int rmindex = mem.get(val);
        int lastindex = index.size() - 1;
        int lastval = index.get(lastindex);
        index.set(rmindex, lastval);
        mem.put(lastval, rmindex);
        index.remove(lastindex);
        count--;
        mem.remove(val);
        return true;
    }
    
    public int getRandom() {
        return index.get(rdm.nextInt(count));
    }
}
    
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
