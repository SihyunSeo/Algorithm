import java.util.*;

class Solution {
    public static Stack<Integer> stack = new Stack<>();
    public static int idx = 0;
    public static int cnt = 0;
    public int solution(int[] order) {
        for (int i = 1; i <= order.length; i++) {
            stack.push(i);
            while (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                cnt++;
            }
        }
        
        return cnt;
    }
}