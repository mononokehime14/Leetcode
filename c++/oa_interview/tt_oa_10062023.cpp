
/**
 * 给定26个字母对应的value 给定string 可以随意arrange string
 * 怎样才能使得相邻字母之前的absolute difference最小
*/
int solutionA(vector<int> security_values, string msg) {
    int n = security_values.size();
    vector<pair<int, char>> values2(n);
    for(int i = 0;i < n;++i) {
        values2[i] = make_pair(security_values[i], 'a'+i);
    }
    sort(values2.begin(), values2.end(), [](const pair<int, char>& a, const pair<int, char>& b) {
        return a.first < b.first;
    });
    unordered_set<char> counting;
    for(char c : msg) counting.insert(c);
    int sum = 0, prev;
    for(int i = 0;i < n;++i) {
        if(counting.find(values2[i].second) != counting.end()) {
            if(prev != -1) {
                sum += abs(values2[i].first - values2[i-1].first);
            }
            prev = i;
        }
    }
    return sum;
}

string solutionB(string& a) {
    vector<int> counting(26, 0);
    for(char c : a) counting[c-'a']++;
    int left = 0, right = 25;
    while(left < right) {
        while(counting[right] % 2 == 0){
            --right;
        }
        while(counting[left] % 2 == 0){
            ++left;
        }
        if(left < right) {
            counting[left]++;
            counting[right]--;
        }
    }
    string result;
    bool hasOdd = false;
    char odd;
    for(int i = 0;i < 26;++i) {
        if(counting[i] % 2 == 0) {
            int adds = counting[i] / 2;
            for(int j = 0;j < adds;++j) {
                result.push_back('a'+i);
            }
            counting[i] -= adds;
        }else{
            int adds = (counting[i] - 1) / 2;
            for(int j = 0;j < adds;++j) result.push_back('a'+i);
            hasOdd = true;
            odd = 'a'+i;
            counting[i] -= (adds + 1);
        }
    }
    if(hasOdd) result.push_back(odd);
    for(int i = 25;i >= 0;--i) {
        for(int j = 0;j < counting[i];++j) {
            result.push_back('a'+i);
        }
    }
    return result;
}