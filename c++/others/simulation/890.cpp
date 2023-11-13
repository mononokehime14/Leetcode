/**
 * 890. Find and Replace Pattern
 * 没有特别的思路,就是迭代一边
 * 可能比较有意思的是pattern的检查, 需要用hashmap记录mapping, 分类讨论三种情况
 * 1. 当前char的mapping存在, map的char必须等同于pattern的char
 * 2. mapping不存在, 想要map pattern的对应char, 但是已经被map过了
 * 3. 成功建立一个新mapping
*/
class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> answer;
        unordered_map<char, char> mapping;
        vector<bool> used(26, false);
        int t = pattern.length();
        for(string& word : words) {
            mapping.clear();
            fill(used.begin(), used.end(), false);
            bool match = true;
            for(int i = 0;i < t;i++) {
                if(mapping.find(word[i]) != mapping.end()) {
                    if(mapping[word[i]] != pattern[i]) {
                        match = false;
                        break;
                    }
                }else if(used[pattern[i]-'a']){
                    match = false;
                    break;
                }else{
                    mapping[word[i]] = pattern[i];
                    used[pattern[i]-'a'] = true;
                }
            }
            if(match) answer.push_back(word);
        }
        return answer;
    }
};