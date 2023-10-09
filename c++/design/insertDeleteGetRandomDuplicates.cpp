/**
 * 381 insert-delete-getrandom-o1-duplicates-allowed
 * 这个和不带duplicates区别很小啊, 只要把map value改成一个array或者set就行了
*/
class RandomizedCollection {
private:
    unordered_map<int, unordered_set<unsigned long>> map;
    vector<int> vec;
public:
    RandomizedCollection() {}
    
    bool insert(int val) {
        map[val].insert(vec.size());
        vec.push_back(val);
        return map[val].size() == 1;
    }
    
    bool remove(int val) {
        if(map.find(val) == map.end() || map[val].empty()) return false;
        int del_index = *map[val].begin();
        map[val].erase(del_index);
        map[vec.back()].insert(del_index);
        map[vec.back()].erase(vec.size() - 1);
        vec[del_index] = vec.back();
        vec.pop_back();
        return true;
    }
    
    int getRandom() {
        return vec[rand()%vec.size()];
    }
};