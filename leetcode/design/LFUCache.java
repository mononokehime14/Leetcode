package design;

public class LFUCache {
    /* 460
     * 首先 cache本身要get O(1)就还是要一个hashmap
     * 然后 要快速知道每个key的frequency 无法使用counting数组 只能又是一个hashmap 记录key freq pair
     * 到这里 如果我们记录全局的minFreq 我们已经可以O(1)的evict最小出现的key了 但是有一个额外的要求 就是出现tie的情况 我们要evict LRU原则
     * 这样的话 我们就需要一个doubly list来记录使用的顺序了 并进行快速的更新使用时间 （拿出来塞到最后） 但是如果全部挤在一个双链表里肯定不行 没办法快速判断freq
     * 所以 就需要用到类似segmented list 每一个freq 保持一个双向链表 这里实现LRU用的双向链表没有用自己实现的 用了linkedhashset 这是一个有序
     * 的hashset 遵从加入的顺序 内部是用doubly linked list + hashmap实现的 符合我们的要求
     * 
     * 这个方法是可以优化的 使用自己的doubly linked list 参照LRU cache 这样的话 就不要只存key integer了 要存自定义node了 可以把frequency也存进去
     * cache改成key node pair 则freq hashmap直接不用 这样会更快
     */
    private HashMap<Integer, Integer> cache; // cache itself, key -> value
    private HashMap<Integer, Integer> freq; // frequency record, key -> frequency group
    private HashMap<Integer, LinkedHashSet<Integer>> freqMap; // frequency group -> doubly linked hashset recording keys
    private int capacity;
    private int minFreq; // record minFreq, to fast look up least frequently used key in freqMap 
    private int size;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        increaseFreq(key);
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        if (this.capacity <= 0) return; // edgecase
        if(cache.containsKey(key)) {
            cache.put(key, value);
            increaseFreq(key);
            return;
        }
        if(this.capacity <= this.size) {
            evict();
        }
        addNew(key, value);
    }

    private void increaseFreq(int key) {
        int oldFreq = freq.get(key);
        freqMap.get(oldFreq).remove(key);
        if(freqMap.get(oldFreq).size() == 0) { //拿空
            freqMap.remove(oldFreq);
            if(oldFreq == this.minFreq) this.minFreq++;
        }
        freqMap.putIfAbsent(oldFreq + 1, new LinkedHashSet<>()); // if this is first time this freq appears
        freqMap.get(oldFreq + 1).add(key);
        freq.put(key, oldFreq + 1);
    }

    private void evict() {
        int evictKey = freqMap.get(this.minFreq).iterator().next(); // first in this group is both LRU and LFU
        freq.remove(evictKey);
        freqMap.get(this.minFreq).remove(evictKey);
        if(freqMap.get(this.minFreq).size() == 0) freqMap.remove(this.minFreq);
        // while(freqMap.get(this.minFreq).size() == 0) minFreq++;
        // 这里没有O(1)的方法来更新minFreq 但是一会儿我们马上一定会执行addNew 那个时候minFreq一定会是1
        cache.remove(evictKey);
        this.size--;
    }

    private void addNew(int key, int value) {
        cache.put(key, value);
        freq.put(key, 1);
        freqMap.putIfAbsent(1, new LinkedHashSet<>());
        freqMap.get(1).add(key);
        this.minFreq = 1;
        this.size++;
    }
}
