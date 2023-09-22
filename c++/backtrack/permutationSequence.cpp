/**
 * 60. Permutation Sequence
 * hard自己做出来了 而且是100%速度 难道我真的是天才?
 * 这道题放在backtrack分类里完全是因为permutation一般是backtrack的
 * 这道题却不用 因为我们在每一层都要选择一个数字继续
 * 最关键的思路是我们发现对于n=3,k=3, 第一次选择取决于买一个选择都有2!种可能 那么k=3的情况下
 * 我们自然是选择第二个能用的数字 因为前一个数字带来了2种可能 我们要排第三的 把树画出来 就很好理解了
 * 
 * 实现的主干仍然使用了permutation回溯(要带visited)的递归 当然C++书上直接写了一个for循环就好了
 * 另一个发现是string不能+= char 只能+=另一个string 所以这里只能int2string
*/
class Solution {
public:
    string getPermutation(int n, int k) {
        string answer;
        vector<long> factorial(n+1, 1);
        for(int i = 2;i <= n;++i) factorial[i] = factorial[i-1] * i;
        vector<bool> visited(n+1, false);
        permutation(answer, factorial, visited, n, n, k);
        return answer;
    }
    void permutation(string & answer, const vector<long> &factorial, vector<bool> &visited, int n, int level, int k) {
        if(level==0) return;
        int next_f = factorial[level-1], current;
        for(int i = 1;i <= n;++i) {
            if(visited[i]) continue;
            if(k <= next_f) {
                current = i;
                break;
            }else{
                k -= next_f;
            }
        }
        answer += to_string(current);
        visited[current] = true;
        // cout << n << " " << next_f << " " << k << " " << current << " " << visited[current] << endl;
        permutation(answer, factorial, visited, n,level-1, k);
    }
};