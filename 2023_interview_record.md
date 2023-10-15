# Shoreline, BackEnd
第一轮线上, 考了到达某个终点的最短路径, 最短路径是path上的权重和.

当时没有确认清楚, 要求的是在DAG中任何一个没有outgoing edge的都算终点.

我就直接dfs了, 最后考官不认为时间复杂度是O(N), 我非常不解, 还是坚持这是O(N). 现在想明白了, 不是每个node都visit一次, 实现的时候有问题, 而应该记录该node到终点的最短距离. 这是因为不同路径访问到这个点是可能产生不一样的解的.

提问了大量513 Dynamic Memory Allocation和PP的优化细节, 证明这个team确实是做rust backend optimization的.

第2、3轮背靠背线下, 第一轮考了file copy service的一个业务代码, 用python写的, 用一个队列就好了?
然后要处理copy失败re-copy. 当然有好多方法, 这里边改边确认, 最后也很模糊, 只能大概觉得考官是想要push回原来的队列, 然后在正常batch操作中进行甄别.

提问了一个比较challenging的project. 我就说了高通的实习项目.

第二轮考的是一个vector of vector, 每个sub vector挑一个数字, output所有不同的组合. 我tmd直接brute force了, 一层一层往下, 维持一个当前组合的vector of vector, 考官没说什么.

提问了最近比较challenging的project, 前一轮说过高通的, 我就说了PP. 问的比较细节, 很多我忘记了, 比如当时为什么要对circles进行exclusive scan. 下次还得好好复习一下PP.

总的来说, 准备的不是很充分. 没犯大错, 也没啥亮点. 大事不妙.

结局: 被拒, position filled. 可能不是面试的问题, 推荐去了另外一个组.

# Shoreline, DevOps
第一次是一个简单的聊天, 我表达了我对在start up工作的热情. 哦我的上帝, 完全是拷贝了上次和Tacit AI面试的时候那个founder说的话. 确定了这个组是做云计算的DevOps, 兴趣乏乏, 但是还是表达了我的表面热情, 约上了下一次面试. 就当练练手了.

第一轮技术面
```
A team is playing a game. At the beginning of the game, the team has 0 points.
The team can gain points in non-negative integer increments of a_1, a_2, ..., or a_k points.
Every time the team gains points, the mascot has to do a number of pushups
equal to the new score.
You are given the possible point increments a_1, a_2, a_3, ..., a_k,
and the total number of pushups p the mascot ended up doing.
Find a possible final score s for the team, or correctly assert that
such a number of pushups is impossible the given game.
---
Example:
a = [3, 4, 5]
p = 23
A possible value for s is 12.
```
```C++
void backtrack(vector<int>& a, int p, int& answer, int current_score, int current_p, unordered_set<string> memo) {
  if(current_p > p) return;
  if(answer != -1) return;
  if(current_p == p) {
    answer = current_score;
    return;
  }
  string key = to_string(current_score) + " " + to_string(current_p);
  if(memo.find(key) != memo.end()) return;
  for(int i : a) {
    backtrack(a, p, answer, current_score + i, current_p + current_score + i, memo);
    if(answer != -1) return;
  }
  memo.insert(key);
}

int solution(vector<int>& a, int p) {
  int answer = -1;
  unordered_set<string> memo;
  backtrack(a, p, answer, 0, 0, memo);
  return answer;
}

p = 28431, a = [13, 15] -> s in [855, 857, 859, 861, 863, 865, 867, 869, 871, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 892, 894, 896, 898, 900, 902, 904, 906, 908, 910, 912, 914]
```
这是极度类似CombinationIV的, 带memo的combination. 但是有一点奇怪, memo加进去之后更慢了 TODO 研究一下

# Health At Scale, MLE
先是大量ML基础的提问. 由于这个公司是做的医疗, 估计regression用的比较多, 而并不是医疗图像, 没有任何深度学习的问题. 

先问了logistic regression的数学表达式, 我忘了, 我直接说的linear regression的仿射变换. 然后他提示了一下logistic regression output 0 - 1之间, 我想这不会是加了个softmax吧, 就把softmax公式说了. 好像大致猜中了, 实际上是叫sigmoid. 

然后问损失函数, 我说了MSE, 一开始说logistic regression也可以用. 但是考官明显要的不是这个, 我就试探的说是不是交叉熵, 好像确实是, 毕竟加了softmax的. 又问交叉熵的数学表达式, 我猜了一下, 直接的有log, 就猜了sum(log(Y - Y')), 估计不对. 

接下来问正则化, 公式、用途答的不错, 但是问有什么区别, 我忘记了, 只记得L2用的比较多.

总结是我确实完全忘了logistic regression, 考的都是ML的基础而非DL, 我本来本科的时候就没有学好orz.

题目做到原题了, 第一题LC560, 用前缀数组哈希表版;第二题word break. 

做第一题的时候被发现做过了, 笑死. 我在那里把思考经历全说了, 什么brute force, 到滑动窗口为什么不行, 到普通前缀数组, 到用哈希表记录前缀数组, 直接暴露了. 人不得瑟会死吗?

总结, coding没啥亮点, ML问题可能有些扣分, 虽然考官一直“哦, 这很好, 完全正确”.

结局: 被拒, 后来复习的时候确实发现自己的回答太不正确了. 要好好复习ML.

# Tacit plus, MLE
再也不面start up了, 再也不面了. 超时了一个半小时, 太折磨了.

CS的问题是如何加速一个代码, 太他妈的general了, 我说了memory, 他好像不是很感兴趣, 只好说算法, 或者针对处理器的优化, 比如ARM可以反向loop什么的. 

ML的问题我答的稀烂, 他问的有点细节, bias和variance的细节我都忘了. 什么是high bias/high variance模型, 我费了好大的劲才在提示下回答出来. 然后问了一些LLM的问题, 我什么都不懂.

然后最离谱的就是之后的提问, 如何保证两个function的output完全相同, 什么test case的构建, 直接开始讨论业务. 答到一半我有点受不了了, 只想结束.

Coding更是烦, 写一个data type的comparator, 我觉得好无聊. 基本上一边交流一边写, 已经把不耐烦打在脸上了. 分类讨论type, 然后recessively检查data structure里的每个element. 他说一个要求我挤一个实现. 我觉得这完全就是业务代码, 本来就是要根据要求改的嘛.

总结就是我一点也不感兴趣了, 2-3人的start up如果老板很抽象太烦了.

后续: 真香啊, 又面了一轮technical. 题目是两道业务代码, 第一道已经忘记了, 好像是分割出一个字符串里的内容; 第二道是计算TF-IDF, follow up问了如果documents size很大怎么办.

算是没啥问题, 面试官也就是founder说pass all technical了. 下一周的VentureBridge SV event我还去见到了他. 

后续: 无消息.

# Nvidia, Deep Learning Algorithm
收到面试邀请的时候真的好激动, Nv面试, 从来没有收到过, 又是在当前这个环境下. 如果能去Nv, 真的是最好的结局了. 花了很多时间复习ML, 也花了一点时间刷题.

面试官直接是一个lead, 40分钟, 主要可能还是看一下水平大概如何. 该部门是做AI Playground和Community Models(也就是开源社区的模型部署什么的). 

大量的时间在问简历的细节. 当前的TTS project用的VITS模型, SPD的time series模型和数据, 甚至是NTU的character creator 4 dataset. 高通的反而没问.

然后自然是PP, 他们估计见多了CUDA, 每个申请的人多多少少都要说自己的CUDA项目. 主要问了Transformer, 我都如实回答了. Megatron居然是他们部门维护的, 这波啊, 这波是关公面前耍大刀了.

然后是Coding, 用PyTorch写一个training loop. 写的其他的很顺利, 漏掉了一个optimizer.zero_grad(). 他问了这一行的左右, 我确实不知道, 猜测了一下grad清零是因为前向传播并不需要grad, 清零可以加速. 但是事实上是这一步清零, 后面的step才能正确计算, 不然就累积了. 多么简单的答案, 因为我从来没有关注过这个函数, 或者说关注过但是忘了. 总的而言, 实力不够. 决定一定要自学DLS了. 其实暑假就应该准备好, 但是像李晨昊学长这样的努力, 我终究是没能做到.

后续: 杯具. 太可惜了, 得到机会但是实力不够, 继续努力学习ML/DL吧.

# Black Sesame, AI Compiler
AI compiler的概念就类似我们之前高通组里的DirectNN? 我的理解是类似TVM. 但是面试官似乎是说不全是, 共同的概念是打通模型和accelerator之间的强梁, 是属于AI部署和infrastructure.

题目是DAG的topological sort. 语言自然是C++, 一开始我还写错了, 用了preorder, 带个visited. 后来test的时候, 面试官提示有问题, 我想了想反应过来了, 应该用后序, 然后将结果再反过来. 时间复杂度答对了O(N+E).

follow up问了是不是真的需要visited, 我说在当前的算法实现中是必须的, 因为即便图无环, 一个node有多个incoming edge就会重复访问. 如果实在不想要, 只能检查当前的track里是否存在当前node, 但track要保持order只能用vector, 那查找就是O(N).

后来问了浩哲, 得知course schedule实际上有第二种解法, 这可能是面试官觉得有更好解法的关键. 通过是通过了, 但是暴露了需要好好复习经典题目.

也有做的好的地方. 我觉得上次那个workshop去的很值, 看别的同学和google的学姐现场表演, 发现不管怎么样, 打代码要快, 要起码显得自己脑子很快. 然后就是如果做不出来, 不妨大胆猜一个思路, 然后从面试官的reaction和hint中再做尝试. 积极在codepad中记录思路, 因为等下面试官会拷贝记录的.说完思路要和面试官确认, 然后开始写代码.

从心情上来说, 即便我写错了一次, 突然想出来的那一刻是很开心的. 我想面试的时候, 这一刻的心情很独特, 那不是说觉得面试把握很大而开心, 而是想出思路单纯的有些小得意, 纵然这是一道基础的题目. 我偷偷的想, 有的时候, 保持和传递这种积极的情绪, 实际上是有帮助的吧. 