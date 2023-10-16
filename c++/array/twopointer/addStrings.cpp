/**
 * 415. Add Strings
 * 不用复习
*/
class Solution {
public:
    string addStrings(string num1, string num2) {
        int first = num1.length() - 1, second = num2.length() - 1, carrier = 0;
        string answer;
        while(first >= 0 && second >= 0) {
            int tmp = (num1[first] - '0') + (num2[second] - '0') + carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            answer += (tmp + '0');
            // cout << tmp << " ";
            --first;
            --second;
        }

        while(first >= 0) {
            int tmp = num1[first] - '0';
            tmp += carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            answer += (tmp + '0');
            // cout << tmp << " ";
            --first;
        }

        while(second >= 0) {
            int tmp = num2[second] - '0';
            tmp += carrier;
            carrier = tmp > 9 ? 1 : 0;
            tmp %= 10;
            answer += (tmp + '0');
            // cout << tmp << " ";
            --second;
        }

        if(carrier) answer += '1';
        reverse(answer.begin(), answer.end());
        // cout << endl;
        return answer;
    }
};