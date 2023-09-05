#include <vector>
#include <queue>
using namespace std;

class numberOfStudentsUnableEat {
public:
    /**
     * 1700. Number of Students Unable to Eat Lunch
     * 纯模拟, 浪费时间, 熟悉了一下C++的queue, 这里换成deque是一样, deque是双向队列, 可以从两边push和pop
     * 但是这里理应只需要一侧
    */
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        queue<int> q;
        for(int i : students) q.push(i);
        int notmoved = 0, i = 0;
        while(!q.empty() && notmoved != q.size()) {
            if(q.front() == sandwiches[i]) {
                i++;
                notmoved = 0;
            }else{
                q.push(q.front());
                notmoved++;
            }
            q.pop();
        }
        return q.size();
    }
};