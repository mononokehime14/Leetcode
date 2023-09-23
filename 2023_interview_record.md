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

# Health At Scale, MLE
先是大量ML基础的提问. 由于这个公司是做的医疗, 估计regression用的比较多, 而并不是医疗图像, 没有任何深度学习的问题. 

先问了logistic regression的数学表达式, 我忘了, 我直接说的linear regression的仿射变换. 然后他提示了一下logistic regression output 0 - 1之间, 我想这不会是加了个softmax吧, 就把softmax公式说了. 好像大致猜中了, 实际上是叫sigmoid. 

然后问损失函数, 我说了MSE, 一开始说logistic regression也可以用. 但是考官明显要的不是这个, 我就试探的说是不是交叉熵, 好像确实是, 毕竟加了softmax的. 又问交叉熵的数学表达式, 我猜了一下, 直接的有log, 就猜了sum(log(Y - Y')), 估计不对. 

接下来问正则化, 公式、用途答的不错, 但是问有什么区别, 我忘记了, 只记得L2用的比较多.

总结是我确实完全忘了logistic regression, 考的都是ML的基础而非DL, 我本来本科的时候就没有学好orz.

题目做到原题了, 第一题LC560, 用前缀数组哈希表版;第二题word break. 

做第一题的时候被发现做过了, 笑死. 我在那里把思考经历全说了, 什么brute force, 到滑动窗口为什么不行, 到普通前缀数组, 到用哈希表记录前缀数组, 直接暴露了. 人不得瑟会死吗?

总结, coding没啥亮点, ML问题可能有些扣分, 虽然考官一直“哦, 这很好, 完全正确”.

# Tacit plus, MLE
再也不面start up了, 再也不面了. 超时了一个半小时, 太折磨了.

CS的问题是如何加速一个代码, 太他妈的general了, 我说了memory, 他好像不是很感兴趣, 只好说算法, 或者针对处理器的优化, 比如ARM可以反向loop什么的. 

ML的问题我答的稀烂, 他问的有点细节, bias和variance的细节我都忘了. 什么是high bias/high variance模型, 我费了好大的劲才在提示下回答出来. 然后问了一些LLM的问题, 我什么都不懂.

然后最离谱的就是之后的提问, 如何保证两个function的output完全相同, 什么test case的构建. 答到一半我有点受不了了, 只想结束.

Coding更是烦, 写一个data type的comparator, 我觉得好无聊. 基本上一边交流一边写, 已经把不耐烦打在脸上了. 分类讨论type, 然后recessively检查data structure里的每个element. 他说一个要求我挤一个实现. 我觉得这完全就是业务代码, 本来就是要根据要求改的嘛.

总结就是我一点也不感兴趣了.