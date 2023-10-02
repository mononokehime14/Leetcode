
#include <string>
#include <unordered_map>
#include <vector>
using namespace std;
void optimalStorage(string& word, int max_operation) {
    unordered_map<char, char> transform;
    for(char i = 'a';i <= 'z';++i) transform[i] = i;
    int n = word.length();
    for(int i = 0;i < n && max_operation > 0;++i) {
        char current = word[i];
        if(current != transform[current]) continue;
        int steps = (current - 'a', max_operation);
        max_operation -= steps;
        for(int j = 0;j < steps;++j) {
            transform[current - j] = current - steps;
        }
    }
    for(int i = 0;i < n;++i) {
        word[i] = transform[word[i]];
    }
    std::cout << word << std::endl;
}

int vulnerablePassword(string& password, int max_ops) {
    int n = password.length();
    if(!n) return 0;
    vector<int> window(26, 0);
    char max_char = password[0];
    window[max_char-'a'] = 1;
    int tolerence = max_ops, left = 0, answer = 0;
    for(int right = 1;right < n;++right) {
        char current = password[right];
        window[current-'a']++;
        if(current != max_char) {
            if(window[current-'a'] > window[max_char-'a']) {
                tolerence = tolerence + window[current-'a'] - window[max_char-'a'];
                max_char = current;
            }else{
                --tolerence;
            }
        }
        while(tolerence < 0) {
            current = password[left];
            window[current-'a']--;
            // find max, check it it max_char,
        
        }
        answer = max(answer, right - left + 1);
    }
    return answer;
}