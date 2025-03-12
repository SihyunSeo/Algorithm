import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for (int num : queue1) {
            q1.offer(num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            q2.offer(num);
            sum2 += num;
        }
        
        long total = sum1 + sum2;
        if (total % 2 != 0) {
            return -1;
        }
        
        total = total / 2;
        
        int cnt = 0;
        int max = queue1.length * 5;
        while (sum1 != sum2 && cnt < max) {
            if (sum1 > sum2) {
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int num = q2.poll();
                q1.offer(num);
                sum2 -= num;
                sum1 += num;
            }
            cnt++;
        }
        
        return sum1 == sum2 ? cnt : -1;
    }
}