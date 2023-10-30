# <center>独孤求败尽破天下剑招于此</center>
<p align="center">归妹趋无妄 无妄趋同人 同人趋大有</p> 

<p align="center">甲转丙 丙转庚 庚转癸</p> 

<p align="center">子丑之交 辰巳之交 午未之交</p> 

<p align="center">风雷是一变 山泽是一变 水火是一变</p>  

<p align="center">乾坤相激 震兑相激 离巽相激</p> 

<p align="center">三增而成五 五增而成九</p> 

## Greedy ##
1. Overlapping circles, 这类题目通常要先转一个弯, 题目求的是minimum remove, 那么能不能做到每一次remove最大化价值?
自然而然顺延到了贪心的思路, 也就是local optimal累积为global optimal.


## 双指针 ##
1. 左右, 快慢, 并行. 左右、并行指针常见数组问题, 快慢指针常见链表问题(也可以用来数组remove duplicates). 
2. 左右指针比如two sum sorted中来, 往往两个candidate拼凑答案, 就可以左右指针试. panlindrome的检查使用左右指针扫一遍, 搞清楚parentheses的检查则是用stack. 背向而行可以称为扩散指针, 也是回文字符串的经典思路.
3. 快慢指针经典用于判定链表是否有环 
4. 并行指针往往是检查两个数组是不是有某种共同的pattern (比如检查子序列subsequence, 指针可以跳着嘛), 或者merge sorted array, 要点在于两者order的保持.


## Math ## 
1. 对规定1～N内的数字 寻找missing等 可以用index配对方法. 这个方法的最高境界是玩起linkedlist, 因为数字->相应的index, 实际上就像链表一样, 可以用来检查duplicate.
2. 快速幂用某种分而治之的思路 区分奇偶 偶数直接平方 奇数平方再乘以底数
3. 质数 丑数 思路是快速筛选法
4. 通过n&(n-1)可以消除掉n的最后一位1
5. 对于任何数字a a ^ 0 = a, a ^ a = 0; XOR计算顺序无关
6. 故此对于任何数组, 想要first ^ last = 剩余的XOR和, 则整个数组xor为0


## 二分搜索 ##
1. 有一类题目是通过二分搜索尝试答案是否正确做出来的 比如快速寻找sqrt 注意要知道min max

## Subarray ##
Subarray由于有连贯性的要求, 每次第一反应就是能不能滑一下(滑动窗口). 不过, 滑动窗口的应用场景是比较特殊的, 通常是window有特殊的维持条件, 比如window定长, 或者要保持sum等于什么. 要从什么时候扩张右指针, 什么时候收缩左指针直到无法满足条件入手思考.

subarray的另一种思路往往是扩散指针(比如palindrome subarray)或者前缀数组(比如subarrays equal sum). 如果我们要尝试左右的window size的可能, 简单的前缀数组会得到NK的复杂度, 此时要考虑用hashmap记录prefix出现的数量, 如果要window和为x, 检查哈希表里是否有多少(当前prefix - x). 这使用用差值找到正确的subarray的思路, 类似two sum. 如果哈希表记录的是出现位置, 当我们只往hashmap里记录一次, 就自然而然的保持着最左边的出现位置, 也就是能找最长的subarray.

另外一种思路是考虑DP. subarray的DP可以从dp[i][j]意味着从i到j的某种状态考虑.
也可以考虑一维dp, dp[i]以i为结尾或者中点的subarray, 考虑前面或者后面的index是不是要满足什么条件.

## Subsequence ##
subsequence理论上复杂度要比subarray高, 因为可能性更多, 可以考虑DP(思路类似subarray), 比如最长子序列.

## DP ##
1. 子序列问题。DP三个经典子序列问题应该是最长增长子序列 最长公共子序列 最长回文子序列问题
* 子序列问题是不同于子数组问题的 子数组要求连续 滑动窗口可能可以解决问题 但是子序列是可以断开挑选的 如果暴力解
* 选择造成的递归树应该至少有2的n次方 所以几乎肯定要用DP了
* 其中 最长增长子序列的状态选择方程是一维的 dp[i]的定义是到i为止最长的增长子序列 转移是取决于前面比i小的数字的dp值
* 当然 有快速的nlogn方法
* 最长公共子序列是两个string 所以dp二维 dp[i][j]的定义是第一个string到i和第二个string到j的最长公共子序列
* 但是最长回文子序列要怎么操作呢 dp[i][j]可以定义成i到j之间的最长回文子序列
* 这样状态转移就是如果i的char和j的char相同 就必然可以加入到回文序列里 因为子序列不要求连续 
* 而如果不相同 i和j就不可能在一个回文序列里了 就可以dp[i+1][j] dp[i][j-1]挑一个最大的 这两个都是前面已经算出来极值的

2. 上题的最长回文子序列问题揭示了一种DP的思路，那就是DP[i][j]代表从i到j的区间内，答案要求的极值。这时候的base case通常就是DP[i][i]，迭代方向是从右下角开始，向上迭代，遍历DP矩阵的右边三角。

3. 思考迭代方向, 一定是基于状态转移. 比如背包问题在空间压缩后是正向还是反向遍历, 实际上就取决于用[i-1][j-k]还是[i][j-k], 01背包自己只能用一次, 要基于一个物品的状态, 故此只能反向避免覆盖掉, 完全背包因为不限次数我们用自己之前更新的, 就必须是正向. 2维可以画图辅助理解.

## Stack ##
从左到右保持顺序运算, 比如calculate operator, 比如(){}[], 比如路径, 都可以利用stack的特性先进先出, 得到正确的前一个的信息. 

## 树 ##
### 二叉树 ###
三种遍历preorder, inorder, postorder产生思路的变化很多. 

还有的思路类似dfs, 答案可能留在树中, 必须更新某个值最后作为答案. 例如需要考虑从左child到root到右child往下的这种path的.

有一部分的搜索二叉树题目, 比如搜索组合, 要考虑加入当前的node和不加入, 两种情况分类讨论, 例如计算和等于targetSum的paths

对于树做删减这类的动态裁剪题目(trim, delete), 通常可以traverse helper function return node(return type 直接是TreeNode), parent直接赋值. 

LCA(Lowerest Common Ancestor)则是可以这么理解: 需要一个p在左边q在右边的node, 在BST中这就直接意味着q < n < q, 然后我们要从上往下找到第一个满足这个条件的node就可以了, 其他的都不是lowerest.
### 二叉搜索树 ###
为了search logN 存在, 当前node左边的所有node都比它小 右边的node都比它大, 故此中序遍历在二叉搜索树中使用一下子就可以变成正常的升序. 

BST的题目也可以从“如果我展平BST变成一个升序数列”开始思考. 比如如何找出一个不合法的node, 正是一个在展开的升序中比前一个小的数字, 那么我们通过维持一个prev来inorder traverse就可以解决这类问题. 

### 线段树 ###

## 重剑无锋, 大巧不工 ##
C++常用方法和trick整理 -> BlueShell/c++/康定情歌.md


