/**
 * 380. Insert Delete GetRandom O(1)
 * C++版本的randomized set (题目的名字改了) 用map映射val -> vector中的index
*/
class RandomizedSet {
private:
    unordered_map<int, int> map;
    vector<int> vec;
public:
    RandomizedSet() {}
    
    bool insert(int val) {
        if(map.find(val) != map.end()) return false;
        map[val] = vec.size();
        vec.push_back(val);
        return true;
    }
    
    bool remove(int val) {
        if(map.find(val) == map.end()) return false;
        int del_index = map[val];
        map[vec.back()] = del_index;
        vec[del_index] = vec.back();
        map.erase(val);
        vec.pop_back();
        return true;
    }
    
    int getRandom() {
        return vec[rand()%vec.size()];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */