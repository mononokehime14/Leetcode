package lccontest;

public class TimeToCrossABridge {
    /* 2532
     * 纯simulation 我的方法是左右两个priority queue 但是这个方法是有问题的 就是排序顺序
     * 先按照时间排序的话 无法解决两人同时等待则priority高的要先 按照priority排序的话 无法解决一人明明可以先过 priority高的人很后面才来
     * 但是却要等priority高的
     * 太烦了 直接copy了正确答案 要用四个priority queue 两个的排序方法是不同的
     */
    public int findCrossingTime(int n, int k, int[][] time) {
        Worker[] worker = new Worker[k];
        for(int i=0;i<k;i++) {
            worker[i] = new Worker(i, time[i][0], time[i][1], time[i][2], time[i][3]);
        }
        //Four priority queues based on current position of the workers
        //Waiting at left side
        PriorityQueue<Worker> leftToRight = new PriorityQueue<Worker>(k);
        //Picking up old boxes
        PriorityQueue<Worker> pickOld = new PriorityQueue<Worker>(k, (a,b) -> a.time-b.time);
        //Waiting at Right Side
        PriorityQueue<Worker> rightToLeft = new PriorityQueue<Worker>(k);
        //Putting new boxes
        PriorityQueue<Worker> putNew = new PriorityQueue<Worker>(k, (a,b) -> a.time-b.time);
        //Initially all workers waiting at left
        for(int i=0;i<k;i++) {
            leftToRight.offer(worker[i]);
        }
        //Num boxes remaining
        int remaining = n;
        //Current time
        int currentTime = 0;
        while(remaining > 0) {
            //Move the workers from pickOld to rightToLeft or putNew to leftToRight if currentTime >= worker's time to complete the task
            while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                Worker w = pickOld.poll();
                rightToLeft.offer(w);
            }
            while(!putNew.isEmpty() && putNew.peek().time <= currentTime) {
                Worker w = putNew.poll();
                leftToRight.offer(w);
            }
            //Move worker on the bridge and update the worker's time to complete the task
            if(!rightToLeft.isEmpty()) {
                Worker w = rightToLeft.poll();
                w.time = currentTime + w.RL + w.PN;
                putNew.offer(w);
                currentTime = currentTime + w.RL;
            } else if(!leftToRight.isEmpty()) {
                Worker w = leftToRight.poll();
                w.time = currentTime + w.LR + w.PO;
                pickOld.offer(w);
                remaining--;
                currentTime = currentTime + w.LR;
            } else {
                //Bridge is empty and no worker waiting, move currenttime to first person finishing the task
                currentTime = Integer.MAX_VALUE;
                if(!pickOld.isEmpty()) {
                    currentTime = Math.min(currentTime, pickOld.peek().time);
                }
                if(!putNew.isEmpty()) {
                    currentTime = Math.min(currentTime, putNew.peek().time);
                }
            }
        }
        //Now empty the right warehouse and keep workers moving
        while(!rightToLeft.isEmpty() || !pickOld.isEmpty()) {
            while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                Worker w = pickOld.poll();
                rightToLeft.offer(w);
            }
            if(!rightToLeft.isEmpty()) {
                Worker w = rightToLeft.poll();
                w.time = currentTime + w.RL + w.PN;
                currentTime = currentTime + w.RL;
            } else {
                currentTime = pickOld.peek().time;
            }
        }
        return currentTime;
    }
    
    class Worker implements Comparable<Worker> {
        int index;
        int LR;
        int PO;
        int RL;
        int PN;
        int time;
        
        Worker(int i, int LR, int PO, int RL, int PN) {
            this.index = i;
            this.LR = LR;
            this.PO = PO;
            this.RL = RL;
            this.PN = PN;
            this.time = 0;
        }
        
        @Override
        public int compareTo(Worker w) {
            if(this.LR+this.RL!=w.LR+w.RL) {
                return w.LR+w.RL-this.LR-this.RL;
            }
            return w.index-this.index;
        }
        
        @Override
        public String toString() {
            return index+":"+time+":["+LR+" "+PO+" "+RL+" "+PN+"]";
        }
    }
    // public int findCrossingTime(int n, int k, int[][] time) {
    //     PriorityQueue<int[]> left = new PriorityQueue<>((a, b) -> {
    //         if(a[5] == b[5]) {
    //             if(a[0] + a[2] == b[0] + b[2]) return a[4] - b[4];
    //             return b[0] + b[2] - a[0] - a[2];
    //         }
    //         return a[5] - b[5];
    //     });
    //     PriorityQueue<int[]> right = new PriorityQueue<>((a, b) -> {
    //         if(a[5] == b[5]) {
    //             if(a[0] + a[2] == b[0] + b[2]) return a[4] - b[4];
    //             return b[0] + b[2] - a[0] - a[2];
    //         }
    //         return a[5] - b[5];
    //     });
    //     int currentTime = 0, countL = 0, countR = 0;
    //     for(int i = 0;i < k;i++) {
    //         int[] t = time[i];
    //         left.offer(new int[]{t[0], t[1], t[2], t[3], i+1, 0});
    //     }
    //     while(!left.isEmpty() || !right.isEmpty()) {
    //         if(countL >= n) left.clear();
    //         if(!right.isEmpty() && right.peek()[5] <= currentTime) {
    //             int[] current = right.poll();
    //             // System.out.println(current[4] + " crosses from right, time " + current[5]);
    //             currentTime += current[2];
    //             countR++;
    //             if(countR == n) return currentTime;
    //             left.offer(new int[]{current[0], current[1], current[2], current[3], current[4], currentTime + current[3]});
    //         }else if(!left.isEmpty() && left.peek()[5] <= currentTime){
    //             countL++;
    //             int[] current = left.poll();
    //             // System.out.println(current[4] + " crosses from left, time " + currentTime);
    //             currentTime += current[0];
    //             right.offer(new int[]{current[0], current[1], current[2], current[3], current[4], currentTime + current[1]});
    //         }else{
    //             currentTime = Integer.MAX_VALUE;
    //             if(!left.isEmpty()) {
    //                 currentTime = Math.min(currentTime, left.peek()[5]);
    //             }
    //             if(!right.isEmpty()) {
    //                 currentTime = Math.min(currentTime, right.peek()[5]);
    //             }
    //         }
    //     }
    //     return -1; 
    // }
}
