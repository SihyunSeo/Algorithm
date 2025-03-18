import java.util.*;
public class Solution{
    public int solution(String[] want, int[] number, String[] discount) {
        // 필요한 제품과 수량을 해시맵으로 저장
        Map<String, Integer> wantedCount = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantedCount.put(want[i], number[i]);
        }

        int validStartDays = 0;

        // 전체 할인 배열을 순회하면서 10일씩 검사
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> currentCount = new HashMap<>();
            // 현재 10일 동안의 제품 수량을 currentCount에 저장
            for (int j = i; j < i + 10; j++) {
                currentCount.put(discount[j], currentCount.getOrDefault(discount[j], 0) + 1);
            }

            // 현재 윈도우가 원하는 제품과 수량을 만족하는지 확인
            boolean isValid = true;
            for (String key : wantedCount.keySet()) {
                if (currentCount.getOrDefault(key, 0) < wantedCount.get(key)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                validStartDays++;
            }
        }

        return validStartDays;
    }
}
