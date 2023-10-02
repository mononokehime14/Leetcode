/**
 * 258. Add Digits
 * 傻逼题, 公式是1 + (num - 1) % 9;
 * 直接提了一个暴力解滚了
*/
class Solution {
public:
    int addDigits(int num) {
        while(num / 10 > 0) {
            int new_sum = 0;
            while(num > 0) {
                new_sum += num % 10;
                num /= 10;
            }
            num = new_sum;
            cout << new_sum << endl;
        }
        return num;
    }
};