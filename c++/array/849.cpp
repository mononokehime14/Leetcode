/**
 * 849. Maximize Distance to Closest Person
 * 只需要讨论两个1之间的间距就可以了, 三种情况分类讨论, 坐第一个, 坐最后一个, insert
 * 还是挺有意思的这个情况讨论, 计算的时候小心一点, 可以推理几个例子
*/
class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        int prev = -1, n = seats.size(), answer = 1;
        for(int i = 0;i < n;++i) {
            if(seats[i] == 1) {
                if(prev == -1) {
                    answer = max(answer, i);
                }else{
                    int distance = i - prev - 1;
                    if(distance % 2 == 0) {
                        distance = distance / 2;
                    }else{
                        distance = distance / 2 + 1;
                    }
                    answer = max(answer, distance);
                }
                prev = i;
            }
        }
        answer = max(answer, n-1-prev);
        return answer;
    }
};