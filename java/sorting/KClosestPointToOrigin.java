package sorting;

import java.util.Arrays;

public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        /**
         * 首先是最蠢的算法也没做对 sort可以直接在array上 我主要是不会写custom comparator
         * 导致我又想导到hashmap上然后再sort 然后再导回来 太蠢了 要学会lamda sort
         * 这里的细节是不用sqrt 平方大的人自然就大
         * 然后copyOfRange的含义是切片 从start切到end - 1
         * 这个方法单纯sort然后切片 (NlogN)
         * 
         * 另外一个sort的方法是保持一个max heap priority queue 有k个elements 多了就滚一个最大的 这样能一直保持一个k最小的heap
         * NlogK logK是因为heap只需要K size
         * 
         * 用QuickSelect可以将最好时间提升到O(N) 当然不用random选择pivot的话 最坏也可能是N*2
         * QuickSelect和另外一道KthLargestElement是同理的
         */
        /* Quick Select */
        quickSelect(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);

         /* PQ */
        // PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        // for (int[] p : points) {
        //     pq.offer(p);
        //     if (pq.size() > K) {
        //         pq.poll();
        //     }
        // }
        // int[][] res = new int[K][2];
        // while (K > 0) {
        //     res[--K] = pq.poll();
        // }
        // return res;

        /* 纯sort */
        // Arrays.sort(points, (i1, i2) -> (i1[0] * i1[0] + i1[1] * i1[1] - i2[0] * i2[0] - i2[1] * i2[1]));
        // return Arrays.copyOfRange(points, 0, k);

        /* 最开始的方法 过不了test case */
        // HashMap<Integer, LinkedList<int[]>> mem = new HashMap<>();
        // for(int[] i: points) {
        //     int dis = (int) Math.sqrt(i[0] * i[0] + i[1] * i[1]);
        //     if(mem.containsKey(dis)){
        //         LinkedList<int[]> temp = mem.get(dis);
        //         temp.add(i);
        //         mem.put(dis, temp);
        //     }else{
        //         LinkedList<int[]> temp = new LinkedList<>();
        //         temp.add(i);
        //         mem.put(dis, temp);
        //     }
            
        // }
        // List<Map.Entry<Integer, LinkedList<int[]>>> list = new LinkedList<Map.Entry<Integer, LinkedList<int[]>>>(mem.entrySet());
        // Collections.sort(list, (i1, i2) -> i1.getKey() - i2.getKey());
        // return list.get(k-1).getValue().toArray(new int[0][0]);
    }
    private void quickSelect(int[][]nums, int left, int right, int k) {
        if(left == right) return;
        int randomIndex = (int) (left + (Math.random()*(right - left + 1)));
        swap(nums, randomIndex, right); // swap pivot with the rightmost
        int l = left - 1, r = right;
        while(true) {
            while(l < r && compare(nums[++l], nums[right]) <= 0){}
            while(l < r && compare(nums[--r], nums[right]) > 0){}
            if(l < r) {
                swap(nums, l, r);
            }else{
                break;
            }
        }
        swap(nums, l, right); //注意这里要和l交换不然的话顺序是不变的 l才是更新过后的pivot位置
        
        if(k == l + 1) {
            return;
        }else if(k > l + 1) {
            quickSelect(nums, l + 1, right, k);
        }else {
            quickSelect(nums, left, l - 1, k);
        }
    }
    
    private int compare(int[] a, int[] b) {
        return (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]);
    }

    private void swap(int[][] nums, int a, int b) {
        int[] temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
