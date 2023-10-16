/**
 * 432. All O`one Data Structure
 * count频率可以用hashmap, 但是无法按照频率排序;如果考虑用priority queue, 则更新一次还是logN
 * 这里需要用到类似segmented list的思路, 称为buckets. 每一个buckets代表某个出现频率, 存有一串相应的keys
 * 顺序如何保持呢? 用双向链表存buckets, 这是为了让bucket的增删也变成O(1)
 * 我们再额外保持一个hashmap, 映射key到哪一个bucket
 * 如此一来, inc的操作就是找到key的bucket, 通过双向链表找到下一个bucket(value + 1, 如果没有的话insert).
 * 旧的bucket里key移除, 新的bucket里key加进去.
 * 
 * 这里的doubly linked list使用的是C++的list, list本身的实现就是双向链表. hashmap映射使用的list的iterator
 * 一个小细节, buckets.end()是dummy的, 但是buckets.begin()正儿八经就是头一个, 所以dec和inc的时候,
 * 对于这部分的检查是不同的. 而list的insert是在当前的iterator前面然后return新的node的指针, 
 * 故此inc的时候next insert, dec的时候current insert
*/
class AllOne {
private:
    struct Bucket{int value; unordered_set<string> keys;};
    list<Bucket> buckets;
    unordered_map<string, list<Bucket>::iterator> key_buckets_map;
public:
    AllOne() {}
    
    void inc(string key) {
        if(key_buckets_map.find(key) == key_buckets_map.end()) {
            key_buckets_map[key] = buckets.insert(buckets.begin(), {0, {key}});
        }
        auto next = key_buckets_map[key], current = next++;
        if(next == buckets.end() || next->value > current->value + 1) {
            next = buckets.insert(next, {current->value + 1, {key}});
        }else{
            next->keys.insert(key);
        }
        key_buckets_map[key] = next;
        current->keys.erase(key);
        if(current->keys.empty()) buckets.erase(current); // 要remove, 保证 getMin/Max都是直接拿不用判断
    }
    
    void dec(string key) {
        auto prev = key_buckets_map[key], current = prev--;
        if(current->value > 1) {
            if(current == buckets.begin() || prev->value < current->value - 1) {
                prev = buckets.insert(current, {current->value - 1, {key}});
            }else{
                prev->keys.insert(key);
            }
            key_buckets_map[key] = prev;
        }else{
            key_buckets_map.erase(key);
        }
        current->keys.erase(key);
        if(current->keys.empty()) buckets.erase(current); // 要remove, 保证 getMin/Max都是直接拿不用判断
    }
    
    string getMaxKey() {
        return buckets.empty() ? "" : *(buckets.rbegin()->keys.begin());
    }
    
    string getMinKey() {
        return buckets.empty() ? "" : *(buckets.begin()->keys.begin());
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */