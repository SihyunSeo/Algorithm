class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        
        for (long idx = left; idx <= right; idx++) {
            int row = (int)(idx / n);
            int col = (int)(idx % n);
            answer[(int)(idx - left)] = Math.max(row, col) + 1;
        }
        
        return answer;
    }
}
