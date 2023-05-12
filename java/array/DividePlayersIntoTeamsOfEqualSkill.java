package array;

public class DividePlayersIntoTeamsOfEqualSkill {
    /* 2491
     * 周赛题 我被Partition Equal Subset Sum带跑了 想用回溯法来解 但是后来实现的时候 发现group更新根本和sum无关
     * 怎么实现怎么怪 而且回溯法无法实现也可以从数组长度为10的5次方看出端倪
     * 最后发现这是一个大型的two sum 只要双指针就是可以了 如果左边加不成右边必寄
     */
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        int left = 0, right = n -1;
        int sum = Arrays.stream(skill).sum();
        sum /= (n / 2);
        long chem = 0;
        Arrays.sort(skill);
        while(left < right) {
            if(skill[left] + skill[right] != sum) return -1;
            chem += skill[left] * skill[right];
            left++;
            right--;
        }
        return chem;
    }
}
