/**
 * 433. Minimum Genetic Mutation
 * 标准的bfs visited的更新位置在push的时候, 这是因为这一层后面的其他string可能会得到同样的新string
 * 而答案的检查在刚刚pop下来的时候
*/
class Solution {
public:
    int minMutation(string startGene, string endGene, vector<string>& bank) {
        unordered_set<string> bank_s;
        for(const string & s : bank) bank_s.insert(s);
        if(bank_s.find(endGene) == bank_s.end()) return -1;
        int n = startGene.length();
        string acgt = "ACGT";
        unordered_set<string> visited;
        queue<string> q;
        q.push(startGene);
        int steps = 0;
        while(!q.empty()) {
            int size = q.size();
            while(size) {
                string current = q.front();
                q.pop();
                cout << steps << " " << current << endl;
                if(current == endGene) return steps;
                // if(visited.find(current) != visited.end()) continue;
                // visited.insert(current);
                for(int i = 0;i < n;++i) {
                    char ori = current[i];
                    for(char c : acgt) {
                        if(c == ori) continue;
                        current[i] = c;
                        if(visited.find(current) == visited.end() && bank_s.find(current) != bank_s.end()) {
                            visited.insert(current);
                            q.push(current);
                        }
                    }
                    current[i] = ori;
                }
                --size;
            }
            steps++;
        }
        return -1;
    }
};