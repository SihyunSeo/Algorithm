class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        
        for(int w = total; w >= 1; w--) {
            if (total % w == 0) {
                int h = total / w;
                if((w-2) * (h-2) == yellow) {
                    return new int[]{w, h};
                }
            }
        }
        return null;
    }
}