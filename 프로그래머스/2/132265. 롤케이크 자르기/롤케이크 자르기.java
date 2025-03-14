import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        ArrayList<Integer> chulsuList = new ArrayList<>();
        HashMap<Integer, Integer> brotherMap = new HashMap<>();

        for (int t : topping) {
            brotherMap.put(t, brotherMap.getOrDefault(t, 0) + 1);
        }

        for (int t : topping) {
            if (!chulsuList.contains(t)) {
                chulsuList.add(t);
            }

            if (brotherMap.get(t) == 1) {
                brotherMap.remove(t);
            } else {
                brotherMap.put(t, brotherMap.get(t) - 1);
            }

            if (chulsuList.size() == brotherMap.size()) {
                answer++;
            }
        }

        return answer;
    }
}
